# qp-assessment
Grocery Booking API

There are tqo types of user ADMIN and USER. ADMIN can access all the APIs abd USER can access APIs which falls under UserController.

Apply below query in db to add user before start testing the APIs:(not created "register" API)
INSERT INTO `` (`id`,`address`,`email`,`password`,`username`,`role`) VALUES (1,'gujarat','first@test.in','$2a$10$80AqCb30YD95T4bplyydsuldc94FD.Y1laWcVh75fjEJPnTM4pUAG','firsttest','USER');
INSERT INTO `` (`id`,`address`,`email`,`password`,`username`,`role`) VALUES (2,'gujarat','second@test.in','$2a$10$80AqCb30YD95T4bplyydsuldc94FD.Y1laWcVh75fjEJPnTM4pUAG','secondtest','ADMIN');

User 1 : 
username : "first@test.in"
password : 123456
type : USER

User 2 : 
username : "second@test.in"
password : 123456
type : ADMIN

