CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE persons (
                         id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         name VARCHAR(255) NOT NULL,
                         color VARCHAR(7) NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         version BIGINT DEFAULT 0
);

-- Índices para melhorar performance
CREATE INDEX idx_persons_name ON persons(name);
CREATE INDEX idx_persons_color ON persons(color);
CREATE INDEX idx_persons_created_at ON persons(created_at);

-- Trigger para atualizar automaticamente o updated_at
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_persons_updated_at
    BEFORE UPDATE ON persons
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Comentários nas colunas
COMMENT ON TABLE persons IS 'Tabela para armazenar informações das pessoas';
COMMENT ON COLUMN persons.id IS 'Identificador único da pessoa';
COMMENT ON COLUMN persons.name IS 'Nome da pessoa';
COMMENT ON COLUMN persons.color IS 'Cor em formato hexadecimal para identificação visual';
COMMENT ON COLUMN persons.created_at IS 'Data e hora de criação do registro';
COMMENT ON COLUMN persons.updated_at IS 'Data e hora da última atualização do registro';
COMMENT ON COLUMN persons.version IS 'Versão do registro para controle de concorrência otimista';