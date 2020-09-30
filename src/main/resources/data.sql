DROP TABLE IF EXISTS Employee;
 
CREATE TABLE Employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  FirstName VARCHAR(250) NOT NULL,
  LastName VARCHAR(250) NOT NULL
);
 
INSERT INTO Employee (FirstName, LastName) VALUES
  ('Aliko', 'Dangote'),
  ('Bill', 'Gates'),
  ('Folrunsho', 'Alakija');