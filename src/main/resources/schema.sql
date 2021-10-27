DROP TABLE IF EXISTS manager;

CREATE TABLE manager (
  id             	INT             AUTO_INCREMENT  PRIMARY KEY,
  manager_name      VARCHAR(250)    NOT NULL
  
);


DROP TABLE IF EXISTS department;

CREATE TABLE department (
  id             	INT             AUTO_INCREMENT  PRIMARY KEY,
  department_name   VARCHAR(250)    NOT NULL
  
);




DROP TABLE IF EXISTS employees;

CREATE TABLE employee (
  id             	INT             AUTO_INCREMENT  PRIMARY KEY,
  first_name        VARCHAR2(20)	NOT NULL,
  last_name         VARCHAR2(25)	NOT NULL,   
  email             VARCHAR2(25)	NOT NULL,
  phone_number      VARCHAR2(20)	NOT NULL,
  hire_date         DATE			NOT NULL,
  salary            NUMBER(8,2)		NOT NULL,
  department_id     INT             NOT NULL,
  manager_id		INT             NOT NULL,
  
  foreign key (manager_id) references manager(id),
  
  foreign key (department_id) references  department (id)
  
);