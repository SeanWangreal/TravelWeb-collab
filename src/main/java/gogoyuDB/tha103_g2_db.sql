create database if not exists tha103_g2_db;

use tha103_g2_db;


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
insert into consumer (cus_name, cus_account, cus_password, cus_mail, cus_phone, cus_address, cus_sex, cus_photo)
	VALUES ("吳一", "abca1", "a2341", "abca2341@bmail.com", 0977777771, "104台北市中山區南京東路三段219號", 0, null);
insert into consumer (cus_name, cus_account, cus_password, cus_mail, cus_phone, cus_address, cus_sex, cus_photo)
	VALUES ("吳二", "abcb1", "a2342", "abcb2342@bmail.com", 0977777772, "104台北市中山區南京東路三段220號", 1, null);
insert into consumer (cus_name, cus_account, cus_password, cus_mail, cus_phone, cus_address, cus_sex, cus_photo)
	VALUES ("吳三", "abcc1", "a2343", "abcc2343@bmail.com", 0977777773, "104台北市中山區南京東路三段221號", 0, null);
insert into consumer (cus_name, cus_account, cus_password, cus_mail, cus_phone, cus_address, cus_sex, cus_photo)
	VALUES ("吳四", "abcd1", "a2344", "abcd2344@bmail.com", 0977777774, "104台北市中山區南京東路三段222號", 1, null);
insert into consumer (cus_name, cus_account, cus_password, cus_mail, cus_phone, cus_address, cus_sex, cus_photo)
	VALUES ("吳五", "abce1", "a2345", "abce2345@bmail.com", 0977777775, "104台北市中山區南京東路三段223號", 0, null);

CREATE TABLE planning (
	plan_id     	INT AUTO_INCREMENT NOT NULL,
	cus_id    		int,
	plan_name       varchar(10),
-- 	CONSTRAINT planning_cus_id_FK FOREIGN KEY (cus_id) REFERENCES consumer (cus_id),
	CONSTRAINT planning_plan_id_PK PRIMARY KEY (plan_id)
);
insert into planning (cus_id, plan_name)
	values(1, "好玩的規劃");
insert into planning (cus_id, plan_name)
	values(2, "有趣的規劃");
insert into planning (cus_id, plan_name)
	values(3, "難忘的規劃");
insert into planning (cus_id, plan_name)
	values(4, "快樂的規劃");
insert into planning (cus_id, plan_name)
	values(5, "刺激的規劃");

CREATE TABLE adm_meb (
  adm_id 		int NOT NULL AUTO_INCREMENT,
  adm_name 		varchar(10) ,
  adm_account 	varchar(35) ,
  adm_password 	varchar(35) ,
  PRIMARY KEY (adm_id)
) ;
insert into adm_meb (adm_name, adm_account, adm_password)
	VALUES ("冠翔", "a12345", "aabb1122" );
insert into adm_meb (adm_name, adm_account, adm_password)
	VALUES ("芝華", "b12345", "aacc1122" );
insert into adm_meb (adm_name, adm_account, adm_password)
	VALUES ("廷恩", "c12345", "aadd1122" );
insert into adm_meb (adm_name, adm_account, adm_password)
	VALUES ("詩翰", "d12345", "aaee1122" );
insert into adm_meb (adm_name, adm_account, adm_password)
	VALUES ("廷晏", "e12345", "aaff1122" );

CREATE TABLE `hotel_info` (
    `hotel_info_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `restaurant` BOOLEAN,
    `room_service` BOOLEAN,
    `allday_counter` BOOLEAN,
    `spa` BOOLEAN,
    `gym` BOOLEAN,
    `garden` BOOLEAN,
    `terrace` BOOLEAN,
    `no_smoking` BOOLEAN,
    `freewifi` BOOLEAN,
    `heater` BOOLEAN,
    `beach` BOOLEAN,
    `pool` BOOLEAN,
    `chargingstation` BOOLEAN,
    `parking` BOOLEAN
);
insert into hotel_info (restaurant, room_service, allday_counter, spa, gym, garden, terrace, no_smoking, freewifi, heater, beach, pool, chargingstation, parking)
		VALUES (false, true, true, true, true, true, true, true, true, true, true, true, true, true);
insert into hotel_info (restaurant, room_service, allday_counter, spa, gym, garden, terrace, no_smoking, freewifi, heater, beach, pool, chargingstation, parking)
		VALUES (false, false, true, true, true, true, true, true, true, true, true, true, true, true);
insert into hotel_info (restaurant, room_service, allday_counter, spa, gym, garden, terrace, no_smoking, freewifi, heater, beach, pool, chargingstation, parking)
		VALUES (false, false, false, true, true, true, true, true, true, true, true, true, true, true);
insert into hotel_info (restaurant, room_service, allday_counter, spa, gym, garden, terrace, no_smoking, freewifi, heater, beach, pool, chargingstation, parking)
		VALUES (false, false, false, false, true, true, true, true, true, true, true, true, true, true);
insert into hotel_info (restaurant, room_service, allday_counter, spa, gym, garden, terrace, no_smoking, freewifi, heater, beach, pool, chargingstation, parking)
		VALUES (false, false, false, false, false, true, true, true, true, true, true, true, true, true);
insert into hotel_info (restaurant, room_service, allday_counter, spa, gym, garden, terrace, no_smoking, freewifi, heater, beach, pool, chargingstation, parking)
		VALUES (false, false, false, false, false, false, true, true, true, true, true, true, true, true);
insert into hotel_info (restaurant, room_service, allday_counter, spa, gym, garden, terrace, no_smoking, freewifi, heater, beach, pool, chargingstation, parking)
		VALUES (false, false, false, false, false, false, false, true, true, true, true, true, true, true);

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
  `check_status` 	int
  -- ,
--    constraint comp_hotel_info_id foreign key (hotel_info_id) references hotel_info(hotel_info_id)
) ;
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (1, 0, "JR東日本大飯店", "104台北市中山區南京東路三段133號", "0999999990", "王一", "0988888880", "abcda1", "a12341", "abcd12341@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (2, 0, "台北富驛時尚酒店 南京東路館", "104台北市中山區南京東路三段131號", "0999999991", "王二", "0988888881", "abcdb1", "a12342", "abcd12342@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (3, 0, "兄弟大飯店", "105台北市松山區南京東路三段255號", "0999999992", "王三", "0988888882", "abcdc1", "a12343", "abcd12343@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (4, 0, "台北商旅慶城館", "105台北市松山區慶城街12號", "0999999993", "王四", "0988888883", "abcdd1", "a12344", "abcd12344@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (5, 0, "Simple+ Hotel", "105台北市松山區敦化北路4巷52號", "0999999994", "王五", "0988888884", "abcde1", "a12345", "abcd12345@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (null, 1, "皇家國際運通旅行社", "10488台北市中山區南京東路三段189號", "0999999995", "王六", "0988888885", "abcdf1", "a12346", "abcd12346@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (null, 1, "志洋旅行社股份有限公司", "10491台北市中山區南京東路三段210號10樓", "0999999996", "王七", "0988888886", "abcdg1", "a12347", "abcd12347@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (null, 1, "太平洋旅行社", "10491台北市中山區南京東路三段168號5樓", "0999999997", "王八", "0988888887", "abcdh1", "a12348", "abcd12348@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (null, 1, "鈦美旅行社", "10491台北市中山區南京東路三段129號3樓", "0999999998", "王九", "0988888888", "abcdi1", "a12349", "abcd12349@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (null, 1, "環遊國際旅行社", "104台北市中山區復興北路五十八號八樓之一", "0999999999", "王十", "0988888889", "abcdj1", "a12340", "abcd12340@amail.com", null, 1);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (6, 0, "安盛商務旅館", "231新北市新店區二十張路31號", "0999999981", "王十一", "0988888879", "abcdk1", "a12350", "abcdk12350@amail.com", null, 0);
insert into company (hotel_info_id, comp_type, comp_name, comp_address, comp_phone, principal_name, principal_phone, comp_account, comp_password, comp_mail, comp_photo, check_status)
		VALUES (7, 0, "日暉商務旅館", "235新北市中和區景平路381號", "0999999982", "王十二", "0988888878", "abcdl1", "a12351", "abcdl12351@amail.com", null, -1);
        
CREATE TABLE room (
	room_id		  	int auto_increment NOT NULL,
	comp_id 	  	int,
	room_type     	int,
    room_name		varchar(15),
    beds 			int,
    price 			decimal(18,3),
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
    main_photo		longblob,
  --   constraint room_comp_id_FK foreign key (comp_id) references company (comp_id),
	constraint room_primary_key primary key (room_id)
);
insert into room (comp_id , room_type, room_name, beds, price, intro, room_status, tissue, shower, bathroom, dryer, tub, freetoiletries, flushseat, slippers, bathrobe, spatub, electric_kettle)
	VALUES (1, 1, "豪華單人房", 1, 1000, "加大單人床", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into room (comp_id , room_type, room_name, beds, price, intro, room_status, tissue, shower, bathroom, dryer, tub, freetoiletries, flushseat, slippers, bathrobe, spatub, electric_kettle)
    VALUES (2, 2, "豪華雙人房", 2, 2000, "兩張加大單人床", 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into room (comp_id , room_type, room_name, beds, price, intro, room_status, tissue, shower, bathroom, dryer, tub, freetoiletries, flushseat, slippers, bathrobe, spatub, electric_kettle)
    VALUES (3, 3, "豪華三人房", 2, 3000, "三張加大單人床", 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1);
insert into room (comp_id , room_type, room_name, beds, price, intro, room_status, tissue, shower, bathroom, dryer, tub, freetoiletries, flushseat, slippers, bathrobe, spatub, electric_kettle)
    VALUES (4, 4, "豪華四人房", 2, 4000, "四張加大單人床", 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1);
insert into room (comp_id , room_type, room_name, beds, price, intro, room_status, tissue, shower, bathroom, dryer, tub, freetoiletries, flushseat, slippers, bathrobe, spatub, electric_kettle)
    VALUES (5, 2, "普通雙人房", 2, 500, "一張雙人床", 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1);
    
CREATE TABLE room_stock (
	room_stock_id 	int auto_increment not null,
	room_id 	  	int,
	stock_date 		date,
    stock 			int,
	
	-- constraint room_stock_room_id_FK foreign key (room_id) references room (room_id),
	constraint room_stock_PRIMARY_KEY primary key (room_stock_id)
);
insert into room_stock (room_id, stock_date, stock)
	VALUES (1, '2023-10-20', 1);
insert into room_stock (room_id, stock_date, stock)
	VALUES (2, '2023-10-20', 2);
insert into room_stock (room_id, stock_date, stock)
	VALUES (2, '2023-10-21', 2);
insert into room_stock (room_id, stock_date, stock)
	VALUES (3, '2023-10-20', 3);
insert into room_stock (room_id, stock_date, stock)
	VALUES (3, '2023-10-21', 3);
insert into room_stock (room_id, stock_date, stock)
	VALUES (3, '2023-10-22', 3);
insert into room_stock (room_id, stock_date, stock)
	VALUES (4, '2023-10-20', 4);


create table room_photo (
	room_photo_id 	int auto_increment not null,
    room_id 		int,
    photo 			longblob,
    upload_time 	datetime default current_timestamp,
-- 	constraint room_photo_room_id_FK foreign key (room_id) references room (room_id),
	constraint room_photo_PRIMARY_KEY primary key (room_photo_id)
);
insert into room_photo (room_id, photo, upload_time)
	VALUES (1, null, "2023-10-03 12:34:56");
insert into room_photo (room_id, photo, upload_time)
	VALUES (2, null, "2023-10-03 12:34:56");
insert into room_photo (room_id, photo, upload_time)
	VALUES (3, null, "2023-10-03 12:34:56");
insert into room_photo (room_id, photo, upload_time)
	VALUES (4, null, "2023-10-03 12:34:56");
insert into room_photo (room_id, photo, upload_time)
	VALUES (5, null, "2023-10-03 12:34:56");
create table room_ord(
	room_ord_id 		int auto_increment not null,
    plan_id 			int,
    room_id 			int,
    cus_id 				int,
    amount 				int,
    total_price 		decimal(18,3),
    commission 			decimal(18,3),
    people 				int,
    check_in_time 		datetime,
    check_out_time 		datetime,
    ord_status 			int,
    ord_time 			timestamp,
    remark 				varchar(100),
    score 				int,
    comments 			longtext,
    comments_time 		datetime default current_timestamp,
   --  constraint room_ord_plan_id_FK  foreign key (plan_id) references planning (plan_id),
-- 	constraint room_ord_room_id_FK  foreign key (room_id) references room (room_id),
--     constraint room_ord_cus_id_FK  foreign key (cus_id) references consumer (cus_id),
    constraint room_ord_PRIMARY_KEY primary key (room_ord_id)
    
);
insert into room_ord (plan_id, room_id, cus_id, amount, total_price, commission, people, check_in_time, check_out_time, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (1, 1, 1, 5, 5000, 500, 5, "2023-10-5 17:14:56", "2023-10-6 11:14:56", 0, "2023-10-2 17:14:56", "幫忙保管物品1", 9, "好棒", "2023-10-10 11:14:56");
insert into room_ord (plan_id, room_id, cus_id, amount, total_price, commission, people, check_in_time, check_out_time, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (2, 2, 2, 4, 8000, 800, 8, "2023-10-5 17:24:56", "2023-10-6 11:24:56", 1, "2023-10-2 17:24:56", "幫忙保管物品2", 8, "好讚", "2023-10-10 11:24:56");
insert into room_ord (plan_id, room_id, cus_id, amount, total_price, commission, people, check_in_time, check_out_time, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (3, 3, 3, 3, 9000, 900, 9, "2023-10-5 17:34:56", "2023-10-6 11:34:56", 2, "2023-10-2 17:34:56", "幫忙保管物品3", null, null, null);
insert into room_ord (plan_id, room_id, cus_id, amount, total_price, commission, people, check_in_time, check_out_time, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (4, 4, 4, 2, 8000, 800, 8, "2023-10-5 17:44:56", "2023-10-6 11:44:56", 0, "2023-10-2 17:44:56", "幫忙保管物品4", 7, "好強", "2023-10-10 11:44:56");
insert into room_ord (plan_id, room_id, cus_id, amount, total_price, commission, people, check_in_time, check_out_time, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (5, 5, 5, 1, 500, 50, 2, "2023-10-5 17:54:56", "2023-10-6 11:54:56", 1, "2023-10-2 17:54:56", "幫忙保管物品5", 9, "好舒服", "2023-10-10 11:54:56");

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
insert into scene (scene_name, open_time, ticket_price, trans_info, parking, address, lon, lat, feature, picture)
	values("湳仔溝自行車道", "早上8點~下午5點", "40元", "公車102", "沒停車場", "新北市220板橋區環河道路", 24.997026046042937, 121.44846021667415, "河岸風景", null);
insert into scene (scene_name, open_time, ticket_price, trans_info, parking, address, lon, lat, feature, picture)
	values("萬里漁村生活館", "早上7點~下午5點", "30元", "公車101", "有停車場", "新北市207萬里區野柳里港東路167號", 25.20523201208685, 121.68918846908646, "有很多魚", null);
insert into scene (scene_name, open_time, ticket_price, trans_info, parking, address, lon, lat, feature, picture)
	values("野柳燈塔", "早上9點~下午5點", "50元", "公車103", "有停車場", "新北市207萬里區野柳燈塔", 25.213740762064287, 121.6973120996188, "看很遠", null);
insert into scene (scene_name, open_time, ticket_price, trans_info, parking, address, lon, lat, feature, picture)
	values("野柳地質公園", "開放時間為上午8:00至下午5:00夏季開放(7、8月)時間為上午9:00至下午6:00除依「天然災停止版辦公及上課作業辦法」規定新北市政府決定停止公告或上課之起止時間。", "1.全票120元 （一般遊客）2.優待票60元（持證優待）■ 學生（以具有臺灣正式學籍之學生證者為認定標準）■ 兒童6歲以上及未滿12歲者。■ 年滿65歲以上長者(需本國籍)。■ ...", "【公車資訊】1.淡水客運862號(在淡水捷運站前搭車→往基隆在野柳站下車) 約每30分鐘一班車。2.國光客運1815號(於國光客運台北車站搭車→往金山青年活動中心路經忠孝東路、台北市政府轉運站→ 在野柳站下車)約每20分鐘一班車。3.基隆客運790號(基隆火車站旁搭車→往金山在野柳站下車) 約每15分鐘一班車。4.基隆客運1068號(台灣大學搭車→往金山在野柳地質公園站...", "設有野柳地質公園停車場", "新北市207萬里區野柳村港東路167-1號", 25.20656911396514, 121.69036259510332, "擁有奇岩美石的野柳地質公園是大屯山餘脈延伸至海中的岬角受到風化、海蝕及地殼運動等作用造就了蕈狀岩、海蝕洞、豆腐石、燭狀岩及壺穴等奇景是揚名國際的天然風景名勝地。其中「女王頭」更是野柳具代表性的地標與熱門打卡點；「俏皮公主」則有著如綁著馬尾般的造型被譽為女王頭的接班人。 在野柳地質公園進入岬角地景區之前可以看到仿真的「女王頭」與「俏皮公主」還有五顏六色的奇岩怪石Q版公仔這裡有樹蔭、也不會太靠近岩石區與海岸適合小朋友在此遊憩、拍照。海岸岬角區共分為三大區域第一區有多樣且造型奇特的蕈狀岩、燭台石在這裡可以看到蕈狀岩的發育過程大小不同的蕈狀岩一字排開形成療癒可愛的景致；野柳夯點&mdash", null);
insert into scene (scene_name, open_time, ticket_price, trans_info, parking, address, lon, lat, feature, picture)
	values("浮洲藝術河濱公園", "早上10點~下午5點", "60元", "公車104", "沒停車場", "新北市220板橋區環河道路", 24.99639480354863, 121.43853679982915, "綠地，親子遊玩好去處", null);

create table `trip`(
	`trip_id` int not null auto_increment primary key,
	`comp_id` int,
	`trip_name` varchar(25),
	`amount` int,
	`price` decimal(18,3),
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
     main_photo		longblob
--  ,
--     constraint trip_comp_id foreign key (comp_id) REFERENCES company(comp_id)
);
insert into trip (comp_id, trip_name, amount, price, people, start_time, end_time, content, state, taipei_city, newtaipei_city, taoyuan_city, taichung_city, tainan_city, kaohsiung_city, hsinchu_county, miaoli_county, changhua_county, nantou_county, yunlin_county, chiayi_county, pingtung_county, yilan_city, hualien_city, taitung_county, kinmen_county, lienchiang_county, keelung_city, hsinchu_city, chiayi_city, penghu_county)
	values(6, "湳仔溝自行車道單日行", 1, 1000, 1, "2023-10-4 07:34:56", "2023-10-4 16:34:56", "總長3.5公里的湳仔溝自行車道起於亞東醫院附近的華東公園終點為湳仔溝與大漢溪的河流處。原身是位於大漢溪右岸、湳仔溝自行車道沿線的湳仔溝抽水站為提供鐵馬族休憩將部分空間改造為鐵馬驛站可沿著指標透過雙溪口防汛陸橋跨堤進入驛站內驛站提供廁所、充氣及加水等服務進入室內休息區還可以一邊休息一邊認識過去板橋湳仔溝的在地故事呢。全程沿著河的右岸綠帶一路有高聳挺拔的老樹遮蔭騎乘起來倒也輕鬆愜意。於鄰近新月橋旁「湳仔溝抽水站」內打造一座結合文化的鐵馬驛站改善站內環境及設施增加周邊引導標誌增設休憩平台及盥洗室讓民眾有回到家的感覺。此", -1, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
insert into trip (comp_id, trip_name, amount, price, people, start_time, end_time, content, state, taipei_city, newtaipei_city, taoyuan_city, taichung_city, tainan_city, kaohsiung_city, hsinchu_county, miaoli_county, changhua_county, nantou_county, yunlin_county, chiayi_county, pingtung_county, yilan_city, hualien_city, taitung_county, kinmen_county, lienchiang_county, keelung_city, hsinchu_city, chiayi_city, penghu_county)
	values(7, "萬里漁村生活館二日行", 2, 2000, 2, "2023-10-4 12:34:56", "2023-10-5 16:34:56", "很多魚", 0,  false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
insert into trip (comp_id, trip_name, amount, price, people, start_time, end_time, content, state, taipei_city, newtaipei_city, taoyuan_city, taichung_city, tainan_city, kaohsiung_city, hsinchu_county, miaoli_county, changhua_county, nantou_county, yunlin_county, chiayi_county, pingtung_county, yilan_city, hualien_city, taitung_county, kinmen_county, lienchiang_county, keelung_city, hsinchu_city, chiayi_city, penghu_county)
	values(8, "野柳燈塔二日行", 3, 3000, 3, "2023-10-4 12:34:56", "2023-10-5 16:34:56", "視野遼闊", 1, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
insert into trip (comp_id, trip_name, amount, price, people, start_time, end_time, content, state, taipei_city, newtaipei_city, taoyuan_city, taichung_city, tainan_city, kaohsiung_city, hsinchu_county, miaoli_county, changhua_county, nantou_county, yunlin_county, chiayi_county, pingtung_county, yilan_city, hualien_city, taitung_county, kinmen_county, lienchiang_county, keelung_city, hsinchu_city, chiayi_city, penghu_county)
	values(9, "野柳地質公園二日行", 4, 4000, 4, "2023-10-4 12:34:56", "2023-10-5 16:34:56", "奇形怪狀的岩石", -1, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
insert into trip (comp_id, trip_name, amount, price, people, start_time, end_time, content, state, taipei_city, newtaipei_city, taoyuan_city, taichung_city, tainan_city, kaohsiung_city, hsinchu_county, miaoli_county, changhua_county, nantou_county, yunlin_county, chiayi_county, pingtung_county, yilan_city, hualien_city, taitung_county, kinmen_county, lienchiang_county, keelung_city, hsinchu_city, chiayi_city, penghu_county)
	values(10, "浮洲藝術河濱公園二日行", 5, 5000, 5, "2023-10-4 12:34:56", "2023-10-5 16:34:56", "親子共遊的好去處", 0, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);

CREATE TABLE trip_photo (
	trip_photo_id     	INT AUTO_INCREMENT NOT NULL,
	trip_id     		int,
	photo       		longblob,
    upload_time     	datetime default current_timestamp,
	-- CONSTRAINT trip_photo_trip_id_FK FOREIGN KEY (trip_id) REFERENCES trip (trip_id),
	CONSTRAINT trip_photo_trip_photo_id_PK PRIMARY KEY (trip_photo_id)
);
insert into trip_photo (trip_id, photo, upload_time)
	values(1, null, "2023-10-3 11:34:56");
insert into trip_photo (trip_id, photo, upload_time)
	values(2, null, "2023-10-3 11:34:56");
insert into trip_photo (trip_id, photo, upload_time)
	values(3, null, "2023-10-3 11:34:56");
insert into trip_photo (trip_id, photo, upload_time)
	values(4, null, "2023-10-3 11:34:56");
insert into trip_photo (trip_id, photo, upload_time)
	values(5, null, "2023-10-3 11:34:56");

create table `itinerary`(
	itinerary_id int not null auto_increment primary key,
	trip_id int ,
	scene_id int,
	begin_time datetime default current_timestamp
    -- ,
 --    constraint itinerary_trip_id foreign key (trip_id) references trip(trip_id),
	-- constraint itinerary_scene_id foreign key (scene_id) references scene(scene_id)
);
insert into itinerary (trip_id, scene_id, begin_time)
	values(1, 1, "2023-10-4 07:34:56");
insert into itinerary (trip_id, scene_id, begin_time)
	values(2, 2, "2023-10-4 12:34:56");
insert into itinerary (trip_id, scene_id, begin_time)
	values(3, 3, "2023-10-4 12:34:56");
insert into itinerary (trip_id, scene_id, begin_time)
	values(4, 4, "2023-10-4 12:34:56");
insert into itinerary (trip_id, scene_id, begin_time)
	values(5, 5, "2023-10-4 12:34:56");

CREATE TABLE trip_ord (
	trip_ord_id		int auto_increment not null,
	trip_id			int,
	plan_id			int,
    cus_id			int,
    amount			int,
    total_price		decimal(18,3),
    commission		decimal(18,3),
    ord_status 	int,
    ord_time		datetime,
    remark			varchar(50),
    score			int,
    comments		longtext,
    comments_time	datetime default current_timestamp,
-- 	CONSTRAINT TRIP_ORD_TRIP_ID_FK FOREIGN KEY (trip_id) REFERENCES trip (trip_id),
-- 	CONSTRAINT TRIP_ORD_PLAN_ID_FK FOREIGN KEY (plan_id) REFERENCES planning (plan_id),
-- 	CONSTRAINT TRIP_ORD_CUS_ID_FK FOREIGN KEY (cus_id) REFERENCES consumer (cus_id),
    CONSTRAINT TRIP_ORD_PRIMARY_KEY PRIMARY KEY (trip_ord_id)
) ;
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (1, 1, 1, 5, 5000, 500, 0, null, null, null, null, null);
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (2, 2, 2, 4, 8000, 800, 1, "2023-10-03 12:24:56", "素食", 4, "太爽了", "2023-10-10 12:34:56");
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (3, 3, 3, 3, 9000, 900, 2, "2023-10-03 12:34:56", null, null, null, null);
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (4, 4, 4, 2, 8000, 800, 0, null, null, null, null, null);
insert into trip_ord (trip_id, plan_id, cus_id, amount, total_price, commission, ord_status, ord_time, remark, score, comments, comments_time)
	VALUES (5, 5, 5, 1, 5000, 500, 1, "2023-10-03 12:54:56", "素食", 3, "還好", "2023-10-10 12:34:56");
    
CREATE TABLE notify (
	notify_id     	INT AUTO_INCREMENT NOT NULL,
	cus_id     		int,
	comp_id     	int,
    room_ord_id     int,
    trip_ord_id     int,
    contents     	varchar(50),
    state     		boolean,
	notify_time     datetime default current_timestamp,
-- 	CONSTRAINT cus_id_FK FOREIGN KEY (cus_id) REFERENCES consumer (cus_id),
-- 	CONSTRAINT comp_id_FK FOREIGN KEY (comp_id) REFERENCES company (comp_id),
-- 	CONSTRAINT room_ord_id_FK FOREIGN KEY (room_ord_id) REFERENCES room_ord (room_ord_id),
-- 	CONSTRAINT trip_ord_id_FK FOREIGN KEY (trip_ord_id) REFERENCES trip_ord (trip_ord_id),
    CONSTRAINT notify_id_PRIMARY_KEY PRIMARY KEY (notify_id)
);
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (1, 1, 1, null, "預定編號1 未完成付款", false, "2023-10-02 17:14:56");
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (2, 2, 2, null, "預定編號2 已完成訂單", true, "2023-10-02 17:24:56");
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (3, 3, 3, null, "預定編號3 已退款", false, "2023-10-02 17:34:56");
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (4, 4, 4, null, "預定編號4 未完成付款", false, "2023-10-02 17:44:56");
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (5, 5, 5, null, "預定編號5 已完成訂單", true, "2023-10-02 17:54:56");
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (1, 6, null, 1, "預定編號6 未完成付款", false, "2023-10-03 12:14:56");
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (2, 7, null, 2, "預定編號7 已完成訂單", true, "2023-10-03 12:24:56");
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (3, 8, null, 3, "預定編號8 已退款", false, "2023-10-03 12:34:36");
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (4, 9, null, 4, "預定編號9 未完成付款", false, "2023-10-03 12:44:56");
insert into notify (cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time)
VALUES (5, 10, null, 5, "預定編號10 已完成訂單", true, "2023-10-03 12:54:56");


create table room_collect(
	cus_id 			int,
    room_id 		int,
    collect_time 	datetime default current_timestamp,
 --    constraint room_collect_cus_id foreign key (cus_id) REFERENCES consumer(cus_id),
--     constraint room_collect_room_id foreign key (room_id) REFERENCES room(room_id),
    constraint room_collect_pk primary key (cus_id, room_id)
);
insert into room_collect (cus_id, room_id, collect_time)
	VALUES (1, 5, "2023-10-3 12:34:56");
insert into room_collect (cus_id, room_id, collect_time)
	VALUES (2, 4, "2023-10-4 12:34:56");
insert into room_collect (cus_id, room_id, collect_time)
	VALUES (3, 3, "2023-10-5 12:34:56");
insert into room_collect (cus_id, room_id, collect_time)
	VALUES (4, 2, "2023-10-6 12:34:56");
insert into room_collect (cus_id, room_id, collect_time)
	VALUES (5, 1, "2023-10-7 12:34:56");

create table trip_collect(
	cus_id 			int,
    trip_id 		int,
    collect_time 	datetime default current_timestamp,
-- 	constraint trip_collect_cus_id foreign key (cus_id) REFERENCES consumer(cus_id),
--     constraint trip_collect_room_id foreign key (trip_id) REFERENCES trip(trip_id),
    constraint trip_collect_pk primary key (cus_id, trip_id)
);
insert into trip_collect (cus_id, trip_id, collect_time)
	values(1,5,"2023-10-4 12:34:56");
insert into trip_collect (cus_id, trip_id, collect_time)
	values(2,4,"2023-10-4 12:34:56");
insert into trip_collect (cus_id, trip_id, collect_time)
	values(3,3,"2023-10-4 12:34:56");
insert into trip_collect (cus_id, trip_id, collect_time)
	values(4,2,"2023-10-4 12:34:56");
insert into trip_collect (cus_id, trip_id, collect_time)
	values(5,1,"2023-10-4 12:34:56");

create table room_thumbup(
	room_ord_id 	int not null,
    cus_id 			int not null,
    thumbup_time	datetime default current_timestamp,
 --    constraint thumbup_room_ord_id foreign key (room_ord_id) REFERENCES room_ord(room_ord_id),
--     constraint thumbup_room_cus_id foreign key (cus_id) REFERENCES consumer(cus_id),
    constraint thumbup_room_pk primary key (room_ord_id, cus_id)
);
insert into room_thumbup (room_ord_id, cus_id, thumbup_time)
	values(1, 1, "2023-10-11 12:34:56");
insert into room_thumbup (room_ord_id, cus_id, thumbup_time)
	values(2, 2, "2023-10-11 12:34:56");
insert into room_thumbup (room_ord_id, cus_id, thumbup_time)
	values(3, 3, "2023-10-11 12:34:56");
insert into room_thumbup (room_ord_id, cus_id, thumbup_time)
	values(4, 4, "2023-10-11 12:34:56");
insert into room_thumbup (room_ord_id, cus_id, thumbup_time)
	values(5, 5, "2023-10-11 12:34:56");

create table trip_thumbup(
	trip_ord_id 	int not null,
	cus_id 			int not null,
    thumbup_time 	datetime default current_timestamp,
 --    constraint thumbup_trip_ord_id foreign key (trip_ord_id) references trip_ord(trip_ord_id),
--     constraint thumbup_trip_cus_id foreign key (cus_id) references consumer(cus_id),
    constraint thumbup_trip_pk primary key (trip_ord_id, cus_id)
);
insert into trip_thumbup (trip_ord_id, cus_id, thumbup_time)
	values(1, 1, "2023-10-11 12:34:56");
insert into trip_thumbup (trip_ord_id, cus_id, thumbup_time)
	values(2, 2, "2023-10-11 12:34:56");
insert into trip_thumbup (trip_ord_id, cus_id, thumbup_time)
	values(3, 3, "2023-10-11 12:34:56");
insert into trip_thumbup (trip_ord_id, cus_id, thumbup_time)
	values(4, 4, "2023-10-11 12:34:56");
insert into trip_thumbup (trip_ord_id, cus_id, thumbup_time)
	values(5, 5, "2023-10-11 12:34:56");