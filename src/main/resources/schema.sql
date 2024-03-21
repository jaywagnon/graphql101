CREATE TABLE authors (
    id int not null AUTO_INCREMENT,
    name varchar(100) not null,
    PRIMARY KEY ( ID )
);

CREATE TABLE books (
    id int not null AUTO_INCREMENT,
    name varchar(100) not null,
    genre varchar(100) not null,
    author_id int not null,
    PRIMARY KEY ( ID )
);
