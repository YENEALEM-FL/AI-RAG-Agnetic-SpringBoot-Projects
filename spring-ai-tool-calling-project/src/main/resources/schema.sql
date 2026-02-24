DROP TABLE IF EXISTS DEVICES;
CREATE TABLE DEVICES
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    device_type         VARCHAR(255) NOT NULL,
    room         VARCHAR(255) NOT NULL,
    is_on        BOOLEAN      NOT NULL DEFAULT FALSE,
    "value" DOUBLE,
    status       VARCHAR(255),
    last_updated TIMESTAMP,
    energy_consumption DOUBLE DEFAULT 0.0
);

DROP TABLE IF EXISTS ENERGY_USAGE;
CREATE TABLE ENERGY_USAGE
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    period VARCHAR (255) NOT NULL,
    consumption DOUBLE NOT NULL,
    cost DOUBLE NOT NULL,
    recorded_at TIMESTAMP NOT NULL
);