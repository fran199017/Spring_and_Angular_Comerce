CREATE TABLE product (
    ID 			    INT	NOT NULL AUTO_INCREMENT,
	NAME 		    VARCHAR(50)	NOT NULL,
	DESCRIPTION     VARCHAR(200) NOT NULL,
	DETALLE_PRODUCTO_ID INT NOT NULL,
    PROVEEDOR_ID INT NOT NULL,
	PRIMARY KEY (ID)
);

CREATE INDEX IDX_PRODUCTS_ID ON product (ID);

