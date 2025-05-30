CREATE TABLE tb_historico_logs
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    cep      VARCHAR(10)           NULL,
    data_hora     datetime              NULL,
    rua        VARCHAR(120)          NULL,
    cidade          VARCHAR(120)          NULL,
    uf         VARCHAR(2)            NULL,
    CONSTRAINT pk_tb_historico_log PRIMARY KEY (id)
);