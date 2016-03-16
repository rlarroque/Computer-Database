drop schema if exists `computer-database-db`;
create schema if not exists `computer-database-db`;

use `computer-database-db`;

drop table if exists computer;
drop table if exists company;

CREATE TABLE company (
  id                        bigint not null auto_increment,
  name                      varchar(255),
  CONSTRAINT pk_company primary key (id))
;

CREATE TABLE computer (
  id                        bigint not null auto_increment,
  name                      varchar(255),
  introduced                timestamp NULL,
  discontinued              timestamp NULL,
  company_id                bigint default NULL,
  CONSTRAINT pk_computer primary key (id))
;

CREATE TABLE user (
  username VARCHAR(45) NOT NULL primary key,
  password VARCHAR(60) NOT NULL)
;

CREATE TABLE user_role (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  CONSTRAINT pk_user_role primary key (user_role_id))
;

alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_computer_company_1 on computer (company_id);

alter table user_role add constraint fk_user_role_users foreign key(username) references user(username) on delete CASCADE on update restrict;
create index ix_role_username on user_role (username,role);