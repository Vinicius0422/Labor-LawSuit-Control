CREATE TABLE IF NOT EXISTS defendant (
    id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
    defendant_name VARCHAR(150) NOT NULL,
    person_type ENUM ('Fisica', 'Juridica') NOT NULL DEFAULT 'Juridica',
    cpf_cnpj VARCHAR(14) NOT NULL,
    address VARCHAR(255),
    city VARCHAR(50),
    neighborhood VARCHAR(50),
    uf VARCHAR(2),
    cep VARCHAR(8),
    contact VARCHAR(255),
    email VARCHAR(150),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE (cpf_cnpj)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
