 CREATE TABLE IF NOT EXISTS milestones (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  msname VARCHAR(255),
  msdesc VARCHAR(255),
  msduedate DATE(3),
  compdate DATE(3),
  userid INTEGER(255),
  FOREIGN KEY(userid)
  REFERENCES person (id)
);