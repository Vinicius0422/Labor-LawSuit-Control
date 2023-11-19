CREATE TABLE IF NOT EXISTS lawsuit (
    id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
    lawsuit_number VARCHAR(22) NOT NULL,
    civil_court VARCHAR(5) NOT NULL,
    distribution_date DATE NOT NULL,
    value_case DECIMAL(10,2),
    lawsuitphase_id BIGINT UNSIGNED DEFAULT(1),
    lawsuitstatus_id BIGINT UNSIGNED DEFAULT(1),
    location_id BIGINT UNSIGNED DEFAULT(1),
    claimant_id BIGINT UNSIGNED NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (lawsuitphase_id) REFERENCES lawsuit_phase (id),
    FOREIGN KEY (lawsuitstatus_id) REFERENCES lawsuit_status (id),
    FOREIGN KEY (location_id) REFERENCES location (id),
    FOREIGN KEY (claimant_id) REFERENCES claimant (id),
    UNIQUE (lawsuit_number)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
