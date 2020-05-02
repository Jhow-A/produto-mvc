DROP TABLE tb_produto;
DROP SEQUENCE tb_produto_seq;

CREATE TABLE tb_produto (
    id               NUMBER(10) NOT NULL PRIMARY KEY,
    nome             VARCHAR2(256),
    sku              VARCHAR2(256),
    descricao        VARCHAR2(256),
    caracteristicas  VARCHAR2(256),
    preco            NUMBER(10, 2)
);

CREATE SEQUENCE tb_produto_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER tb_insert_id_produto
                  BEFORE INSERT ON tb_produto FOR EACH ROW       
BEGIN
 
SELECT tb_produto_seq.NEXTVAL
INTO :NEW.id
FROM DUAL;
END;
/

INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO) VALUES ('Nome do produto 1', 'sku-0001', 'Descrição do Produto 1', 'Características do produto 1', 1.99);
INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO) VALUES ('Nome do produto 2', 'sku-0002', 'Descrição do Produto 2', 'Características do produto 2', 2.99);
INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO) VALUES ('Nome do produto 3', 'sku-0002', 'Descrição do Produto 3', 'Características do produto 3', 3.99);
INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO) VALUES ('Nome do produto 4', 'sku-0002', 'Descrição do Produto 4', 'Características do produto 4', 4.99);

commit;

SELECT * FROM TB_PRODUTO;