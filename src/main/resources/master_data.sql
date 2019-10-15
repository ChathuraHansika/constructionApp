INSERT INTO T_ROLE (ROLE_ID, ROLE_NAME) VALUES (1, 'Administrator');
INSERT INTO T_PRIVILEGE (PRIVILEGE_ID, ROLE_ID, NAME) VALUES (1, 1, 'ADMIN_LEVEL');
INSERT INTO T_USER (USER_ID, ROLE_ID, USER_NAME, PASSWORD, STATUS) VALUES (1, 1, 'AkvaSoft', '$2a$10$fqZ.RvsTkxOEp3/y6SJhmeVhnCDYUtO0wGIeZ6IZOM0JIj7EJsvDy', 'ACT');

INSERT INTO T_JOB_TYPE (JOB_TYPE_ID, INSENTIVE_PERCENTAGE, JOB_TYPE, STATUS)
VALUES  (1, 30.00, 'QA', 'ACT'),(2, 50.00, 'Supervisor', 'ACT'),(3, 80.00, 'Project Manager', 'ACT'),(4, 05.00, 'Worker', 'ACT'),(5, 70.00, 'Site Engineer', 'ACT');

INSERT INTO T_JOB_TYPE_SALARY (JOB_TYPE_SALARY_ID, JOB_TYPE_ID, DESCRIPTION, AMOUNT, PER_HOUR, PER_DAY, SALARY_TYPE,STATUS)
VALUES (1, 1, 'BASIC',25000.00, 0, 0, 'BASE', 'ACT'), (2, 2, 'BASIC',35000.00, 0, 0, 'BASE', 'ACT'), (3, 4, 'ALLOWANCE',500.00, 0, 1, 'ALLOWANCE', 'ACT');

INSERT INTO T_LEAVE_TYPE (LEAVE_TYPE_ID, TYPE, DAY_PORTION, REDUCT_FROM, STATUS)
VALUES (1, 'SHORT', 0.25, 'CASUAL', 'ACT'),(2, 'HALF', 0.5, 'ANNUAL', 'ACT'),(3, 'LEAVE', 1, 'SICK', 'ACT'),(4, 'NOPAY', 1, 'SALARY', 'ACT');

INSERT INTO T_JOB_TYPE_LEAVE (JOB_TYPE_LEAVE_ID, JOB_TYPE_ID, LEAVE_TYPE_ID, TYPE, REDUCT_FROM, PORTION, AMOUNT, STATUS)
VALUES (1, 1, 1, 'SHORT', 'CASUAL', 0.25, 5, 'ACT'),(2, 1, 2, 'HALF', 'ANNUAL', 0.5, 10, 'ACT'),(3, 1, 3, 'LEAVE', 'SICK', 1, 15, 'ACT');
