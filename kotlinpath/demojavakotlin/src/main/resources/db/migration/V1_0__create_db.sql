CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public."movie"
(
    id          uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        text NOT NULL UNIQUE,
    director    text NOT NULL,
    description text
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

INSERT INTO movie (name, director, description)
VALUES ('Batman', 'Tim Burton', 'The Batman is a 2022 American superhero film based on the DC Comics character');

INSERT INTO ticket (price, quantity, movie_id, user_id)
VALUES (1.99, 100, (SELECT id FROM movie WHERE name = 'Batman'), null);
