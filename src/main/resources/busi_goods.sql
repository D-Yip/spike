create table `busi_goods` (
  `id` bigint(20) not null auto_increment comment '商品ID',
  `goods_name` varchar(16) default null comment '商品名称',
  `goods_title` varchar(64) default null comment '商品标题',
  `goods_img` varchar(64) default null comment '商品的图片',
  `goods_detail` longtext comment '商品的详细介绍',
  `goods_price` decimal(10,2) default '0.00' comment '商品单价',
  `goods_stock` int(11) default '0' comment '商品库存，-1表示没有限制',
  primary key (`id`)
)ENGINE = InnoDB auto_increment=3 default charset =utf8mb4;

insert into busi_goods(goods_name,goods_title,goods_detail,goods_price,goods_stock)
values('IPhone X','IPhone X 64G','IPhone X 64G全网通',8000,100)
insert into busi_goods(goods_name,goods_title,goods_detail,goods_price,goods_stock)
values('MacBook Pro','MacBook Pro 128G','MacBook Pro 128G超级本',18000,100)
