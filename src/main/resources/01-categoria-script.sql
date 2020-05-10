--DROP TABLE tb_categoria;
--DROP SEQUENCE CATEGORIA_SEQ;

CREATE TABLE tb_categoria (
    id_categoria 		NUMBER(10) NOT NULL PRIMARY KEY,
    nome_categoria  	VARCHAR2(256)
);

CREATE SEQUENCE CATEGORIA_SEQ START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER tr_insert_id_categoria
                  BEFORE INSERT ON tb_categoria FOR EACH ROW       
BEGIN
 
SELECT CATEGORIA_SEQ.NEXTVAL
INTO :new.id_categoria
FROM DUAL;
END;
/

INSERT INTO tb_categoria (nome_categoria) VALUES ('Notebook');
INSERT INTO tb_categoria (nome_categoria) VALUES ('Smartphone');
INSERT INTO tb_categoria (nome_categoria) VALUES ('TV');
INSERT INTO tb_categoria (nome_categoria) VALUES ('Tablet');

commit;

SELECT * FROM tb_categoria;