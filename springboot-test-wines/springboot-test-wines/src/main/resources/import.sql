INSERT INTO wines (name, winery,año) VALUES ('Espumante, CASA BOHER', 'CASA BOHER',2015);

INSERT INTO owners (name, apellido) VALUES ('Juan', 'Perez');
INSERT INTO owners (name, apellido) VALUES ('Maria', 'Garcia');


INSERT INTO wine_owners (wine_id, owner_id) VALUES (1, 1);
INSERT INTO wine_owners (wine_id, owner_id) VALUES (1, 2);

-- Agregar las relaciones en la tabla wine_owners
--INSERT INTO wine_owners (wine_id, owner_id) VALUES (1, 1); -- 1 es el ID del vino y 1 es el ID del primer propietario (Juan Cruz Grattone)
--INSERT INTO wine_owners (wine_id, owner_id) VALUES (1, 2); -- 1 es el ID del vino y 2 es el ID del segundo propietario (Agustin Acosta)