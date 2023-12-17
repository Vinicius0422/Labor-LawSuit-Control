CREATE TABLE IF NOT EXISTS lawsuit_defendant (
	lawsuit_id BIGINT UNSIGNED NOT NULL,
    defendant_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (lawsuit_id, defendant_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
