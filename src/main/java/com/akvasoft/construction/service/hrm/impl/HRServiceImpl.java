package com.akvasoft.construction.service.hrm.impl;

import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.dto.hrm.*;
import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.entity.hrm.*;
import com.akvasoft.construction.repo.ConstructionSiteRepo;
import com.akvasoft.construction.repo.UserRepo;
import com.akvasoft.construction.repo.hrm.*;
import com.akvasoft.construction.service.common.CommonService;
import com.akvasoft.construction.service.hrm.HRService;
import com.akvasoft.construction.util.DateTimeConverter;
import com.akvasoft.construction.util.DomainConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HRServiceImpl implements HRService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ConstructionSiteRepo siteRepo;
    @Autowired
    private JobTypeRepo jobTypeRepo;
    @Autowired
    private JobTypeSalaryRepo jobTypeSalaryRepo;
    @Autowired
    private LeaveTypeRepo leaveTypeRepo;
    @Autowired
    private JobTypeLeaveRepo jobTypeLeaveRepo;
    @Autowired
    private EmployeeSalaryRepo employeeSalaryRepo;
    @Autowired
    private EmployeeLeaveYearRepo employeeLeaveYearRepo;
    @Autowired
    private EmployeeAttendenceRepo employeeAttendenceRepo;
    @Autowired
    private EmployeeLeaveRepo employeeLeaveRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PayRollRepo payRollRepo;
    @Autowired
    private PayRollDescriptionRepo payRollDescriptionRepo;
    @Autowired
    private DateTimeConverter converter;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String addEmployee(EmployeeDto employee) {
        Employee save = employeeRepo.findByNicNumberEquals(employee.getNicNumber());
        save = employee.getEntity(save);
        save.setSite(siteRepo.getOne(employee.getSiteAssigned()));
        save.setJobType(jobTypeRepo.getOne(employee.getJobType()));
        save = employeeRepo.save(save);

        saveEmployeeSalary(save, employee.getSalary());
        saveEmployeeLeave(save, employee.getLeaves());
        return "Successfully saved!";
    }

    @Override
    public List<JobTypeDto> getJobTypes() {
        List<JobType> jobTypes = jobTypeRepo.findAll();
        return jobTypes.stream().map(JobType::convertToDto).collect(Collectors.toList());

    }

    @Override
    public List<JobTypeSalary> findJobTypeSalary(int jobType) {
        JobType type = jobTypeRepo.getOne(jobType);
        return jobTypeSalaryRepo.findAllByJobTypeEqualsAndStatusEquals(type, DomainConstant.Status.ACT);
    }

    @Override
    public List<LeaveTypeDto> getLeaveTypes() {
        List<LeaveType> all = leaveTypeRepo.findAllByStatusEquals(DomainConstant.Status.ACT);
        return all.stream().map(LeaveType::getLeaveTypeDto).collect(Collectors.toList());
    }

    @Override
    public List<JobTypeLeavesDto> getJobTypeLeaves(int jobType) {
        JobType type = jobTypeRepo.getOne(jobType);
        List<JobTypeLeave> all = jobTypeLeaveRepo.findAllByJobTypeEqualsAndStatusEquals(type, DomainConstant.Status.ACT);
        return all.stream().map(JobTypeLeave::getDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getEmployeesBySite(int siteID) {
        List<EmployeeDto> list;
        ConstructionSite site = siteRepo.getOne(siteID);
        List<Employee> all = employeeRepo.findAllBySiteEquals(site);
        list = all.stream().map(Employee::getDTO).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<EmployeeDto> searchEmployeeBySiteAndName(int site, String name) {
        ConstructionSite one = siteRepo.getOne(site);
        List<Employee> contains = employeeRepo.findAllBySiteEqualsAndFullNameContains(one, name);
        return contains.stream().map(Employee::getDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> searchEmployeeByNic(String nic) {
        List<Employee> list = new ArrayList<>();
        Employee employee = employeeRepo.findByNicNumberEquals(nic);
        if (employee == null) return null;
        list.add(employee);
        return list.stream().map(Employee::getDTO).collect(Collectors.toList());
    }


    @Override
    public Response searchByIdAndDate(int id, String date) {
        Date date1 = convertToDate(date);
        List<EmployeeAttendanceDto> res = new ArrayList<>();
        if (id == 0) {
            List<EmployeeAttendence> allByDate = employeeAttendenceRepo.findByAttendenceDateEquals(date1);
            if (allByDate.isEmpty()) {
                res = employeeRepo.findAll().stream().map(employee -> new EmployeeAttendanceDto().convertToEmployeeDto(employee)).collect(Collectors.toList());
                return new Response(res, true);
            }
            return commonService.makeResponse(true, new EmployeeAttendanceDto()
                    .convertToEADDto(allByDate, allByDate.size(), employeeRepo.allCount(), id), "", "");
        } else {
            List<Employee> employees = employeeRepo.findEmplloyesBySiteId(id);
            List<EmployeeAttendence> list = employeeAttendenceRepo
                    .findByAttendenceDateandsiteId(id, date1);
            if (list.isEmpty()) {
                for (Employee employee : employees) {
                    res.add(new EmployeeAttendanceDto().convertToEmployeeDto(employee));
                    return new Response(res, true);
                }
            } else {
                return commonService.makeResponse(true, new EmployeeAttendanceDto()
                        .convertToEADDto(list, getTotalPresentEmployees(date, id),
                                employees.size(), id), "", "");
            }

        }
        return new Response(res, true);
    }


    @Override
    public List<EmployeeAttendanceDto> searchById(int id) {
        if (id == 0) {
            return employeeRepo.findAll().stream().map(employee -> new EmployeeAttendanceDto().convertToEmployeeDto(employee)).collect(Collectors.toList());
        } else {
            return employeeRepo.findEmplloyesBySiteId(id).stream().map(employee -> new EmployeeAttendanceDto().convertToEmployeeDto(employee)).collect(Collectors.toList());
        }
    }

    @Override
    public Response saveAttendanceInData(EmployeeAttendanceDto dto) {
        Date date = convertToDate(dto.getAttendanceDate());
        int siteTotal = getSiteTotal(dto);

        int totalPresent = getTotalPresentEmployees(dto.getAttendanceDate(), dto.getSiteId());
        final User user = findUser(dto.getUser());
        if (dto.getAttendance().equals("Absent")) {
            EmployeeLeave leaveExists = checkIsLeaveExists(dto);
            int total = getTotalPresentEmployees(dto.getAttendanceDate(), dto.getSiteId());
            if (getCounts(dto)) {
                noPaySave(dto, date);
                int leaveCount = getLeaveCount(date);
                return commonService.makeResponse(true, new EmployeeAttendanceDto().getLeaveDto(leaveCount, siteTotal, totalPresent), "NoN", "NoN");
            } else {
                if (leaveExists != null) {
                    Integer count = leaveExists.getCount() + 1;
                    leaveExists.setCount(count);
                    leaveExists.setLeaveDate(date);
                    employeeLeaveRepo.saveAndFlush(leaveExists);
                    int leaveCount = getLeaveCount(date);
                    return commonService.makeResponse(true, new EmployeeAttendanceDto().getLeaveDto(leaveCount, siteTotal, totalPresent), "NoN", "NoN");

                } else {
                    LeaveType one = leaveTypeRepo.getOne(dto.getLeaveTypeId());
                    EmployeeLeave entity = new EmployeeLeaveDto().getEntity(dto, one);
                    entity.setLeaveDate(date);
                    employeeLeaveRepo.save(entity);
                    int leaveCount = getLeaveCount(date);
                    return commonService.makeResponse(true, new EmployeeAttendanceDto().getLeaveDto(leaveCount, siteTotal, totalPresent), "NoN", "NoN");
                }
            }
        } else {

            Employee employee = employeeRepo.getOne(dto.getEmployeeId());
            List<EmployeeAttendence> employees = employeeAttendenceRepo.findAllByEmployeeAndAttendenceDateEquals(employee, date);
            EmployeeAttendence entity = new EmployeeAttendanceDto().getTimeInEntity(dto, user);
            EmployeeAttendence save = employeeAttendenceRepo.save(entity);
            List<EmployeeAttendence> list = employeeAttendenceRepo.findByAttendenceDateandsiteId(dto.getSiteId(), date);
            int siteCount = employeeRepo.countBySiteSiteIdEquals(dto.getSiteId());
            return commonService.makeResponse(true, new EmployeeAttendanceDto().convertToEADDto(list, list.size(), siteCount, 0), "NoN", "NoN");
        }
    }

    @Transactional
    @Override
    public Response saveAttendanceOutData(EmployeeAttendanceDto dto) {
        final User user = findUser(dto.getUser());
        Date date = convertToDate(dto.getAttendanceDate());
        EmployeeAttendence Attendance = employeeAttendenceRepo
                .findAttendanceId(date, dto.getEmployeeId());
        if (dto.getLeaveTypeId() == 0) {
            EmployeeAttendence entity = dto.getTimeOutEntity(dto, Attendance, null, user);
            employeeAttendenceRepo.saveAndFlush(entity);
            return commonService.makeResponse(true, "EmployeeOutSaved", "NoN", "NoN");
        } else {
//            No Pay Save
            if (getCounts(dto)) {
                int leaveCount = getLeaveCount(date);
                noPaySave(dto, date);
                LeaveType one = leaveTypeRepo.getOne(4);
                EmployeeLeave entity = new EmployeeLeaveDto().getEntity(dto, one);
                EmployeeAttendence timeOutEntity = new EmployeeAttendanceDto().getTimeOutEntity
                        (dto, Attendance, one, user);
                employeeAttendenceRepo.saveAndFlush(timeOutEntity);

                return commonService.makeResponse(true, new EmployeeAttendanceDto().getLeaveDto(leaveCount, 0, 0), "NoN", "NoN");
            } else {

                LeaveType one = leaveTypeRepo.getOne(dto.getLeaveTypeId());
                EmployeeLeave entity = new EmployeeLeaveDto().getEntity(dto, one);
                EmployeeLeave leaveExists = checkIsLeaveExists(dto);
                if (leaveExists != null) {
                    Integer count = leaveExists.getCount() + 1;

                    employeeLeaveRepo.updateCount(count, leaveExists.getEmployee().getEmployeeId(),
                            leaveExists.getLeaveTypeId().getLeaveTypeId());

                    EmployeeAttendence timeOutEntity = new EmployeeAttendanceDto().getTimeOutEntity
                            (dto, Attendance, one, user);
                    employeeAttendenceRepo.saveAndFlush(timeOutEntity);
                    return commonService.makeResponse(true, "EmployeeLeaveAdded", "NoN", "NoN");
                } else {
                    employeeLeaveRepo.save(entity);
                    EmployeeAttendence timeOutEntity = new EmployeeAttendanceDto().getTimeOutEntity
                            (dto, Attendance, one, user);
                    employeeAttendenceRepo.saveAndFlush(timeOutEntity);
                    return commonService.makeResponse(true, "EmployeeTimeOutSaved", "NoN", "NoN");
                }
            }

        }

    }

    @Override
    public String checkLeave(int id, int leaveId) {
        Employee employee = employeeRepo.getOne(id);
        LeaveType leavetype = leaveTypeRepo.getOne(leaveId);
        EmployeeLeaveYear leaveyear = employeeLeaveYearRepo.findTopByEmployeeEqualsAndLeaveTypeIdEquals(employee, leavetype);
        EmployeeLeave employeeLeave = employeeLeaveRepo.getEmployeeLeave(id, leaveId);
        if (leaveyear != null & employeeLeave != null) if (leaveyear.getCount().equals(employeeLeave.getCount())) {
            return "0";
        }
        return "1";
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public EmployeePayment PayrollDetails(EmployeePayment payment) throws ParseException {
        Employee employee = employeeRepo.getOne(payment.getEmpID());
        PayRoll last = payRollRepo.getTopByEmployeeEqualsAndYearEqualsAndMonthEquals(employee,
                Integer.parseInt(payment.getYear()),
                Integer.parseInt(payment.getMonth()));
        if (last != null) payment.setPayrollData(last);
        if (last == null) calculateSalaries(employee, payment);
        return payment;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean saveMonthlyPayment(EmployeePayment payment) {
        Employee employee = employeeRepo.getOne(payment.getEmpID());
        User req_by = getUserEntity(payment.getReq_by());
        User approved_by = checkApproval(payment.getApproved_by(), payment.isApproval());
        PayRoll payRoll = new PayRoll(payment.getPayrollID(), employee, converter.parseDate(payment.getDate()), payment.getMonth(), payment.getYear(),
                payment.isApproval(), req_by, approved_by);
        payRoll = payRollRepo.saveAndFlush(payRoll);
        for (EmployeePaymentDetails detail : payment.getDetails())
            payRollDescriptionRepo.save(detail.paymentDescription(payRoll));
        return true;
    }

    @Override
    public String getUnsavedAttendance() {
        List<EmployeeAttendence> hasUnmarkedAttendace = employeeAttendenceRepo.findAllByDepartureTimeIsNullAndAndArrivalTimeIsNotNull();
        if (!hasUnmarkedAttendace.isEmpty()) return "yes";
        return "no";
    }

    public List<EmployeePayment> getPayments(int month, int year) {
        return payRollRepo.findAllByMonthEqualsAndYearEquals(month, year).stream().map(PayRoll::getEmployeePayment).collect(Collectors.toList());
    }

    @Override
    public List<EmployeePayment> getAllPayments() {
        return payRollRepo.findAll().stream().map(PayRoll::getEmployeePayment).collect(Collectors.toList());

    }

    private int getSiteTotal(EmployeeAttendanceDto dto) {
        return employeeRepo.countBySiteSiteIdEquals(dto.getSiteId());
    }


    private boolean getCounts(EmployeeAttendanceDto dto) {
        Employee employee = employeeRepo.getOne(dto.getEmployeeId());
        LeaveType leavetype = leaveTypeRepo.getOne(dto.getLeaveTypeId());
        EmployeeLeaveYear leaveyear = employeeLeaveYearRepo.findTopByEmployeeEqualsAndLeaveTypeIdEquals(employee, leavetype);
        EmployeeLeave employeeLeave = employeeLeaveRepo.getEmployeeLeave(dto.getEmployeeId(), dto.getLeaveTypeId());
        if (employeeLeave != null & leaveyear != null) {
            return leaveyear.getCount().equals(employeeLeave.getCount());
        } else return false;
    }

    private int getLeaveCount(Date date) {
        return employeeLeaveRepo.countByLeaveDateEquals(date);
    }

    private int getYear(Date date) {
        return Integer.parseInt(String.valueOf(date).substring(24));
    }

    private void noPaySave(EmployeeAttendanceDto dto, Date date) {
        Employee employee = employeeRepo.getOne(dto.getEmployeeId());
        EmployeeLeave nopay = employeeLeaveRepo.findByTypeAndEmployeeEquals(DomainConstant.EmployeeLeaveStatus.NOPAY, employee);
        EmployeeLeaveYear nopayLeaveYear = employeeLeaveYearRepo.findByTypeAndEmployeeEquals(DomainConstant.EmployeeLeaveStatus.NOPAY, employee);
        LeaveType leaveType = new LeaveType();
        leaveType.setLeaveTypeId(4);

        if (nopayLeaveYear != null) {
            nopayLeaveYear.setCount(nopayLeaveYear.getCount() + 1);
            nopayLeaveYear.setLeaveTypeId(leaveType);
            nopayLeaveYear.setType(DomainConstant.EmployeeLeaveStatus.NOPAY);
            nopayLeaveYear.setStatus("YES");
            nopayLeaveYear.setReductFrom("SALARY");
            nopayLeaveYear.setEmployee(employee);
            nopayLeaveYear.setYear(getYear(date));
            employeeLeaveYearRepo.saveAndFlush(nopayLeaveYear);
        }


        if (nopay != null) {
            Integer count = nopay.getCount();
            nopay.setCount(count + 1);
            nopay.setLeaveDate(date);
            employeeLeaveRepo.saveAndFlush(nopay);
        } else {
            nopay = new EmployeeLeave();
            nopay.setLeaveDate(date);
            nopay.setCount(1);
            nopay.setEmployee(employee);
            nopay.setPortion(new BigDecimal(1));
            nopay.setType(DomainConstant.EmployeeLeaveStatus.NOPAY);
            nopay.setReductFrom("SALARY");
            nopay.setStatus("YES");
            nopay.setLeaveTypeId(leaveType);
            employeeLeaveRepo.save(nopay);

        }


    }

    private User checkApproval(int approved_by, boolean approval) {
        if (approval) if (approved_by > 0) return userRepo.getOne(approved_by);
        else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Integer userId = ((User) authentication.getPrincipal()).getUserId();
            return userRepo.getOne(userId);
        }
        else return null;
    }

    private User getUserEntity(int req_by) {
        if (req_by > 0) return userRepo.getOne(req_by);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User) authentication.getPrincipal()).getUserId();
        return userRepo.getOne(userId);
    }

    private void calculateSalaries(Employee employee, EmployeePayment payment) throws ParseException {

        List<EmployeeAttendence> attendances = employeeAttendenceRepo.findAllByEmployeeEqualsAndMonthEqualsAndYearEquals(
                employee, Integer.parseInt(payment.getMonth()), Integer.parseInt(payment.getYear()));
        if (attendances.size() < 1) return;

        EmployeePaymentDetails details = new EmployeePaymentDetails(DomainConstant.Status.ACT.name(), employee.getTotalBaseSalary().doubleValue(), "Basic",
                DomainConstant.SalaryType.DEFINITE.name());
        payment.setPayment(details);
        List<EmployeeSalary> variableHour = employeeSalaryRepo.findAllByEmployeeEqualsAndBaseEqualsAndPerDayEqualsAndStatusEquals(
                employee, 0, false, DomainConstant.Status.ACT);

        List<EmployeeSalary> variableDay = employeeSalaryRepo.findAllByEmployeeEqualsAndBaseEqualsAndPerDayEqualsAndStatusEquals(
                employee, 0, true, DomainConstant.Status.ACT);

        int noPayDays = employeeLeaveRepo.countAllByEmployeeEqualsAndTypeEqualsAndStatusEquals(employee, DomainConstant.EmployeeLeaveStatus.NOPAY, DomainConstant.Status.YES.name());
        variableDay.stream().map(allowance -> allowance.getPayrollDetails(attendances.size())).forEach(payment::setPayment);

        int otHours = 0;
        for (EmployeeAttendence attendance : attendances) {
            if (attendance.getDepartureTime() == null) continue;
            otHours = otHours + attendance.getExtraHours();
        }

        for (EmployeeSalary salary : variableHour) payment.setPayment(salary.getPayrollDetails(otHours));
        double noPayDeduct = employee.getNoPayDeduct().doubleValue() * noPayDays;
        payment.setPayment(new EmployeePaymentDetails(DomainConstant.Status.ACT.name(), noPayDeduct, "NO PAY DEDUCT",
                DomainConstant.SalaryType.DEFINITE.name()));
        payment.setPayment(new EmployeePaymentDetails(DomainConstant.Status.ACT.name(),
                employee.getOverTimeRatePerHour().doubleValue() * otHours, "OT", DomainConstant.SalaryType.DEFINITE.name()));
    }

    @Override
    public EmployeePayment searchEmployeeForPayment(String user) {
        EmployeePayment payment = new EmployeePayment();
        Employee employee = employeeRepo.findFirstByFullNameContainsOrNicNumberEquals(user, user);
        if (employee == null) return null;
        payment.setEmployeeData(employee);
        return payment;
    }


    private int getTotalPresentEmployees(String date, int siteId) {
        return employeeAttendenceRepo.countByAttendenceDateAndEmployee_Site_SiteId(convertToDate(date), siteId);
    }


    private void saveEmployeeSalary(Employee employee, List<JobTypeSalaryDto> salary) {
        employeeSalaryRepo.deleteAllByEmployeeEquals(employee);
        List<EmployeeSalary> salaries = salary.stream().map(salaryDto -> new EmployeeSalary().setEntity(employee, salaryDto)).collect(Collectors.toList());
        employeeSalaryRepo.saveAll(salaries);
    }


    private void saveEmployeeLeave(Employee employee, List<JobTypeLeavesDto> leaves) {
        employeeLeaveYearRepo.deleteAllByEmployeeEquals(employee);
        boolean noPayExist = false;
        List<EmployeeLeaveYear> list = new ArrayList<>();
        LeaveType leaveType = null;
        for (JobTypeLeavesDto elve : leaves) {
            leaveType = leaveTypeRepo.getOne(elve.getLeave_type());
            list.add(elve.getEntity(employee, leaveType));
            if (elve.getType().equalsIgnoreCase(DomainConstant.EmployeeLeaveStatus.NOPAY.name())) noPayExist = true;
        }
        if (!noPayExist) {
            JobTypeLeavesDto dto = new JobTypeLeavesDto();
            dto.setLeave_type(4);
            dto.setType(DomainConstant.EmployeeLeaveStatus.NOPAY.name());
            dto.setReduct_from("SALARY");
            dto.setStatus(DomainConstant.Status.ACT.name());
            dto.setAmount(0);
            list.add(dto.getEntity(employee, leaveType));
        }
        employeeLeaveYearRepo.saveAll(list);
    }


    private EmployeeLeave checkIsLeaveExists(EmployeeAttendanceDto dto) {
        return employeeLeaveRepo.getEmployeeLeave(dto.getEmployeeId(), dto.getLeaveTypeId());
    }

    private User findUser(String user) {
        User byUserName = userRepo.findByUserName(user);
        System.out.println("byUserName.getPassword() = " + byUserName.getPassword());
        return byUserName;
    }

    private Date convertToDate(String stringFormatDate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(stringFormatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
