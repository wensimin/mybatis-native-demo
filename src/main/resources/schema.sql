CREATE TABLE messages(
    id bigint auto_increment,
    message text,
    message_type varchar(20),
    create_user varchar(50),
    update_user varchar(50)
);