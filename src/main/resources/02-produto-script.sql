--DROP TABLE tb_produto;
--DROP SEQUENCE PRODUTO_SEQ;

CREATE TABLE tb_produto (
    id               NUMBER(10) NOT NULL PRIMARY KEY,
    nome             VARCHAR2(256),
    sku              VARCHAR2(256),
    descricao        VARCHAR2(256),
    caracteristicas  VARCHAR2(256),
    preco            NUMBER(10, 2),
    id_categoria     NUMBER(10),
    CONSTRAINT FK_ID_CATEGORIA FOREIGN KEY (id_categoria) REFERENCES tb_categoria(id_categoria)
);

CREATE SEQUENCE produto_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER tr_insert_id_produto
                  BEFORE INSERT ON tb_produto FOR EACH ROW       
BEGIN
 
SELECT produto_seq.NEXTVAL
INTO :NEW.id
FROM DUAL;
END;
/

INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO, ID_CATEGORIA) VALUES ('Nome do produto 1', 'sku-0001', 'Descrição do Produto 1', 'Características do produto 1', 1.99, 1);
INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO, ID_CATEGORIA) VALUES ('Nome do produto 2', 'sku-0002', 'Descrição do Produto 2', 'Características do produto 2', 2.99, 2);
INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO, ID_CATEGORIA) VALUES ('Nome do produto 3', 'sku-0002', 'Descrição do Produto 3', 'Características do produto 3', 3.99, 3);
INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO, ID_CATEGORIA) VALUES ('Nome do produto 4', 'sku-0002', 'Descrição do Produto 4', 'Características do produto 4', 4.99, 4);

commit;

SELECT * FROM TB_PRODUTO;