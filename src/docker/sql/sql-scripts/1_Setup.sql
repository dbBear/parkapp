DROP DATABASE IF EXISTS park_app_db;
CREATE DATABASE IF NOT EXISTS park_app_db;

CREATE USER 'parkappAdmin'@'%' IDENTIFIED BY 'admin';

GRANT ALTER ON park_app_db.* to 'parkappAdmin'@'%';
GRANT INSERT ON park_app_db.* to 'parkappAdmin'@'%';
GRANT SELECT ON park_app_db.* to 'parkappAdmin'@'%';
GRANT UPDATE ON park_app_db.* to 'parkappAdmin'@'%';
GRANT DELETE ON park_app_db.* to 'parkappAdmin'@'%';
