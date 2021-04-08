-- user 생성
CREATE USER 'study'@'localhost' IDENTIFIED BY '1111';
CREATE USER 'study'@'%' IDENTIFIED BY '1111';

-- database 생성
CREATE DATABASE documentdb CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 권한부여
GRANT ALL ON documentdb.* TO 'study'@'localhost';

-- =================================================

-- 테이블삭제
drop table if exists docm_member;
drop table if exists docm_vacation;
drop table if exists docm_resignation;

-- =================================================

-- Member 테이블 생성
create table docm_member (
    no int not null,
    name varchar(30) not null,
    age int not null,
    dep int not null,
    pos int not null,
    cdt datetime default now(),
    photo varchar(255)
);

-- pk 설정
alter table docm_member
    add constraint docm_member_pk primary key(no);

-- no 컬럼 자동증가설정
alter table docm_member
    modify column no int not null auto_increment;

-- =================================================

-- Vacation 테이블 설정
create table docm_vacation (
    no int not null,
    writer int not null,
    vacation int not null,
    sdt date not null,
    edt date not null,
    contents text not null,
    appr int default 0
);

-- no 컬럼 자동증가설정
alter table docm_vacation
    modify column no int not null auto_increment;

-- pk 설정
alter table docm_vacation
    add constraint docm_vacation_pk primary key(no);

-- fk 설정
alter table docm_vacation
    add constraint docm_vacation_fk foreign key(writer) references docm_member(no);

-- =================================================

-- Resignation 테이블 설정
create table docm_resignation (
    no int not null,
    writer int not null,
    odt date not null,
    contents text not null,
    appr int default 0
);

-- no 컬럼 자동증가설정
alter table docm_resignation
    modify column no int not null auto_increment;

-- pk 설정
alter table docm_resignation
    add constraint docm_resignation_pk primary key(no);

-- fk 설정
alter table docm_resignation
    add constraint docm_resignation_fk foreign key(writer) references docm_member(no);

-- =================================================

-- 가상 데이터
insert into docm_member(name, age, dep, pos, cdt, photo)
    values('홍길동', 24, 3, 5, 2016-3-2, '1.jpg');
insert into docm_member(name, age, dep, pos, cdt, photo)
    values('박효신', 32, 2, 3, 2010-7-7, '2.jpg');
insert into docm_member(name, age, dep, pos, cdt, photo)
    values('김연아', 32, 1, 4, 2019-11-4, '4.jpg');
insert into docm_member(name, age, dep, pos, cdt, photo)
    values('공유', 40, 2, 2, 2007-4-28, '3.jpg');
insert into docm_member(name, age, dep, pos, photo)
    values('아이유', 29, 2, 5, '5.jpg');

insert into docm_vacation(wrtier, vacation, sdt, edt, contents)
    values(2, 1, 2013-8-1, 2013-8-5, '뜨거운 햇살을 피하기 위한 여름 휴가');
insert into docm_vacation(wrtier, vacation, sdt, edt, contents)
    values(4, 3, 2020-10-7, 2020-10-7, '나이때문인지 허리통증으로 통원치료');
insert into docm_vacation(wrtier, vacation, sdt, edt, contents)
    values(2, 1, 2019-4-30, 2019-5-8, '날이 좋아서, 날이 좋지 않아서 제주도 여행');
insert into docm_vacation(wrtier, vacation, sdt, edt, contents)
    values(1, 2, 2017-6-6, 2017-6-6, '아버지를 아버지라 부르기 위해 선물 픽업');
insert into docm_vacation(wrtier, vacation, sdt, edt, contents)
    values(3, 3, 2021-2-25, 2020-2-25, '틀어진 체형으로 체형교정을 위한 치료');
insert into docm_vacation(wrtier, vacation, sdt, edt, contents)
    values(4, 4, 2018-4-4, 2018-4-4, '저승사자같은 친구 조문');

insert into docm_resignation(wrtier, odt, contents)
    values(3, 2021-3-29, '로또 당첨되서 취미로 다니다가 도저히 못다니겠음');