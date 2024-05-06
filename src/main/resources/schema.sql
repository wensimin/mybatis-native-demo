CREATE TABLE messages
(
    id           bigint auto_increment,
    message      text,
    user_id      bigint,
    message_type varchar(20),
    create_user  varchar(50),
    update_user  varchar(50)
);

CREATE TABLE sys_user
(
    id       bigint auto_increment,
    username varchar(50)
);