insert into users values(100, 'test1', 'test1', 'test1', 'b444ac06613fc8d63795be9ad0beaf55011936ac');
insert into users values(200, 'test2', 'test2', 'test2', '109f4b3c50d7b0df729d299bc6f8e9ef9066971f');
insert into users values(300, 'test3', 'test3', 'test3', '3ebfa301dc59196f18593c45e519287a23297589');
insert into users values(400, 'test4', 'test4', 'test4', '1ff2b3704aede04eecb51e50ca698efd50a1379b');
insert into users values(500, 'test5', 'test5', 'test5', '911ddc3b8f9a13b5499b6bc4638a2b4f3f68bf23');
insert into users values(600, 'test6', 'test6', 'test6', 'a66df261120b6c2311c6ef0b1bab4e583afcbcc0');

insert into ranking values(100, 2, 20, 100);
insert into ranking values(200, 2, 18, 200);
insert into ranking values(300, 2, 26, 300);
insert into ranking values(400, 1, 10, 400);
insert into ranking values(500, 2, 21, 500);
insert into ranking values(600, 2, 21, 600);

insert into administrators values(10, 'admin', 'admin');

insert into challenges values(10, 'Description1', '10 10', 'name1', 'pass1', NULL , 10, 'qwe', true);
insert into hints values(100, 5, NULL, 'hint11', 10);
insert into hints values(200, 10, NULL, 'hint12', 10);

insert into challenges values(20, 'Description2', '10 10', 'name2', 'pass2', NULL, 10, 'qwe', true);
insert into hints values(300, 5, NULL, 'hint21', 20);
insert into hints values(400, 10, NULL, 'hint22', 20);

insert into challenges values(30, 'Description3', '5 10', 'name3', 'pass3', NULL, 10, 'qwe', true);
insert into hints values(500, 5, NULL, 'hint31', 30);
insert into hints values(600, 10, NULL, 'hint32', 30);

insert into challenges values(40, 'Description4', '10 5', 'name4', 'pass4', NULL, 10, 'qwe', true);
insert into hints values(700, 5, NULL, 'hint41', 40);
insert into hints values(800, 10, NULL, 'hint42', 40);

insert into challenges values(50, 'Description5', '10 100', 'name5', 'pass5', NULL, 10, 'qwe', true);
insert into hints values(900, 5, NULL, 'hint51', 50);
insert into hints values(1000, 10, NULL, 'hint52', 50);