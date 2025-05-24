--Để bỏ kí tự C##
alter session set "_ORACLE_SCRIPT"=true;
--Để thay đổi schema
ALTER SESSION SET CURRENT_SCHEMA = GK;
--Để connect tới user
CONNECT GK/Admin123@orcl;