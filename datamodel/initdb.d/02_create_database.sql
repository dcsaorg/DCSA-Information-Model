-- Database setup script
-- Needs to be executed by user postgres or equivalent

DROP DATABASE IF EXISTS dcsa_openapi;
CREATE DATABASE dcsa_openapi OWNER dcsa_db_owner;
\connect dcsa_openapi
CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- Used to generate UUIDs
CREATE SCHEMA IF NOT EXISTS dcsa_ebl_v1_0;
GRANT ALL PRIVILEGES ON DATABASE dcsa_openapi TO dcsa_db_owner;
GRANT ALL PRIVILEGES ON SCHEMA dcsa_ebl_v1_0 TO dcsa_db_owner;
ALTER DEFAULT PRIVILEGES IN SCHEMA dcsa_ebl_v1_0 GRANT ALL ON TABLES TO dcsa_db_owner;
