  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  USE `computer-database-db_TEST`;  
  
  DROP USER 'admincdb'@'localhost';
  FLUSH PRIVILEGES;;
  
  CREATE USER 'admincdb'@'localhost' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `computer-database-db_TEST`.* TO 'admincdb'@'localhost' WITH GRANT OPTION;

  FLUSH PRIVILEGES;
	  

  

