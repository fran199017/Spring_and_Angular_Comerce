CREATE TABLE detalle_producto (
    ID 			    INT	NOT NULL AUTO_INCREMENT,
	MATERIAL 		VARCHAR(50)	NOT NULL,
	PESO            FLOAT NOT NULL,
	COST            FLOAT NOT NULL,
	FINAL_COST      FLOAT NOT NULL,
    IS_DISCOUNT     INT NOT NULL,
    IMPUESTO        INTEGER NOT NULL,
    OFERTA_ID       INTEGER NULL,
	PRIMARY KEY (ID)
);

CREATE INDEX IDX_DETALLE_PRODUCTO_ID ON detalle_producto (ID);

