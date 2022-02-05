SELECT id, clientID, startReservation, endReservation, bookID, isCompleted
FROM Orders
where clientID = (?)
order by id