INSERT INTO pedido (id, cliente_id, cliente_nome, rua, bairro, cidade, estado, cep, status, data_criacao)
VALUES ('pedido-teste-001', 'cliente-001', 'João da Silva', 'Rua das Flores', 'Centro', 'Rio de Janeiro', 'RJ', '20040-020', 'FECHADO', '2026-06-23T10:00:00');

INSERT INTO pedido_itens (pedido_id, produto_id, nome_produto, quantidade, unidade)
VALUES ('pedido-teste-001', '1', 'Ração Golden', 2, 'KG');

INSERT INTO pedido_itens (pedido_id, produto_id, nome_produto, quantidade, unidade)
VALUES ('pedido-teste-001', '2', 'Shampoo Pet', 1, 'UN');
