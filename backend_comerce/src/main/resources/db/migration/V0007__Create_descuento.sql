CREATE TABLE descuento (
    ID 			    INT	NOT NULL AUTO_INCREMENT,
    NOMBRE 		    VARCHAR(50)	NOT NULL,
    DESCUENTO 		INT	NOT NULL
);

CREATE INDEX IDX_DESCUENTO_ID ON descuento (ID);

