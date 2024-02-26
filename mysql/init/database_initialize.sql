USE database;

CREATE TABLE Example_Table(id INT, info varchar(200), primary key(id));
CREATE TABLE Employees(id INT, first_name varchar(50), last_name varchar(50), date_of_employment DATE, primary key(id));

INSERT INTO Example_Table VALUES(1, "This is an example entry");
INSERT INTO Example_Table VALUES(2, "This is an example entry");
INSERT INTO Example_Table VALUES(3, "This is an example entry");
INSERT INTO Example_Table VALUES(4, "This is an example entry");
INSERT INTO Example_Table VALUES(5, "This is an example entry");
INSERT INTO Example_Table VALUES(6, "This is an example entry");
INSERT INTO Example_Table VALUES(7, "This is an example entry");
INSERT INTO Example_Table VALUES(8, "This is an example entry");
INSERT INTO Example_Table VALUES(9, "This is an example entry");
INSERT INTO Example_Table VALUES(10, "This is an example entry");

INSERT INTO Employees VALUES(1, "Elon", "Musk", '2000-01-01');
INSERT INTO Employees VALUES(2, "Mark", "Zuckerberg", '2000-01-01');
INSERT INTO Employees VALUES(3, "Bill", "Gates", '2000-01-01');
INSERT INTO Employees VALUES(4, "Joe", "Biden", '2000-01-01');