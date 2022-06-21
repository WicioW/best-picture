--liquibase formatted sql
--changeset wiciow:1

CREATE DATABASE IF NOT EXISTS bestpicture;

# create
# user 'bestpictureuser'@'localhost' identified by 'bestpicturepassword';
#
# GRANT ALL PRIVILEGES ON bestpicture.* TO
# 'bestpictureuser'@'localhost';