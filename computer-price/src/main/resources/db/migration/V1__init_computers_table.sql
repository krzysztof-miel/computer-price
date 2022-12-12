CREATE TABLE computers (
  id INT AUTO_INCREMENT NOT NULL,
   nazwa VARCHAR(255),
   dataKsiegowania date,
   kosztUSD DOUBLE NOT NULL,
   kosztPLN DOUBLE NOT NULL,
   CONSTRAINT pk_computers PRIMARY KEY (id)
);
