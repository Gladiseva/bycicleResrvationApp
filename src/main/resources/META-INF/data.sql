INSERT INTO employee (id, name, surname) VALUES (nextval('employee_seq'),'Ann', 'White');
INSERT INTO bicycle (id, manufacturer, model, year_produced, breakages) VALUES (nextval('bicycle_seq'),'manufacturer1', 'model1', '2010', 'two');
INSERT INTO bicycle_reservation (id, employee_id, bicycle_id, start_usage_date, end_usage_date) VALUES (nextval('reservation_seq'), currval('employee_seq'), currval('bicycle_seq'), '2018-03-16 12:00:00.0000', '2018-03-17 23:00:00.0000');
INSERT INTO bicycle_reservation (id, employee_id, bicycle_id, start_usage_date, end_usage_date) VALUES (nextval('reservation_seq'), currval('employee_seq'), currval('bicycle_seq'), '2018-03-18 12:00:00.0000', '2018-03-19 23:00:00.0000');



INSERT INTO employee (id, name, surname) VALUES (nextval('employee_seq'),'Alex', 'Black');
INSERT INTO bicycle (id, manufacturer, model, year_produced, breakages) VALUES (nextval('bicycle_seq'),'manufacturer2', 'model2', '2000', 'none');
INSERT INTO bicycle_reservation (id, employee_id, bicycle_id, start_usage_date, end_usage_date) VALUES (nextval('reservation_seq'), currval('employee_seq'), currval('bicycle_seq'), '2018-03-20 12:00:00.0000', '2018-03-21 23:00:00.0000');
INSERT INTO bicycle_reservation (id, employee_id, bicycle_id, start_usage_date, end_usage_date) VALUES (nextval('reservation_seq'), currval('employee_seq'), currval('bicycle_seq'), '2018-04-16 12:00:00.0000', '2018-04-17 23:00:00.0000');