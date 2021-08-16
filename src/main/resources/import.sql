-- /////////////
-- //  TESTDATEN //
-- /////////////
--
--
--



--  // TABELLE ACCOUNT ////////////////////////

insert into ACCOUNT (id,email,password,name,description, faculty) values ('0','testAccount0@test.de','$2a$12$43ReZRF4mxQAMt9v7OVIUuvMoijG/FtHsrY1MMSFZNVmaErSIZmP.', 'nameAccount0', 'descripAccount0','0');
insert into ACCOUNT (id,email,password,name,description, faculty) values ('1','testAccount1@test.de','$2a$12$43ReZRF4mxQAMt9v7OVIUuvMoijG/FtHsrY1MMSFZNVmaErSIZmP.', 'nameAccount1', 'descripAccount1','1');
insert into ACCOUNT (id,email,password,name,description, faculty) values ('2','testAccount2@test.de','$2a$12$43ReZRF4mxQAMt9v7OVIUuvMoijG/FtHsrY1MMSFZNVmaErSIZmP.', 'nameAccount2', 'descripAccount2','2');
--
-- // TABELLE CARDCONTENT /////////////////////////////////
--
-- // 20 FrontCards + 20 BackCards

insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values ('0','textdataFront0','TitelFront0','0'),('1','textdataFront1','TitelFront1','0'),
                                                       ('2','textdataFront2','TitelFront2','0'),('3','textdataFront3','TitelFront3','0'),
                                                       ('4','textdataFront4','TitelFront4','0'),('5','textdataFront5','TitelFront5','0')
        ,('6','textdataFront6','TitelFront6','0'),('7','textdataFront7','TitelFront7','0'),('8','textdataFront8','TitelFront8','0'),
                                                       ('9','textdataFront9','TitelFront9','0'),
                                                       ('10','textdataFront10','TitelFront10','0'),('11','textdataFront11','TitelFront11','0'),('12','textdataFront12','TitelFront12','0'),
                                                       ('13','textdataFront13','TitelFront13','0'),
                                                       ('14','textdataFront14','TitelFront14','0'),('15','textdataFront15','TitelFront15','0'),
                                                       ('16','textdataFront16','TitelFront16','0'),('17','textdataFront17','TitelFront17','0'),
                                                       ('18','textdataFront18','TitelFront18','0'),('19','textdataFront19','TitelFront19','0'),
                                                       ('20','textdataBack0','TitelBack0','0'),('21','textdataBack1','TitelBack1','0'),('22','textdataBack2','TitelBack2','0'),
                                                       ('23','textdataBack3','TitelBack3','0'),('24','textdataBack4','TitelBack4','0'),('25','textdataBack5','TitelBack5','0'),
                                                       ('26','textdataBack6','TitelBack6','0'),
                                                       ('27','textdataBack7','TitelBack7','0'),('28','textdataBack8','TitelBack8','0'),('29','textdataBack9','TitelBack9','0'),
                                                       ('30','textdataBack10','TitelBack10','0'),('31','textdataBack11','TitelBack11','0'),('32','textdataBack12','TitelBack12','0'),
                                                       ('33','textdataBack13','TitelBack13','0'),('34','textdataBack14','TitelBack14','0'),('35','textdataBack15','TitelBack15','0'),
                                                       ('36','textdataBack16','TitelBack16','0'),
                                                       ('37','textdataBack17','TitelBack17','0'),('38','textdataBack18','TitelBack18','0'),('39','textdataBack19','TitelBack19','0');

-- // TABELLE CARD ///////////////////////////////
-- // 20 Cards
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('0','0','20'),('1','1','21'),('2','2','22'),('3','3','23'),('4','4','24'),('5','5','25'),
                                                        ('6','6','26'),('7','7','27'),('8','8','28'),('9','9','29'),
                                                        ('10','10','30'),('11','11','31'),('12','12','32'),('13','13','33'),('14','14','34'),
                                                        ('15','15','35'),('16','16','36'),('17','17','37'),('18','18','38'),('19','19','39');

-- // TABELLE CARDLIST ///////////////////////////////////////

-- // 3 CardLists
insert into CARDLIST (ID, LISTINDEX) values ('0','0'),('1','0'),('2','0');



-- // TABELLE CARDLIST_CARD ///////////////////////////////////////

-- // 3 CardLists

insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values ('0','0'),('0','1'),('0','2'),('0','3'),('0','4'),('0','5'),('0','6'),('0','7'),
                                                               ('1','8'),('1','9'),('1','10'),('1','11'),('1','12'),('1','13');


-- // TABELLE LEARNSET //////////////////////////

insert into LEARNSET (ID, DESCRIPTION, EVALUATIONS, FACULTY, NUMBEROFEVALUATIONS, TITLE, VISIBILITY, CARDLIST_ID, OWNER_ID)
VALUES  ('0','Learnset0', -1,'4','0','Title0','2', '0','0'),
        ('1','Learnset1', -1,'2','0','Title1','2', '1','1'),
        ('2','Learnset2', -1,'3','0','Title2','2', '2','2');


-- // TABELLE LEARNINGSESSION //

insert into LEARNINGSESSION (ID, CURRENTCARD, ISACTIVE) values ('0','0','0'),('1','3','1');

-- // TABELLE CARDSTATUS //////////////////////////////////////

insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('0','0','0'),('1','0','1'),('2','0','2'),('3','0','3'),('4','0','4'),
                                                                ('5','0','5'),('6','0','6'),('7','0','7'),
                                                                ('8','0','0'), ('9','0','1'),
                                                                ('10','0','2'),('11','0','3'),('12','0','4'),('13','0','5'),('14','0','6'),
                                                                ('15','0','7'),
                                                                ('16','0','8'),('17','0','9'),('18','0','10'),('19','0','11'),
                                                                ('20','0','12'),('21','0','13'),
                                                                ('22','0','14'),('23','0','15'),
                                                                ('24','0','16'),('25','0','17'),('26','0','18'),('27','0','19'),
                                                                ('28','0','0'),('29','0','1'),('30','0','2'),('31','0','3'),('32','0','4'),
                                                                ('33','0','14'),('34','0','15'),('35','0','16'),('36','0','17'),('37','0','18'),('38','0','19');


-- // TABELLE LEARNINGSESSION_CARDSTATUS //


insert into LEARNINGSESSION_CARDSTATUS (LEARNINGSESSION_ID, CARDSTATUSLIST_ID) values ('0','28'),('0','29'),('0','30'),('0','31'),('0','32');


-- // TABELLE LEARNSETABO /////////////////////////////
--
-- // 4 LearnsetAbos, LA 0 und 1 get Data from Learnset0
insert into LEARNSETABO (ID, EVALUATION, LEARNSETSTATUS, LEARNSET_ID) values ('0', -1,'0','0'),('1', -1,'1','0'),('2', -1,'0','1')
                                                                           ,('3', -1,'0','2');

-- // TABELLE LEARNSETABO_CARDSTATUS ////////////////////////////////


insert into LEARNSETABO_CARDSTATUS (LEARNSETABO_ID, CARDSTATUS_ID) values ('0','0'),('0','1'),('0','2'),('0','3'),('0','4'),('0','5'),('0','6'),('0','7'),
                                                                          ('1','8'),('1','9'),('1','10'),('1','11'),('1','12'),('1','13'),('1','14'),('1','15'),
                                                                          ('2','16'),('2','17'),('2','18'),('2','19'),('2','20'),('2','21'),
                                                                          ('3','22'),('3','23'),('3','24'),('3','25'),('3','26'),('3','27');

insert into ACCOUNT_LEARNSETABO (ACCOUNT_ID, LEARNSETABOS_ID) values ('0','0'),('0','2'),('1','1'),('2','2');

insert into LEARNSET_ACCOUNT (LEARNSET_ID, ADMINLIST_ID) values ('0','0'),('1','1'),('1','1');



-- // TABELLE MESSAGE ////////////////////////////////

-- insert into MESSAGE (ID, MESSAGE, TIME, SENDER_ID) values ('0','This is Comment 0',CURRENT_TIMESTAMP,'0'),('1','This is Comment 1',CURRENT_TIMESTAMP,'1'),
--                                                           ('2','This is Comment 2',CURRENT_TIMESTAMP,'2'),('3','This is Comment 3',CURRENT_TIMESTAMP,'0');
-- -- // TABELLE LEARNSET_MESSAGE ///////////////////////////////
--
-- insert into LEARNSET_MESSAGE (LEARNSET_ID, COMMENTLIST_ID) values ('0','0'),('0','1'),('1','2'),('2','3');











