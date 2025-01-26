CREATE DATABASE pokemondb;
USE pokemondb;
-- Creación de la tabla Pokemon
CREATE TABLE Pokemon (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Generacion INT NOT NULL CHECK (Generacion BETWEEN 1 AND 4),
    Tipo1 VARCHAR(20) NOT NULL,
    Tipo2 VARCHAR(20),
    HP INT NOT NULL CHECK (HP >= 0),
    Ataque INT NOT NULL CHECK (Ataque >= 0),
    Defensa INT NOT NULL CHECK (Defensa >= 0),
    Velocidad INT NOT NULL CHECK (Velocidad >= 0)
);

-- Tabla de habilidades
CREATE TABLE Habilidad (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Descripcion TEXT NOT NULL
);

-- Tabla de movimientos
CREATE TABLE Movimiento (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Tipo VARCHAR(20) NOT NULL,
    Categoria VARCHAR(20) NOT NULL CHECK (Categoria IN ('Físico', 'Especial', 'Estado')),
    Potencia INT CHECK (Potencia >= 0),
    Precisio INT CHECK (Precisio BETWEEN 1 AND 100 OR Precisio IS NULL),
    PP INT NOT NULL CHECK (PP > 0)
);

-- Relación Pokémon-Habilidades (un Pokémon puede tener varias habilidades)
CREATE TABLE Pokemon_Habilidad (
    PokemonID INT,
    HabilidadID INT,
    PRIMARY KEY (PokemonID, HabilidadID),
    FOREIGN KEY (PokemonID) REFERENCES Pokemon(ID) ON DELETE CASCADE,
    FOREIGN KEY (HabilidadID) REFERENCES Habilidad(ID) ON DELETE CASCADE
);

-- Relación Pokémon-Movimientos (un Pokémon puede aprender varios movimientos)
CREATE TABLE Pokemon_Movimiento (
    PokemonID INT,
    MovimientoID INT,
    PRIMARY KEY (PokemonID, MovimientoID),
    FOREIGN KEY (PokemonID) REFERENCES Pokemon(ID) ON DELETE CASCADE,
    FOREIGN KEY (MovimientoID) REFERENCES Movimiento(ID) ON DELETE CASCADE
);

-- Inserción de pokemons de la primera generación
INSERT INTO Pokemon (ID, Nombre, Generacion, Tipo1, Tipo2, HP, Ataque, Defensa, Velocidad) VALUES
(1, 'Bulbasaur', 1, 'Planta', 'Veneno', 45, 49, 49, 45),
(2, 'Ivysaur', 1, 'Planta', 'Veneno', 60, 62, 63, 60),
(3, 'Venusaur', 1, 'Planta', 'Veneno', 80, 82, 83, 80),
(4, 'Charmander', 1, 'Fuego', NULL, 39, 52, 43, 65),
(5, 'Charmeleon', 1, 'Fuego', NULL, 58, 64, 58, 80),
(6, 'Charizard', 1, 'Fuego', 'Volador', 78, 84, 78, 100),
(7, 'Squirtle', 1, 'Agua', NULL, 44, 48, 65, 43),
(8, 'Wartortle', 1, 'Agua', NULL, 59, 63, 80, 58),
(9, 'Blastoise', 1, 'Agua', NULL, 79, 83, 100, 78),
(10, 'Caterpie', 1, 'Bicho', NULL, 45, 30, 35, 45),
(11, 'Metapod', 1, 'Bicho', NULL, 50, 20, 55, 30),
(12, 'Butterfree', 1, 'Bicho', 'Volador', 60, 45, 50, 70),
(13, 'Weedle', 1, 'Bicho', 'Veneno', 40, 35, 30, 50),
(14, 'Kakuna', 1, 'Bicho', 'Veneno', 45, 25, 50, 35),
(15, 'Beedrill', 1, 'Bicho', 'Veneno', 65, 90, 40, 75),
(16, 'Pidgey', 1, 'Normal', 'Volador', 40, 45, 40, 56),
(17, 'Pidgeotto', 1, 'Normal', 'Volador', 63, 60, 55, 71),
(18, 'Pidgeot', 1, 'Normal', 'Volador', 83, 80, 75, 101),
(19, 'Rattata', 1, 'Normal', NULL, 30, 56, 35, 72),
(20, 'Raticate', 1, 'Normal', NULL, 55, 81, 60, 97),
(22, 'Fearow', 1, 'Normal', 'Volador', 65, 90, 65, 100),
(23, 'Ekans', 1, 'Veneno', NULL, 35, 60, 44, 55);
(25, 'Pikachu', 1, 'Eléctrico', NULL, 35, 55, 40, 90),
(26, 'Raichu', 1, 'Eléctrico', NULL, 60, 90, 55, 110),
(41, 'Zubat', 1, 'Veneno', 'Volador', 40, 45, 35, 55),
(42, 'Golbat', 1, 'Veneno', 'Volador', 75, 80, 70, 90),
(54, 'Psyduck', 1, 'Agua', NULL, 50, 52, 48, 55),
(55, 'Golduck', 1, 'Agua', NULL, 80, 82, 78, 85),
(92, 'Gastly', 1, 'Fantasma', 'Veneno', 30, 35, 30, 80),
(93, 'Haunter', 1, 'Fantasma', 'Veneno', 45, 50, 45, 95),
(94, 'Gengar', 1, 'Fantasma', 'Veneno', 60, 65, 60, 110),
(111, 'Rhyhorn', 1, 'Tierra', 'Roca', 80, 85, 95, 25),
(112, 'Rhydon', 1, 'Tierra', 'Roca', 105, 130, 120, 40),
(142, 'Aerodactyl', 1, 'Roca', 'Volador', 80, 105, 65, 130);

-- Inserción de pokemons de la segunda generación
INSERT INTO Pokemon (ID, Nombre, Generacion, Tipo1, Tipo2, HP, Ataque, Defensa, Velocidad) VALUES
(152, 'Chikorita', 2, 'Planta', NULL, 45, 49, 65, 45),
(153, 'Bayleef', 2, 'Planta', NULL, 60, 62, 80, 60),
(154, 'Meganium', 2, 'Planta', NULL, 80, 82, 100, 80),
(155, 'Cyndaquil', 2, 'Fuego', NULL, 39, 52, 43, 65),
(156, 'Quilava', 2, 'Fuego', NULL, 58, 64, 58, 80),
(157, 'Typhlosion', 2, 'Fuego', NULL, 78, 84, 78, 100),
(158, 'Totodile', 2, 'Agua', NULL, 50, 65, 64, 43),
(159, 'Croconaw', 2, 'Agua', NULL, 65, 80, 80, 58),
(160, 'Feraligatr', 2, 'Agua', NULL, 85, 105, 100, 78),
(161, 'Sentret', 2, 'Normal', NULL, 35, 46, 34, 20),
(162, 'Furret', 2, 'Normal', NULL, 85, 76, 64, 90),
(172, 'Pichu', 2, 'Eléctrico', NULL, 20, 40, 15, 60),
(173, 'Cleffa', 2, 'Hada', NULL, 50, 25, 28, 15),
(174, 'Clefairy', 2, 'Hada', NULL, 70, 45, 48, 35),
(175, 'Togepi', 2, 'Hada', NULL, 35, 20, 65, 20),
(176, 'Togetic', 2, 'Hada', 'Volador', 55, 40, 85, 40),
(182, 'Bellossom', 2, 'Planta', NULL, 75, 80, 95, 50),
(185, 'Sudowoodo', 2, 'Roca', NULL, 70, 100, 115, 30),
(193, 'Yamask', 2, 'Fantasma', NULL, 38, 40, 85, 30),
(198, 'Murkrow', 2, 'Siniestro', 'Volador', 60, 85, 42, 91),
(202, 'Wobbuffet', 2, 'Psíquico', NULL, 190, 33, 58, 33),
(214, 'Heracross', 2, 'Bicho', 'Lucha', 80, 125, 75, 85),
(216, 'Teddiursa', 2, 'Normal', NULL, 60, 80, 50, 40),
(217, 'Ursaring', 2, 'Normal', NULL, 90, 130, 75, 55),
(236, 'Tyrogue', 2, 'Lucha', NULL, 35, 35, 35, 35),
(237, 'Hitmontop', 2, 'Lucha', NULL, 50, 95, 95, 35),
(246, 'Larvitar', 2, 'Roca', 'Tierra', 50, 64, 50, 41),
(247, 'Pupitar', 2, 'Roca', 'Tierra', 70, 84, 70, 51),
(248, 'Tyranitar', 2, 'Roca', 'Siniestro', 100, 134, 110, 61);

-- Inserción de datos de la tercera generación
INSERT INTO Pokemon (ID, Nombre, Generacion, Tipo1, Tipo2, HP, Ataque, Defensa, Velocidad) VALUES
(252, 'Treecko', 3, 'Planta', NULL, 40, 45, 35, 70),
(253, 'Grovyle', 3, 'Planta', NULL, 50, 65, 45, 95),
(254, 'Sceptile', 3, 'Planta', NULL, 70, 85, 65, 120),
(255, 'Torchic', 3, 'Fuego', NULL, 45, 60, 40, 45),
(256, 'Combusken', 3, 'Fuego', 'Lucha', 60, 85, 60, 55),
(257, 'Blaziken', 3, 'Fuego', 'Lucha', 80, 120, 70, 80),
(258, 'Mudkip', 3, 'Agua', NULL, 50, 70, 50, 40),
(259, 'Marshtomp', 3, 'Agua', 'Tierra', 70, 85, 70, 50),
(260, 'Swampert', 3, 'Agua', 'Tierra', 100, 110, 90, 60),
(261, 'Poochyena', 3, 'Siniestro', NULL, 35, 55, 35, 35),
(262, 'Mightyena', 3, 'Siniestro', NULL, 70, 90, 70, 70),
(273, 'Seedot', 3, 'Planta', NULL, 40, 40, 50, 30),
(274, 'Nuzleaf', 3, 'Planta', 'Siniestro', 70, 70, 40, 60),
(275, 'Shiftry', 3, 'Planta', 'Siniestro', 90, 100, 60, 80),
(280, 'Ralts', 3, 'Psíquico', 'Hada', 28, 25, 25, 40),
(281, 'Kirlia', 3, 'Psíquico', 'Hada', 38, 35, 35, 50),
(282, 'Gardevoir', 3, 'Psíquico', 'Hada', 68, 65, 65, 80),
(301, 'Skitty', 3, 'Normal', NULL, 50, 45, 45, 50),
(302, 'Delcatty', 3, 'Normal', NULL, 70, 65, 65, 70),
(324, 'Trapinch', 3, 'Tierra', NULL, 45, 100, 45, 10),
(325, 'Vibrava', 3, 'Tierra', 'Dragón', 50, 70, 50, 70),
(326, 'Flygon', 3, 'Tierra', 'Dragón', 80, 100, 80, 100),
(333, 'Swablu', 3, 'Normal', 'Volador', 45, 40, 60, 50),
(334, 'Altaria', 3, 'Dragón', 'Volador', 75, 70, 90, 80),
(361, 'Snorunt', 3, 'Hielo', NULL, 50, 50, 50, 50),
(362, 'Glalie', 3, 'Hielo', NULL, 80, 80, 80, 80);

-- Inserción de datos de la cuarta generación
INSERT INTO Pokemon (ID, Nombre, Generacion, Tipo1, Tipo2, HP, Ataque, Defensa, Velocidad) VALUES
(387, 'Turtwig', 4, 'Planta', NULL, 55, 68, 64, 31),
(388, 'Grotle', 4, 'Planta', NULL, 75, 89, 85, 36),
(389, 'Torterra', 4, 'Planta', 'Tierra', 95, 109, 105, 56),
(390, 'Chimchar', 4, 'Fuego', NULL, 44, 58, 44, 61),
(391, 'Monferno', 4, 'Fuego', 'Lucha', 64, 78, 52, 81),
(392, 'Infernape', 4, 'Fuego', 'Lucha', 76, 104, 71, 108),
(393, 'Piplup', 4, 'Agua', NULL, 53, 51, 53, 40),
(394, 'Prinplup', 4, 'Agua', NULL, 64, 66, 68, 50),
(395, 'Empoleon', 4, 'Agua', 'Acero', 84, 86, 88, 60),
(396, 'Starly', 4, 'Normal', 'Volador', 40, 55, 30, 60),
(397, 'Staravia', 4, 'Normal', 'Volador', 55, 75, 50, 80),
(398, 'Staraptor', 4, 'Normal', 'Volador', 85, 120, 70, 100),
(403, 'Shinx', 4, 'Eléctrico', NULL, 45, 65, 34, 45),
(404, 'Luxio', 4, 'Eléctrico', NULL, 60, 85, 49, 60),
(405, 'Luxray', 4, 'Eléctrico', NULL, 80, 120, 79, 70),
(406, 'Budew', 4, 'Planta', 'Veneno', 40, 30, 35, 60),
(407, 'Roserade', 4, 'Planta', 'Veneno', 60, 70, 65, 65),
(413, 'Wormadam', 4, 'Bicho', 'Planta', 60, 59, 85, 36),
(414, 'Mothim', 4, 'Bicho', 'Volador', 70, 65, 60, 66),
(419, 'Cherubi', 4, 'Planta', NULL, 45, 35, 45, 35),
(420, 'Cherrim', 4, 'Planta', NULL, 70, 60, 70, 85),
(423, 'Gastrodon', 4, 'Agua', 'Tierra', 111, 83, 68, 39),
(425, 'Drifloon', 4, 'Fantasma', 'Volador', 90, 50, 34, 40),
(426, 'Drifblim', 4, 'Fantasma', 'Volador', 150, 80, 44, 90),
(428, 'Buneary', 4, 'Normal', NULL, 55, 66, 44, 85),
(430, 'Honchkrow', 4, 'Siniestro', 'Volador', 100, 125, 52, 71),
(443, 'Gible', 4, 'Dragón', 'Tierra', 58, 70, 45, 42),
(444, 'Gabite', 4, 'Dragón', 'Tierra', 68, 90, 65, 82),
(445, 'Garchomp', 4, 'Dragón', 'Tierra', 108, 130, 95, 102),
(478, 'Froslass', 4, 'Hielo', 'Fantasma', 70, 80, 70, 110);

-- Inserción de habilidades
INSERT INTO Habilidad (ID, Nombre, Descripcion) VALUES
(1, 'Espesura', 'Potencia los ataques de tipo Planta en momentos críticos.'),
(2, 'Mar llamas', 'Potencia los ataques de tipo Fuego en momentos críticos.'),
(3, 'Torrente', 'Potencia los ataques de tipo Agua en momentos críticos.'),
(4, 'Impulso', 'Aumenta la velocidad gradualmente durante el combate.'),
(5, 'Intimidación', 'Reduce el ataque del oponente al entrar en combate.'),
(6, 'Pararrayos', 'Atrae los ataques de tipo Eléctrico y aumenta el ataque especial.'),
(7, 'Chlorofila', 'Aumenta la velocidad bajo luz solar intensa.'),
(8, 'Electricidad Estática', 'Puede paralizar al oponente al recibir contacto directo.'),
(9, 'Enjambre', 'Potencia los ataques de tipo Bicho en momentos críticos.'),
(10, 'Levitación', 'Inmune a ataques de tipo Tierra.'),
(11, 'Agallas', 'Aumenta el ataque cuando está alterado de estado.'),
(12, 'Nado Rápido', 'Duplica la velocidad en lluvia.'),
(13, 'Piel Tosca', 'Hiere al atacante si realiza un ataque de contacto.'),
(14, 'Velo Natural', 'Evita alteraciones de estado en climas soleados.'),
(20, 'Absorbe Electricidad', 'Inmunidad a movimientos de tipo eléctrico, restaura PS al ser golpeado.'),
(21, 'Energía Pura', 'Aumenta el Ataque pero reduce otras estadísticas.'),
(22, 'Maldición Helada', 'Potencia movimientos de tipo Hielo al estar en peligro.'),
(23, 'Intimidación', 'Reduce el Ataque del oponente al entrar en batalla.');

-- Inserción de movimientos
INSERT INTO Movimiento (ID, Nombre, Tipo, Categoria, Potencia, Precisio, PP) VALUES
(1, 'Placaje', 'Normal', 'Físico', 40, 100, 35),
(2, 'Gruñido', 'Normal', 'Estado', NULL, 100, 40),
(3, 'Látigo Cepa', 'Planta', 'Físico', 45, 100, 25),
(4, 'Ascuas', 'Fuego', 'Especial', 40, 100, 25),
(5, 'Pistola Agua', 'Agua', 'Especial', 40, 100, 25),
(6, 'Colmillo Ígneo', 'Fuego', 'Físico', 65, 95, 15),
(7, 'Terremoto', 'Tierra', 'Físico', 100, 100, 10),
(8, 'Rayo', 'Eléctrico', 'Especial', 90, 100, 15),
(9, 'Hoja Afilada', 'Planta', 'Físico', 55, 95, 25),
(10, 'Danza Espada', 'Normal', 'Estado', NULL, NULL, 20),
(11, 'Vuelo', 'Volador', 'Físico', 90, 95, 15),
(12, 'Doble Equipo', 'Normal', 'Estado', NULL, NULL, 15),
(13, 'Impactrueno', 'Eléctrico', 'Especial', 40, 100, 30),
(14, 'Golpe Aéreo', 'Volador', 'Físico', 60, NULL, 20),
(15, 'Corte', 'Normal', 'Físico', 50, 95, 30),
(16, 'Colmillo Ígneo', 'Fuego', 'Físico', 65, 95, 15),
(17, 'Pulso Dragón', 'Dragón', 'Especial', 85, 100, 10),
(18, 'Danza Dragón', 'Dragón', 'Estado', NULL, NULL, 20),
(19, 'Rayo Confuso', 'Fantasma', 'Estado', NULL, 100, 10),
(20, 'Psíquico', 'Psíquico', 'Especial', 90, 100, 10),
(21, 'Golpe Roca', 'Lucha', 'Físico', 40, 100, 15),
(22, 'Avalancha', 'Roca', 'Físico', 75, 90, 10),
(29, 'Triturar', 'Siniestro', 'Físico', 80, 100, 15),
(30, 'Ventisca', 'Hielo', 'Especial', 110, 70, 5),
(31, 'Terremoto', 'Tierra', 'Físico', 100, 100, 10),
(32, 'Pájaro Osado', 'Volador', 'Físico', 120, 100, 15),
(33, 'Mofa', 'Siniestro', 'Estado', NULL, 100, 20),
(34, 'Golpe Roca', 'Roca', 'Físico', 75, 90, 15);

-- Relación Pokémon-Habilidades
INSERT INTO Pokemon_Habilidad (PokemonID, HabilidadID) VALUES
(1, 1), (2, 1), (3, 1), -- Bulbasaur, Ivysaur, Venusaur
(4, 2), (5, 2), (6, 2), -- Charmander, Charmeleon, Charizard
(7, 3), (8, 3), (9, 3), -- Squirtle, Wartortle, Blastoise
(10, 9), (11, 9), (12, 9), -- Caterpie, Metapod, Butterfree: Enjambre
(25, 8), (26, 8), -- Pikachu y Raichu: Electricidad Estática
(398, 5), -- Staraptor: Intimidación
(280, 7), (281, 7), (282, 7), -- Ralts, Kirlia, Gardevoir: Chlorofila
(25, 4), (26, 4), -- Pikachu, Raichu: Habilidad Impulso
(12, 1), -- Butterfree: Habilidad Espesura
(398, 4), -- Staraptor: Habilidad Impulso
(41, 10), (42, 10), -- Zubat, Golbat: Levitación
(54, 12), (55, 12), -- Psyduck, Golduck: Nado Rápido
(92, 10), (93, 10), (94, 10), -- Gastly, Haunter, Gengar: Levitación
(246, 13), (247, 13), (248, 13), -- Larvitar, Pupitar, Tyranitar: Piel Tosca
(333, 14), (334, 14), -- Swablu, Altaria: Velo Natural
(443, 13), (444, 13), (445, 13), -- Gible, Gabite, Garchomp: Piel Tosca
(111, 23), (112, 23), -- Rhyhorn, Rhydon: Intimidación
(142, 20), -- Aerodactyl: Absorbe Electricidad
(198, 23), (430, 23), -- Murkrow, Honchkrow: Intimidación
(216, 21), (217, 21), -- Teddiursa, Ursaring: Energía Pura
(361, 22), (362, 22), (478, 22); -- Snorunt, Glalie, Froslass: Maldición Helada


-- Relación Pokémon-Movimientos
INSERT INTO Pokemon_Movimiento (PokemonID, MovimientoID) VALUES
(1, 1), (1, 3), -- Bulbasaur: Placaje, Látigo Cepa
(4, 1), (4, 4), -- Charmander: Placaje, Ascuas
(7, 1), (7, 5), -- Squirtle: Placaje, Pistola Agua
(25, 1), (25, 2), -- Pikachu: Placaje, Gruñido
(26, 1), (26, 6), -- Raichu: Placaje, Colmillo Ígneo
(396, 1), (396, 2), -- Starly: Placaje, Gruñido
(398, 7), -- Staraptor: Terremoto
(12, 11), (12, 14), -- Butterfree: Vuelo, Golpe Aéreo
(25, 13), (25, 8), -- Pikachu: Impactrueno, Rayo
(26, 13), (26, 8), (26, 12), -- Raichu: Impactrueno, Rayo, Doble Equipo
(398, 11), (398, 15), -- Staraptor: Vuelo, Corte
(282, 10), (282, 9), -- Gardevoir: Danza Espada, Hoja Afilada
(41, 19), (42, 19), -- Zubat, Golbat: Rayo Confuso
(54, 20), (55, 20), -- Psyduck, Golduck: Psíquico
(92, 19), (92, 20), -- Gastly: Rayo Confuso, Psíquico
(248, 21), (248, 22), -- Tyranitar: Golpe Roca, Avalancha
(334, 17), (334, 18), -- Altaria: Pulso Dragón, Danza Dragón
(445, 17), (445, 18), -- Garchomp: Pulso Dragón, Danza Dragón
(333, 14), -- Swablu: Golpe Aéreo
(443, 16), -- Gible: Colmillo Ígneo
(444, 16), -- Gabite: Colmillo Ígneo
(445, 16), -- Garchomp: Colmillo Ígneo
(111, 31), (112, 31), -- Rhyhorn, Rhydon: Terremoto
(142, 34), (142, 32), -- Aerodactyl: Golpe Roca, Pájaro Osado
(198, 29), (430, 29), -- Murkrow, Honchkrow: Triturar
(216, 29), (217, 34), -- Teddiursa, Ursaring: Triturar, Golpe Roca
(361, 30), (362, 30), -- Snorunt, Glalie: Ventisca
(478, 30), (478, 33), -- Froslass: Ventisca, Mofa
(257, 6), (257, 32); --Blazeiken: Colmillo ígneo, Pájaro osado