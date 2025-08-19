# 📚 LiterAlura - Catálogo de Libros

## 📖 Descripción

LiterAlura es una aplicación Java desarrollada con Spring Boot que permite gestionar un catálogo de libros utilizando la API de Gutendex. La aplicación ofrece funcionalidades para buscar libros, guardarlos en una base de datos y realizar consultas avanzadas sobre libros y autores.

## 🚀 Características

- ✅ **Búsqueda de libros** por título utilizando la API de Gutendx
- ✅ **Persistencia en base de datos** con PostgreSQL
- ✅ **Gestión de autores** con información de nacimiento y fallecimiento
- ✅ **Consultas avanzadas** como autores vivos en determinado año
- ✅ **Filtrado por idioma** de los libros registrados
- ✅ **Interfaz de consola** intuitiva y fácil de usar
- ✅ **Manejo robusto de errores** y conexión offline

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **PostgreSQL** (Base de datos)
- **Jackson** (Procesamiento JSON)
- **Maven** (Gestión de dependencias)

## 📋 Prerrequisitos

- Java 17 o superior
- Maven 3.6+
- PostgreSQL instalado y ejecutándose
- Conexión a internet (para búsquedas en la API)

## ⚙️ Configuración

### 1. Base de Datos

Crear una base de datos PostgreSQL:

```sql
CREATE DATABASE literalura;
2. Configuración de application.properties
🚀 Instalación y Ejecución
1. Clonar el repositorio
2. Compilar el proyecto
3. Ejecutar la aplicación
🎮 Uso de la Aplicación
Al ejecutar la aplicación, se mostrará el siguiente menú:

📚 Ejemplos de Uso
Buscar un libro:
Selecciona opción 1
Escribe el título: pride and prejudice
El sistema mostrará la información del libro y lo guardará en la base de datos
Consultar autores vivos en un año:
Selecciona opción 4
Ingresa el año: 1600
Verás todos los autores que estaban vivos en ese año
🏗️ Arquitectura del Proyecto
🌐 API Externa
La aplicación utiliza la API de Gutendx para obtener información de libros:

Base URL: https://gutendex.com/books/
Búsqueda: https://gutendex.com/books/?search=titulo
📊 Funcionalidades Detalladas
🔍 Búsqueda de Libros
Consulta la API de Gutendx por título
Muestra información completa del libro
Guarda automáticamente en la base de datos
Previene duplicados
👥 Gestión de Autores
Almacena información de nacimiento y fallecimiento
Consultas por año de vida
Relación uno-a-muchos con libros
🌍 Filtros por Idioma
Idiomas soportados:

es - Español
en - Inglés
fr - Francés
pt - Portugués
🛡️ Manejo de Errores
Sin conexión: Utiliza datos de ejemplo para continuar la demostración
API no disponible: Mensajes informativos y fallback
Errores de base de datos: Validaciones y mensajes descriptivos
Entradas inválidas: Validación de datos de usuario
📝 Libros de Prueba Recomendados
Para probar la aplicación, busca estos títulos:

"Pride and Prejudice" - Jane Austen
"Romeo and Juliet" - William Shakespeare
"Alice's Adventures in Wonderland" - Lewis Carroll
"Frankenstein" - Mary Wollstonecraft Shelley
🤝 Contribución
Haz fork del proyecto
Crea una rama para tu feature (git checkout -b feature/AmazingFeature)
Commit tus cambios (git commit -m 'Add some AmazingFeature')
Push a la rama (git push origin feature/AmazingFeature)
Abre un Pull Request
📄 Licencia
Este proyecto está bajo la Licencia MIT. Ver el archivo LICENSE para más detalles.

👨‍💻 Autor
Alex Fernando

GitHub: @alexefnando
🙏 Agradecimientos
Gutendex API por proporcionar acceso gratuito a libros del Proyecto Gutenberg
Spring Boot por el framework robusto
Alura por el desafío y la formación
⭐ ¡No olvides dar una estrella al proyecto si te fue útil!

