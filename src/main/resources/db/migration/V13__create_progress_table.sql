CREATE TABLE IF NOT EXISTS progress (
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
    progress_date DATE NOT NULL,
    lawsuitphase_id BIGINT UNSIGNED NOT NULL,
    lawsuit_id BIGINT UNSIGNED NOT NULL,
   	expected_date DATE,
    expected_time TIME,
    description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (lawsuitphase_id) REFERENCES lawsuit_phase (id),
    FOREIGN KEY (lawsuit_id) REFERENCES lawsuit (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
