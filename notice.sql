use test;

create table board_info_table(
		board_info_idx int primary key,
        board_info_name varchar(100) not null
);

insert into board_info_table
values (1, '공지사항');
insert into board_info_table
values (2, '유머게시판');
insert into board_info_table
values (3, '자유게시판');
insert into board_info_table
values (4, '게임게시판');

select * from board_info_table;

create table user_table(
	user_idx int auto_increment primary key,
    user_name varchar(50) not null,
    user_id varchar(100) not null,
    user_pw varchar(100) not null,
    user_add1 varchar(100) not null,
    user_add2 varchar(100) not null,
    user_add3 varchar(100) not null,
    user_num varchar(11) not null
);

create table content_table(
	content_idx int auto_increment primary key,
    content_ttl varchar(100) not null,
    content_cont varchar(500) not null,
    content_file varchar(100),
    content_writer_idx int not null,
    content_board_idx int not null,
    content_ymd timestamp default current_timestamp not null,
    foreign key (content_writer_idx) references user_table (user_idx),
    foreign key (content_board_idx) references board_info_table(board_info_idx)
);

desc content_table; 

alter table user_table
add user_num varchar(11) not null;

desc user_table;