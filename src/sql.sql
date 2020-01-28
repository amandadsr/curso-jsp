

CREATE TABLE usuario
(
id bigint NOT NULL DEFAULT nextval('usersequence'::regclass),
login character varying(500),
senha character varying(20),
CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH(
OIDS=FALSE
);

ALTER TABLE usuario
OWNER TO postgres;

ALTER TABLE usuario ADD COLUMN nome character varying(500);

ALTER TABLE usuario ADD COLUMN fone character varying(500);

INSERT INTO usuario(id, login, senha)
VALUES (1, 'alex', 'alex');

INSERT INTO usuario(id, login, senha)
VALUES (2, 'admin', 'admin');

