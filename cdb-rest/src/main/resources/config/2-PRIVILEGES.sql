  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  USE `computer-database-db`;  
  
  #DROP USER 'admincdb'@'localhost';
  FLUSH PRIVILEGES;;
  
  #CREATE USER 'admincdb'@'localhost' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `computer-database-db`.* TO 'admincdb'@'localhost' WITH GRANT OPTION;

  FLUSH PRIVILEGES;
	  

  

