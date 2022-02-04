CREATE TABLE Orders (
    id INTEGER NOT NULL,
    clientID INTEGER NOT NULL,
    startReservation VARCHAR (30) NOT NULL,
    endReservation VARCHAR (30) NOT NULL,
    bookID INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (clientID) REFERENCES Clients(id),
    FOREIGN KEY (bookID) REFERENCES Books(id)
)