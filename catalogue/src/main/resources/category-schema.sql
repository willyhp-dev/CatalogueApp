DROP TABLE IF EXISTS ms_category;

CREATE TABLE ms_category(

    id VARCHAR(6) PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    creator_id INT NOT NULL,
    updated_date TIMESTAMP NULL,
    updater_id INT NULL,
    deleted_date TIMESTAMP NULL,
    deleter_id INT NULL,
    rec_status VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
) WITH ( OIDS = FALSE);

SELECT * FROM ms_category;