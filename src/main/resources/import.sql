-- won`t do nothng cause we have flyway and stuff

insert into ROLES (id, role) values (0, 'ROLE_ADMIN');
insert into ROLES (id, role) values (1, 'ROLE_USER');
insert into ROLES (id, role) values (2, 'ROLE_SPECIAL');
insert into ROLES (id, role) values (3, 'ROLE_ANONYMOUS');


insert into USERS (id,username, password  ) values (7,'admin', '4015bc9ee91e437d90df83fb64fbbe312d9c9f05');

insert into USERS (id,username, password ) values (8,'user1', 'user1');
insert into USERS (id,username, password,enabled ) values (9,'asd', 'asd',true);
insert into USERS (id,username, password ) values (10,' 6  import.sql from resurces .sql', 'insert.sql');
