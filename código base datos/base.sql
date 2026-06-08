DROP DATABASE IF EXISTS suculentasProyecto;

CREATE DATABASE suculentasProyecto;

USE suculentasProyecto;

-- 1. REGISTRAR PRIMERO LAS CATEGORÍAS PARA OBTENER SUS IDs
-- El ID de 'suculentas' será 1, 'cactus' será 2, y 'premium' será 3 (debido al AUTO_INCREMENT)
INSERT INTO categorias (nombre) VALUES ('suculentas');
INSERT INTO categorias (nombre) VALUES ('cactus');
INSERT INTO categorias (nombre) VALUES ('premium');

-- 2. INSERTAR LOS 18 PRODUCTOS ASOCIADOS A SU ID DE CATEGORÍA Y COLUMNA STOCK
INSERT INTO productos (nombre, descripcion, precio, imagen_url, id_categoria, disponible, tamano, color, stock) VALUES
('Echeveria Elegans', 'Suculenta en forma de roseta, ideal para decoración.', 3.00, 'img/elegans.jpg', 1, TRUE, 'Mediano', 'Azul Grisáceo', 10),
('Asiento de Suegra', 'Cactus resistente y perfecto para exteriores.', 5.00, 'img/suegra.jpg', 2, TRUE, 'Grande', 'Verde Amarillo', 5),
('Haworthia (Cebra)', 'Suculenta elegante con rayas blancas decorativas.', 4.00, 'img/hawo.jpg', 1, TRUE, 'Pequeño', 'Verde Oscuro', 15),
('Echeveria Agavoides', 'Echeveria agavoides, híbrido de la casa.', 5.00, 'img/agavoides1.png', 1, TRUE, 'Mediano', 'Verde con Puntas Rojas', 8),
('Echeveria Cante', 'Echeveria de origen mexicano, muy pruinosa.', 15.00, 'img/cante.png', 3, TRUE, 'Grande', 'Blanco Rosado', 3),
('Echeveria Chihuahuahensis', 'Echeveria de tamaño mediano, pruinosa y con mucrones rojos.', 4.00, 'img/chi.png', 1, TRUE, 'Mediano', 'Verde Claro', 12),
('Echeveria Cream CreamPuff', 'Echeveria de tamaño mediano, hojas gorditas y de roseta compacta.', 6.00, 'img/cream.png', 1, TRUE, 'Mediano', 'Crema', 7),
('Echeveria Cubic CubicFrost', 'Echeveria de tamaño mediano, hojas gorditas y de roseta compacta, muy prolífica.', 3.50, 'img/cubic.png', 1, TRUE, 'Mediano', 'Lila Frost', 20),
('Echeveria Cupeno', 'Echeveria de tamaño grande, hojas rulosas y de roseta ancha.', 10.00, 'img/cupeno.png', 1, TRUE, 'Grande', 'Verde', 4),
('Echeveria Pulina', 'Echeveria de tamaño mediano, hojas gorditas con bordes rojos, con tricomas y de roseta compacta.', 8.50, 'img/pulina.png', 1, TRUE, 'Mediano', 'Verde Bordes Rojos', 6),
('Graptoveria Ilaló', 'Graptoveria híbrida de la casa! cruza entre Graptopetalum Amethystinum y Echeveria Agavoides.', 7.50, 'img/ilalo.png', 3, TRUE, 'Mediano', 'Rosado', 5),
('Lithop Lesliei', 'También conocida como piedras vivas, esta suculenta es originaria de Sudáfrica.', 7.50, 'img/lesliei.png', 1, TRUE, 'Pequeño', 'Café Tierra', 9),
('Lithop Salicola', 'También conocida como piedras vivas, de color vino, esta suculenta es originaria de Sudáfrica.', 7.50, 'img/salicola.png', 1, TRUE, 'Pequeño', 'Vino', 11),
('Echeveria Madiva', 'Echeveria de tamaño mediano, pruinosa.', 3.50, 'img/madiva.png', 1, TRUE, 'Mediano', 'Gris', 14),
('Echeveria Raindrops', 'Echeveria de tamaño mediano, pruinosa con carúnculas.', 3.50, 'img/rain.png', 1, TRUE, 'Mediano', 'Verde Azulado', 8),
('Echeveria Agavoides Romeo Rubín', 'Echeveria de tamaño mediano, de color vino.', 6.50, 'img/romeo.png', 3, TRUE, 'Mediano', 'Rojo Vino', 2),
('Sedum Rubrotinctum Aurora', 'Sedum variegado de varias tonalidades.', 1.50, 'img/sedum1.png', 1, TRUE, 'Pequeño', 'Multicolor', 25),
('Sedum Tokyo', 'Sedum variegado.', 1.00, 'img/sedum.png', 1, TRUE, 'Pequeño', 'Verde Lima', 30);

-- se agrega una nueva planta para comprobar que los inserts funcionan y esto se vea reflejado en la página sin haber ingresado 
-- a visual 

INSERT INTO productos (nombre, descripcion, precio, imagen_url, id_categoria, disponible, tamano, color, stock) VALUES
('Echeveria Lutea', 'Hermosa suculenta de hojas alargadas color verde.', 5.50, 'img/lutea.png', 1, TRUE, 'Mediano', 'Verde', 8);

select*from productos;

UPDATE productos
SET imagen_url = 'img/lutea.jpg' -- CORREGIDO: Con comillas simples alrededor del texto
WHERE id = 19; -- CORREGIDO: Nombre de la tabla bien escrito

UPDATE productos
SET nombre = 'Echeveria Cream Puff' -- CORREGIDO: Con comillas simples alrededor del texto
WHERE id = 7; -- CORREGIDO: Nombre de la tabla bien escrito

UPDATE productos
SET nombre = 'Echeveria Cubic Frost' -- CORREGIDO: Con comillas simples alrededor del texto
WHERE id = 8; 
