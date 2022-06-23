CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public."movie"
(
    id               uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name             text NOT NULL UNIQUE,
    director         text NOT NULL,
    description      text,
    tickets_quantity bigint,
    ticket_price     double precision
);

CREATE TABLE IF NOT EXISTS public."users"
(
    id    uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    money double precision,
    name  text NOT NULL
);

CREATE TABLE IF NOT EXISTS public."ticket"
(
    id       uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    price    double precision NOT NULL,
    quantity bigint,
    movie_id uuid             NOT NULL
        CONSTRAINT movie_id_fkey REFERENCES movie (id),
    user_id  uuid
        CONSTRAINT user_id_fkey REFERENCES users (id)
);

INSERT INTO users (money, name)
VALUES (300, 'Homer Simpson');

INSERT INTO movie (name, director, description, tickets_quantity, ticket_price)
VALUES ('Batman', 'Tim Burton', 'The Batman is a 2022 American superhero film based on the DC Comics character', 250,
        1.99);

INSERT INTO movie(name, director, description, tickets_quantity, ticket_price)
VALUES ('The Matrix Resurrections', 'Lana Wachowski',
        'Return to a world of two realities: one, everyday life; the other, what lies behind it. To find out if his reality' ||
        ' is a construct, to truly know himself, Mr. Anderson will have to choose to follow the white rabbit once more.',
        300, 1.99);

INSERT INTO ticket (price, quantity, movie_id, user_id)
VALUES (1.99, 1, (SELECT id FROM movie WHERE name = 'Batman'), (SELECT id FROM users WHERE name = 'Homer Simpson'));

INSERT INTO ticket (price, quantity, movie_id, user_id)
VALUES (1.99, 1, (SELECT id FROM movie WHERE name = 'The Matrix Resurrections'),
        (SELECT id FROM users WHERE name = 'Homer Simpson'));
