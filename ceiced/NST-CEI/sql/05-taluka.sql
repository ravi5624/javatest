INSERT INTO talukas(id,taluka_code, taluka_name, district_id) VALUES (NEXTVAL('districts_seq'),'VDR', 'Vadodara', (select id from districts where district_name = 'Vadodara'));
INSERT INTO talukas(id,taluka_code, taluka_name, district_id) VALUES (NEXTVAL('districts_seq'),'SKD', 'Sankheda', (select id from districts where district_name = 'Vadodara'));
INSERT INTO talukas(id,taluka_code, taluka_name, district_id) VALUES (NEXTVAL('districts_seq'),'DBI', 'Dabhoi', (select id from districts where district_name = 'Vadodara'));
INSERT INTO talukas(id,taluka_code, taluka_name, district_id) VALUES (NEXTVAL('districts_seq'),'NWD', 'Naswadi', (select id from districts where district_name = 'Vadodara'));
INSERT INTO talukas(id,taluka_code, taluka_name, district_id) VALUES (NEXTVAL('districts_seq'),'MRB', 'Morbi', (select id from districts where district_name = 'Vadodara'));
INSERT INTO talukas(id, taluka_code, taluka_name, district_id) VALUES (NEXTVAL('districts_seq'), 'CRS', 'Chorasi', (select id from districts where district_name = 'Surat'));
INSERT INTO talukas(id,  taluka_code, taluka_name, district_id) VALUES (NEXTVAL('districts_seq'),'BDL', 'Bardoli', (select id from districts where district_name = 'Surat'));
INSERT INTO talukas(id,  taluka_code, taluka_name, district_id) VALUES (NEXTVAL('districts_seq'),'MGL', 'Mangrol', (select id from districts where district_name = 'Surat'));
