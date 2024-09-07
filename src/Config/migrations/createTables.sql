create table users (
    id  int  primary key ,
    name varchar(255),
    age int
);

CREATE TYPE type_carbon AS ENUM ('TRANSPORT', 'LOGEMENT', 'ALIMENTATION');


CREATE TABLE carbons (
        id SERIAL PRIMARY KEY,
        quantity DOUBLE PRECISION NOT NULL,
        start_date DATE NOT NULL,
        end_date DATE NOT NULL,
        type type_carbon NOT NULL,
        user_id INT REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE transports (
                            id SERIAL PRIMARY KEY ,
                            carbon_id INT,
                            distance_parcourue float NOT NULL,
                            type_de_vehicule VARCHAR(255) NOT NULL,
                            FOREIGN KEY (carbon_id) REFERENCES carbons(id)
);

CREATE TABLE logements (
                           id SERIAL PRIMARY KEY ,
                           carbon_id INT,
                           consommation_energie float NOT NULL,
                           type_energie VARCHAR(255) NOT NULL,
                           FOREIGN KEY (carbon_id) REFERENCES carbons(id)
);


CREATE TABLE alimentations (
                               id SERIAL PRIMARY KEY ,
                               carbon_id INT,
                               type_aliment VARCHAR(255) NOT NULL,
                               poids float NOT NULL,
                               FOREIGN KEY (carbon_id) REFERENCES carbons(id)
);
