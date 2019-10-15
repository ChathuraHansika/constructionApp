package com.akvasoft.construction.util;

public class DomainConstant {
    public enum Status {
        ACT, INA, YES, NO;
        public static Status getStatus(String status) {
            if (status != null) {
                return Status.valueOf(status);
            }
            return null;
        }
    }

    public enum Title {
        MR, MRS, MISS;
        public static Title getTitle(String title) {
            if (title != null) {
                return Title.valueOf(title);
            }
            return null;
        }
    }

    public enum EmployeeStatus {
        CURRENT, FIRED, LEFT, RETIRED;
        public static EmployeeStatus getEmployeeStatus(String status) {
            if (status != null) {
                return EmployeeStatus.valueOf(status);
            }
            return null;
        }
    }

    public enum SalaryType {
        BASE, ALLOWANCE, DEFINITE, VARIABLE;
        public static SalaryType getSalaryType(String status) {
            if (status != null) {
                return SalaryType.valueOf(status);
            }
            return null;
        }
    }

    public enum ApprovalStatus {
        PENDING, APPROVED;
        public static ApprovalStatus getApprovalStatus(String status) {
            if (status != null) {
                return ApprovalStatus.valueOf(status);
            }
            return null;
        }
    }

    public enum EmployeeAttendence {
        INIT, ARRIV, DEPT;
        public static EmployeeAttendence getEmployeeAttendence(String status) {
            if (status != null) {
                return EmployeeAttendence.valueOf(status);
            }
            return null;
        }
    }

    public enum BankAccountType {
        SAVING, CURRENT;
        public static BankAccountType getBankAccountType(String status) {
            if (status != null) {
                return BankAccountType.valueOf(status);
            }
            return null;
        }
    }

    public enum PaymentMode {
        CASH, CHEQUE;
        public static PaymentMode getPaymentMode(String status) {
            if (status != null) {
                return PaymentMode.valueOf(status);
            }
            return null;
        }
    }

    public enum PurchaseStatus {
        INIT, REQ, PART_REQ, INA;
        public static PurchaseStatus getPurchaseStatus(String status) {
            if (status != null) {
                return PurchaseStatus.valueOf(status);
            }
            return null;
        }
    }

    public enum OrderCompletedStatus {
        REQ, PART_REQ, PUR, PART_PUR;
        public static OrderCompletedStatus getOrderCompletedStatus(String status) {
            if (status != null) {
                return OrderCompletedStatus.valueOf(status);
            }
            return null;
        }
    }

    public enum EquipmentStatus {
        ACT, INA, RET, SOLD;
        public static EquipmentStatus getEquipmentStatus(String status) {
            if (status != null) {
                return EquipmentStatus.valueOf(status);
            }
            return null;
        }
    }

    public enum WeatherStatus {
        CLOUD, RAIN, FINE;
        public static WeatherStatus getWeatherStatus(String status) {
            if (status != null) {
                return WeatherStatus.valueOf(status);
            }
            return null;
        }
    }

    public enum EmployeeLeaveStatus {
        HALF, SHORT, LEAVE, NOPAY;
        public static EmployeeLeaveStatus getLeaveStatus(String status) {
            if (status != null) {
                return EmployeeLeaveStatus.valueOf(status);
            }
            return null;
        }
    }
}
