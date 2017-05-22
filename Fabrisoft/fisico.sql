CREATE TABLE acesso(
    codigo            serial not null,
    cod_usuario       int        NOT NULL,
    cod_formulario    int        NOT NULL,
    acesso            char(1)    NULL,
    editar            char(1)    NULL,
    visualizar        char(1)    NULL,
    CONSTRAINT PK31 PRIMARY KEY  (codigo, cod_usuario, cod_formulario)
);

CREATE TABLE Cidade(
    codigo    Serial not null,
    nome      varchar(100)    NULL,
    sigla     char(2)         NOT NULL,
    CONSTRAINT PK5 PRIMARY KEY  (codigo)
);

/* 
 * TABLE: Cliente 
 */

CREATE TABLE Cliente(
    codigo         Serial not null,
    dt_exclusao    date            NULL,
    email          varchar(200)    NULL,
    celular        varchar(12)     NULL,
    cep            varchar(9)      NULL,
    telefone       varchar(100)    NULL,
    end_numero     varchar(10)     NULL,
    nome           varchar(200)    NULL,
    tipo           char(1)         NULL,
    documento      varchar(15)     NULL,
    endereco       char(200)       NULL,
    bairro         varchar(60)     NULL,
    cod_cidade     int             NOT NULL,
    CONSTRAINT PK2 PRIMARY KEY  (codigo)
);

CREATE TABLE Compra(
    codigo            Serial not null,
    tipo_pagamento    char(1)           NULL,
    vl_total          numeric(10, 2)    NULL,
    data              date              NULL,
    cod_forncedor     int               NULL,
    CONSTRAINT PK6_1 PRIMARY KEY  (codigo)
);




/* 
 * TABLE: compra_produto 
 */

CREATE TABLE compra_produto(
    cod_produto    int               NOT NULL,
    cod_cor        int               NOT NULL,
    cod_tamanho    int               NOT NULL,
    cod_compra     int               NOT NULL,
    preco          numeric(10, 2)    NOT NULL,
    qtde           int               NULL,
    CONSTRAINT PK49 PRIMARY KEY  (cod_produto, cod_cor, cod_tamanho, cod_compra)
);
/* 
 * TABLE: cor 
 */

CREATE TABLE cor(
    codigo       Serial not null,
    descricao    varchar(50)    NOT NULL,
    CONSTRAINT PK38 PRIMARY KEY  (codigo)
);
/* 
 * TABLE: empresa 
 */

CREATE TABLE empresa(
    codigo        Serial not null,
    email         varchar(200)     NULL,
    nome          varchar(200)     NULL,
    razao         varchar(200)     NULL,
    endereco      varchar(200)     NULL,
    cep           varchar(9)       NULL,
    telefone      varchar(15)      NULL,
    logo          varchar(1000)    NULL,
    end_numero    varchar(20)      NULL,
    documento     varchar(15)      NULL,
    bairro        varchar(60)      NULL,
    cod_cidade    int              NOT NULL,
    CONSTRAINT PK35 PRIMARY KEY  (codigo)
);


CREATE TABLE estoque(
    tipo           char(1)    NULL,
    quantidade     int not    NULL,
    origem         varchar(20)  not   NULL,
    estoque        int   not    NULL,
    cod_produto    int         NOT NULL,
    cod_cor        int         NOT NULL,
    cod_tamanho    int         NOT NULL,
    CONSTRAINT PK42 PRIMARY KEY  (cod_produto, cod_cor, cod_tamanho)
);


CREATE TABLE formularios(
    codigo    Serial not null,
    nome      varchar(100)    NULL,
    CONSTRAINT PK22 PRIMARY KEY  (codigo)
);


CREATE TABLE Fornecedor(
    codigo         Serial not null,
    dt_exclusao    date            NULL,
    email          varchar(200)    NULL,
    contato        varchar(50)     NULL,
    cep            varchar(9)      NULL,
    telefone       varchar(100)    NULL,
    end_numero     varchar(10)     NULL,
    nome           varchar(200)    NULL,
    tipo           char(1)         NULL,
    documento      varchar(15)     NULL,
    endereco       char(200)       NULL,
    bairro         varchar(60)     NULL,
    cod_cidade     int             NOT NULL,
    CONSTRAINT PK2_2 PRIMARY KEY  (codigo)
);


CREATE TABLE Funcioanario(
    codigo         Serial not null,
    dt_exclusao    date            NULL,
    sexo           char(1)         NOT NULL,
    email          varchar(200)    NULL,
    cep            varchar(9)      NULL,
    telefone       varchar(100)    NULL,
    end_numero     varchar(10)     NULL,
    nome           varchar(200)    NULL,
    tipo           char(1)         NULL,
    documento      varchar(15)     NULL,
    endereco       char(200)       NULL,
    bairro         varchar(60)     NULL,
    cod_cidade     int             NOT NULL,
    CONSTRAINT PK2_1 PRIMARY KEY  (codigo)
);


CREATE TABLE insumo(
    cod_insumo         int    NOT NULL,
    cod_cor_insumo     int    NOT NULL,
    cod_tam_insumo     int    NOT NULL,
    cod_produto        int    NOT NULL,
    cod_cor_produto    int    NOT NULL,
    cod_tam_produto    int    NOT NULL,
    qtde               int    NULL,
    CONSTRAINT PK50 PRIMARY KEY  (cod_cor_insumo, cod_tam_insumo, cod_produto, cod_insumo, cod_cor_produto, cod_tam_produto)
);

CREATE TABLE pagar(
    codigo            Serial not null,
    dt_exclusao       date              NULL,
    vl_encargos       numeric(10, 2)    NULL,
    dt_vencimento     date              NULL,
    vl_pago           numeric(10, 2)    NULL,
    vl_total          numeric(10, 2)    NULL,
    dt_pagamento      date              NULL,
    cod_fornecedor    int               NOT NULL,
    cod_compra        int               NULL,
    CONSTRAINT PK11 PRIMARY KEY  (codigo)
);

CREATE TABLE parcela_compra(
    codigo         Serial not null,
    num_parcela    numeric(10, 0)    NULL,
    valor          numeric(10, 2)    NULL,
    vencimento     date              NULL,
    cod_venda      int               NOT NULL,
    cod_compra     int               NOT NULL,
    CONSTRAINT PK17_1 PRIMARY KEY  (codigo)
);

CREATE TABLE parcela_venda(
    codigo         Serial not null,
    num_parcela    numeric(10, 0)    NULL,
    valor          numeric(10, 2)    NULL,
    vencimento     date              NULL,
    cod_venda      int               NOT NULL,
    CONSTRAINT PK17 PRIMARY KEY  (codigo)
);

CREATE TABLE producao(
    codigo            Serial not null,
    data              char(10)          NULL,
    dt_exclusao       char(10)          NULL,
    status            char(10)          NULL,
    data_conclusao    char(10)          NULL,
    CONSTRAINT PK32 PRIMARY KEY  (codigo)
);


CREATE TABLE producao_produto(
    cod_producao    serial  NOT NULL,
    cod_produto     int               NOT NULL,
    cod_tamanho     int               NOT NULL,
    cod_cor         int               NOT NULL,
    qtde            numeric(10, 0)    NULL,
    CONSTRAINT PK48 PRIMARY KEY  (cod_producao, cod_produto, cod_tamanho, cod_cor)
);


/* 
 * TABLE: Produto 
 */

CREATE TABLE Produto(
    codigo         Serial not null,
    dt_exclusao    date              NULL,
    nome           varchar(200)      NULL,
    preco          numeric(10, 2)    NULL,
    estoque        numeric(10, 0)    NULL,
    CONSTRAINT PK20 PRIMARY KEY  (codigo)
);



CREATE TABLE produto_venda(
    cod_produto    int               NOT NULL,
    cod_cor        int               NOT NULL,
    cod_tamanho    int               NOT NULL,
    qtde           int               NULL,
    valor          numeric(10, 2)    NULL,
    CONSTRAINT PK52 PRIMARY KEY  (cod_produto, cod_tamanho, cod_cor)
);


CREATE TABLE receber(
    codigo           Serial not null,
    dt_exclusao      date              NULL,
    vl_encargos      numeric(10, 2)    NULL,
    dt_vencimento    date              NULL,
    vl_pago          numeric(10, 2)    NULL,
    vl_total         numeric(10, 2)    NULL,
    dt_pagamento     date              NULL,
    cod_cliente      int               NOT NULL,
    cod_venda        int               NULL,
    CONSTRAINT PK11_1 PRIMARY KEY  (codigo)
);


CREATE TABLE tamanho(
    codigo        Serial not null,
    descricao     varchar(20)    NOT NULL,
    abreviacao    varchar(5)     NOT NULL,
    CONSTRAINT PK39 PRIMARY KEY  (codigo)
);


/* 
 * TABLE: UF 
 */

CREATE TABLE UF(
    sigla    char(2)        NOT NULL,
    nome     varchar(50)    NULL,
    CONSTRAINT PK4 PRIMARY KEY  (sigla)
);

CREATE TABLE Usuario(
    codigo          Serial not null,
    tipo_usuario    char(1)         NULL,
    dt_exclusao     date            NULL,
    usuario         varchar(100)    NULL,
    senha           varchar(100)    NULL,
    CONSTRAINT PK1 PRIMARY KEY  (codigo)
);

/* 
 * TABLE: Venda 
 */

CREATE TABLE Venda(
    codigo             Serial not null,
    dt_exclusao        char(10)          NULL,
    tipo_pagamento     char(1)           NULL,
    vl_total           numeric(10, 2)    NULL,
    data               date              NULL,
    cod_cliente        int               NOT NULL,
    cod_producao       numeric(10, 0)    NOT NULL,
    cod_funcionario    int               NOT NULL,
    CONSTRAINT PK6 PRIMARY KEY  (codigo)
);




ALTER TABLE acesso ADD CONSTRAINT RefUsuario40 
    FOREIGN KEY (cod_usuario)
    REFERENCES Usuario(codigo)
;

ALTER TABLE acesso ADD CONSTRAINT Refformularios41 
    FOREIGN KEY (cod_formulario)
    REFERENCES formularios(codigo)


;
ALTER TABLE Cidade ADD CONSTRAINT RefUF6 
    FOREIGN KEY (sigla)
    REFERENCES UF(sigla)



;
ALTER TABLE Cliente ADD CONSTRAINT RefCidade8 
    FOREIGN KEY (cod_cidade)
    REFERENCES Cidade(codigo)

;

ALTER TABLE Compra ADD CONSTRAINT RefFornecedor18 
    FOREIGN KEY (cod_forncedor)
    REFERENCES Fornecedor(codigo)

;

ALTER TABLE compra_produto ADD CONSTRAINT RefProduto85 
    FOREIGN KEY (cod_produto)
    REFERENCES Produto(codigo)

;
ALTER TABLE compra_produto ADD CONSTRAINT Refcor86 
    FOREIGN KEY (cod_cor)
    REFERENCES cor(codigo)
;

ALTER TABLE compra_produto ADD CONSTRAINT Reftamanho89 
    FOREIGN KEY (cod_tamanho)
    REFERENCES tamanho(codigo)

;
ALTER TABLE compra_produto ADD CONSTRAINT RefCompra90 
    FOREIGN KEY (cod_compra)
    REFERENCES Compra(codigo)


;
ALTER TABLE empresa ADD CONSTRAINT RefCidade64 
    FOREIGN KEY (cod_cidade)
    REFERENCES Cidade(codigo)

;

ALTER TABLE estoque ADD CONSTRAINT RefProduto71 
    FOREIGN KEY (cod_produto)
    REFERENCES Produto(codigo)

;
ALTER TABLE estoque ADD CONSTRAINT Refcor91 
    FOREIGN KEY (cod_cor)
    REFERENCES cor(codigo)

;
ALTER TABLE estoque ADD CONSTRAINT Reftamanho92 
    FOREIGN KEY (cod_tamanho)
    REFERENCES tamanho(codigo)



;
ALTER TABLE Fornecedor ADD CONSTRAINT RefCidade9 
    FOREIGN KEY (cod_cidade)
    REFERENCES Cidade(codigo)



;
ALTER TABLE Funcioanario ADD CONSTRAINT RefCidade7 
    FOREIGN KEY (cod_cidade)
    REFERENCES Cidade(codigo)


;
ALTER TABLE insumo ADD CONSTRAINT Refcor94 
    FOREIGN KEY (cod_cor_insumo)
    REFERENCES cor(codigo)
;

ALTER TABLE insumo ADD CONSTRAINT Reftamanho95 
    FOREIGN KEY (cod_tam_insumo)
    REFERENCES tamanho(codigo)

;
ALTER TABLE insumo ADD CONSTRAINT RefProduto96 
    FOREIGN KEY (cod_produto)
    REFERENCES Produto(codigo)

;
ALTER TABLE insumo ADD CONSTRAINT RefProduto97 
    FOREIGN KEY (cod_insumo)
    REFERENCES Produto(codigo)

;
ALTER TABLE insumo ADD CONSTRAINT Refcor98 
    FOREIGN KEY (cod_cor_produto)
    REFERENCES cor(codigo)

;
ALTER TABLE insumo ADD CONSTRAINT Reftamanho99 
    FOREIGN KEY (cod_tam_produto)
    REFERENCES tamanho(codigo)


;

ALTER TABLE pagar ADD CONSTRAINT RefFornecedor16 
    FOREIGN KEY (cod_fornecedor)
    REFERENCES Fornecedor(codigo)
;

ALTER TABLE pagar ADD CONSTRAINT RefCompra105 
    FOREIGN KEY (cod_compra)
    REFERENCES Compra(codigo)



;
ALTER TABLE parcela_compra ADD CONSTRAINT RefCompra28 
    FOREIGN KEY (cod_compra)
    REFERENCES Compra(codigo)


;

ALTER TABLE parcela_venda ADD CONSTRAINT RefVenda11 
    FOREIGN KEY (cod_venda)
    REFERENCES Venda(codigo)



;

ALTER TABLE producao_produto ADD CONSTRAINT Refproducao79 
    FOREIGN KEY (cod_producao)
    REFERENCES producao(codigo)
;

ALTER TABLE producao_produto ADD CONSTRAINT RefProduto80 
    FOREIGN KEY (cod_produto)
    REFERENCES Produto(codigo)

;
ALTER TABLE producao_produto ADD CONSTRAINT Reftamanho81 
    FOREIGN KEY (cod_tamanho)
    REFERENCES tamanho(codigo)

;
ALTER TABLE producao_produto ADD CONSTRAINT Refcor83 
    FOREIGN KEY (cod_cor)
    REFERENCES cor(codigo)


;
ALTER TABLE produto_venda ADD CONSTRAINT RefProduto101 
    FOREIGN KEY (cod_produto)
    REFERENCES Produto(codigo)

;
ALTER TABLE produto_venda ADD CONSTRAINT Reftamanho102 
    FOREIGN KEY (cod_tamanho)
    REFERENCES tamanho(codigo)

;
ALTER TABLE produto_venda ADD CONSTRAINT Refcor104 
    FOREIGN KEY (cod_cor)
    REFERENCES cor(codigo)


;
ALTER TABLE receber ADD CONSTRAINT RefVenda66 
    FOREIGN KEY (cod_venda)
    REFERENCES Venda(codigo)
;

ALTER TABLE receber ADD CONSTRAINT RefCliente15 
    FOREIGN KEY (cod_cliente)
    REFERENCES Cliente(codigo)

;

ALTER TABLE Venda ADD CONSTRAINT Refproducao62 
    FOREIGN KEY (cod_producao)
    REFERENCES producao(codigo);


ALTER TABLE Venda ADD CONSTRAINT RefFuncioanario63 
    FOREIGN KEY (cod_funcionario)
    REFERENCES Funcioanario(codigo)
;

ALTER TABLE Venda ADD CONSTRAINT RefCliente14 
    FOREIGN KEY (cod_cliente)
    REFERENCES Cliente(codigo)
;


