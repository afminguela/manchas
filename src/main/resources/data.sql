-- Crear tablas si no existen
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS manchas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    descripcion TEXT,
    ubicacion VARCHAR(255),
    estado VARCHAR(20) NOT NULL,
    fecha_reporte TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS soluciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    tipo_mancha VARCHAR(50),
    pasos TEXT,
    tiempo_estimado INT,
    dificultad VARCHAR(20)
);

-- Insertar datos de ejemplo
INSERT INTO usuarios (nombre, email, password, rol) VALUES
('Admin', 'admin@manchas.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ADMIN'),
('Juan Pérez', 'juan@manchas.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'USUARIO'),
('María García', 'maria@manchas.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'USUARIO');

INSERT INTO manchas (tipo, descripcion, ubicacion, estado, usuario_id) VALUES
('Café', 'Mancha de café en la alfombra del salón', 'Salón principal', 'PENDIENTE', 2),
('Vino tinto', 'Mancha de vino en el sofá', 'Sala de estar', 'EN_PROCESO', 3),
('Grasa', 'Mancha de grasa en la cocina', 'Cocina', 'RESUELTA', 2),
('Chocolate', 'Mancha de chocolate en la ropa de cama', 'Dormitorio principal', 'PENDIENTE', 2),
('Sangre', 'Mancha de sangre en la alfombra', 'Pasillo', 'EN_PROCESO', 3),
('Pintura', 'Mancha de pintura en la pared', 'Habitación de niños', 'PENDIENTE', 2),
('Tinta', 'Mancha de tinta en el escritorio', 'Oficina', 'RESUELTA', 3),
('Cera', 'Mancha de cera en la mesa de madera', 'Comedor', 'PENDIENTE', 2),
('Rust', 'Mancha de óxido en la ropa', 'Lavandería', 'EN_PROCESO', 3),
('Sudor', 'Mancha de sudor en la camisa', 'Armario', 'PENDIENTE', 2);

INSERT INTO soluciones (nombre, descripcion, tipo_mancha, pasos, tiempo_estimado, dificultad) VALUES
('Limpieza de café', 'Solución para manchas de café en telas', 'Café', 
'1. Absorber el exceso con papel\n2. Aplicar mezcla de agua y vinagre\n3. Lavar con detergente', 30, 'MEDIA'),
('Eliminador de vino', 'Solución para manchas de vino', 'Vino tinto',
'1. Aplicar sal\n2. Verter agua caliente\n3. Usar detergente específico', 45, 'ALTA'),
('Limpiador de grasa', 'Solución para manchas de grasa', 'Grasa',
'1. Aplicar bicarbonato\n2. Dejar actuar\n3. Limpiar con agua caliente', 20, 'BAJA'),
('Removedor de chocolate', 'Solución para manchas de chocolate en telas', 'Chocolate',
'1. Raspar el exceso\n2. Aplicar agua fría\n3. Usar detergente enzimático', 35, 'MEDIA'),
('Eliminador de sangre', 'Solución para manchas de sangre', 'Sangre',
'1. Enjuagar con agua fría\n2. Aplicar peróxido de hidrógeno\n3. Lavar con agua fría', 40, 'ALTA'),
('Removedor de pintura', 'Solución para manchas de pintura en paredes', 'Pintura',
'1. Raspar suavemente\n2. Aplicar removedor de pintura\n3. Limpiar con agua jabonosa', 60, 'ALTA'),
('Eliminador de tinta', 'Solución para manchas de tinta', 'Tinta',
'1. Aplicar alcohol isopropílico\n2. Dejar actuar\n3. Lavar con agua caliente', 25, 'MEDIA'),
('Removedor de cera', 'Solución para manchas de cera', 'Cera',
'1. Congelar la cera\n2. Raspar suavemente\n3. Limpiar con agua caliente', 30, 'MEDIA'),
('Eliminador de óxido', 'Solución para manchas de óxido', 'Rust',
'1. Aplicar jugo de limón\n2. Dejar actuar\n3. Lavar con agua caliente', 45, 'ALTA'),
('Removedor de sudor', 'Solución para manchas de sudor', 'Sudor',
'1. Aplicar vinagre blanco\n2. Dejar actuar\n3. Lavar con detergente', 20, 'BAJA'),
('Limpieza general de telas', 'Solución general para manchas en telas', 'General',
'1. Identificar el tipo de mancha\n2. Aplicar tratamiento específico\n3. Lavar según instrucciones', 30, 'MEDIA'),
('Limpieza de superficies duras', 'Solución para manchas en superficies duras', 'General',
'1. Limpiar con agua jabonosa\n2. Aplicar desinfectante\n3. Secar completamente', 25, 'BAJA'); 