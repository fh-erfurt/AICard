-- /////////////
-- //  TESTDATEN //
-- /////////////
--
--
--



--  // TABELLE ACCOUNT ////////////////////////

insert into ACCOUNT (id,email,password,name,description, faculty) values ('1','testAccount1@test.de','$2a$12$43ReZRF4mxQAMt9v7OVIUuvMoijG/FtHsrY1MMSFZNVmaErSIZmP.', 'nameAccount1', 'descripAccount1','0');
insert into ACCOUNT (id,email,password,name,description, faculty) values ('2','testAccount2@test.de','$2a$12$43ReZRF4mxQAMt9v7OVIUuvMoijG/FtHsrY1MMSFZNVmaErSIZmP.', 'nameAccount2', 'descripAccount2','1');
insert into ACCOUNT (id,email,password,name,description, faculty) values ('3','testAccount3@test.de','$2a$12$43ReZRF4mxQAMt9v7OVIUuvMoijG/FtHsrY1MMSFZNVmaErSIZmP.', 'nameAccount3', 'descripAccount3','2');
--
-- // TABELLE CARDCONTENT /////////////////////////////////



insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('1','textdataFront1','TitelFront1','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('2','textdataFront2','TitelFront2','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('3','textdataFront3','TitelFront3','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('4','textdataFront4','TitelFront4','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('5','textdataFront5','TitelFront5','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('6','textdataFront6','TitelFront6','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('7','textdataFront7','TitelFront7','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('8','textdataFront8','TitelFront8','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('9','textdataFront9','TitelFront9','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('10','textdataFront10','TitelFront10','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('11','textdataFront11','TitelFront11','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('12','textdataFront12','TitelFront12','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('13','textdataFront13','TitelFront13','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('14','textdataFront14','TitelFront14','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('15','textdataFront15','TitelFront15','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('16','textdataFront16','TitelFront16','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('17','textdataFront17','TitelFront17','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('18','textdataFront18','TitelFront18','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('19','textdataFront19','TitelFront19','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('20','textdataFront20','TitelFront20','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('21','textdataBack1','TitelBack1','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('22','textdataBack2','TitelBack2','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('23','textdataBack3','TitelBack3','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('24','textdataBack4','TitelBack4','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('25','textdataBack5','TitelBack5','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('26','textdataBack6','TitelBack6','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('27','textdataBack7','TitelBack7','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('28','textdataBack8','TitelBack8','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('29','textdataBack9','TitelBack9','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('30','textdataBack10','TitelBack10','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('31','textdataBack11','TitelBack11','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('32','textdataBack12','TitelBack12','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('33','textdataBack13','TitelBack13','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('34','textdataBack14','TitelBack14','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('35','textdataBack15','TitelBack15','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('36','textdataBack16','TitelBack16','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('37','textdataBack17','TitelBack17','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('38','textdataBack18','TitelBack18','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('39','textdataBack19','TitelBack19','0');
insert into CARDCONTENT (ID, DATA, TITLE, TYPE) values('40','textdataBack20','TitelBack20','0');

-- // TABELLE CARD ///////////////////////////////

insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('1','1','21');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('2','2','22');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('3','3','23');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('4','4','24');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('5','5','25');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('6','6','26');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('7','7','27');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('8','8','28');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('9','9','29');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('10','10','30');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('11','11','31');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('12','12','32');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('13','13','33');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('14','14','34');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('15','15','35');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('16','16','36');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('17','17','37');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('18','18','38');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('19','19','39');
insert into CARD (ID, CARDBACK_ID, CARDFRONT_ID) values ('20','20','40');

-- // TABELLE CARDLIST ///////////////////////////////////////


insert into CARDLIST (ID, LISTINDEX) values ('1','0');
insert into CARDLIST (ID, LISTINDEX) values ('2','0');
insert into CARDLIST (ID, LISTINDEX) values ('3','0');



-- // TABELLE CARDLIST_CARD ///////////////////////////////////////



insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('1','1');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('1','2');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('1','3');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('1','4');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('1','5');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('1','6');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('1','7');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('1','8');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('2','9');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('2','10');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('2','11');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('2','12');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('2','13');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('2','14');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('3','15');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('3','16');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('3','17');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('3','18');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('3','19');
insert into CARDLIST_CARD (CARDLIST_ID, LISTOFCARDS_ID) values('3','20');


-- // TABELLE LEARNSET //////////////////////////

insert into LEARNSET (ID, DESCRIPTION, EVALUATIONS, FACULTY, NUMBEROFEVALUATIONS, TITLE, VISIBILITY, CARDLIST_ID, OWNER_ID) values  ('1','Learnset1', -1,'4','0','Title1','2', '1','1');
insert into LEARNSET (ID, DESCRIPTION, EVALUATIONS, FACULTY, NUMBEROFEVALUATIONS, TITLE, VISIBILITY, CARDLIST_ID, OWNER_ID) values  ('2','Learnset2', -1,'2','0','Title2','2', '2','2');
insert into LEARNSET (ID, DESCRIPTION, EVALUATIONS, FACULTY, NUMBEROFEVALUATIONS, TITLE, VISIBILITY, CARDLIST_ID, OWNER_ID) values  ('3','Learnset3', -1,'3','0','Title3','2', '3','3');




-- // TABELLE CARDSTATUS //////////////////////////////////////

insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('1','0','1');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('2','0','2');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('3','0','3');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('4','0','4');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('5','0','5');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('6','0','6');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('7','0','7');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('8','0','8');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('9','0','1');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('10','0','2');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('11','0','3');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('12','0','4');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('13','0','5');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('14','0','6');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('15','0','7');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('16','0','8');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('17','0','9');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('18','0','10');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('19','0','11');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('20','0','12');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('21','0','13');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('22','0','14');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('23','0','15');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('24','0','16');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('25','0','17');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('26','0','18');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('27','0','19');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('28','0','20');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('29','0','1');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('30','0','2');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('31','0','3');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('32','0','4');
insert into CARDSTATUS (ID, CARDKNOWLEDGELEVEL, CARD_ID) values ('33','0','5');


-- // TABELLE LEARNINGSESSION //

insert into LEARNINGSESSION (ID, CURRENTCARD, ISACTIVE) values ('1','0','0'),('2','3','1');

-- // TABELLE LEARNINGSESSION_CARDSTATUS //


insert into LEARNINGSESSION_CARDSTATUS (LEARNINGSESSION_ID, CARDSTATUSLIST_ID) values ('1','29'),('1','30'),('1','31'),('1','32'),('1','33');


-- // TABELLE LEARNSETABO /////////////////////////////


insert into LEARNSETABO (ID, EVALUATION, LEARNSETSTATUS, LEARNSET_ID) values ('1', -1,'0','1'),('2', -1,'1','1'),('3', -1,'0','2'),('4', -1,'0','3');

-- // TABELLE ACCOUNT_LEARNSETABO ///////////////////////////////



insert into ACCOUNT_LEARNSETABO (ACCOUNT_ID, LEARNSETABOS_ID) values ('1','1');
insert into ACCOUNT_LEARNSETABO (ACCOUNT_ID, LEARNSETABOS_ID) values ('1','3');
insert into ACCOUNT_LEARNSETABO (ACCOUNT_ID, LEARNSETABOS_ID) values('2','2');
insert into ACCOUNT_LEARNSETABO (ACCOUNT_ID, LEARNSETABOS_ID) values('3','3');


-- // TABELLE LEARNSETABO_CARDSTATUS ////////////////////////////////


insert into LEARNSETABO_CARDSTATUS (LEARNSETABO_ID, CARDSTATUS_ID) values ('1','1'),('1','2'),('1','3'),('1','4'),('1','5'),('1','6'),('1','7'),('1','8');
insert into LEARNSETABO_CARDSTATUS (LEARNSETABO_ID, CARDSTATUS_ID) values ('2','9'),('2','10'),('2','11'),('2','12'),('2','13'),('2','14'),('2','15'),('2','16');
insert into LEARNSETABO_CARDSTATUS (LEARNSETABO_ID, CARDSTATUS_ID) values ('3','17'),('3','18'),('3','19'),('3','20'),('3','21'),('3','22');
insert into LEARNSETABO_CARDSTATUS (LEARNSETABO_ID, CARDSTATUS_ID) values ('4','23'),('4','24'),('4','25'),('4','26'),('4','27'),('4','28');


-- // TABELLE LEARNSET_ACCOUNT //////////////////////////////////////////

insert into LEARNSET_ACCOUNT (LEARNSET_ID, ADMINLIST_ID) values ('1','1'),('2','2'),('3','3');



-- // TABELLE MESSAGE ////////////////////////////////

insert into MESSAGE (ID, MESSAGE, TIME, SENDER_ID) values ('1','This is Comment 1',CURRENT_TIMESTAMP,'1'),('2','This is Comment 2',CURRENT_TIMESTAMP,'2');
insert into MESSAGE (ID, MESSAGE, TIME, SENDER_ID) values ('3','This is Comment 3',CURRENT_TIMESTAMP,'3'),('4','This is Comment 4',CURRENT_TIMESTAMP,'1');
-- // TABELLE LEARNSET_MESSAGE ///////////////////////////////
--
insert into LEARNSET_MESSAGE (LEARNSET_ID, COMMENTLIST_ID) values ('1','1'),('1','2'),('2','3'),('3','4');









