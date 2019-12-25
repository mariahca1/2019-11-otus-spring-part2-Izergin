/*
drop database java_db_izergin;
drop schema java_schema_izergin;
revoke connect on database postgres from java_user_izergin;
drop user java_user_izergin;
*/


create role java_user_izergin with
    encrypted password 'java_user_izergin'
login;

CREATE DATABASE java_db_izergin OWNER=java_user_izergin;
GRANT ALL PRIVILEGES ON DATABASE java_db_izergin TO java_user_izergin;

grant connect on database java_db_izergin to java_user_izergin;

/*
--после подключения из под java_user_izergin выполнить
create schema if not exists java_schema_izergin;
SET search_path TO java_schema_izergin;
*/
