CREATE TABLE IF NOT EXISTS lawsuit_attorney (
    lawsuit_id BIGINT UNSIGNED NOT NULL,
	attorney_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (lawsuit_id, attorney_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
