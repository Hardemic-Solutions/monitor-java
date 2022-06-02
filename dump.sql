



-- create a table
CREATE TABLE tb_computadores (
    id_computador int PRIMARY KEY auto_increment,
    hostname varchar(20),
    patrimonio varchar(20)
);

-- create a table
CREATE TABLE tb_salas (
    id_sala int PRIMARY KEY auto_increment,
    nomeSala varchar(20)
);

-- create a table
CREATE TABLE tb_hard_computadores (
    id_hard int PRIMARY KEY auto_increment,
    ram int,
    armazenamento int,
    gpu int,
    fk_computador int,
    FOREIGN KEY(fk_computador) REFERENCES tb_computadores(id_computador),
    dataHardComputador datetime DEFAULT CURRENT_TIMESTAMP,
    SO varchar(20)
);

Create table tb_chamado(
  id_chamado int primary key auto_increment,
  fk_computador int,
  FOREIGN KEY(fk_computador) REFERENCES tb_computadores(id_computador),
  descricao varchar(45),
  data_chamado datetime DEFAULT CURRENT_TIMESTAMP
);

Create table tb_logs (
  id_log int PRIMARY KEY AUTO_INCREMENT,
  fk_computador int,
  ram decimal(10, 2),
  disco decimal(10, 2),
  gpu int,
  cpu decimal(10, 2),
  rede decimal(10, 2),
  temperatura decimal(10, 2),
  data_log datetime DEFAULT CURRENT_TIMESTAMP
);

Create table tb_alerta(
  id_alerta int primary key auto_increment,
  nome_alerta varchar(45),
  fk_log int, 
  FOREIGN KEY(fk_log) REFERENCES tb_logs(id_log),
  fk_chamado int,
  FOREIGN KEY(fk_chamado) REFERENCES tb_chamado(id_chamado),
  data_alerta date
);

Create table tb_empresa(
  id_empresa int auto_increment primary key,
  Nome_empresa varchar(60),
  cnpj int
);

Create table tb_usuario(
  id_usuario int auto_increment primary key,
  nome_usuario varchar(60),
  permissao varchar(60),
  fk_empresa int,
  FOREIGN KEY(fk_empresa) REFERENCES tb_empresa(id_empresa),
  senha varchar(50),
  email varchar(45)
);

Create table tb_configs(
  id_config int auto_increment primary key,
  nome_config varchar(60),
  valor int,
  fk_empresa int,
  FOREIGN KEY(fk_empresa) REFERENCES tb_empresa(id_empresa),
  situacao bit
);

Create table tb_rotinas(
  id_rotina int auto_increment primary key,
  nome_rotina varchar(60),
  caminho_rotina varchar (60),
  fk_empresa int,
  FOREIGN KEY(fk_empresa) REFERENCES tb_empresa(id_empresa),
  descricao varchar(255) 
);





