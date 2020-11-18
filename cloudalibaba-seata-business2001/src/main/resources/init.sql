-- 初始化库存模拟数据
delete from seata_demo.storage_tbl;
delete from seata_demo.order_tbl;
delete from seata_demo.account_tbl;
INSERT INTO seata_demo.storage_tbl (commodity_code, count) VALUES ('product-1', 10);
INSERT INTO seata_demo.account_tbl (user_id, money) VALUES ('david001', 100);