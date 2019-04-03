create table `busi_spike_order`(
  `id` bigint(20) not null auto_increment,
  `user_id` bigint(20) default null comment '用户ID',
  `order_id` bigint(20) default null comment '订单ID',
  `goods_id` bigint(20) default null comment '商品ID',
  primary key (`id`)
)engine = InnoDB auto_increment=3 default charset = utf8mb4;

alter table busi_spike_order add unique u_uid_gid(`user_id`,`goods_id`)
