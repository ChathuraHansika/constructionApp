DROP database construct;
CREATE database construct;
USE construct;

CREATE TABLE `T_ROLE`
(
    `ROLE_ID`   INT(11)     NOT NULL AUTO_INCREMENT,
    `ROLE_NAME` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`ROLE_ID`)
);


CREATE TABLE T_PRIVILEGE
(
    PRIVILEGE_ID INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ROLE_ID      INT         NOT NULL,
    NAME         VARCHAR(50) NOT NULL,
    FOREIGN KEY (ROLE_ID) REFERENCES T_ROLE (ROLE_ID)
);


CREATE TABLE T_USER
(
    USER_ID   INT NOT NULL AUTO_INCREMENT,
    ROLE_ID   INT,
    USER_NAME VARCHAR(20) UNIQUE,
    PASSWORD  VARCHAR(200),
    STATUS    VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA')),
    PRIMARY KEY (USER_ID),
    CONSTRAINT ROLE_USER FOREIGN KEY (ROLE_ID)
        REFERENCES T_ROLE (ROLE_ID)
);

CREATE TABLE T_CLIENT
(
    CLIENT_ID      INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME           VARCHAR(500)  NOT NULL,
    ADDRESS_LINE_1 VARCHAR(500)  NOT NULL,
    TEL            VARCHAR(20)   NULL,
    CITY           VARCHAR(150)  NOT NULL,
    COUNTRY        VARCHAR(150)  NULL,
    REMARKS        VARCHAR(1000) NULL
);


CREATE TABLE T_CONSTRUCTION_SITE
(
    CONSTRUCTION_SITE_ID INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ID            INT           NOT NULL,
    PROJECT_ID           VARCHAR(200)  NOT NULL,
    PROJECT_NAME         VARCHAR(300)  NOT NULL,
    DESCRIPTION          VARCHAR(1000) NULL,
    ADDRESS_LINE_1       VARCHAR(500)  NOT NULL,
    ADDRESS_LINE_2       VARCHAR(500)  NULL,
    CITY                 VARCHAR(140)  NULL,
    COUNTRY              VARCHAR(140)  NULL,
    PROJECT_TYPE         VARCHAR(20)   NOT NULL,
    VAT_ADDED            VARCHAR(20)   NOT NULL,
    FOREIGN KEY (CLIENT_ID) REFERENCES T_CLIENT (CLIENT_ID)
);

-- # HR MODULE
CREATE TABLE T_JOB_TYPE
(
    JOB_TYPE_ID          INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    INSENTIVE_PERCENTAGE DECIMAL,
    JOB_TYPE             VARCHAR(20) NOT NULL,
    STATUS               VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA'))
);

CREATE TABLE T_EMPLOYEE
(
    EMPLOYEE_ID             INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SITE_ASSIGNED           INT          NOT NULL,
    TITLE                   VARCHAR(4) CHECK (TITLE IN ('MR', 'MRS', 'MISS')),
    INITIALS                VARCHAR(20)  NULL,
    FIRST_NAME              VARCHAR(300) NOT NULL,
    SURNAME                 VARCHAR(300) NULL,
    FULL_NAME               varchar(600) NOT NULL,
    NIC_NUMBER              VARCHAR(50)  NOT NULL,
    DATE_OF_BIRTH           DATE         NULL,
    ADDRESS_LINE_1          VARCHAR(500) NOT NULL,
    ADDRESS_LINE_2          VARCHAR(500) NULL,
    CITY                    VARCHAR(150) NOT NULL,
    COUNTRY                 VARCHAR(150) NOT NULL,
    CONTACT_1               VARCHAR(20)  NOT NULL,
    CONTACT_2               VARCHAR(20)  NULL,
    JOIN_DATE               DATE         NOT NULL,
    JOB_TYPE_ID             INT          NOT NULL,
    DESIGNATION             VARCHAR(20)  NOT NULL,
    PER_DAY_WORK_HOUR       int,
    OVER_TIME_RATE_PER_HOUR DECIMAL(15, 2),
    TOTAL_SALARY            DECIMAL(15, 2),
    TOTAL_BASE_SALARY       DECIMAL(15, 2),
    TOTAL_ALLOWANCE_SALARY  DECIMAL(15, 2),
    NO_PAY_DEDUCT           DECIMAL(15, 2),
    ETF                     DECIMAL(15, 2),
    EPF                     DECIMAL(15, 2),
    STATUS                  VARCHAR(10) CHECK (STATUS IN ('CURRENT', 'FIRED', 'LEFT')),
    FOREIGN KEY (SITE_ASSIGNED) REFERENCES T_CONSTRUCTION_SITE (CONSTRUCTION_SITE_ID),
    FOREIGN KEY (JOB_TYPE_ID) REFERENCES T_JOB_TYPE (JOB_TYPE_ID)
);

CREATE TABLE T_EMPLOYEE_SALARY
(
    SALARY_TYPE_ID INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    EMPLOYEE_ID    INT          NOT NULL,
    DESCRIPTION    VARCHAR(100) NULL,
    PER_HOUR       TINYINT(1),
    PER_DAY        TINYINT(1),
    AMOUNT         DECIMAL(10, 2),
    BASE           INT,
    SALARY_TYPE    VARCHAR(10) CHECK (SALARY_TYPE IN ('DEFINITE', 'VARIABLE')),
    STATUS         VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA')),
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES T_EMPLOYEE (EMPLOYEE_ID)
);


CREATE TABLE T_JOB_TYPE_SALARY
(
    JOB_TYPE_SALARY_ID INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    JOB_TYPE_ID        INT          NOT NULL,
    DESCRIPTION        VARCHAR(100) NULL,
    AMOUNT             DECIMAL(15, 2),
    PER_HOUR           TINYINT(1),
    PER_DAY            TINYINT(1),
    SALARY_TYPE        VARCHAR(10) CHECK (SALARY_TYPE IN ('BASE', 'ALLOWANCE')),
    STATUS             VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA')),
    FOREIGN KEY (JOB_TYPE_ID) REFERENCES T_JOB_TYPE (JOB_TYPE_ID)
);

CREATE TABLE T_LEAVE_TYPE
(
    LEAVE_TYPE_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TYPE          VARCHAR(10),
    DAY_PORTION   DECIMAL(15, 2),
    REDUCT_FROM   VARCHAR(30),
    STATUS        VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA'))
);

CREATE TABLE T_JOB_TYPE_LEAVE
(
    JOB_TYPE_LEAVE_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    JOB_TYPE_ID       INT,
    LEAVE_TYPE_ID     INT,
    TYPE              VARCHAR(10) CHECK (TYPE IN ('HALF', ' SHORT', 'LEAVE', 'NOPAY')),
    REDUCT_FROM       VARCHAR(30),
    PORTION           DECIMAL(15, 2),
    AMOUNT            INT,
    STATUS            VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA')),
    FOREIGN KEY (JOB_TYPE_ID) REFERENCES T_JOB_TYPE (JOB_TYPE_ID),
    FOREIGN KEY (LEAVE_TYPE_ID) REFERENCES T_LEAVE_TYPE (LEAVE_TYPE_ID)
);

CREATE TABLE T_EMPLOYEE_LEAVE
(
    EMPLOYEE_LEAVE_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    LEAVE_TYPE_ID     INT,
    EMPLOYEE_ID       INT,
    LEAVE_DATE        DATE,
    TYPE              VARCHAR(10) CHECK (TYPE IN ('HALF', ' SHORT', 'LEAVE', 'NOPAY')),
    COUNT             INT,
    REDUCT_FROM       VARCHAR(30),
    PORTION           DECIMAL(3, 2),
    STATUS            VARCHAR(3) CHECK ( STATUS IN ('YES', 'NO')),
    FOREIGN KEY (LEAVE_TYPE_ID) REFERENCES T_LEAVE_TYPE (LEAVE_TYPE_ID),
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES T_EMPLOYEE (EMPLOYEE_ID)
);

CREATE TABLE T_EMPLOYEE_LEAVE_YEAR
(
    EMPLOYEE_LEAVE_YEAR_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    LEAVE_TYPE_ID          INT,
    EMPLOYEE_ID            INT,
    TYPE                   VARCHAR(10) CHECK (TYPE IN ('HALF', ' SHORT', 'LEAVE', 'NOPAY')),
    COUNT                  INT,
    YEAR                   INT,
    REDUCT_FROM            VARCHAR(30),
    STATUS                 VARCHAR(3) CHECK ( STATUS IN ('YES', 'NO')),
    FOREIGN KEY (LEAVE_TYPE_ID) REFERENCES T_LEAVE_TYPE (LEAVE_TYPE_ID),
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES T_EMPLOYEE (EMPLOYEE_ID)
);


CREATE TABLE T_PAY_ROLL
(
    PAY_ROLL_ID           INT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    EMPLOYEE_ID           INT  NOT NULL,
    MONTH                 INT  NOT NULL,
    YEAR                  INT  NOT NULL,
    PAYMENT_DATE          DATE NULL,
    APPROVAL_STATUS       VARCHAR(10) CHECK (APPROVAL_STATUS IN ('PEND', 'APPROVED')),
    APPROVAL_REQUESTED_BY INT  NOT NULL,
    APPROVED_BY           INT  NULL,
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES T_EMPLOYEE (EMPLOYEE_ID),
    FOREIGN KEY (APPROVAL_REQUESTED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (APPROVED_BY) REFERENCES T_USER (USER_ID)
);

CREATE TABLE T_PAY_ROLL_DESCRIPTION
(
    PAY_ROLL_DESCRIPTION_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    PAY_ROLL_ID             INT NOT NULL,
    SALARY_DESCRIPTION      VARCHAR(150),
    AMOUNT                  DECIMAL(15, 2),
    STATUS                  VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA')),
    SALARY_TYPE             VARCHAR(10) CHECK (SALARY_TYPE IN ('DEFINITE', 'VARIABLE')),
    FOREIGN KEY (PAY_ROLL_ID) REFERENCES T_PAY_ROLL (PAY_ROLL_ID)

);

CREATE TABLE T_EMPLOYEE_ATTENDENCE_SUMMARY
(
    EMPLOYEE_ATTENDENCE_SUMMARY_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SUMMARY_DATE                   DATE,
    INITIATED_BY                   INT,
    TOTAL_PRESENT                  INT,
    TOTAL_ABSENT                   INT,
    TOTAL_WAGE                     DECIMAL(15, 2),
    FOREIGN KEY (INITIATED_BY) REFERENCES T_USER (USER_ID)
);

CREATE TABLE T_EMPLOYEE_ATTENDENCE
(
    EMPLOYEE_ATTENDENCE_ID     INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    EMPLOYEE_ID                INT,
    YEAR                       INT,
    MONTH                      INT,
    DATE_MARKED                DATE,
    ATTENDENCE_DATE            DATE,
    ARRIVAL_TIME               VARCHAR(100) NULL,
    DEPARTURE_TIME             VARCHAR(100) NULL,
    EXTRA_HOURS                INT,
    WAGE_CALCULLATED           DECIMAL(15, 2),
    ARRIVAL_TIME_MARKED_BY     INT,
    ARRIVAL_TIME_MARKED_TIME   VARCHAR(100) NULL,
    DEPARTURE_TIME_MARKED_BY   INT,
    DEPARTURE_TIME_MARKED_TIME VARCHAR(100) NULL,
    TOOK_LEAVE                 TINYINT(1),
    LEAVE_TYPE                 INT,
    REDUCED_LEAVE_TYPE         INT,
    REDUCED_PORTION            INT,
    STATUS                     VARCHAR(5) CHECK (STATUS IN ('INIT', 'ARRIV', 'DEPT')),
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES T_EMPLOYEE (EMPLOYEE_ID),
    FOREIGN KEY (ARRIVAL_TIME_MARKED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (DEPARTURE_TIME_MARKED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (LEAVE_TYPE) REFERENCES T_LEAVE_TYPE (LEAVE_TYPE_ID),
    FOREIGN KEY (REDUCED_PORTION) REFERENCES T_LEAVE_TYPE (LEAVE_TYPE_ID),
    FOREIGN KEY (REDUCED_LEAVE_TYPE) REFERENCES T_LEAVE_TYPE (LEAVE_TYPE_ID)
);

-- #ACCOUNTING

CREATE TABLE T_BANK
(
    BANK_ID INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME    VARCHAR(20) NOT NULL
);

CREATE TABLE T_BANK_DETAIL
(
    BANK_DETAIL_ID INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    BANK_ID        INT         NOT NULL,
    BRANCH         VARCHAR(20) NOT NULL,
    ACCOUNT_NO     VARCHAR(30) NOT NULL,
    ACCOUNT_NAME   VARCHAR(50) NOT NULL,
    SITE_ID        INT         NOT NULL,
    ACCOUNT_TYPE   VARCHAR(20) CHECK (ACCOUNT_TYPE IN ('SAVING', 'CURRENT')),
    ACCOUNT_STATUS VARCHAR(3) CHECK (ACCOUNT_TYPE IN ('ACT', 'INA')),
    BALANCE        DECIMAL(15, 2),
    FOREIGN KEY (BANK_ID) REFERENCES T_BANK (BANK_ID),
    FOREIGN KEY (SITE_ID) REFERENCES T_CONSTRUCTION_SITE (CONSTRUCTION_SITE_ID)
);

CREATE TABLE T_EXPENSE
(
    EXPENSE_ID     INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SITE_ID        INT NOT NULL,
    BANK_DETAIL_ID INT NOT NULL,
    ACCOUNT_DATE   DATE,
    AMOUNT         DECIMAL(15, 2),
    ACCOUNT_NO     VARCHAR(30),
    INVOICE_NUMBER VARCHAR(20),
    DESCRIPTION    VARCHAR(100),
    ENTERED_BY     INT,
    ENTERED_DATE   DATE,
    APPROVED_BY    INT,
    APPROVED_DATE  DATE,
    PAY_BY         INT,
    FOREIGN KEY (ENTERED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (APPROVED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (PAY_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (BANK_DETAIL_ID) REFERENCES T_BANK_DETAIL (BANK_DETAIL_ID),
    FOREIGN KEY (SITE_ID) REFERENCES T_CONSTRUCTION_SITE (CONSTRUCTION_SITE_ID)
);

CREATE TABLE T_INCOME
(
    INCOME_ID      INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SITE_ID        INT         NOT NULL,
    BANK_DETAIL_ID INT         NOT NULL,
    ACCOUNT_DATE   DATE,
    ACCOUNT_NO     VARCHAR(30) NOT NULL,
    REF_NO         VARCHAR(20) NOT NULL,
    AMOUNT         DECIMAL(15, 2),
    DESCRIPTION    VARCHAR(100),
    ENTERED_BY     INT         NOT NULL,
    ENTERED_DATE   DATE,
    ISSUE_DATE     DATE,
    RECEIVED_DATE  DATE,
    PAYMENT_MODE   VARCHAR(6) CHECK (PAYMENT_MODE IN ('CASH', 'CHEQUE')),
    CHEQUE_NUMBER  VARCHAR(50),
    FOREIGN KEY (ENTERED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (BANK_DETAIL_ID) REFERENCES T_BANK_DETAIL (BANK_DETAIL_ID),
    FOREIGN KEY (SITE_ID) REFERENCES T_CONSTRUCTION_SITE (CONSTRUCTION_SITE_ID)

);

CREATE TABLE T_PURCHASE_REQUEST
(
    PURCHASE_REQUEST_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SITE_ID             INT,
    REQUEST_DATE        DATE,
    REQUESTED_BY        INT,
    EMERGENCY_BOUGHT    TINYINT(1),
    STATUS              VARCHAR(10) CHECK (STATUS IN ('INIT', 'REQ', 'PART_REQ', 'INA')),
    FOREIGN KEY (SITE_ID) REFERENCES T_CONSTRUCTION_SITE (CONSTRUCTION_SITE_ID),
    FOREIGN KEY (REQUESTED_BY) REFERENCES T_USER (USER_ID)
);

CREATE TABLE T_MATERIAL
(
    MATERIAL_ID INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME        VARCHAR(50) NOT NULL,
    DESCRIPTION VARCHAR(200),
    STOCK       DECIMAL(15, 2),
    UNIT        VARCHAR(5),
    STATUS      VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA'))
);


CREATE TABLE T_PURCHASE_MATERIAL
(
    PURCHASE_MATERIAL_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    MATERIAL_ID          INT,
    QUANTITY             INT,
    DESCRIPTION          VARCHAR(100),
    FOREIGN KEY (MATERIAL_ID) REFERENCES T_MATERIAL (MATERIAL_ID)
);

CREATE TABLE T_SUPPLIER
(
    SUPPLIER_ID    INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME           VARCHAR(20)  NOT NULL,
    CONTACT_PERSON VARCHAR(300) NOT NULL,
    MOBILE         VARCHAR(15)  NOT NULL,
    FIXED          VARCHAR(15),
    REG_NUMBER     VARCHAR(30),
    ADDRESS_LINE_1 VARCHAR(300) NOT NULL,
    ADDRESS_LINE_2 VARCHAR(300),
    CITY           VARCHAR(30)  NOT NULL,
    COUNTRY        VARCHAR(30),
    JOINED_DATE    DATE,
    STATUS         VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA'))
);

CREATE TABLE T_PURCHASE_ORDER
(
    PURCHASE_ORDER_ID   INT            NOT NULL PRIMARY KEY AUTO_INCREMENT,
    PURCHASE_REQUEST_ID INT            NOT NULL,
    ORDER_DATE          DATE           NOT NULL,
    ORDER_VALUE         DECIMAL(15, 2) NOT NULL,
    ORDER_BY            INT            NOT NULL,
    SUPPLIER_ID         INT,
    COMPLETED_STATUS    VARCHAR(8) CHECK (STATUS IN ('REQ', 'PART_REQ')),
    STATUS              VARCHAR(3) CHECK (STATUS IN ('INA', 'INIT', 'PUR', 'PART_PUR')),
    FOREIGN KEY (ORDER_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (SUPPLIER_ID) REFERENCES T_SUPPLIER (SUPPLIER_ID),
    FOREIGN KEY (PURCHASE_REQUEST_ID) REFERENCES T_PURCHASE_REQUEST (PURCHASE_REQUEST_ID)
);



CREATE TABLE T_PURCHASE_ORDER_MATERIAL
(
    PURCHASE_ORDER_MATERIAL_ID INT            NOT NULL PRIMARY KEY AUTO_INCREMENT,
    MATERIAL_ID                INT,
    QUANTITY                   INT            NOT NULL,
    UNIT_PRICE                 DECIMAL(15, 2) NOT NULL,
    TOTAL_PRICE                DECIMAL(15, 2),
    RECEIVED_QUANTITY          INT,
    FOREIGN KEY (MATERIAL_ID) REFERENCES T_MATERIAL (MATERIAL_ID)
);

create table T_GOOD_RECEIVE_NOTE
(
    GOOD_RECEIVE_ID   INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    PURCHASE_ORDER_ID INT,
    INVOICE_NUMBER    VARCHAR(50) NOT NULL,
    RECEIVED_DATE     DATE,
    RECEIVED_BY       INT,
    TOTAL_AMOUNT      DECIMAL(15, 2),
    PAID_AMOUNT       DECIMAL(15, 2),
    BALANCE_AMOUNT    DECIMAL(15, 2),
    COMPLETED_STATUS  VARCHAR(8) CHECK (COMPLETED_STATUS IN ('PUR', 'PART_PUR')),
    STATUS            VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA')),
    FOREIGN KEY (PURCHASE_ORDER_ID) REFERENCES T_PURCHASE_ORDER (PURCHASE_ORDER_ID),
    FOREIGN KEY (RECEIVED_BY) REFERENCES T_USER (USER_ID)
);

CREATE TABLE T_GOOD_RECEIVED
(
    GOOD_RECEIVE_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    MATERIAL_ID     INT,
    QUANTITY        DECIMAL(15, 2),
    UNIT_PRICE      DECIMAL(15, 2),
    TOTAL_PRICE     DECIMAL(15, 2),
    FOREIGN KEY (MATERIAL_ID) REFERENCES T_MATERIAL (MATERIAL_ID)
);

CREATE TABLE T_SUPPLIER_PAYMENT
(
    SUPPLIER_PAYMENT_ID INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SUPPLIER_ID         INT,
    INVOICE_NUMBER      VARCHAR(50) NOT NULL,
    PAID_DATE           DATE,
    PAID_AMOUNT         DECIMAL(15, 2),
    FOREIGN KEY (SUPPLIER_ID) REFERENCES T_SUPPLIER (SUPPLIER_ID)
);

CREATE TABLE T_MATERIAL_DAILY_USAGE
(
    MATERIAL_USAGE_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    MATERIAL_ID       INT,
    MATERIAL_USAGE    DECIMAL(15, 2),
    MARKED_BY         INT,
    MARKED_DATE       DATE,
    FOREIGN KEY (MATERIAL_ID) REFERENCES T_MATERIAL (MATERIAL_ID),
    FOREIGN KEY (MARKED_BY) REFERENCES T_USER (USER_ID)
);

CREATE TABLE T_MANAGER_APPROVAL
(
    MANAGER_APPROVAL_ID    INT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    REQUESTED_BY           INT  NOT NULL,
    REQUESTED_DATE         DATE NOT NULL,
    DESCRIPTION            VARCHAR(50),
    APPROVAL_ROLE_REQUIRED INT  NOT NULL,
    TYPE                   VARCHAR(10),
    APPROVAL_PRIMARY_ID    INT,
    APPROVED_BY            INT  NOT NULL,
    APPROVED_DATE          DATE,
    FOREIGN KEY (REQUESTED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (APPROVED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (APPROVAL_ROLE_REQUIRED) REFERENCES T_ROLE (ROLE_ID)
);

#MACHINARY

CREATE TABLE T_LARGE_EQUIPMENT
(
    LARGE_EQUIPMENT_ID INT            NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SITE_ID            INT            NOT NULL,
    TYPE               VARCHAR(20),
    NAME               VARCHAR(20)    NOT NULL,
    DESCRIPTION        VARCHAR(100),
    REG_NO             VARCHAR(30),
    PURCHASE_DATE      DATE,
    PRICE              DECIMAL(15, 2) NOT NULL,
    ANUAL_DEPRICIATION DECIMAL(15, 2),
    STATUS             VARCHAR(4) CHECK (STATUS IN ('ACT', 'INA', 'RET', 'SOLD')),
    FOREIGN KEY (SITE_ID) REFERENCES T_CONSTRUCTION_SITE (CONSTRUCTION_SITE_ID)
);

CREATE TABLE T_SMALL_EQUIPMENT
(
    SMALL_EQUIPMENT_ID INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME               VARCHAR(20) NOT NULL,
    DESCRIPTION        VARCHAR(100),
    TOTAL_QUANTITY     DECIMAL(15, 2),
    STATUS             VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA')),
    MODIFIED_BY        INT,
    MODIFIED_DATE      DATE,
    FOREIGN KEY (MODIFIED_BY) REFERENCES T_USER (USER_ID)
);

CREATE TABLE T_SMALL_EQUIPMENT_SITE
(
    SMALL_EQUIPMENT_SITE_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SMALL_EQUIPMENT_ID      INT,
    QUANTITY                DECIMAL(15, 2),
    STATUS                  VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA')),
    MODIFIED_By             INT,
    MODIFIED_DATE           DATE,
    FOREIGN KEY (MODIFIED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (SMALL_EQUIPMENT_ID) REFERENCES T_SMALL_EQUIPMENT (SMALL_EQUIPMENT_ID)
);

# REPORTS

CREATE TABLE T_DAILY_REPORT
(
    DAILY_REPORT_ID                  INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SITE_ID                          INT       NOT NULL,
    REPORT_DATE                      DATE      NOT NULL,
    TOTAL_COUN                       INT,
    TOTAL_MAN_POWER                  DECIMAL(15, 2),
    REMARK                           VARCHAR(200),
    SITE_REQUIRMENT                  VARCHAR(200),
    MODIFIED_BY                      INT,
    MODIFIED_TIME                    TIMESTAMP NULL,
    APPROVED_BY_SITE_CONSULTANT      INT,
    APPROVED_BY_SITE_CONSULTANT_TIME TIMESTAMP NULL,
    APPROVED_BY_COMPANY_MANAGER      INT,
    APPROVED_BY_COMPANY_MANAGER_TIME TIMESTAMP NULL,
    FOREIGN KEY (SITE_ID) REFERENCES T_CONSTRUCTION_SITE (CONSTRUCTION_SITE_ID),
    FOREIGN KEY (MODIFIED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (APPROVED_BY_SITE_CONSULTANT) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (APPROVED_BY_COMPANY_MANAGER) REFERENCES T_USER (USER_ID)
);

CREATE TABLE T_WEATHER_REPORT
(
    WEATHER_REPORT_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DAILY_REPORT_ID   INT NOT NULL,
    HOUR              INT,
    TYPE              VARCHAR(5) CHECK (TYPE IN ('CLOUD', 'RAIN', 'FINE')),
    DESCRIPTION       VARCHAR(100),
    MODIFIED_BY       INT,
    MODIFIED_TIME     TIMESTAMP,
    FOREIGN KEY (DAILY_REPORT_ID) REFERENCES T_DAILY_REPORT (DAILY_REPORT_ID),
    FOREIGN KEY (MODIFIED_BY) REFERENCES T_USER (USER_ID)
);

CREATE TABLE T_TODAY_ACTIVITY
(
    ACTIVITY_ID     INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DAILY_REPORT_ID INT,
    ACTIVITY_NO     VARCHAR(30),
    DESCRIPTION     VARCHAR(150),
    SUPERVISED_BY   INT,
    STATUS          VARCHAR(3) CHECK (STATUS IN ('ACT', 'INA')),
    FOREIGN KEY (DAILY_REPORT_ID) REFERENCES T_DAILY_REPORT (DAILY_REPORT_ID),
    FOREIGN KEY (SUPERVISED_BY) REFERENCES T_EMPLOYEE (EMPLOYEE_ID)
);

CREATE TABLE T_MAN_POWER
(
    MAN_POWER_ID     INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DAILY_REPORT_ID  INT,
    EMPLOYEE_TYPE    VARCHAR(10) NOT NULL,
    COUNT            INT,
    ATTENDENCE_COUNT INT,
    MAN_POWER_HOUR   DECIMAL(15, 2),
    MODIFIED_BY      INT,
    MODIFIED_TIME    TIMESTAMP,
    FOREIGN KEY (MODIFIED_BY) REFERENCES T_USER (USER_ID),
    FOREIGN KEY (DAILY_REPORT_ID) REFERENCES T_DAILY_REPORT (DAILY_REPORT_ID)
);

CREATE TABLE T_LARGE_EQUIPMENT_USAGE
(
    LARGE_EQUIPMENT_USAGE_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TYPE                     VARCHAR(20),
    APPLICABLE               TINYINT(1),
    WORK_HOUR                DECIMAL(15, 2),
    IDLE_HOUR                DECIMAL(15, 2),
    TOTAL_HOUR               DECIMAL(15, 2),
    MODIFIED_BY              INT,
    MODIFIED_TIME            TIMESTAMP,
    FOREIGN KEY (MODIFIED_BY) REFERENCES T_USER (USER_ID)
);
