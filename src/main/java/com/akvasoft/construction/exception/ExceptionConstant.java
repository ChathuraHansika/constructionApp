package com.akvasoft.construction.exception;

public class ExceptionConstant {
    public enum ExceptionType {
        INCORRECT_DATA("Incorrect-Data", "Opps\nOperation data sent is incorrect. Operation failed"),
        ALL_MUST_BE_FILLED("Incorrect-Data", "Each of the five fields must be filled. \nPut 0 if there is no allocation yet"),
        BAD_REQUEST("Data incorrect", "Opps\nPlease re-check data entered"),
        NO_PRIVILEGE("Authorization-Fail", "Opps\nYour account verification failed"),
        TIME_PERIOD_NOT_ALLOWED("Incorrect Request", "Opps\nThis functionality is allowed only for December of this year or later"),
        OBJECT_IS_INACTIVE("INCORRECT-OPERATION", "Sorry, You can not change removed/completed object"),
        MULTIPLE_WITH_SAME_REG_NO("MULTIPLE SAME REG", "Sorry, You have to be more specific. There are more than 1 Aid receivers in this register number. \n Please choose correct Aid type"),
        NO_RECORD_WITH_GIVEN_CRITEIRA("NO DATA", "No records found for given search criteria"),
        SAME_REGISTER_NUMBER("SAME_REGISTER_NUMBER", "Sorry, You an aid receiver exist with the same register number and aid type category"),
        SAME_NUMBER("SAME_NUMBER", "Sorry, You an aid receiver exist with the same number"),
        YEARLY_ALLOCATION_RETURNED("INCORRECT-OPERATION", "This year yearly allocation had returned. No further changes can be made this year"),
        INACTIVE_PAYMENT_EXIST_AFTER_THAT("INCORREC-DATA", "Inactive payments exist after the given month to activate"),
        VALUES_LESS_THAN_PREVIOUS("INCORRECT_VALUES", "Sorry, new allocations must be greater than or equal to previous allocations"),
        SERVER_ERROR("Server-Error", "Opps\nProblem occurred in server. \nPlease contact developers"),
        NOT_CONSECUTIVE_PAYMENTS("INCORRECT-OPERATION", "Opps\nYou Have Undone Previous Payments.\nPlease Complete Them");

        private String errorCode;
        private String errorMessage;

        ExceptionType(String errorCode, String frontEndMessage) {
            this.errorCode = errorCode;
            this.errorMessage = frontEndMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
