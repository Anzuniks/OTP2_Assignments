CREATE DATABASE IF NOT EXISTS converterdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE converterdb;

CREATE TABLE IF NOT EXISTS unit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    unit_key VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS unit_translation (
    unit_id INT NOT NULL,
    lang VARCHAR(5) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (unit_id, lang),
    FOREIGN KEY (unit_id) REFERENCES unit(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS conversion_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    value_from DOUBLE NOT NULL,
    from_unit VARCHAR(50) NOT NULL,
    to_unit VARCHAR(50) NOT NULL,
    result DOUBLE NOT NULL,
    lang VARCHAR(5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO unit (id, unit_key) VALUES (1, 'celsius'), (2, 'fahrenheit'), (3, 'kelvin');

INSERT INTO unit_translation (unit_id, lang, name, description) VALUES
(1, 'en', 'Celsius', 'Degree Celsius scale'),
(1, 'fi', 'Celsius', 'Celsius-asteikko'),
(1, 'sv', 'Celsius', 'Celsiusgrad'),
(1, 'ja', '摂氏', '摂氏温度目盛り'),
(1, 'ar', 'مئوية', 'مقياس درجة حرارة مئوية'),
(2, 'en', 'Fahrenheit', 'Fahrenheit scale'),
(2, 'fi', 'Fahrenheit', 'Fahrenheit-asteikko'),
(2, 'sv', 'Fahrenheit', 'Fahrenheitgrad'),
(2, 'ja', '華氏', '華氏温度目盛り'),
(2, 'ar', 'فهرنهايت', 'مقياس فهرنهايت'),
(3, 'en', 'Kelvin', 'Kelvin scale'),
(3, 'fi', 'Kelvin', 'Kelvin-asteikko'),
(3, 'sv', 'Kelvin', 'Kelvin-asteikko'),
(3, 'ja', 'ケルビン', '絶対温度目盛り'),
(3, 'ar', 'كلفن', 'مقياس كلفن');
