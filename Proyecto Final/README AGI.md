# 🌱 Agro Gestión Integral

Un sistema de gestión agrícola integral para cultivos intensivos, desarrollado con Java y Swing. Este proyecto fue realizado como trabajo final integrador para el 2° Semestre de la **Tecnicatura Universitaria en Programación (2025)** por el equipo **Código Crítico**.

## 📖 Descripción del Proyecto

**Agro Gestión Integral** es una aplicación de escritorio diseñada para simplificar y centralizar la administración de una finca agrícola. El sistema permite llevar un registro detallado de todas las operaciones, desde las tareas diarias en el campo hasta la gestión financiera, el control del personal y la logística de la cosecha.

El objetivo principal es ofrecer una herramienta intuitiva y potente que cumpla con los requisitos de la gestión agrícola moderna, utilizando una interfaz de usuario amigable y un diseño modular que permite futuras expansiones.

## ✨ Características Principales

El sistema está organizado en los siguientes módulos funcionales:

* **Cuaderno de Campo:**
    * Registro de tareas diarias (aplicaciones, riegos, etc.).
    * Asociación de tareas a parcelas, productos y maquinaria.
    * Gestión de parcelas (alta y listado).

* **Cosecha y Transporte:**
    * Registro de movimientos de cosecha, incluyendo kilos, producto y parcela de origen.
    * Gestión de datos de logística: número de remito, transportista, patente y código DTVe.

* **Personal (RRHH):**
    * Contratación de empleados (permanentes, temporales, contratados).
    * Registro de datos completos: CUIT, categoría, sueldo, ART, obra social, etc.
    * Visualización de la nómina completa y fichas individuales con foto.
    * Gestión del ciclo de vida del empleado (alta y baja).

* **Riego y Fertilización:**
    * Creación y seguimiento de planes de riego.
    * Creación y seguimiento de planes de fertilización.

* **Finanzas y Contabilidad:**
    * Registro de transacciones (ingresos y egresos).
    * Cálculo automático de IVA (10.5%, 21%, 27%) en cada transacción.
    * Generación de un libro contable con balance total y filtro por fecha.
    * Emisión de reportes de posición de IVA (débito, crédito y saldo).

* **Gestión de Stock:**
    * Alta de productos e insumos agrícolas.
    * Control de inventario en tiempo real.

* **Control de Maquinaria:**
    * Registro de la flota de maquinaria.
    * Control de estado y horas de uso.

## 💻 Tecnologías Utilizadas

-   **Lenguaje:** Java (JDK 11 o superior).
-   **Interfaz Gráfica:** Java Swing.
-   **Look and Feel (Tema):** [FlatLaf](https://www.formdev.com/flatlaf/)
-   **IDE:** Apache NetBeans / Visual Studio Code

## 🚀 Instalación y Ejecución

Para ejecutar este proyecto, sigue estos pasos:

1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://github.com/HotCode2025/Codigo-Critico-Segundo-Semestre/tree/main/Proyecto%20Final/AgroGestionIntegral]

2.  **Abrir en tu IDE preferido** (NetBeans o VS Code con el "Extension Pack for Java").

3.  **Añadir la Biblioteca FlatLaf:**
    * Crea una carpeta `lib` en la raíz del proyecto.
    * Copia el archivo `flatlaf-x.x.x.jar` dentro de `lib`.
    * En tu IDE, añade ese archivo `.jar` al "Classpath" o a las "Libraries" del proyecto.

4.  **Ejecutar:**
    * Localiza el archivo `Main.java` en el paquete `agestion.main`.
    * Ejecuta el método `main`.

## 👨‍💻 Autores - Equipo "Código Crítico"

Este proyecto fue desarrollado con la colaboración de:

-   **Mazara Ariel**
-   **Zuñiga Agustina**
-   **Silva Daniel**
-   **Lucas Reynoso**
-   **Gonzalez Joel**
-   **Baz Samira**
-   **Mendez Oscar**
-   **Mamani Santino**
-   **Ponce de Leon Damian**

---
*Tecnicatura Universitaria en Programación - 2025*