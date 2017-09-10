CREATE TABLE "User" (
	"UserID" serial NOT NULL,
	"FirstName" TEXT NOT NULL,
	"MiddleName" TEXT,
	"LastName" TEXT NOT NULL,
	"AddressID" int NOT NULL,
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
	"ReturnDate" DATE,
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

