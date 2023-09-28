create database if not exists tha103_g2_db;

use tha103_g2_db;

drop table if exists chat_room_mem;
drop table if exists chat_msg;
drop table if exists chat_room;
drop table if exists notify;
drop table if exists room_photo;
drop table if exists room_stock;
drop table if exists room_collect;
drop table if exists room_thumbup;
drop table if exists room_ord;
drop table if exists room;
drop table if exists itinerary;
drop table if exists trip_collect;
drop table if exists trip_thumbup;
drop table if exists trip_ord;
drop table if exists trip_photo;
drop table if exists trip;
drop table if exists planning;
drop table if exists scene;
drop table if exists adm_meb;
drop table if exists company;
drop table if exists hotel_info;
drop table if exists consumer;

CREATE TABLE consumer (
  cus_id 		int NOT NULL AUTO_INCREMENT,
  cus_name 		varchar(10) ,
  cus_account 	varchar(30) ,
  cus_password 	varchar(30) ,
  cus_mail 		varchar(35) ,
  cus_phone 	char(10) ,
  cus_address 	varchar(30) ,
  cus_sex 		int ,
  cus_photo 	longblob,
  PRIMARY KEY (cus_id)
);

CREATE TABLE planning (
	plan_id     	INT AUTO_INCREMENT NOT NULL,
	cus_id    		int,
	plan_name       varchar(10),
	CONSTRAINT planning_cus_id_FK FOREIGN KEY (cus_id) REFERENCES consumer (cus_id),
	CONSTRAINT planning_plan_id_PK PRIMARY KEY (plan_id)
);

CREATE TABLE adm_meb (
  adm_id 		int NOT NULL AUTO_INCREMENT,
  adm_name 		varchar(10) ,
  adm_account 	varchar(35) ,
  adm_password 	varchar(35) ,
  PRIMARY KEY (adm_id)
) ;
CREATE TABLE `hotel_info` (
  `hotel_info_id` 	int not null auto_increment primary key,
  `restaurant` 		boolean ,
  `room_service` 	boolean,
  `allday_counter` 	boolean,
  `spa` 			boolean,
  `gym` 			boolean,
  `garden` 			boolean,
  `terrace` 		boolean,
  `no_smoking` 		boolean,
  `freewifi` 		boolean,
  `heater` 			boolean,
  `beach` 			boolean,
  `pool` 			boolean,
  `chargingstation` boolean,
  `parking` 		boolean
) ;

CREATE TABLE `company` (
  `comp_id` 		int not null auto_increment primary key,
  `hotel_info_id` 	int ,
  `comp_type` 		int NOT NULL,
  `comp_name` 		varchar(25) ,
  `comp_address` 	varchar(25) ,
  `comp_phone` 		char(10) ,
  `principal_name` 	varchar(10) ,
  `principal_phone` varchar(10) ,
  `comp_account` 	varchar(35) ,
  `comp_password` 	varchar(35) ,
  `comp_mail` 		varchar(35) ,
  `comp_photo` 		longblob,
  `check_status` 	int ,
  constraint comp_hotel_info_id foreign key (hotel_info_id) references hotel_info(hotel_info_id)
) ;

CREATE TABLE room (
	room_id		  	int auto_increment NOT NULL,
	comp_id 	  	int,
	room_type     	int,
    room_name		varchar(15),
    beds 			int,
    price 			double,
    intro 			longtext,
    room_status 	int,
    tissue 			boolean,
    shower 			boolean,
    bathroom 		boolean,
    dryer 			boolean,
    tub 			boolean,
    freetoiletries 	boolean,
    flushseat 		boolean,
    slippers 		boolean,
    bathrobe 		boolean,
    spatub 			boolean,
    electric_kettle boolean,
	constraint room_primary_key primary key (room_id),
    constraint room_comp_id_FK foreign key (comp_id) references company (comp_id)
);

CREATE TABLE room_stock (
	room_stock_id 	int auto_increment not null,
	room_id 	  	int,
	stock_date 		date,
    stock 			int,
	
	constraint room_stock_room_id_FK foreign key (room_id) references room (room_id),
	constraint room_stock_PRIMARY_KEY primary key (room_stock_id)
);

create table room_photo (
	room_photo_id 	int auto_increment not null,
    room_id 		int,
    photo 			longblob,
    upload_time 	timestamp,
	constraint room_photo_room_id_FK foreign key (room_id) references room (room_id),
	constraint room_photo_PRIMARY_KEY primary key (room_photo_id)
);
create table room_ord(
	room_ord_id 		int auto_increment not null,
    plan_id 			int,
    room_id 			int,
    cus_id 				int,
    amount 				int,
    total_price 		double,
    commission 			double,
    people 				int,
    check_in_time 		timestamp,
    check_out_time 		timestamp,
    ord_status 			int,
    ord_time 			timestamp,
    remark 				varchar(100),
    score 				int,
    comments 			longtext,
    comments_time 		timestamp,
    constraint room_ord_PRIMARY_KEY primary key (room_ord_id),
    constraint room_ord_plan_id_FK  foreign key (plan_id) references planning (plan_id),
	constraint room_ord_room_id_FK  foreign key (room_id) references room (room_id),
    constraint room_ord_cus_id_FK  foreign key (cus_id) references consumer (cus_id)
);

create table `scene`(
	`scene_id` int not null auto_increment primary key,
    `scene_name` varchar(50),
	`open_time` longtext,
	`ticket_price` longtext,
    `trans_info` longtext,
    `parking` longtext,
	`address` longtext,
    `lon`	  decimal(20,10),
    `lat`	  decimal(20,10),	
	`feature` longtext,
	`picture` varchar(200)
);

create table `trip`(
	`trip_id` int not null auto_increment primary key,
	`comp_id` int,
	`trip_name` varchar(25),
	`amount` int,
	`price` int,
	`people` int,
	`start_time` datetime,
	`end_time` datetime,
	`content` longtext,
	`state` int,
	`taipei_city` boolean,
	`newTaipei_city` boolean,
	`taoyuan_city` boolean,
	`taichung_city` boolean,
	`tainan_city` boolean,
	`kaohsiung_city` boolean,
	`hsinchu_county` boolean,
	`miaoli_county` boolean,
	`changhua_county` boolean,
	`nantou_county` boolean,
	`yunlin_county` boolean,
	`chiayi_county` boolean,
	`pingtung_county` boolean,
	`yilan_city` boolean,
	`hualien_city` boolean,
	`taitung_county` boolean,
	`kinmen_county` boolean,
	`lienchiang_county` boolean,
	`keelung_city` boolean,
	`hsinchu_city` boolean,
	`chiayi_city` boolean,
	`penghu_county` boolean,
    constraint trip_comp_id foreign key (comp_id) REFERENCES company(comp_id)
);
CREATE TABLE trip_photo (
	trip_photo_id     	INT AUTO_INCREMENT NOT NULL,
	trip_id     		int,
	photo       		longblob,
    upload_time     	timestamp,
	CONSTRAINT trip_photo_trip_id_FK FOREIGN KEY (trip_id) REFERENCES trip (trip_id),
	CONSTRAINT trip_photo_trip_photo_id_PK PRIMARY KEY (trip_photo_id)
);

create table `itinerary`(
	itinerary_id int not null auto_increment primary key,
	trip_id int ,
	scene_id int,
	begin_time datetime,
    constraint itinerary_trip_id foreign key (trip_id) references trip(trip_id),
	constraint itinerary_scene_id foreign key (scene_id) references scene(scene_id)
);
CREATE TABLE trip_ord (
	trip_ord_id		int auto_increment not null,
	trip_id			int,
	plan_id			int,
    cus_id			int,
    amount			int,
    total_price		decimal(18,3),
    commission		decimal(18,3),
    ord_status 		int,
    ord_time		datetime,
    remark			varchar(50),
    score			int,
    comments		longtext,
    comments_time	datetime,
	CONSTRAINT TRIP_ORD_TRIP_ID_FK FOREIGN KEY (trip_id) REFERENCES trip (trip_id),
	CONSTRAINT TRIP_ORD_PLAN_ID_FK FOREIGN KEY (plan_id) REFERENCES planning (plan_id),
	CONSTRAINT TRIP_ORD_CUS_ID_FK FOREIGN KEY (cus_id) REFERENCES consumer (cus_id),
    CONSTRAINT TRIP_ORD_PRIMARY_KEY PRIMARY KEY (trip_ord_id)
) ;
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
 			VALUES (null, null, null, 5, 60000.011, 6000.012, 0, now(), "remark_1", 9, "comments_1", null);
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
 			VALUES (null, null, null, 4, 40000.021, 4000.022, 1, now(), "remark_2", 4, "comments_2", null);
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
 			VALUES (null, null, null, 3, 50000.031, 5000.032, 2, now(), "remark_3", 1, "comments_3", null);
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
 			VALUES (null, null, null, 2, 10000.041, 1000.042, 0, now(), "remark_4", 6, "comments_4", null);
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
 			VALUES (null, null, null, 1, 90000.051, 9000.052, 1, now(), "remark_5", 8, "comments_5", null);

-- 	insert into trip_ord (trip_id,plan_id,cus_id,amount,total_price,commission,order_status,ord_time, remark,score,chat_msg,comment_time)
-- 			VALUES (122, 414, 325, 5553, 48719.0, 21321, 0, now(), "hiihui", 10, ?, ?);
CREATE TABLE notify (
	notify_id     	INT AUTO_INCREMENT NOT NULL,
	cus_id     		int,
	comp_id     	int,
    room_ord_id     int,
    trip_ord_id     int,
    contents     	varchar(50),
    state     		boolean,
	notify_time     timestamp,
	CONSTRAINT cus_id_FK FOREIGN KEY (cus_id) REFERENCES consumer (cus_id),
	CONSTRAINT comp_id_FK FOREIGN KEY (comp_id) REFERENCES company (comp_id),
	CONSTRAINT room_ord_id_FK FOREIGN KEY (room_ord_id) REFERENCES room_ord (room_ord_id),
	CONSTRAINT trip_ord_id_FK FOREIGN KEY (trip_ord_id) REFERENCES trip_ord (trip_ord_id),
    CONSTRAINT notify_id_PRIMARY_KEY PRIMARY KEY (notify_id)
);
create table room_collect(
	cus_id 			int,
    room_id 		int,
    collect_time 	datetime,
    constraint room_collect_cus_id foreign key (cus_id) REFERENCES consumer(cus_id),
    constraint room_collect_room_id foreign key (room_id) REFERENCES room(room_id),
    constraint room_collect_pk primary key (cus_id, room_id)
);


create table trip_collect(
	cus_id 			int,
    trip_id 		int,
    collect_time 	datetime,
	constraint trip_collect_cus_id foreign key (cus_id) REFERENCES consumer(cus_id),
    constraint trip_collect_room_id foreign key (trip_id) REFERENCES trip(trip_id),
    constraint trip_collect_pk primary key (cus_id, trip_id)
);

create table room_thumbup(
	room_ord_id 	int not null,
    cus_id 			int not null,
    thumbup_time	datetime,
    constraint thumbup_room_ord_id foreign key (room_ord_id) REFERENCES room_ord(room_ord_id),
    constraint thumbup_room_cus_id foreign key (cus_id) REFERENCES consumer(cus_id),
    constraint thumbup_room_pk primary key (room_ord_id, cus_id)
);

create table trip_thumbup(
	trip_ord_id 	int not null,
	cus_id 			int not null,
    thumbup_time 	datetime,
    constraint thumbup_trip_ord_id foreign key (trip_ord_id) references trip_ord(trip_ord_id),
    constraint thumbup_trip_cus_id foreign key (cus_id) references consumer(cus_id),
    constraint thumbup_trip_pk primary key (trip_ord_id, cus_id)
);