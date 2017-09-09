INSERT INTO "Address" VALUES(1, 'Croatia', '10000', 'Vlaška', '24a');
INSERT INTO "Address" VALUES(2, 'Croatia', '10430', 'Podravska', '72');
INSERT INTO "Address" VALUES(3, 'Croatia', '21000', NULL, '10');
INSERT INTO "Address" VALUES(4, 'Croatia', '22222', 'Zvonimirova', NULL);
INSERT INTO "Address" VALUES(5, 'Croatia', '31000', NULL, NULL);
INSERT INTO "Address" VALUES(6, 'Croatia', '31511', 'Petrova', '52c');
INSERT INTO "Address" VALUES(7, 'Croatia', '42205', 'Ilica', NULL);
INSERT INTO "Address" VALUES(8, 'Croatia', '52444', 'Maksimirska', '14');

INSERT INTO "Library" VALUES(1, 'Knjižnica Vladimira Nazora', 1, '+38516352970', NULL, 'Uđite na stražnja vrata.'):
INSERT INTO "Library" VALUES(2, 'Knjižnica Petra Preradovića', 2, '01/2960-558', 'petra.preradovica@knjiznice.hr', NULL):
INSERT INTO "Library" VALUES(3, 'Knjižnica Boško Buha', 8, '+385915480622', 'bosko.buha@libraries.com', 'Na Božić nećemo raditi.'):
INSERT INTO "Library" VALUES(4, 'Online knjižnica Evolucija tehnologije', NULL '+38523631974', 'evolucija-tehnologije@online-knjiznica.com', 'Dostavljamo na vaše zadovoljstvo i trošak.'):

INSERT INTO "Location" VALUES(DEFAULT, 1, 1, NULL);
INSERT INTO "Location" VALUES(DEFAULT, 1, 2, NULL);
INSERT INTO "Location" VALUES(DEFAULT, 1, 3, NULL);
INSERT INTO "Location" VALUES(DEFAULT, 1, 4, NULL);

INSERT INTO "BusinessHours" VALUES(1, 1, FALSE, '08:00:00', '20:00:00');
INSERT INTO "BusinessHours" VALUES(1, 2, FALSE, '08:00:00', '20:00:00');
INSERT INTO "BusinessHours" VALUES(1, 3, FALSE, '08:00:00', '20:00:00');
INSERT INTO "BusinessHours" VALUES(1, 4, FALSE, '08:00:00', '20:00:00');
INSERT INTO "BusinessHours" VALUES(1, 5, FALSE, '08:00:00', '16:00:00');
INSERT INTO "BusinessHours" VALUES(1, 6, TRUE,  '00:00:00', '00:00:00');
INSERT INTO "BusinessHours" VALUES(1, 7, TRUE,  '00:00:00', '00:00:00');

INSERT INTO "BusinessHours" VALUES(2, 1, FALSE, '07:15:00', '18:30:00');
INSERT INTO "BusinessHours" VALUES(2, 2, FALSE, '07:15:00', '18:30:00');
INSERT INTO "BusinessHours" VALUES(2, 3, FALSE, '07:15:00', '18:30:00');
INSERT INTO "BusinessHours" VALUES(2, 4, FALSE, '07:15:00', '18:30:00');
INSERT INTO "BusinessHours" VALUES(2, 5, FALSE, '07:15:00', '18:00:00');
INSERT INTO "BusinessHours" VALUES(2, 6, FALSE, '08:30:00', '14:30:00');
INSERT INTO "BusinessHours" VALUES(2, 7, FALSE, '08:45:00', '14:15:00');

INSERT INTO "BusinessHours" VALUES(3, 1, FALSE, '09:00:00', '17:00:00');
INSERT INTO "BusinessHours" VALUES(3, 2, FALSE, '09:00:00', '17:00:00');
INSERT INTO "BusinessHours" VALUES(3, 3, FALSE, '09:00:00', '17:00:00');
INSERT INTO "BusinessHours" VALUES(3, 4, FALSE, '09:00:00', '17:00:00');
INSERT INTO "BusinessHours" VALUES(3, 5, FALSE, '09:00:00', '17:00:00');
INSERT INTO "BusinessHours" VALUES(3, 6, FALSE, '10:00:00', '13:00:00');
INSERT INTO "BusinessHours" VALUES(3, 7, TRUE,  '00:00:00', '00:00:00');

INSERT INTO "BusinessHours" VALUES(4, 1, FALSE, '00:00:00', '23:59:00');
INSERT INTO "BusinessHours" VALUES(4, 2, FALSE, '00:00:00', '23:59:00');
INSERT INTO "BusinessHours" VALUES(4, 3, FALSE, '00:00:00', '23:59:00');
INSERT INTO "BusinessHours" VALUES(4, 4, FALSE, '00:00:00', '23:59:00');
INSERT INTO "BusinessHours" VALUES(4, 5, FALSE, '00:00:00', '23:59:00');
INSERT INTO "BusinessHours" VALUES(4, 6, FALSE, '00:00:00', '23:59:00');
INSERT INTO "BusinessHours" VALUES(4, 7, FALSE, '00:00:00', '23:59:00');

INSERT INTO "User" VALUES(1, 'Jurica', 'Petar', 'Paterović', 5, NULL, 'jurica-petar.pater@gmail.com');
INSERT INTO "User" VALUES(2, 'Chuck', NULL, 'Norris', 4, '095/7162-666', 'gmail@chuck-norris.com');
INSERT INTO "User" VALUES(3, 'Matea', NULL, 'Kalašić', NULL, '+38523973367', 'matea-kalasic@fer.hr');
INSERT INTO "User" VALUES(4, 'Željko', NULL, 'Kerum', 3, '021/7777-777', 'zeljko.kerum@gradonacelnik-svijeta.hr');
INSERT INTO "User" VALUES(5, 'Ana', 'Marija', 'Anastazović', NULL, NULL, 'ana-marija.anastazovic@yahoo.com');

INSERT INTO "Location" VALUES(DEFAULT, 2, NULL, 1);
INSERT INTO "Location" VALUES(DEFAULT, 2, NULL, 2);
INSERT INTO "Location" VALUES(DEFAULT, 2, NULL, 3);
INSERT INTO "Location" VALUES(DEFAULT, 2, NULL, 4);
INSERT INTO "Location" VALUES(DEFAULT, 2, NULL, 5);
