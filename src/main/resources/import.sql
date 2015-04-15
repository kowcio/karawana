-- won`t do nothng cause we have flyway and stuff

insert into authorities (id, role) values (0, 'ROLE_ADMIN');
insert into authorities (id, role) values (1, 'ROLE_USER');
insert into authorities (id, role) values (2, 'ROLE_SPECIAL');
insert into authorities (id, role) values (3, 'ROLE_ANONYMOUS');


insert into USERS (id,username, password,enabled  ) values (7,'admin', '$2a$10$gxD/aaa8HLVbC9DjGMyk4OuZfQP3u5FI57JlKeWykfipIWVtKK3LS',true);

insert into USERS (id,username, password ) values (8,'user1', 'user1');
insert into USERS (id,username, password,enabled ) values (9,'asd', '$2a$10$y5ZV/tPREdIDIrD.WIVvZOUlqx9yiQctKTFNCT7Q2f99nX6U4oYaq',true);
insert into USERS (id,username, password ) values (10,' 6  import.sql from resurces .sql', 'insert.sql');
