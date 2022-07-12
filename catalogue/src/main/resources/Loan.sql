
DROP TABLE IF EXISTS ms_loan;


CREATE TABLE ms_loan(
    id VARCHAR(10) PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    role_id VARCHAR(5) NOT NULL UNIQUE,
    loan_amount NUMERIC(10,2) NOT NULL,
    loan_term NUMERIC(10,2) NOT NULL,
    interest_rate NUMERIC(10,2) NOT NULL,
    total_loan NUMERIC(12,2) NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    startdate TIMESTAMP  NOT NULL
) WITH(OIDS=FALSE);

SELECT * FROM ms_loan;