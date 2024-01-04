CREATE TABLE IF NOT EXISTS annotation (
    id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
    annotation_date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    lawsuit_id BIGINT UNSIGNED NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (lawsuit_id) REFERENCES lawsuit (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
