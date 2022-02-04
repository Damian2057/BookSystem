CREATE TABLE Books (
    id INTEGER NOT NULL,
    title VARCHAR(50) NOT NULL,
    AuthorID INTEGER NOT NULL,
    publicationDate VARCHAR (30) NOT NULL,
    pageCount INTEGER NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (AuthorID) REFERENCES Authors(id)
)