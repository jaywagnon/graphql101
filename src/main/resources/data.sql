INSERT INTO authors VALUES (1, 'Jane Austen');
INSERT INTO authors VALUES (2, 'Charles Dickens');
INSERT INTO authors VALUES (3, 'Mary Shelley');
INSERT INTO authors VALUES (4, 'Louisa May Alcott');
INSERT INTO authors VALUES (5, 'H. G. Wells');
INSERT INTO authors VALUES (6, 'Lewis Carroll');

INSERT INTO books VALUES (1, 'Pride and Prejudice', 1);
INSERT INTO books VALUES (2, 'Persuasion', 1);
INSERT INTO books VALUES (3, 'Great Expectations', 2);
INSERT INTO books VALUES (4, 'A Christmas Carol', 2);
INSERT INTO books VALUES (5, 'Frankenstein', 3);
INSERT INTO books VALUES (6, 'Little Women', 4);
INSERT INTO books VALUES (7, 'The Invisible Man', 5);
INSERT INTO books VALUES (8, 'The Time Machine', 5);
INSERT INTO books VALUES (9, 'Alice''s Adventures in Wonderland & Through the Looking-Glass', 6);

INSERT INTO libraries VALUES (1, 'Salt Lake City Library');
INSERT INTO libraries VALUES (2, 'Ogden Library');
INSERT INTO libraries VALUES (3, 'Provo Library');
INSERT INTO libraries VALUES (4, 'Logan Library');

INSERT INTO libraries_books (library_id, book_id) VALUES (1, 1);
INSERT INTO libraries_books (library_id, book_id) VALUES (1, 2);
INSERT INTO libraries_books (library_id, book_id) VALUES (1, 3);
INSERT INTO libraries_books (library_id, book_id) VALUES (1, 4);
INSERT INTO libraries_books (library_id, book_id) VALUES (1, 5);
INSERT INTO libraries_books (library_id, book_id) VALUES (1, 6);
INSERT INTO libraries_books (library_id, book_id) VALUES (1, 7);
INSERT INTO libraries_books (library_id, book_id) VALUES (1, 8);
INSERT INTO libraries_books (library_id, book_id) VALUES (1, 9);

INSERT INTO libraries_books (library_id, book_id) VALUES (2, 1);
INSERT INTO libraries_books (library_id, book_id) VALUES (2, 5);
INSERT INTO libraries_books (library_id, book_id) VALUES (2, 6);
INSERT INTO libraries_books (library_id, book_id) VALUES (2, 8);
INSERT INTO libraries_books (library_id, book_id) VALUES (2, 9);

INSERT INTO libraries_books (library_id, book_id) VALUES (3, 4);
INSERT INTO libraries_books (library_id, book_id) VALUES (3, 5);
INSERT INTO libraries_books (library_id, book_id) VALUES (3, 6);
INSERT INTO libraries_books (library_id, book_id) VALUES (3, 7);

INSERT INTO libraries_books (library_id, book_id) VALUES (4, 3);
INSERT INTO libraries_books (library_id, book_id) VALUES (4, 5);
INSERT INTO libraries_books (library_id, book_id) VALUES (4, 7);
