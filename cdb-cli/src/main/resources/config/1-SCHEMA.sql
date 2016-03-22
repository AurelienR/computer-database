drop schema if exists `computer-database-db`;
  create schema if not exists `computer-database-db`;
  use `computer-database-db`;  
  
  drop table if exists computer;
  drop table if exists company;
  drop table if exists user_entity;
  drop table if exists user_role;

  create table company (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    constraint pk_company primary key (id))
  ;

  create table computer (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    introduced                timestamp NULL,
    discontinued              timestamp NULL,
    company_id                bigint default NULL,
    constraint pk_computer primary key (id))
  ;
  
  create table user_entity (
    id                        bigint not null auto_increment,
    username                  varchar(255),
    password                  varchar(255),
    user_role_id              bigint default NULL,
    constraint pk_user_entity primary key (id))
  ;

    create table user_role (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    constraint pk_user_role primary key (id))
  ;
  
  alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
  create index ix_computer_company_1 on computer (company_id);

  alter table user_entity add constraint fk_user_entity_user_role_1 foreign key (user_role_id) references user_role (id) on delete restrict on update restrict;
  create index ix_user_entity_user_role_1 on user_entity (user_role_id);