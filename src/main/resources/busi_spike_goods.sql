create table `busi_spike_goods`(
  `id` bigint(20) not null auto_increment comment '秒杀的商品表',
  `goods_id` bigint(20) default null comment '商品ID',
  `spike_price` decimal(10,2) default '0.00' comment '秒杀价',
  `stock_count` int(11) default null comment '库存数量',
  `start_date` datetime default null comment '秒杀开始时间',
  `end_date` datetime default null comment '秒杀结束时间',
  primary key (`id`)
)engine =InnoDB auto_increment = 3 default charset = utf8mb4;

insert into busi_spike_goods(goods_id, spike_price, stock_count, start_date, end_date)
values(1,0.01,10,now(),adddate(now(),interval 10 minute))
