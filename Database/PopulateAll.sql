CREATE TABLE "User" (
	"UserID" serial NOT NULL,
	"FirstName" TEXT NOT NULL,
	"MiddleName" TEXT,
	"LastName" TEXT NOT NULL,
	"AddressID" int,
	"PhoneNumber" varchar(20),
	"Email" varchar(254) NOT NULL UNIQUE,
	CONSTRAINT User_pk PRIMARY KEY ("UserID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Book" (
	"BookID" serial NOT NULL,
	"ISBN" varchar(13) NOT NULL,
	"EditionID" int NOT NULL,
	"OwnerID" int NOT NULL,
	"CurrentLocationID" int NOT NULL,
	"Available" BOOLEAN NOT NULL,
	"DatumVracanja" DATE,
	"Information" TEXT,
	CONSTRAINT Book_pk PRIMARY KEY ("BookID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Author" (
	"AuthorID" serial NOT NULL,
	"FirstName" TEXT NOT NULL,
	"MiddleName" TEXT,
	"LastName" TEXT NOT NULL,
	"Alive" BOOLEAN,
	"YearOfBirth" char(4),
	"YearOfDeath" char(4),
	CONSTRAINT Author_pk PRIMARY KEY ("AuthorID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "IsbnLinks" (
	"ISBN" varchar(13) NOT NULL,
	"Title" TEXT NOT NULL,
	"Summary" TEXT,
	CONSTRAINT IsbnLinks_pk PRIMARY KEY ("ISBN")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Edition" (
	"EditionID" serial NOT NULL,
	"Number" int,
	"Year" char(4),
	"NumberOfPages" int,
	CONSTRAINT Edition_pk PRIMARY KEY ("EditionID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Publisher" (
	"PublisherID" serial NOT NULL,
	"Name" TEXT NOT NULL,
	"AddressID" int,
	CONSTRAINT Publisher_pk PRIMARY KEY ("PublisherID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Library" (
	"LibraryID" serial NOT NULL,
	"Name" TEXT NOT NULL,
	"AddressID" int,
	"PhoneNumber" varchar(20) NOT NULL,
	"Email" varchar(254),
	"Information" TEXT,
	CONSTRAINT Library_pk PRIMARY KEY ("LibraryID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Address" (
	"AddressID" serial NOT NULL,
	"Country" TEXT NOT NULL,
	"PostalCode" int NOT NULL,
	"Street" TEXT,
	"HouseNumber" varchar(6),
	CONSTRAINT Address_pk PRIMARY KEY ("AddressID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "City" (
	"PostalCode" serial NOT NULL,
	"Name" TEXT NOT NULL,
	CONSTRAINT City_pk PRIMARY KEY ("PostalCode")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "EventLog" (
	"EventID" serial NOT NULL,
	"EventDateTime" TIMESTAMP NOT NULL,
	"EventTypeID" int NOT NULL,
	"StartLocationID" int NOT NULL,
	"EndLocationID" int NOT NULL,
	"BookID" int NOT NULL,
	CONSTRAINT EventLog_pk PRIMARY KEY ("EventID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "LanguageList" (
	"LanguageID" serial NOT NULL,
	"Name" TEXT NOT NULL UNIQUE,
	CONSTRAINT LanguageList_pk PRIMARY KEY ("LanguageID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "GenreList" (
	"GenreID" serial NOT NULL,
	"Name" TEXT NOT NULL UNIQUE,
	CONSTRAINT GenreList_pk PRIMARY KEY ("GenreID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Location" (
	"LocationID" serial NOT NULL,
	"TypeID" int NOT NULL,
	"LibraryID" int UNIQUE,
	"UserID" int UNIQUE,
	CONSTRAINT Location_pk PRIMARY KEY ("LocationID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "LocationList" (
	"TypeID" serial NOT NULL,
	"Name" TEXT NOT NULL UNIQUE,
	CONSTRAINT LocationList_pk PRIMARY KEY ("TypeID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "EventTypeList" (
	"EventTypeID" serial NOT NULL,
	"Name" TEXT NOT NULL UNIQUE,
	CONSTRAINT EventTypeList_pk PRIMARY KEY ("EventTypeID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "AuthorLinks" (
	"ISBN" varchar(13) NOT NULL,
	"AuthorID" int NOT NULL,
	CONSTRAINT AuthorLinks_pk PRIMARY KEY ("ISBN","AuthorID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "GenreLinks" (
	"ISBN" varchar(13) NOT NULL,
	"GenreID" int NOT NULL,
	CONSTRAINT GenreLinks_pk PRIMARY KEY ("ISBN","GenreID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "LanguageLinks" (
	"ISBN" varchar(13) NOT NULL,
	"LanguageID" int NOT NULL,
	CONSTRAINT LanguageLinks_pk PRIMARY KEY ("ISBN","LanguageID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "PublisherLinks" (
	"ISBN" varchar(13) NOT NULL,
	"PublisherID" int NOT NULL,
	CONSTRAINT PublisherLinks_pk PRIMARY KEY ("ISBN","PublisherID")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "BusinessHours" (
	"LibraryID" int NOT NULL,
	"DayOfWeek" int NOT NULL,
	"Closed" BOOLEAN NOT NULL,
	"OpenTime" TIME NOT NULL,
	"CloseTime" TIME NOT NULL,
	CONSTRAINT BusinessHours_pk PRIMARY KEY ("LibraryID","DayOfWeek")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "User" ADD CONSTRAINT "User_fk0" FOREIGN KEY ("AddressID") REFERENCES "Address"("AddressID");

ALTER TABLE "Book" ADD CONSTRAINT "Book_fk0" FOREIGN KEY ("ISBN") REFERENCES "IsbnLinks"("ISBN");
ALTER TABLE "Book" ADD CONSTRAINT "Book_fk1" FOREIGN KEY ("EditionID") REFERENCES "Edition"("EditionID");
ALTER TABLE "Book" ADD CONSTRAINT "Book_fk2" FOREIGN KEY ("OwnerID") REFERENCES "Location"("LocationID");
ALTER TABLE "Book" ADD CONSTRAINT "Book_fk3" FOREIGN KEY ("CurrentLocationID") REFERENCES "Location"("LocationID");




ALTER TABLE "Publisher" ADD CONSTRAINT "Publisher_fk0" FOREIGN KEY ("AddressID") REFERENCES "Address"("AddressID");

ALTER TABLE "Library" ADD CONSTRAINT "Library_fk0" FOREIGN KEY ("AddressID") REFERENCES "Address"("AddressID");

ALTER TABLE "Address" ADD CONSTRAINT "Address_fk0" FOREIGN KEY ("PostalCode") REFERENCES "City"("PostalCode");


ALTER TABLE "EventLog" ADD CONSTRAINT "EventLog_fk0" FOREIGN KEY ("EventTypeID") REFERENCES "EventTypeList"("EventTypeID");
ALTER TABLE "EventLog" ADD CONSTRAINT "EventLog_fk1" FOREIGN KEY ("StartLocationID") REFERENCES "Location"("LocationID");
ALTER TABLE "EventLog" ADD CONSTRAINT "EventLog_fk2" FOREIGN KEY ("EndLocationID") REFERENCES "Location"("LocationID");
ALTER TABLE "EventLog" ADD CONSTRAINT "EventLog_fk3" FOREIGN KEY ("BookID") REFERENCES "Book"("BookID");



ALTER TABLE "Location" ADD CONSTRAINT "Location_fk0" FOREIGN KEY ("TypeID") REFERENCES "LocationList"("TypeID");
ALTER TABLE "Location" ADD CONSTRAINT "Location_fk1" FOREIGN KEY ("LibraryID") REFERENCES "Library"("LibraryID");
ALTER TABLE "Location" ADD CONSTRAINT "Location_fk2" FOREIGN KEY ("UserID") REFERENCES "User"("UserID");



ALTER TABLE "AuthorLinks" ADD CONSTRAINT "AuthorLinks_fk0" FOREIGN KEY ("ISBN") REFERENCES "IsbnLinks"("ISBN");
ALTER TABLE "AuthorLinks" ADD CONSTRAINT "AuthorLinks_fk1" FOREIGN KEY ("AuthorID") REFERENCES "Author"("AuthorID");

ALTER TABLE "GenreLinks" ADD CONSTRAINT "GenreLinks_fk0" FOREIGN KEY ("ISBN") REFERENCES "IsbnLinks"("ISBN");
ALTER TABLE "GenreLinks" ADD CONSTRAINT "GenreLinks_fk1" FOREIGN KEY ("GenreID") REFERENCES "GenreList"("GenreID");

ALTER TABLE "LanguageLinks" ADD CONSTRAINT "LanguageLinks_fk0" FOREIGN KEY ("ISBN") REFERENCES "IsbnLinks"("ISBN");
ALTER TABLE "LanguageLinks" ADD CONSTRAINT "LanguageLinks_fk1" FOREIGN KEY ("LanguageID") REFERENCES "LanguageList"("LanguageID");

ALTER TABLE "PublisherLinks" ADD CONSTRAINT "PublisherLinks_fk0" FOREIGN KEY ("ISBN") REFERENCES "IsbnLinks"("ISBN");
ALTER TABLE "PublisherLinks" ADD CONSTRAINT "PublisherLinks_fk1" FOREIGN KEY ("PublisherID") REFERENCES "Publisher"("PublisherID");

ALTER TABLE "BusinessHours" ADD CONSTRAINT "BusinessHours_fk0" FOREIGN KEY ("LibraryID") REFERENCES "Library"("LibraryID");



INSERT INTO "City" VALUES(10000, 'Zagreb');
INSERT INTO "City" VALUES(10010, 'Zagreb-Sloboština');
INSERT INTO "City" VALUES(10020, 'Zagreb-Novi Zagreb');
INSERT INTO "City" VALUES(10040, 'Zagreb-Dubrava');
INSERT INTO "City" VALUES(10090, 'Zagreb-Susedgrad');
INSERT INTO "City" VALUES(10110, 'Zagreb-Jarun');
INSERT INTO "City" VALUES(10250, 'Lučko');
INSERT INTO "City" VALUES(10251, 'Hrvatski Leskovac');
INSERT INTO "City" VALUES(10253, 'Donji Dragonožec');
INSERT INTO "City" VALUES(10255, 'Gornji Stupnik');
INSERT INTO "City" VALUES(10257, 'Brezovica');
INSERT INTO "City" VALUES(10290, 'Zaprešić');
INSERT INTO "City" VALUES(10291, 'Prigorje Brdovečko');
INSERT INTO "City" VALUES(10292, 'Šenkovec');
INSERT INTO "City" VALUES(10293, 'Dubravica');
INSERT INTO "City" VALUES(10294, 'Donja Pušća');
INSERT INTO "City" VALUES(10295, 'Kupljenovo');
INSERT INTO "City" VALUES(10296, 'Luka');
INSERT INTO "City" VALUES(10297, 'Jakovlje');
INSERT INTO "City" VALUES(10298, 'Donja Bistra');
INSERT INTO "City" VALUES(10299, 'Marija Gorica');
INSERT INTO "City" VALUES(10310, 'Ivanić-Grad');
INSERT INTO "City" VALUES(10311, 'Posavski Bregi');
INSERT INTO "City" VALUES(10312, 'Kloštar Ivanić');
INSERT INTO "City" VALUES(10313, 'Graberje Ivaničko');
INSERT INTO "City" VALUES(10314, 'Križ');
INSERT INTO "City" VALUES(10315, 'Novoselec');
INSERT INTO "City" VALUES(10316, 'Lijevi Dubrovčak');
INSERT INTO "City" VALUES(10340, 'Vrbovec');
INSERT INTO "City" VALUES(10341, 'Lonjica');
INSERT INTO "City" VALUES(10342, 'Dubrava');
INSERT INTO "City" VALUES(10343, 'Nova Kapela');
INSERT INTO "City" VALUES(10344, 'Farkaševac');
INSERT INTO "City" VALUES(10345, 'Gradec');
INSERT INTO "City" VALUES(10346, 'Preseka');
INSERT INTO "City" VALUES(10347, 'Rakovec');
INSERT INTO "City" VALUES(10360, 'Sesvete');
INSERT INTO "City" VALUES(10361, 'Sesvete-Kraljevec');
INSERT INTO "City" VALUES(10362, 'Kašina');
INSERT INTO "City" VALUES(10363, 'Belovar');
INSERT INTO "City" VALUES(10370, 'Dugo Selo');
INSERT INTO "City" VALUES(10372, 'Oborovo');
INSERT INTO "City" VALUES(10373, 'Ivanja Reka');
INSERT INTO "City" VALUES(10380, 'Sveti Ivan Zelina');
INSERT INTO "City" VALUES(10381, 'Bedenica');
INSERT INTO "City" VALUES(10382, 'Donja Zelina');
INSERT INTO "City" VALUES(10383, 'Komin');
INSERT INTO "City" VALUES(10408, 'Velika Mlaka');
INSERT INTO "City" VALUES(10410, 'Velika Gorica');
INSERT INTO "City" VALUES(10411, 'Orle');
INSERT INTO "City" VALUES(10412, 'Donja Lomnica');
INSERT INTO "City" VALUES(10413, 'Kravarsko');
INSERT INTO "City" VALUES(10414, 'Pokupsko');
INSERT INTO "City" VALUES(10415, 'Novo Čiče');
INSERT INTO "City" VALUES(10417, 'Buševec');
INSERT INTO "City" VALUES(10418, 'Dubranec');
INSERT INTO "City" VALUES(10419, 'Vukovina');
INSERT INTO "City" VALUES(10430, 'Samobor');
INSERT INTO "City" VALUES(10431, 'Sveta Nedjelja');
INSERT INTO "City" VALUES(10432, 'Bregana');
INSERT INTO "City" VALUES(10434, 'Strmec Samoborski');
INSERT INTO "City" VALUES(10435, 'Sveti Martin pod Okićem');
INSERT INTO "City" VALUES(10436, 'Rakov Potok');
INSERT INTO "City" VALUES(10437, 'Bestovje');
INSERT INTO "City" VALUES(10450, 'Jastrebarsko');
INSERT INTO "City" VALUES(10451, 'Pisarovina');
INSERT INTO "City" VALUES(10453, 'Gorica Svetojanska');
INSERT INTO "City" VALUES(10454, 'Krašić');
INSERT INTO "City" VALUES(10455, 'Kostanjevac');
INSERT INTO "City" VALUES(10456, 'Kalje');
INSERT INTO "City" VALUES(10457, 'Sošice');
INSERT INTO "City" VALUES(20000, 'Dubrovnik');
INSERT INTO "City" VALUES(20205, 'Topolo');
INSERT INTO "City" VALUES(20207, 'Mlini');
INSERT INTO "City" VALUES(20210, 'Cavtat');
INSERT INTO "City" VALUES(20213, 'Čilipi');
INSERT INTO "City" VALUES(20215, 'Gruda');
INSERT INTO "City" VALUES(20216, 'Dubravka');
INSERT INTO "City" VALUES(20217, 'Pridvorje');
INSERT INTO "City" VALUES(20218, 'Pločice');
INSERT INTO "City" VALUES(20221, 'Koločep');
INSERT INTO "City" VALUES(20222, 'Lopud');
INSERT INTO "City" VALUES(20223, 'Šipanjska Luka');
INSERT INTO "City" VALUES(20224, 'Maranovići');
INSERT INTO "City" VALUES(20225, 'Babino Polje');
INSERT INTO "City" VALUES(20226, 'Goveđari');
INSERT INTO "City" VALUES(20230, 'Ston');
INSERT INTO "City" VALUES(20231, 'Doli');
INSERT INTO "City" VALUES(20232, 'Slano');
INSERT INTO "City" VALUES(20233, 'Trsteno');
INSERT INTO "City" VALUES(20234, 'Orašac');
INSERT INTO "City" VALUES(20235, 'Zaton Veliki');
INSERT INTO "City" VALUES(20236, 'Mokošica');
INSERT INTO "City" VALUES(20240, 'Trpanj');
INSERT INTO "City" VALUES(20242, 'Oskorušno');
INSERT INTO "City" VALUES(20243, 'Kuna');
INSERT INTO "City" VALUES(20244, 'Potomje');
INSERT INTO "City" VALUES(20245, 'Trstenik');
INSERT INTO "City" VALUES(20246, 'Janjina');
INSERT INTO "City" VALUES(20247, 'Žuljana');
INSERT INTO "City" VALUES(20248, 'Putniković');
INSERT INTO "City" VALUES(20250, 'Orebić');
INSERT INTO "City" VALUES(20260, 'Korčula');
INSERT INTO "City" VALUES(20263, 'Lumbarda');
INSERT INTO "City" VALUES(20264, 'Račišće');
INSERT INTO "City" VALUES(20267, 'Kućište');
INSERT INTO "City" VALUES(20269, 'Lovište');
INSERT INTO "City" VALUES(20270, 'Vela Luka');
INSERT INTO "City" VALUES(20271, 'Blato');
INSERT INTO "City" VALUES(20272, 'Smokvica');
INSERT INTO "City" VALUES(20273, 'Čara');
INSERT INTO "City" VALUES(20274, 'Pupnat');
INSERT INTO "City" VALUES(20275, 'Žrnovo');
INSERT INTO "City" VALUES(20278, 'Nova Sela');
INSERT INTO "City" VALUES(20290, 'Lastovo');
INSERT INTO "City" VALUES(20340, 'Ploče');
INSERT INTO "City" VALUES(20341, 'Kula Norinska');
INSERT INTO "City" VALUES(20342, 'Otrić Seoci');
INSERT INTO "City" VALUES(20343, 'Rogotin');
INSERT INTO "City" VALUES(20344, 'Komin (Dalmacija)');
INSERT INTO "City" VALUES(20345, 'Staševica');
INSERT INTO "City" VALUES(20350, 'Metković');
INSERT INTO "City" VALUES(20352, 'Vid');
INSERT INTO "City" VALUES(20353, 'Mlinište');
INSERT INTO "City" VALUES(20355, 'Opuzen');
INSERT INTO "City" VALUES(20356, 'Klek');
INSERT INTO "City" VALUES(20357, 'Blace');
INSERT INTO "City" VALUES(21000, 'Split');
INSERT INTO "City" VALUES(21201, 'Prgomet');
INSERT INTO "City" VALUES(21202, 'Lećevica');
INSERT INTO "City" VALUES(21203, 'Donji Muć');
INSERT INTO "City" VALUES(21204, 'Dugopolje');
INSERT INTO "City" VALUES(21205, 'Donji Dolac');
INSERT INTO "City" VALUES(21206, 'Donje Ogorje');
INSERT INTO "City" VALUES(21207, 'Kostanje');
INSERT INTO "City" VALUES(21208, 'Kučiće');
INSERT INTO "City" VALUES(21209, 'Mravince');
INSERT INTO "City" VALUES(21210, 'Solin');
INSERT INTO "City" VALUES(21212, 'Kaštel Sućurac');
INSERT INTO "City" VALUES(21213, 'Kaštel Gomilica');
INSERT INTO "City" VALUES(21214, 'Kaštel Kambelovac');
INSERT INTO "City" VALUES(21215, 'Kaštel Lukšić');
INSERT INTO "City" VALUES(21216, 'Kaštel Stari');
INSERT INTO "City" VALUES(21218, 'Seget Donji');
INSERT INTO "City" VALUES(21220, 'Trogir');
INSERT INTO "City" VALUES(21222, 'Marina');
INSERT INTO "City" VALUES(21223, 'Okrug Gornji');
INSERT INTO "City" VALUES(21224, 'Slatine');
INSERT INTO "City" VALUES(21225, 'Drvenik Veliki');
INSERT INTO "City" VALUES(21226, 'Vinišće');
INSERT INTO "City" VALUES(21227, 'Primorski Dolac');
INSERT INTO "City" VALUES(21228, 'Blizna Donja');
INSERT INTO "City" VALUES(21229, 'Crivac');
INSERT INTO "City" VALUES(21230, 'Sinj');
INSERT INTO "City" VALUES(21231, 'Klis');
INSERT INTO "City" VALUES(21232, 'Dicmo');
INSERT INTO "City" VALUES(21233, 'Hrvace');
INSERT INTO "City" VALUES(21235, 'Otišić');
INSERT INTO "City" VALUES(21236, 'Vrlika');
INSERT INTO "City" VALUES(21238, 'Otok (Dalmacija)');
INSERT INTO "City" VALUES(21240, 'Trilj');
INSERT INTO "City" VALUES(21241, 'Obrovac Sinjski');
INSERT INTO "City" VALUES(21242, 'Grab');
INSERT INTO "City" VALUES(21243, 'Ugljane');
INSERT INTO "City" VALUES(21244, 'Cista Velika');
INSERT INTO "City" VALUES(21245, 'Tijarica');
INSERT INTO "City" VALUES(21246, 'Aržano');
INSERT INTO "City" VALUES(21247, 'Neorić');
INSERT INTO "City" VALUES(21250, 'Šestanovac');
INSERT INTO "City" VALUES(21251, 'Žrnovnica');
INSERT INTO "City" VALUES(21252, 'Tugare');
INSERT INTO "City" VALUES(21253, 'Gata');
INSERT INTO "City" VALUES(21254, 'Blato na Cetini');
INSERT INTO "City" VALUES(21255, 'Zadvarje');
INSERT INTO "City" VALUES(21256, 'Cista Provo');
INSERT INTO "City" VALUES(21257, 'Lovreć');
INSERT INTO "City" VALUES(21260, 'Imotski');
INSERT INTO "City" VALUES(21261, 'Runović');
INSERT INTO "City" VALUES(21262, 'Kamenmost');
INSERT INTO "City" VALUES(21263, 'Krivodol');
INSERT INTO "City" VALUES(21264, 'Donji Proložac');
INSERT INTO "City" VALUES(21265, 'Studenci');
INSERT INTO "City" VALUES(21266, 'Zmijavci');
INSERT INTO "City" VALUES(21267, 'Ričice');
INSERT INTO "City" VALUES(21270, 'Zagvozd');
INSERT INTO "City" VALUES(21271, 'Grabovac');
INSERT INTO "City" VALUES(21272, 'Slivno');
INSERT INTO "City" VALUES(21273, 'Župa');
INSERT INTO "City" VALUES(21275, 'Dragljane');
INSERT INTO "City" VALUES(21276, 'Vrgorac');
INSERT INTO "City" VALUES(21277, 'Veliki Prolog');
INSERT INTO "City" VALUES(21292, 'Srinjine');
INSERT INTO "City" VALUES(21300, 'Makarska');
INSERT INTO "City" VALUES(21310, 'Omiš');
INSERT INTO "City" VALUES(21311, 'Stobreč');
INSERT INTO "City" VALUES(21312, 'Podstrana');
INSERT INTO "City" VALUES(21314, 'Jesenice');
INSERT INTO "City" VALUES(21315, 'Dugi Rat');
INSERT INTO "City" VALUES(21317, 'Lokva Rogoznica');
INSERT INTO "City" VALUES(21318, 'Mimice');
INSERT INTO "City" VALUES(21320, 'Baška Voda');
INSERT INTO "City" VALUES(21322, 'Brela');
INSERT INTO "City" VALUES(21323, 'Promajna');
INSERT INTO "City" VALUES(21325, 'Tučepi');
INSERT INTO "City" VALUES(21327, 'Podgora');
INSERT INTO "City" VALUES(21328, 'Drašnice');
INSERT INTO "City" VALUES(21329, 'Igrane');
INSERT INTO "City" VALUES(21330, 'Gradac');
INSERT INTO "City" VALUES(21333, 'Drvenik');
INSERT INTO "City" VALUES(21334, 'Zaostrog');
INSERT INTO "City" VALUES(21335, 'Podaca');
INSERT INTO "City" VALUES(21400, 'Supetar');
INSERT INTO "City" VALUES(21403, 'Sutivan');
INSERT INTO "City" VALUES(21404, 'Ložišća');
INSERT INTO "City" VALUES(21405, 'Milna');
INSERT INTO "City" VALUES(21410, 'Postira');
INSERT INTO "City" VALUES(21412, 'Pučišća');
INSERT INTO "City" VALUES(21413, 'Povlja');
INSERT INTO "City" VALUES(21420, 'Bol');
INSERT INTO "City" VALUES(21423, 'Nerežišća');
INSERT INTO "City" VALUES(21424, 'Pražnica');
INSERT INTO "City" VALUES(21425, 'Selca');
INSERT INTO "City" VALUES(21426, 'Sumartin');
INSERT INTO "City" VALUES(21430, 'Grohote');
INSERT INTO "City" VALUES(21432, 'Stomorska');
INSERT INTO "City" VALUES(21450, 'Hvar');
INSERT INTO "City" VALUES(21454, 'Brusje');
INSERT INTO "City" VALUES(21460, 'Stari Grad');
INSERT INTO "City" VALUES(21462, 'Vrbanj');
INSERT INTO "City" VALUES(21463, 'Vrboska');
INSERT INTO "City" VALUES(21465, 'Jelsa');
INSERT INTO "City" VALUES(21466, 'Zastražišće');
INSERT INTO "City" VALUES(21467, 'Gdinj');
INSERT INTO "City" VALUES(21468, 'Bogomolje');
INSERT INTO "City" VALUES(21469, 'Sućuraj');
INSERT INTO "City" VALUES(21480, 'Vis');
INSERT INTO "City" VALUES(21483, 'Podšpilje');
INSERT INTO "City" VALUES(21485, 'Komiža');
INSERT INTO "City" VALUES(22000, 'Šibenik');
INSERT INTO "City" VALUES(22010, 'Šibenik-Brodarica');
INSERT INTO "City" VALUES(22020, 'Šibenik-Ražine');
INSERT INTO "City" VALUES(22030, 'Šibenik-Zablaće');
INSERT INTO "City" VALUES(22202, 'Primošten');
INSERT INTO "City" VALUES(22203, 'Rogoznica');
INSERT INTO "City" VALUES(22204, 'Široke');
INSERT INTO "City" VALUES(22205, 'Perković');
INSERT INTO "City" VALUES(22206, 'Boraja');
INSERT INTO "City" VALUES(22211, 'Vodice');
INSERT INTO "City" VALUES(22212, 'Tribunj');
INSERT INTO "City" VALUES(22213, 'Pirovac');
INSERT INTO "City" VALUES(22214, 'Čista Velika');
INSERT INTO "City" VALUES(22215, 'Zaton');
INSERT INTO "City" VALUES(22221, 'Lozovac');
INSERT INTO "City" VALUES(22222, 'Skradin');
INSERT INTO "City" VALUES(22223, 'Bribir(kod Skradina)');
INSERT INTO "City" VALUES(22232, 'Zlarin');
INSERT INTO "City" VALUES(22233, 'Prvić Luka');
INSERT INTO "City" VALUES(22234, 'Prvić Šepurine');
INSERT INTO "City" VALUES(22235, 'Kaprije');
INSERT INTO "City" VALUES(22236, 'Žirje');
INSERT INTO "City" VALUES(22240, 'Tisno');
INSERT INTO "City" VALUES(22242, 'Jezera');
INSERT INTO "City" VALUES(22243, 'Murter');
INSERT INTO "City" VALUES(22244, 'Betina');
INSERT INTO "City" VALUES(22300, 'Knin');
INSERT INTO "City" VALUES(22301, 'Golubić');
INSERT INTO "City" VALUES(22302, 'Polača/kod Knina/');
INSERT INTO "City" VALUES(22303, 'Oklaj');
INSERT INTO "City" VALUES(22304, 'Radučić');
INSERT INTO "City" VALUES(22305, 'Kistanje');
INSERT INTO "City" VALUES(22306, 'Ervenik');
INSERT INTO "City" VALUES(22307, 'Mokro Polje');
INSERT INTO "City" VALUES(22310, 'Kijevo');
INSERT INTO "City" VALUES(22311, 'Strmica');
INSERT INTO "City" VALUES(22312, 'Kosovo/Zvjerinac/');
INSERT INTO "City" VALUES(22317, 'Plavno');
INSERT INTO "City" VALUES(22318, 'Pađene');
INSERT INTO "City" VALUES(22319, 'Đevrske');
INSERT INTO "City" VALUES(22320, 'Drniš');
INSERT INTO "City" VALUES(22321, 'Siverić');
INSERT INTO "City" VALUES(22322, 'Ružić');
INSERT INTO "City" VALUES(22323, 'Unešić');
INSERT INTO "City" VALUES(22324, 'Drinovci');
INSERT INTO "City" VALUES(23000, 'Zadar');
INSERT INTO "City" VALUES(23205, 'Bibinje');
INSERT INTO "City" VALUES(23206, 'Sukošan');
INSERT INTO "City" VALUES(23207, 'Sveti Filip i Jakov');
INSERT INTO "City" VALUES(23210, 'Biograd na moru');
INSERT INTO "City" VALUES(23211, 'Pakoštane');
INSERT INTO "City" VALUES(23212, 'Tkon');
INSERT INTO "City" VALUES(23222, 'Zemunik');
INSERT INTO "City" VALUES(23223, 'Škabrnja');
INSERT INTO "City" VALUES(23226, 'Pridraga');
INSERT INTO "City" VALUES(23231, 'Petrčane');
INSERT INTO "City" VALUES(23232, 'Nin');
INSERT INTO "City" VALUES(23233, 'Privlaka (Dalmacija)');
INSERT INTO "City" VALUES(23234, 'Vir');
INSERT INTO "City" VALUES(23235, 'Vrsi');
INSERT INTO "City" VALUES(23241, 'Poličnik');
INSERT INTO "City" VALUES(23242, 'Posedarje');
INSERT INTO "City" VALUES(23243, 'Jasenice');
INSERT INTO "City" VALUES(23244, 'Starigrad Paklenica');
INSERT INTO "City" VALUES(23245, 'Tribanj');
INSERT INTO "City" VALUES(23247, 'Vinjerac');
INSERT INTO "City" VALUES(23248, 'Ražanac');
INSERT INTO "City" VALUES(23249, 'Povljana');
INSERT INTO "City" VALUES(23250, 'Pag');
INSERT INTO "City" VALUES(23251, 'Kolan');
INSERT INTO "City" VALUES(23262, 'Pašman');
INSERT INTO "City" VALUES(23263, 'Ždrelac');
INSERT INTO "City" VALUES(23264, 'Neviđane');
INSERT INTO "City" VALUES(23271, 'Kukljica');
INSERT INTO "City" VALUES(23272, 'Kali');
INSERT INTO "City" VALUES(23273, 'Preko');
INSERT INTO "City" VALUES(23274, 'Lukoran');
INSERT INTO "City" VALUES(23275, 'Ugljan');
INSERT INTO "City" VALUES(23281, 'Sali');
INSERT INTO "City" VALUES(23282, 'Žman');
INSERT INTO "City" VALUES(23283, 'Rava');
INSERT INTO "City" VALUES(23284, 'Veli Iž');
INSERT INTO "City" VALUES(23285, 'Brbinj');
INSERT INTO "City" VALUES(23286, 'Božava');
INSERT INTO "City" VALUES(23287, 'Veli Rat');
INSERT INTO "City" VALUES(23291, 'Sestrunj');
INSERT INTO "City" VALUES(23292, 'Molat');
INSERT INTO "City" VALUES(23293, 'Ist');
INSERT INTO "City" VALUES(23294, 'Premuda');
INSERT INTO "City" VALUES(23295, 'Silba');
INSERT INTO "City" VALUES(23296, 'Olib');
INSERT INTO "City" VALUES(23312, 'Novigrad (Dalmacija)');
INSERT INTO "City" VALUES(23420, 'Benkovac');
INSERT INTO "City" VALUES(23421, 'Bjelina');
INSERT INTO "City" VALUES(23422, 'Stankovci');
INSERT INTO "City" VALUES(23423, 'Polača');
INSERT INTO "City" VALUES(23424, 'Smilčić');
INSERT INTO "City" VALUES(23440, 'Gračac');
INSERT INTO "City" VALUES(23441, 'Bruvno');
INSERT INTO "City" VALUES(23442, 'Otrić');
INSERT INTO "City" VALUES(23443, 'Zrmanja');
INSERT INTO "City" VALUES(23445, 'Srb');
INSERT INTO "City" VALUES(23446, 'Kaldrma');
INSERT INTO "City" VALUES(23450, 'Obrovac');
INSERT INTO "City" VALUES(23451, 'Žegar');
INSERT INTO "City" VALUES(23452, 'Karin');
INSERT INTO "City" VALUES(31000, 'Osijek');
INSERT INTO "City" VALUES(31204, 'Bijelo Brdo');
INSERT INTO "City" VALUES(31205, 'Aljmaš');
INSERT INTO "City" VALUES(31206, 'Erdut');
INSERT INTO "City" VALUES(31207, 'Tenja');
INSERT INTO "City" VALUES(31208, 'Petrijevci');
INSERT INTO "City" VALUES(31214, 'Laslovo');
INSERT INTO "City" VALUES(31215, 'Ernestinovo');
INSERT INTO "City" VALUES(31216, 'Antunovac');
INSERT INTO "City" VALUES(31220, 'Višnjevac');
INSERT INTO "City" VALUES(31221, 'Josipovac');
INSERT INTO "City" VALUES(31222, 'Bizovac');
INSERT INTO "City" VALUES(31223, 'Brođanci');
INSERT INTO "City" VALUES(31224, 'Koška');
INSERT INTO "City" VALUES(31225, 'Breznica Našička');
INSERT INTO "City" VALUES(31226, 'Dalj');
INSERT INTO "City" VALUES(31227, 'Zelčin');
INSERT INTO "City" VALUES(31300, 'Beli Manastir');
INSERT INTO "City" VALUES(31301, 'Branjin Vrh');
INSERT INTO "City" VALUES(31302, 'Kneževo');
INSERT INTO "City" VALUES(31303, 'Popovac');
INSERT INTO "City" VALUES(31304, 'Duboševica');
INSERT INTO "City" VALUES(31305, 'Draž');
INSERT INTO "City" VALUES(31306, 'Batina');
INSERT INTO "City" VALUES(31307, 'Zmajevac');
INSERT INTO "City" VALUES(31308, 'Suza');
INSERT INTO "City" VALUES(31309, 'Kneževi Vinogradi');
INSERT INTO "City" VALUES(31315, 'Karanac');
INSERT INTO "City" VALUES(31321, 'Petlovac');
INSERT INTO "City" VALUES(31322, 'Baranjsko Petrovo Selo');
INSERT INTO "City" VALUES(31323, 'Bolman');
INSERT INTO "City" VALUES(31324, 'Jagodnjak');
INSERT INTO "City" VALUES(31325, 'Čeminac');
INSERT INTO "City" VALUES(31326, 'Darda');
INSERT INTO "City" VALUES(31327, 'Bilje');
INSERT INTO "City" VALUES(31328, 'Lug');
INSERT INTO "City" VALUES(31400, 'Đakovo');
INSERT INTO "City" VALUES(31401, 'Viškovci');
INSERT INTO "City" VALUES(31402, 'Semeljci');
INSERT INTO "City" VALUES(31403, 'Vuka');
INSERT INTO "City" VALUES(31404, 'Vladislavci');
INSERT INTO "City" VALUES(31410, 'Strizivojna');
INSERT INTO "City" VALUES(31411, 'Trnava');
INSERT INTO "City" VALUES(31415, 'Selci Đakovački');
INSERT INTO "City" VALUES(31416, 'Levanjska Varoš');
INSERT INTO "City" VALUES(31417, 'Piškorevci');
INSERT INTO "City" VALUES(31418, 'Drenje');
INSERT INTO "City" VALUES(31421, 'Satnica Đakovačka');
INSERT INTO "City" VALUES(31422, 'Gorjani');
INSERT INTO "City" VALUES(31423, 'Bračevci');
INSERT INTO "City" VALUES(31424, 'Punitovci');
INSERT INTO "City" VALUES(31431, 'Čepin');
INSERT INTO "City" VALUES(31432, 'Budimci');
INSERT INTO "City" VALUES(31433, 'Podgorač');
INSERT INTO "City" VALUES(31500, 'Našice');
INSERT INTO "City" VALUES(31511, 'Đurđenovac');
INSERT INTO "City" VALUES(31512, 'Feričanci');
INSERT INTO "City" VALUES(31513, 'Donja Motičina');
INSERT INTO "City" VALUES(31530, 'Podravska Moslavina');
INSERT INTO "City" VALUES(31531, 'Viljevo');
INSERT INTO "City" VALUES(31540, 'Donji Miholjac');
INSERT INTO "City" VALUES(31542, 'Magadenovac');
INSERT INTO "City" VALUES(31543, 'Miholjački Poreč');
INSERT INTO "City" VALUES(31550, 'Valpovo');
INSERT INTO "City" VALUES(31551, 'Belišće');
INSERT INTO "City" VALUES(31552, 'Podgajci Podravski');
INSERT INTO "City" VALUES(31553, 'Črnkovci');
INSERT INTO "City" VALUES(31554, 'Gat');
INSERT INTO "City" VALUES(31555, 'Marijanci');
INSERT INTO "City" VALUES(32000, 'Vukovar');
INSERT INTO "City" VALUES(32010, 'Vukovar');
INSERT INTO "City" VALUES(32100, 'Vinkovci');
INSERT INTO "City" VALUES(32211, 'Ostrovo');
INSERT INTO "City" VALUES(32212, 'Gaboš');
INSERT INTO "City" VALUES(32213, 'Markušica');
INSERT INTO "City" VALUES(32214, 'Tordinci');
INSERT INTO "City" VALUES(32221, 'Nuštar');
INSERT INTO "City" VALUES(32222, 'Bršadin');
INSERT INTO "City" VALUES(32224, 'Trpinja');
INSERT INTO "City" VALUES(32225, 'Bobota');
INSERT INTO "City" VALUES(32227, 'Borovo');
INSERT INTO "City" VALUES(32229, 'Petrovci');
INSERT INTO "City" VALUES(32232, 'Sotin');
INSERT INTO "City" VALUES(32233, 'Opatovac');
INSERT INTO "City" VALUES(32234, 'Šarengrad');
INSERT INTO "City" VALUES(32235, 'Bapska');
INSERT INTO "City" VALUES(32236, 'Ilok');
INSERT INTO "City" VALUES(32237, 'Lovas');
INSERT INTO "City" VALUES(32238, 'Čakovci');
INSERT INTO "City" VALUES(32239, 'Negoslavci');
INSERT INTO "City" VALUES(32241, 'Stari Jankovci');
INSERT INTO "City" VALUES(32242, 'Slakovci');
INSERT INTO "City" VALUES(32243, 'Orolik');
INSERT INTO "City" VALUES(32244, 'Đeletovci');
INSERT INTO "City" VALUES(32245, 'Nijemci');
INSERT INTO "City" VALUES(32246, 'Lipovac');
INSERT INTO "City" VALUES(32247, 'Banovci');
INSERT INTO "City" VALUES(32248, 'Ilača');
INSERT INTO "City" VALUES(32249, 'Tovarnik');
INSERT INTO "City" VALUES(32251, 'Privlaka');
INSERT INTO "City" VALUES(32252, 'Otok');
INSERT INTO "City" VALUES(32253, 'Komletinci');
INSERT INTO "City" VALUES(32254, 'Vrbanja');
INSERT INTO "City" VALUES(32255, 'Soljani');
INSERT INTO "City" VALUES(32256, 'Strošinci');
INSERT INTO "City" VALUES(32257, 'Drenovci');
INSERT INTO "City" VALUES(32258, 'Posavski Podgajci');
INSERT INTO "City" VALUES(32260, 'Gunja');
INSERT INTO "City" VALUES(32261, 'Rajevo Selo');
INSERT INTO "City" VALUES(32262, 'Račinovci');
INSERT INTO "City" VALUES(32263, 'Đurići');
INSERT INTO "City" VALUES(32270, 'Županja');
INSERT INTO "City" VALUES(32271, 'Rokovci Andrijaševci');
INSERT INTO "City" VALUES(32272, 'Cerna');
INSERT INTO "City" VALUES(32273, 'Gradište');
INSERT INTO "City" VALUES(32274, 'Štitar');
INSERT INTO "City" VALUES(32275, 'Bošnjaci');
INSERT INTO "City" VALUES(32276, 'Babina Greda');
INSERT INTO "City" VALUES(32280, 'Jarmina');
INSERT INTO "City" VALUES(32281, 'Ivankovo');
INSERT INTO "City" VALUES(32282, 'Retkovci');
INSERT INTO "City" VALUES(32283, 'Vođinci');
INSERT INTO "City" VALUES(32284, 'Stari Mikanovci');
INSERT INTO "City" VALUES(33000, 'Virovitica');
INSERT INTO "City" VALUES(33404, 'Špišić Bukovica');
INSERT INTO "City" VALUES(33405, 'Pitomača');
INSERT INTO "City" VALUES(33406, 'Lukač');
INSERT INTO "City" VALUES(33407, 'Gornje Bazje');
INSERT INTO "City" VALUES(33410, 'Suhopolje');
INSERT INTO "City" VALUES(33411, 'Gradina');
INSERT INTO "City" VALUES(33412, 'Cabuna');
INSERT INTO "City" VALUES(33507, 'Crnac');
INSERT INTO "City" VALUES(33513, 'Zdenci');
INSERT INTO "City" VALUES(33514, 'Čačinci');
INSERT INTO "City" VALUES(33515, 'Orahovica');
INSERT INTO "City" VALUES(33516, 'Slatinski Drenovac');
INSERT INTO "City" VALUES(33517, 'Mikleuš');
INSERT INTO "City" VALUES(33518, 'Nova Bukovica');
INSERT INTO "City" VALUES(33520, 'Slatina');
INSERT INTO "City" VALUES(33521, 'Ćeralije');
INSERT INTO "City" VALUES(33522, 'Voćin');
INSERT INTO "City" VALUES(33523, 'Čađavica');
INSERT INTO "City" VALUES(33525, 'Sopje');
INSERT INTO "City" VALUES(33533, 'Pivnica Slavonska');
INSERT INTO "City" VALUES(34000, 'Požega');
INSERT INTO "City" VALUES(34308, 'Jakšić');
INSERT INTO "City" VALUES(34310, 'Pleternica');
INSERT INTO "City" VALUES(34311, 'Kuzmica');
INSERT INTO "City" VALUES(34312, 'Sesvete (kod Požege)');
INSERT INTO "City" VALUES(34315, 'Ratkovica');
INSERT INTO "City" VALUES(34320, 'Orljavac');
INSERT INTO "City" VALUES(34322, 'Brestovac');
INSERT INTO "City" VALUES(34330, 'Velika');
INSERT INTO "City" VALUES(34334, 'Kaptol');
INSERT INTO "City" VALUES(34335, 'Vetovo');
INSERT INTO "City" VALUES(34340, 'Kutjevo');
INSERT INTO "City" VALUES(34343, 'Bektež');
INSERT INTO "City" VALUES(34350, 'Čaglin');
INSERT INTO "City" VALUES(34543, 'Poljana');
INSERT INTO "City" VALUES(34550, 'Pakrac');
INSERT INTO "City" VALUES(34551, 'Lipik');
INSERT INTO "City" VALUES(34552, 'Badljevina');
INSERT INTO "City" VALUES(34553, 'Bučje');
INSERT INTO "City" VALUES(35000, 'Slavonski Brod');
INSERT INTO "City" VALUES(35105, 'Slavonski Brod');
INSERT INTO "City" VALUES(35106, 'Slavonski Brod');
INSERT INTO "City" VALUES(35107, 'Podvinje');
INSERT INTO "City" VALUES(35201, 'Podcrkavlje');
INSERT INTO "City" VALUES(35207, 'Gornja Vrba');
INSERT INTO "City" VALUES(35208, 'Ruščica');
INSERT INTO "City" VALUES(35209, 'Bukovlje');
INSERT INTO "City" VALUES(35210, 'Vrpolje');
INSERT INTO "City" VALUES(35211, 'Trnjani');
INSERT INTO "City" VALUES(35212, 'Garčin');
INSERT INTO "City" VALUES(35213, 'Oprisavci');
INSERT INTO "City" VALUES(35214, 'Donji Andrijevci');
INSERT INTO "City" VALUES(35215, 'Svilaj');
INSERT INTO "City" VALUES(35216, 'Prnjavor');
INSERT INTO "City" VALUES(35220, 'Slavonski Šamac');
INSERT INTO "City" VALUES(35221, 'Velika Kopanica');
INSERT INTO "City" VALUES(35222, 'Gundinci');
INSERT INTO "City" VALUES(35224, 'Sikirevci');
INSERT INTO "City" VALUES(35250, 'Oriovac');
INSERT INTO "City" VALUES(35252, 'Sibinj');
INSERT INTO "City" VALUES(35253, 'Brodski Stupnik');
INSERT INTO "City" VALUES(35254, 'Bebrina');
INSERT INTO "City" VALUES(35255, 'Slavonski Kobaš');
INSERT INTO "City" VALUES(35257, 'Lužani');
INSERT INTO "City" VALUES(35400, 'Nova Gradiška');
INSERT INTO "City" VALUES(35403, 'Rešetari');
INSERT INTO "City" VALUES(35404, 'Cernik');
INSERT INTO "City" VALUES(35410, 'Nova Kapela');
INSERT INTO "City" VALUES(35414, 'Vrbova');
INSERT INTO "City" VALUES(35420, 'Staro Petrovo Selo');
INSERT INTO "City" VALUES(35422, 'Zapolje');
INSERT INTO "City" VALUES(35423, 'Vrbje');
INSERT INTO "City" VALUES(35424, 'Orubica');
INSERT INTO "City" VALUES(35425, 'Davor');
INSERT INTO "City" VALUES(35428, 'Dragalić');
INSERT INTO "City" VALUES(35429, 'Gornji Bogićevci');
INSERT INTO "City" VALUES(35430, 'Okučani');
INSERT INTO "City" VALUES(35432, 'Medari');
INSERT INTO "City" VALUES(35435, 'Stara Gradiška');
INSERT INTO "City" VALUES(40000, 'Čakovec');
INSERT INTO "City" VALUES(40305, 'Nedelišče');
INSERT INTO "City" VALUES(40306, 'Macinec');
INSERT INTO "City" VALUES(40311, 'Lopatinec');
INSERT INTO "City" VALUES(40312, 'Štrigova');
INSERT INTO "City" VALUES(40313, 'Sveti Martin na Muri');
INSERT INTO "City" VALUES(40314, 'Selnica');
INSERT INTO "City" VALUES(40315, 'Mursko Središče');
INSERT INTO "City" VALUES(40316, 'Vratišinec');
INSERT INTO "City" VALUES(40317, 'Podturen');
INSERT INTO "City" VALUES(40318, 'Dekanovec');
INSERT INTO "City" VALUES(40319, 'Belica');
INSERT INTO "City" VALUES(40320, 'Donji Kraljevec');
INSERT INTO "City" VALUES(40321, 'Mala Subotica');
INSERT INTO "City" VALUES(40322, 'Orehovica');
INSERT INTO "City" VALUES(40323, 'Prelog');
INSERT INTO "City" VALUES(40324, 'Goričan');
INSERT INTO "City" VALUES(40325, 'Draškovec');
INSERT INTO "City" VALUES(40326, 'Sveta Marija');
INSERT INTO "City" VALUES(40327, 'Donji Vidovec');
INSERT INTO "City" VALUES(40328, 'Donja Dubrava');
INSERT INTO "City" VALUES(40329, 'Kotoriba');
INSERT INTO "City" VALUES(42000, 'Varaždin');
INSERT INTO "City" VALUES(42201, 'Beretinec');
INSERT INTO "City" VALUES(42202, 'Trnovec Bartolovečki');
INSERT INTO "City" VALUES(42203, 'Jalžabet');
INSERT INTO "City" VALUES(42204, 'Turčin');
INSERT INTO "City" VALUES(42205, 'Vidovec');
INSERT INTO "City" VALUES(42206, 'Petrijanec');
INSERT INTO "City" VALUES(42207, 'Vinica');
INSERT INTO "City" VALUES(42208, 'Cestica');
INSERT INTO "City" VALUES(42209, 'Sračinec');
INSERT INTO "City" VALUES(42214, 'Sveti Ilija');
INSERT INTO "City" VALUES(42220, 'Novi Marof');
INSERT INTO "City" VALUES(42222, 'Ljubeščica');
INSERT INTO "City" VALUES(42223, 'Varaždinske Toplice');
INSERT INTO "City" VALUES(42224, 'Visoko');
INSERT INTO "City" VALUES(42225, 'Breznički Hum');
INSERT INTO "City" VALUES(42226, 'Bisag');
INSERT INTO "City" VALUES(42230, 'Ludbreg');
INSERT INTO "City" VALUES(42231, 'Mali Bukovec');
INSERT INTO "City" VALUES(42232, 'Donji Martijanec');
INSERT INTO "City" VALUES(42233, 'Sveti Đurđ');
INSERT INTO "City" VALUES(42240, 'Ivanec');
INSERT INTO "City" VALUES(42242, 'Radovan');
INSERT INTO "City" VALUES(42243, 'Maruševec');
INSERT INTO "City" VALUES(42244, 'Klenovnik');
INSERT INTO "City" VALUES(42245, 'Donja Voća');
INSERT INTO "City" VALUES(42250, 'Lepoglava');
INSERT INTO "City" VALUES(42253, 'Bednja');
INSERT INTO "City" VALUES(42254, 'Trakošćan');
INSERT INTO "City" VALUES(42255, 'Donja Višnjica');
INSERT INTO "City" VALUES(43000, 'Bjelovar');
INSERT INTO "City" VALUES(43202, 'Zrinski Topolovac');
INSERT INTO "City" VALUES(43203, 'Kapela');
INSERT INTO "City" VALUES(43211, 'Predavac');
INSERT INTO "City" VALUES(43212, 'Rovišće');
INSERT INTO "City" VALUES(43226, 'Veliko Trojstvo');
INSERT INTO "City" VALUES(43227, 'Šandrovac');
INSERT INTO "City" VALUES(43231, 'Ivanska');
INSERT INTO "City" VALUES(43232, 'Berek');
INSERT INTO "City" VALUES(43233, 'Trnovitički Popovac');
INSERT INTO "City" VALUES(43240, 'Čazma');
INSERT INTO "City" VALUES(43245, 'Gornji Draganec');
INSERT INTO "City" VALUES(43246, 'Štefanje');
INSERT INTO "City" VALUES(43247, 'Narta');
INSERT INTO "City" VALUES(43251, 'Gudovac');
INSERT INTO "City" VALUES(43252, 'Prgomelje');
INSERT INTO "City" VALUES(43270, 'Veliki Grđevac');
INSERT INTO "City" VALUES(43271, 'Velika Pisanica');
INSERT INTO "City" VALUES(43272, 'Nova Rača');
INSERT INTO "City" VALUES(43273, 'Bulinac');
INSERT INTO "City" VALUES(43274, 'Severin');
INSERT INTO "City" VALUES(43280, 'Garešnica');
INSERT INTO "City" VALUES(43282, 'Veliko Vukovje');
INSERT INTO "City" VALUES(43283, 'Kaniška Iva');
INSERT INTO "City" VALUES(43284, 'Hercegovac');
INSERT INTO "City" VALUES(43285, 'Velika Trnovitica');
INSERT INTO "City" VALUES(43290, 'Grubišno Polje');
INSERT INTO "City" VALUES(43293, 'Veliki Zdenci');
INSERT INTO "City" VALUES(43500, 'Daruvar');
INSERT INTO "City" VALUES(43504, 'Ivanovo Selo');
INSERT INTO "City" VALUES(43505, 'Končanica');
INSERT INTO "City" VALUES(43506, 'Dežanovac');
INSERT INTO "City" VALUES(43507, 'Uljanik');
INSERT INTO "City" VALUES(43531, 'Veliki Bastaji');
INSERT INTO "City" VALUES(43532, 'Đulovac');
INSERT INTO "City" VALUES(43541, 'Sirač');
INSERT INTO "City" VALUES(44000, 'Sisak');
INSERT INTO "City" VALUES(44010, 'Sisak-Caprag');
INSERT INTO "City" VALUES(44201, 'Martinska Ves');
INSERT INTO "City" VALUES(44202, 'Topolovac');
INSERT INTO "City" VALUES(44203, 'Gušće');
INSERT INTO "City" VALUES(44204, 'Jabukovac');
INSERT INTO "City" VALUES(44205, 'Donja Bačuga');
INSERT INTO "City" VALUES(44210, 'Sunja');
INSERT INTO "City" VALUES(44211, 'Blinjski Kut');
INSERT INTO "City" VALUES(44212, 'Mala Gradusa');
INSERT INTO "City" VALUES(44213, 'Kratečko');
INSERT INTO "City" VALUES(44214, 'Bobovac');
INSERT INTO "City" VALUES(44221, 'Staza');
INSERT INTO "City" VALUES(44222, 'Šaš');
INSERT INTO "City" VALUES(44231, 'Blinja');
INSERT INTO "City" VALUES(44250, 'Petrinja');
INSERT INTO "City" VALUES(44251, 'Gora');
INSERT INTO "City" VALUES(44253, 'Mošćenica');
INSERT INTO "City" VALUES(44271, 'Letovanić');
INSERT INTO "City" VALUES(44272, 'Lekenik');
INSERT INTO "City" VALUES(44273, 'Sela');
INSERT INTO "City" VALUES(44316, 'Velika Ludina');
INSERT INTO "City" VALUES(44317, 'Popovača');
INSERT INTO "City" VALUES(44318, 'Voloder');
INSERT INTO "City" VALUES(44320, 'Kutina');
INSERT INTO "City" VALUES(44321, 'Banova Jaruga');
INSERT INTO "City" VALUES(44322, 'Lipovljani');
INSERT INTO "City" VALUES(44323, 'Rajić');
INSERT INTO "City" VALUES(44324, 'Jasenovac');
INSERT INTO "City" VALUES(44325, 'Krapje');
INSERT INTO "City" VALUES(44330, 'Novska');
INSERT INTO "City" VALUES(44400, 'Glina');
INSERT INTO "City" VALUES(44401, 'Hajtić');
INSERT INTO "City" VALUES(44402, 'Mali Obljaj');
INSERT INTO "City" VALUES(44403, 'Maja');
INSERT INTO "City" VALUES(44404, 'Gornji Klasnić');
INSERT INTO "City" VALUES(44405, 'Mali Gradac');
INSERT INTO "City" VALUES(44406, 'Vlahović');
INSERT INTO "City" VALUES(44410, 'Gvozd');
INSERT INTO "City" VALUES(44412, 'Stankovac');
INSERT INTO "City" VALUES(44414, 'Bović');
INSERT INTO "City" VALUES(44415, 'Topusko');
INSERT INTO "City" VALUES(44425, 'Gornja Bučica');
INSERT INTO "City" VALUES(44430, 'Hrvatska Kostajnica');
INSERT INTO "City" VALUES(44431, 'Donji Kukuruzari');
INSERT INTO "City" VALUES(44432, 'Mečenčani');
INSERT INTO "City" VALUES(44433, 'Majur');
INSERT INTO "City" VALUES(44434, 'Graboštani');
INSERT INTO "City" VALUES(44435, 'Divuša');
INSERT INTO "City" VALUES(44437, 'Rujevac');
INSERT INTO "City" VALUES(44440, 'Dvor');
INSERT INTO "City" VALUES(44441, 'Brđani Šamarički');
INSERT INTO "City" VALUES(44443, 'Donji Žirovac');
INSERT INTO "City" VALUES(44450, 'Hrvatska Dubica');
INSERT INTO "City" VALUES(47000, 'Karlovac');
INSERT INTO "City" VALUES(47201, 'Draganići');
INSERT INTO "City" VALUES(47203, 'Rečica');
INSERT INTO "City" VALUES(47204, 'Šišljavić');
INSERT INTO "City" VALUES(47205, 'Vukmanić');
INSERT INTO "City" VALUES(47206, 'Lasinja');
INSERT INTO "City" VALUES(47211, 'Utinja');
INSERT INTO "City" VALUES(47212, 'Skakavac');
INSERT INTO "City" VALUES(47213, 'Sjeničak Lasinjski');
INSERT INTO "City" VALUES(47220, 'Vojnić');
INSERT INTO "City" VALUES(47221, 'Krstinja');
INSERT INTO "City" VALUES(47222, 'Cetingrad');
INSERT INTO "City" VALUES(47240, 'Slunj');
INSERT INTO "City" VALUES(47241, 'Tušilović');
INSERT INTO "City" VALUES(47242, 'Krnjak');
INSERT INTO "City" VALUES(47243, 'Veljun');
INSERT INTO "City" VALUES(47244, 'Primišlje');
INSERT INTO "City" VALUES(47245, 'Rakovica');
INSERT INTO "City" VALUES(47246, 'Drežnik Grad');
INSERT INTO "City" VALUES(47250, 'Duga Resa');
INSERT INTO "City" VALUES(47251, 'Bosiljevo');
INSERT INTO "City" VALUES(47252, 'Barilović');
INSERT INTO "City" VALUES(47253, 'Perjasica');
INSERT INTO "City" VALUES(47261, 'Zvečaj');
INSERT INTO "City" VALUES(47262, 'Generalski Stol');
INSERT INTO "City" VALUES(47263, 'Gornje Dubrave');
INSERT INTO "City" VALUES(47264, 'Tounj');
INSERT INTO "City" VALUES(47271, 'Netretić');
INSERT INTO "City" VALUES(47272, 'Ribnik');
INSERT INTO "City" VALUES(47273, 'Srednje Prilišće');
INSERT INTO "City" VALUES(47276, 'Žakanje');
INSERT INTO "City" VALUES(47280, 'Ozalj');
INSERT INTO "City" VALUES(47281, 'Mali Erjavec');
INSERT INTO "City" VALUES(47282, 'Kamanje');
INSERT INTO "City" VALUES(47283, 'Vivodina');
INSERT INTO "City" VALUES(47284, 'Kašt');
INSERT INTO "City" VALUES(47285, 'Radatovići');
INSERT INTO "City" VALUES(47286, 'Mahično');
INSERT INTO "City" VALUES(47300, 'Ogulin');
INSERT INTO "City" VALUES(47302, 'Oštarije');
INSERT INTO "City" VALUES(47303, 'Josipdol');
INSERT INTO "City" VALUES(47304, 'Plaški');
INSERT INTO "City" VALUES(47305, 'Lička Jesenica');
INSERT INTO "City" VALUES(47306, 'Saborsko');
INSERT INTO "City" VALUES(47307, 'Gornje Zagorje');
INSERT INTO "City" VALUES(47313, 'Drežnica');
INSERT INTO "City" VALUES(47314, 'Jasenak');
INSERT INTO "City" VALUES(48000, 'Koprivnica');
INSERT INTO "City" VALUES(48213, 'Cirkvena');
INSERT INTO "City" VALUES(48214, 'Sveti Ivan Žabno');
INSERT INTO "City" VALUES(48260, 'Križevci');
INSERT INTO "City" VALUES(48263, 'Carevdar');
INSERT INTO "City" VALUES(48264, 'Kloštar Vojakovački');
INSERT INTO "City" VALUES(48265, 'Raven');
INSERT INTO "City" VALUES(48267, 'Orehovec');
INSERT INTO "City" VALUES(48268, 'Gornja Rijeka');
INSERT INTO "City" VALUES(48269, 'Kalnik');
INSERT INTO "City" VALUES(48305, 'Reka');
INSERT INTO "City" VALUES(48306, 'Sokolovac');
INSERT INTO "City" VALUES(48311, 'Kunovec');
INSERT INTO "City" VALUES(48312, 'Rasinja');
INSERT INTO "City" VALUES(48314, 'Koprivnički Ivanec');
INSERT INTO "City" VALUES(48316, 'Đelekovec');
INSERT INTO "City" VALUES(48317, 'Legrad');
INSERT INTO "City" VALUES(48321, 'Peteranec');
INSERT INTO "City" VALUES(48322, 'Drnje');
INSERT INTO "City" VALUES(48323, 'Hlebine');
INSERT INTO "City" VALUES(48324, 'Koprivnički Bregi');
INSERT INTO "City" VALUES(48325, 'Novigrad Podravski');
INSERT INTO "City" VALUES(48326, 'Virje');
INSERT INTO "City" VALUES(48327, 'Molve');
INSERT INTO "City" VALUES(48331, 'Gola');
INSERT INTO "City" VALUES(48332, 'Ždala');
INSERT INTO "City" VALUES(48350, 'Đurđevac');
INSERT INTO "City" VALUES(48355, 'Novo Virje');
INSERT INTO "City" VALUES(48356, 'Ferdinandovac');
INSERT INTO "City" VALUES(48361, 'Kalinovac');
INSERT INTO "City" VALUES(48362, 'Kloštar Podravski');
INSERT INTO "City" VALUES(48363, 'Podravske Sesvete');
INSERT INTO "City" VALUES(49000, 'Krapina');
INSERT INTO "City" VALUES(49210, 'Zabok');
INSERT INTO "City" VALUES(49214, 'Veliko Trgovišće');
INSERT INTO "City" VALUES(49215, 'Tuhelj');
INSERT INTO "City" VALUES(49216, 'Desinić');
INSERT INTO "City" VALUES(49217, 'Krapinske Toplice');
INSERT INTO "City" VALUES(49218, 'Pregrada');
INSERT INTO "City" VALUES(49221, 'Bedekovčina');
INSERT INTO "City" VALUES(49222, 'Poznanovec');
INSERT INTO "City" VALUES(49223, 'Sveti Križ Začretje');
INSERT INTO "City" VALUES(49224, 'Lepajci');
INSERT INTO "City" VALUES(49225, 'Đurmanec');
INSERT INTO "City" VALUES(49228, 'Brestovec Orehovički');
INSERT INTO "City" VALUES(49231, 'Hum na Sutli');
INSERT INTO "City" VALUES(49232, 'Radoboj');
INSERT INTO "City" VALUES(49233, 'Gornje Jesenje');
INSERT INTO "City" VALUES(49234, 'Petrovsko');
INSERT INTO "City" VALUES(49240, 'Donja Stubica');
INSERT INTO "City" VALUES(49243, 'Oroslavje');
INSERT INTO "City" VALUES(49244, 'Stubičke Toplice');
INSERT INTO "City" VALUES(49245, 'Gornja Stubica');
INSERT INTO "City" VALUES(49246, 'Marija Bistrica');
INSERT INTO "City" VALUES(49247, 'Zlatar Bistrica');
INSERT INTO "City" VALUES(49250, 'Zlatar');
INSERT INTO "City" VALUES(49251, 'Mače');
INSERT INTO "City" VALUES(49252, 'Mihovljan');
INSERT INTO "City" VALUES(49253, 'Lobor');
INSERT INTO "City" VALUES(49254, 'Belec');
INSERT INTO "City" VALUES(49255, 'Novi Golubovec');
INSERT INTO "City" VALUES(49282, 'Konjščina');
INSERT INTO "City" VALUES(49283, 'Hraščina-Trgovišće');
INSERT INTO "City" VALUES(49284, 'Budinšćina');
INSERT INTO "City" VALUES(49290, 'Klanjec');
INSERT INTO "City" VALUES(49294, 'Kraljevec na Sutli');
INSERT INTO "City" VALUES(49295, 'Kumrovec');
INSERT INTO "City" VALUES(49296, 'Zagorska Sela');
INSERT INTO "City" VALUES(51000, 'Rijeka');
INSERT INTO "City" VALUES(51211, 'Matulji');
INSERT INTO "City" VALUES(51212, 'Vele Mune');
INSERT INTO "City" VALUES(51213, 'Jurdani');
INSERT INTO "City" VALUES(51214, 'Šapjane');
INSERT INTO "City" VALUES(51215, 'Kastav');
INSERT INTO "City" VALUES(51216, 'Viškovo');
INSERT INTO "City" VALUES(51217, 'Klana');
INSERT INTO "City" VALUES(51218, 'Dražice');
INSERT INTO "City" VALUES(51219, 'Čavle');
INSERT INTO "City" VALUES(51221, 'Kostrena');
INSERT INTO "City" VALUES(51222, 'Bakar');
INSERT INTO "City" VALUES(51223, 'Škrljevo');
INSERT INTO "City" VALUES(51224, 'Krasica');
INSERT INTO "City" VALUES(51225, 'Praputnjak');
INSERT INTO "City" VALUES(51226, 'Hreljin');
INSERT INTO "City" VALUES(51227, 'Kukuljanovo');
INSERT INTO "City" VALUES(51241, 'Križišće');
INSERT INTO "City" VALUES(51242, 'Drivenik');
INSERT INTO "City" VALUES(51243, 'Tribalj');
INSERT INTO "City" VALUES(51244, 'Grižane');
INSERT INTO "City" VALUES(51250, 'Novi Vinodolski');
INSERT INTO "City" VALUES(51251, 'Ledenice');
INSERT INTO "City" VALUES(51252, 'Klenovica');
INSERT INTO "City" VALUES(51253, 'Bribir');
INSERT INTO "City" VALUES(51260, 'Crikvenica');
INSERT INTO "City" VALUES(51261, 'Bakarac');
INSERT INTO "City" VALUES(51262, 'Kraljevica');
INSERT INTO "City" VALUES(51263, 'Šmrika');
INSERT INTO "City" VALUES(51264, 'Jadranovo');
INSERT INTO "City" VALUES(51265, 'Dramalj');
INSERT INTO "City" VALUES(51266, 'Selce');
INSERT INTO "City" VALUES(51280, 'Rab');
INSERT INTO "City" VALUES(51281, 'Lopar');
INSERT INTO "City" VALUES(51300, 'Delnice');
INSERT INTO "City" VALUES(51301, 'Brod na Kupi');
INSERT INTO "City" VALUES(51302, 'Kuželj');
INSERT INTO "City" VALUES(51303, 'Plešce');
INSERT INTO "City" VALUES(51304, 'Gerovo');
INSERT INTO "City" VALUES(51305, 'Tršće');
INSERT INTO "City" VALUES(51306, 'Čabar');
INSERT INTO "City" VALUES(51307, 'Prezid');
INSERT INTO "City" VALUES(51311, 'Skrad');
INSERT INTO "City" VALUES(51312, 'Brod Moravice');
INSERT INTO "City" VALUES(51313, 'Kupjak');
INSERT INTO "City" VALUES(51314, 'Ravna Gora');
INSERT INTO "City" VALUES(51315, 'Mrkopalj');
INSERT INTO "City" VALUES(51316, 'Lokve');
INSERT INTO "City" VALUES(51317, 'Crni Lug');
INSERT INTO "City" VALUES(51321, 'Vrata');
INSERT INTO "City" VALUES(51322, 'Fužine');
INSERT INTO "City" VALUES(51323, 'Lič');
INSERT INTO "City" VALUES(51324, 'Zlobin');
INSERT INTO "City" VALUES(51325, 'Moravice');
INSERT INTO "City" VALUES(51326, 'Vrbovsko');
INSERT INTO "City" VALUES(51327, 'Gomirje');
INSERT INTO "City" VALUES(51328, 'Lukovdol');
INSERT INTO "City" VALUES(51329, 'Severin na Kupi');
INSERT INTO "City" VALUES(51410, 'Opatija');
INSERT INTO "City" VALUES(51414, 'Ičići');
INSERT INTO "City" VALUES(51415, 'Lovran');
INSERT INTO "City" VALUES(51417, 'Mošćenička Draga');
INSERT INTO "City" VALUES(51418, 'Brseč');
INSERT INTO "City" VALUES(51500, 'Krk');
INSERT INTO "City" VALUES(51511, 'Malinska');
INSERT INTO "City" VALUES(51512, 'Njivice');
INSERT INTO "City" VALUES(51513, 'Omišalj');
INSERT INTO "City" VALUES(51514, 'Dobrinj');
INSERT INTO "City" VALUES(51515, 'Šilo');
INSERT INTO "City" VALUES(51516, 'Vrbnik');
INSERT INTO "City" VALUES(51517, 'Kornić');
INSERT INTO "City" VALUES(51521, 'Punat');
INSERT INTO "City" VALUES(51522, 'Draga Bašćanska');
INSERT INTO "City" VALUES(51523, 'Baška');
INSERT INTO "City" VALUES(51550, 'Mali Lošinj');
INSERT INTO "City" VALUES(51551, 'Veli Lošinj');
INSERT INTO "City" VALUES(51552, 'Ilovik');
INSERT INTO "City" VALUES(51554, 'Nerezine');
INSERT INTO "City" VALUES(51555, 'Belej');
INSERT INTO "City" VALUES(51556, 'Martinšćica');
INSERT INTO "City" VALUES(51557, 'Cres');
INSERT INTO "City" VALUES(51559, 'Beli');
INSERT INTO "City" VALUES(51561, 'Susak');
INSERT INTO "City" VALUES(51562, 'Unije');
INSERT INTO "City" VALUES(51564, 'Ćunski');
INSERT INTO "City" VALUES(52000, 'Pazin');
INSERT INTO "City" VALUES(52100, 'Pula');
INSERT INTO "City" VALUES(52203, 'Medulin');
INSERT INTO "City" VALUES(52204, 'Ližnjan (Lisignano)');
INSERT INTO "City" VALUES(52206, 'Marčana');
INSERT INTO "City" VALUES(52207, 'Barban');
INSERT INTO "City" VALUES(52208, 'Krnica');
INSERT INTO "City" VALUES(52210, 'Rovinj (Rovigno)');
INSERT INTO "City" VALUES(52211, 'Bale (Valle)');
INSERT INTO "City" VALUES(52212, 'Fažana');
INSERT INTO "City" VALUES(52215, 'Vodnjan (Dignano)');
INSERT INTO "City" VALUES(52216, 'Galižana (Gallesano)');
INSERT INTO "City" VALUES(52220, 'Labin');
INSERT INTO "City" VALUES(52221, 'Rabac');
INSERT INTO "City" VALUES(52222, 'Koromačno');
INSERT INTO "City" VALUES(52223, 'Raša');
INSERT INTO "City" VALUES(52224, 'Trget');
INSERT INTO "City" VALUES(52231, 'Nedešćina');
INSERT INTO "City" VALUES(52232, 'Kršan');
INSERT INTO "City" VALUES(52233, 'Šušnjevica');
INSERT INTO "City" VALUES(52234, 'Plomin');
INSERT INTO "City" VALUES(52332, 'Pićan');
INSERT INTO "City" VALUES(52333, 'Podpićan');
INSERT INTO "City" VALUES(52341, 'Žminj');
INSERT INTO "City" VALUES(52342, 'Svetvinčenat');
INSERT INTO "City" VALUES(52352, 'Kanfanar');
INSERT INTO "City" VALUES(52402, 'Cerovlje');
INSERT INTO "City" VALUES(52403, 'Gračišće');
INSERT INTO "City" VALUES(52404, 'Sveti Petar u šumi');
INSERT INTO "City" VALUES(52420, 'Buzet');
INSERT INTO "City" VALUES(52422, 'Lanišće');
INSERT INTO "City" VALUES(52423, 'Karojba');
INSERT INTO "City" VALUES(52424, 'Motovun');
INSERT INTO "City" VALUES(52425, 'Roč');
INSERT INTO "City" VALUES(52426, 'Lupoglav');
INSERT INTO "City" VALUES(52427, 'Livade');
INSERT INTO "City" VALUES(52428, 'Oprtalj (Portole)');
INSERT INTO "City" VALUES(52429, 'Grožnjan (Grisignana)');
INSERT INTO "City" VALUES(52434, 'Boljun');
INSERT INTO "City" VALUES(52440, 'Poreč');
INSERT INTO "City" VALUES(52444, 'Tinjan');
INSERT INTO "City" VALUES(52445, 'Baderna');
INSERT INTO "City" VALUES(52446, 'Nova Vas');
INSERT INTO "City" VALUES(52447, 'Vižinada');
INSERT INTO "City" VALUES(52448, 'Sveti Lovreč');
INSERT INTO "City" VALUES(52449, 'Červar Porat');
INSERT INTO "City" VALUES(52450, 'Vrsar');
INSERT INTO "City" VALUES(52452, 'Funtana');
INSERT INTO "City" VALUES(52460, 'Buje (Buie)');
INSERT INTO "City" VALUES(52462, 'Momjan (Momiano)');
INSERT INTO "City" VALUES(52463, 'Višnjan');
INSERT INTO "City" VALUES(52464, 'Kaštelir');
INSERT INTO "City" VALUES(52465, 'Tar');
INSERT INTO "City" VALUES(52466, 'Novigrad (Cittanova)');
INSERT INTO "City" VALUES(52470, 'Umag (Umago)');
INSERT INTO "City" VALUES(52474, 'Brtonigla (Verteneglio)');
INSERT INTO "City" VALUES(52475, 'Savudrija (Salvore)');
INSERT INTO "City" VALUES(53000, 'Gospić');
INSERT INTO "City" VALUES(53201, 'Lički Osik');
INSERT INTO "City" VALUES(53202, 'Perušić');
INSERT INTO "City" VALUES(53203, 'Kosinj');
INSERT INTO "City" VALUES(53205, 'Medak');
INSERT INTO "City" VALUES(53206, 'Brušane');
INSERT INTO "City" VALUES(53211, 'Smiljan');
INSERT INTO "City" VALUES(53212, 'Klanac');
INSERT INTO "City" VALUES(53213, 'Donje Pazarište');
INSERT INTO "City" VALUES(53220, 'Otočac');
INSERT INTO "City" VALUES(53221, 'Škare');
INSERT INTO "City" VALUES(53222, 'Dabar');
INSERT INTO "City" VALUES(53223, 'Vrhovine');
INSERT INTO "City" VALUES(53224, 'Ličko Lešće');
INSERT INTO "City" VALUES(53225, 'Švica');
INSERT INTO "City" VALUES(53226, 'Kompolje');
INSERT INTO "City" VALUES(53230, 'Korenica');
INSERT INTO "City" VALUES(53231, 'Plitvička Jezera');
INSERT INTO "City" VALUES(53233, 'Ličko Petrovo Selo');
INSERT INTO "City" VALUES(53234, 'Udbina');
INSERT INTO "City" VALUES(53235, 'Bunić');
INSERT INTO "City" VALUES(53236, 'Podlapača');
INSERT INTO "City" VALUES(53244, 'Lovinac');
INSERT INTO "City" VALUES(53250, 'Donji Lapac');
INSERT INTO "City" VALUES(53251, 'Nebljusi');
INSERT INTO "City" VALUES(53252, 'Doljani');
INSERT INTO "City" VALUES(53260, 'Brinje');
INSERT INTO "City" VALUES(53261, 'Križpolje');
INSERT INTO "City" VALUES(53262, 'Jezerane');
INSERT INTO "City" VALUES(53270, 'Senj');
INSERT INTO "City" VALUES(53271, 'Krivi Put');
INSERT INTO "City" VALUES(53273, 'Vratnik');
INSERT INTO "City" VALUES(53274, 'Krasno');
INSERT INTO "City" VALUES(53284, 'Sveti Juraj');
INSERT INTO "City" VALUES(53287, 'Jablanac');
INSERT INTO "City" VALUES(53288, 'Karlobag');
INSERT INTO "City" VALUES(53289, 'Lukovo Šugarje');
INSERT INTO "City" VALUES(53291, 'Novalja');
INSERT INTO "City" VALUES(53294, 'Lun');
INSERT INTO "City" VALUES(53296, 'Zubovići');


INSERT INTO "EventTypeList" VALUES(DEFAULT, 'Borrow');
INSERT INTO "EventTypeList" VALUES(DEFAULT, 'Return');
INSERT INTO "EventTypeList" VALUES(DEFAULT, 'Transfer');

INSERT INTO "GenreList" VALUES(DEFAULT, 'Unknown');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Other');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Komedija');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Tragedija');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Drama');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Ep');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Pripovijetka');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Novela');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Roman');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Zbirka pjesama');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Časopis');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Esej');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Putopis');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Basna');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Bajka');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Životopis');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Udžbenik');
INSERT INTO "GenreList" VALUES(DEFAULT, 'Enciklopedija');

INSERT INTO "LanguageList" VALUES(DEFAULT, 'Unknown');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Other');

INSERT INTO "LanguageList" VALUES(DEFAULT, 'Hrvatski');

INSERT INTO "LanguageList" VALUES(DEFAULT, 'Engleski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Njemačkiki');

INSERT INTO "LanguageList" VALUES(DEFAULT, 'Srpski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Češki');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Slovački');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Poljski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Ruski');

INSERT INTO "LanguageList" VALUES(DEFAULT, 'Francuski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Talijanski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Španjolski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Portugalski');

INSERT INTO "LanguageList" VALUES(DEFAULT, 'Danski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Nizozemski');

INSERT INTO "LanguageList" VALUES(DEFAULT, 'Finski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Norveški');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Švedski');

INSERT INTO "LanguageList" VALUES(DEFAULT, 'Mađarski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Rumunjski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Bugarski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Makedonski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Albanski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Grčki');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Turski');

INSERT INTO "LanguageList" VALUES(DEFAULT, 'Kineski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Japanski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Arapski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Hindski');
INSERT INTO "LanguageList" VALUES(DEFAULT, 'Pandžapski');

INSERT INTO "LocationList" VALUES(DEFAULT, 'Library');
INSERT INTO "LocationList" VALUES(DEFAULT, 'User');

