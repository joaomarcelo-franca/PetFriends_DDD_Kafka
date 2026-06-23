-- Produto 1: Ração Golden — precisa de pelo menos 2 KG
INSERT INTO item_estoque (status, produto_id, valor, unidade, quantidade_reservada_valor, quantidade_reservada_unidade)
VALUES ('COM_ESTOQUE', 1, 10, 'KG', 0, 'KG');

-- Produto 2: Shampoo Pet — precisa de pelo menos 1 UN
INSERT INTO item_estoque (status, produto_id, valor, unidade, quantidade_reservada_valor, quantidade_reservada_unidade)
VALUES ('COM_ESTOQUE', 2, 5, 'UN', 0, 'UN');