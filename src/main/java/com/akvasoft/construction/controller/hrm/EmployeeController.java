package com.akvasoft.construction.controller.hrm;

import com.akvasoft.construction.aop.ResponseGenerator;
import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.dto.hrm.EmployeeAttendanceDto;
import com.akvasoft.construction.dto.hrm.EmployeeDto;
import com.akvasoft.construction.service.common.CommonService;
import com.akvasoft.construction.dto.hrm.EmployeePayment;
import com.akvasoft.construction.dto.hrm.EmployeePaymentDetails;
import com.akvasoft.construction.service.hrm.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private HRService service;

    @ResponseGenerator
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createCustomer(@RequestBody EmployeeDto employee) {
        return new Response(service.addEmployee(employee), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/jobTypes", method = RequestMethod.GET)
    public Response findAllJobTypes() {
        return new Response(service.getJobTypes(), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/jobTypePayments", method = RequestMethod.GET)
    public Response findJobTypePayments(@RequestParam(name = "id") int id) {
        return new Response(service.findJobTypeSalary(id), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/LeaveTypes", method = RequestMethod.GET)
    public Response getLeaveTypes() {
        return new Response(service.getLeaveTypes(), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/JobTypeLeave", method = RequestMethod.GET)
    public Response getJobTypeLeaves(@RequestParam(name = "id") int id) {
        return new Response(service.getJobTypeLeaves(id), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/searchBySite", method = RequestMethod.GET)
    public Response getEmployeesBySite(@RequestParam(name = "id") int id) {
        List<EmployeeDto> employees = service.getEmployeesBySite(id);
        return new Response(employees, true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/searchByNic", method = RequestMethod.GET)
    public Response getEmployeesBySite(@RequestParam(name = "nic") String nic) {
        List<EmployeeDto> list = service.searchEmployeeByNic(nic);
        Response response = new Response();
        response.setSuccess(true);
        if (list == null) {
            response.setResult("UNF");
        } else {
            response.setResult(list);
        }
        return response;
    }

    @ResponseGenerator
    @RequestMapping(value = "/searchByNameOrNic", method = RequestMethod.GET)
    public Response getEmployeesByNameOrNIC(@RequestParam(name = "user") String user) {
        Response response = new Response();
        EmployeePayment employee = service.searchEmployeeForPayment(user);
        if (employee == null) {
            response.setSuccess(false);
            response.setErrorCode("INFO");
            response.setResult("There is no employees for this input");
            return response;
        } else response.setResult(employee);
        return response;
    }

    @ResponseGenerator
    @RequestMapping(value = "/searchMonthlyPayment", method = RequestMethod.POST)
    @ResponseBody
    public Response getEmployeePayroll(@RequestBody EmployeePayment user) throws ParseException {
        return new Response(service.PayrollDetails(user), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/saveMonthlyPayment", method = RequestMethod.POST)
    @ResponseBody
    public Response saveEmployeePayroll(@RequestBody EmployeePayment user) throws ParseException {
        return new Response(service.saveMonthlyPayment(user), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/searchByNameAndSite", method = RequestMethod.GET)
    public Response getEmployeesBySite(@RequestParam(name = "site") int site, @RequestParam(name = "name") String name) {
        return new Response(service.searchEmployeeBySiteAndName(site, name), true);
    }

    //    @ResponseGenerator
    @RequestMapping(value = "/searchByIdAndDate", method = RequestMethod.GET)
    public Response searchByIdAndDate(@RequestParam(name = "siteId") int id, @RequestParam(name = "attendanceDate") String date) {
        return service.searchByIdAndDate(id, date);
    }

    @ResponseGenerator
    @RequestMapping(value = "/searchById", method = RequestMethod.GET)
    public Response searchById(@RequestParam(name = "id") String siteId) {
        List<EmployeeAttendanceDto> list = service.searchById(Integer.parseInt(siteId));
        Response response = new Response();
        response.setResult(list);
        response.setSuccess(true);
        return response;
    }

    @ResponseGenerator
    @RequestMapping(value = "/saveAttendanceInData", method = RequestMethod.POST)
    public Response saveAttendanceInData(@RequestBody EmployeeAttendanceDto dto) {
        return service.saveAttendanceInData(dto);
    }

    @ResponseGenerator
    @RequestMapping(value = "/saveAttendanceOutData", method = RequestMethod.POST)
    public Response saveAttendanceOutData(@RequestBody EmployeeAttendanceDto attendanceDetailsDto) {
        return service.saveAttendanceOutData(attendanceDetailsDto);
    }

    @ResponseGenerator
    @RequestMapping(value = "/checkLeave", method = RequestMethod.GET)
    public Response checkLeave(@RequestParam(name = "empId") String id, @RequestParam(name = "leaveId") String leaveId) {
        return new Response(service.checkLeave(Integer.parseInt(id),
                Integer.parseInt(leaveId)), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/getUnsavedAttendance", method = RequestMethod.GET)
    public Response getUnsavedAttendance() {
        return new Response(service.getUnsavedAttendance(), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/payrollsByMonthAndYear", method = RequestMethod.GET)
    public Response getPayrollsByMonthAndYear(@RequestParam(name = "month") int month, @RequestParam(name = "year") int year) {
        return new Response(service.getPayments(month, year), true);
    }

    @ResponseGenerator
    @RequestMapping(value = "/payrolls", method = RequestMethod.GET)
    public Response getPayrolls() {
        return new Response(service.getAllPayments(), true);
    }


}
