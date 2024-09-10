INSERT INTO books (id, updated_at, author, isbn_code, title, number_of_copies) VALUES (1, '2024-08-24 12:00:00.000000', 'Dante Alighieri', '978-8817124013', 'La Divina Commedia', 2), (2, '2024-08-24 12:01:00.000000', 'Alessandro Manzoni', '978-8804668230', 'I Promessi Sposi', 4), (3, '2024-08-24 12:02:00.000000', 'Giacomo Leopardi', '978-8804683714', 'Canti', 4), (4, '2024-08-24 12:03:00.000000', 'Giovanni Boccaccio', '978-8804677508', 'Il Decameron', 4), (5, '2024-08-24 12:04:00.000000', 'Niccolò Machiavelli', '978-8817050138', 'Il Principe', 4)
INSERT INTO rooms (id, name, available, capacity) VALUES (1, 'Dante', true, 20), (2, 'Ariosto', true, 30), (3, 'Tasso', true, 15), (4, 'Manzoni', false, 13);
INSERT INTO borrowings (id, borrowing_date, return_date, book_id) VALUES (1, '2024-08-24', null, 1), (2, '2024-08-25', null, 1), (3, '2024-08-20', '2024-08-25', 1), (4, '2024-08-20', '2024-08-25', 2), (5, '2024-08-25', null, 3);