CREATE TABLE libraries (
    id int not null AUTO_INCREMENT,
    name varchar(100) not null,
    PRIMARY KEY ( ID )
);

CREATE TABLE authors (
    id int not null AUTO_INCREMENT,
    name varchar(100) not null,
    PRIMARY KEY ( ID )
);

CREATE TABLE books (
    id int not null AUTO_INCREMENT,
    name varchar(100) not null,
    author_id int not null,
    PRIMARY KEY ( ID )
);

CREATE TABLE libraries_books (
    id int not null AUTO_INCREMENT,
    library_id int not null,
    book_id int not null,
    PRIMARY KEY ( ID )
);