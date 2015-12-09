INSERT INTO grades (date_end, date_start, date_update, name) VALUES ('2016-09-18', '2014-10-08', NOW(), 'RIL14');
INSERT INTO grades (date_end, date_start, date_update, name) VALUES ('2016-09-18', '2014-10-08', NOW(), 'RAR14');

INSERT INTO classrooms (id, name, nbPlace) VALUES (1, 'Lacanau', 20);
INSERT INTO classrooms (id, name, nbPlace) VALUES (2, 'Biarritz', 30);
INSERT INTO classrooms (id, name, nbPlace) VALUES (3, 'Bordeaux', 12);

INSERT INTO cops (id, date_last_sync, date_update, name, classroom_id) VALUES (1, NOW(), NOW(), 'COP Lacanau', 1);
INSERT INTO cops (id, date_last_sync, date_update, name, classroom_id) VALUES (2, NOW(), NOW(), 'COP Biarritz', 2);
INSERT INTO cops (id, date_last_sync, date_update, name, classroom_id) VALUES (3, NOW(), NOW(), 'COP Bordeaux', 3);

INSERT INTO offenders (id, date_update, email, firstname, lastname, type, grade_id)
  VALUES (1, NOW(), 'ribera.maxime@gmail.com', 'Maxime', 'Ribera', 'STUDENT', 1);
INSERT INTO offenders (id, date_update, email, firstname, lastname, type, grade_id)
  VALUES (2, NOW(), 'arthur.soze@gmail.com', 'Arthur', 'Sore', 'STUDENT', 1);
INSERT INTO offenders (id, date_update, email, firstname, lastname, type, grade_id)
  VALUES (3, NOW(), 'benjamin@kolapsis.fr', 'Benjamin', 'Touchard', 'TEACHER', null);