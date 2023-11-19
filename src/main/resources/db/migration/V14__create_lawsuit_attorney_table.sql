CREATE TABLE IF NOT EXISTS lawsuit_attorney (
    lawsuit_id BIGINT UNSIGNED NOT NULL,
	attorney_id BIGINT UNSIGNED NOT NULL,
	created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (lawsuit_id, attorney_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
