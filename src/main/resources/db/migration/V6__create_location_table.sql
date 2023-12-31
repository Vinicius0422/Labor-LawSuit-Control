CREATE TABLE IF NOT EXISTS location (
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
    location VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE (location)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
