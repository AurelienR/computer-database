  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'admincdb' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `computer-database-db_TEST`.* TO 'admincdb' WITH GRANT OPTION;


  FLUSH PRIVILEGES;
