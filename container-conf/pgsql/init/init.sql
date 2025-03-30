CREATE SEQUENCE input_seq;
CREATE SEQUENCE result_seq;

CREATE TABLE input_data
(
    id INT PRIMARY KEY NOT NULL,
    last_name VARCHAR(100),
    first_name VARCHAR(100),
    done BOOLEAN DEFAULT FALSE
);

CREATE TABLE result_data
(
    id INT PRIMARY KEY NOT NULL,
    input_id INT NOT NULL REFERENCES input_data (id),
    initials VARCHAR(2)
);
