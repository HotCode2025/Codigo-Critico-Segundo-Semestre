// DatabaseConnection.java - VERSIÓN SOLO BASE DE DATOS REAL
package agestion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * CLASE DE CONEXIÓN A BASE DE DATOS - SOLO MODO REAL
 * 
 * Gestiona la conexión a la base de datos con mejor manejo de errores
 * y compatibilidad con múltiples drivers.
 * 
 * @author Código Crítico 2025
 * @version 2.4 - Sin modo simulación
 */
public class DatabaseConnection {
    
    private static final String[] DATABASE_URLS = {
        "jdbc:sqlite:gestion_agricola.db",
        "jdbc:h2:./gestion_agricola"  // Fallback a H2
    };
    
    private static final String[] DRIVERS = {
        "org.sqlite.JDBC",
        "org.h2.Driver"
    };
    
    private static DatabaseConnection instance;
    private Connection connection;
    private String databaseType;
    private boolean databaseInitialized = false;
    
    // Constructor privado para Singleton
    private DatabaseConnection() {
        initializeDatabase();
    }
    
    /**
     * INICIALIZA LA BASE DE DATOS CON MÚLTIPLES INTENTOS
     */
    private void initializeDatabase() {
        System.out.println("🗄️  Inicializando base de datos...");
        
        for (int i = 0; i < DRIVERS.length; i++) {
            try {
                System.out.println("🔧 Intentando conector: " + DRIVERS[i]);
                Class.forName(DRIVERS[i]);
                
                // Intentar conexión
                this.connection = DriverManager.getConnection(DATABASE_URLS[i]);
                this.databaseType = (i == 0) ? "SQLite" : "H2";
                
                System.out.println("✅ Conector cargado: " + DRIVERS[i]);
                System.out.println("✅ Conectado a: " + DATABASE_URLS[i]);
                System.out.println("✅ Tipo de base de datos: " + databaseType);
                
                // Configuraciones iniciales
                configurarBaseDatos();
                crearTablas();
                databaseInitialized = true;
                
                System.out.println("✅ Base de datos inicializada correctamente con " + databaseType);
                return;
                
            } catch (ClassNotFoundException e) {
                System.out.println("⚠️  Driver no disponible: " + DRIVERS[i] + " - " + e.getMessage());
                continue;
            } catch (SQLException e) {
                System.out.println("⚠️  Error de conexión con " + DATABASE_URLS[i] + " - " + e.getMessage());
                continue;
            } catch (Exception e) {
                System.out.println("⚠️  Error inesperado: " + e.getMessage());
                continue;
            }
        }
        
        // Si llegamos aquí, todos los intentos fallaron
        System.err.println("❌ ERROR CRÍTICO: No se pudo conectar a ninguna base de datos");
        System.err.println("❌ Verifique que los drivers estén en el classpath");
        throw new RuntimeException("No se pudo inicializar la base de datos");
    }
    
    /**
     * CONFIGURACIONES ESPECÍFICAS DE LA BASE DE DATOS
     */
    private void configurarBaseDatos() throws SQLException {
        if (isSQLite()) {
            // Configuraciones para SQLite
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON");
                stmt.execute("PRAGMA journal_mode = WAL");
                stmt.execute("PRAGMA synchronous = NORMAL");
                stmt.execute("PRAGMA temp_store = MEMORY");
            }
        } else if (isH2()) {
            // Configuraciones para H2
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET DB_CLOSE_DELAY -1");
                stmt.execute("SET DEFAULT_LOCK_TIMEOUT 10000");
            }
        }
    }
    
    /**
     * OBTIENE LA INSTANCIA ÚNICA (SINGLETON)
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    /**
     * OBTIENE LA CONEXIÓN A LA BASE DE DATOS
     */
    public Connection getConnection() {
        if (!databaseInitialized) {
            throw new IllegalStateException("Base de datos no inicializada");
        }
        
        try {
            if (connection == null || connection.isClosed()) {
                // Reconectar
                initializeDatabase();
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener conexión: " + e.getMessage(), e);
        }
    }
    
    /**
     * CREA LAS TABLAS DE LA BASE DE DATOS
     */
    private void crearTablas() {
        System.out.println("📋 Creando tablas...");
        
        crearTablaParcelas();
        crearTablaProductosAgricolas();
        crearTablaMaquinaria();
        crearTablaEmpleados();
        crearTablaTransacciones();
        crearTablaPlanesRiego();
        crearTablaPlanesFertilizacion();
        crearTablaTareasCampo();
        crearTablaMovimientosCosecha();
        
        System.out.println("✅ Todas las tablas creadas/verificadas");
    }
    
    /**
     * CREA LA TABLA DE PARCELAS
     */
    private void crearTablaParcelas() {
        String sql = """
            CREATE TABLE IF NOT EXISTS parcelas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL UNIQUE,
                superficie REAL NOT NULL CHECK (superficie > 0),
                tipo_cultivo TEXT NOT NULL,
                fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """;
        ejecutarSQL(sql, "parcelas");
    }
    
    /**
     * CREA LA TABLA DE PRODUCTOS AGRÍCOLAS
     */
    private void crearTablaProductosAgricolas() {
        String sql = """
            CREATE TABLE IF NOT EXISTS productos_agricolas (
                codigo INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL UNIQUE,
                tipo TEXT NOT NULL,
                cantidad_stock REAL NOT NULL DEFAULT 0 CHECK (cantidad_stock >= 0),
                fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """;
        ejecutarSQL(sql, "productos_agricolas");
    }
    
    /**
     * CREA LA TABLA DE MAQUINARIA
     */
    private void crearTablaMaquinaria() {
        String sql = """
            CREATE TABLE IF NOT EXISTS maquinaria (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL UNIQUE,
                estado TEXT NOT NULL DEFAULT 'Disponible',
                horas_uso REAL NOT NULL DEFAULT 0 CHECK (horas_uso >= 0),
                fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """;
        ejecutarSQL(sql, "maquinaria");
    }
    
    /**
     * CREA LA TABLA DE EMPLEADOS
     */
    private void crearTablaEmpleados() {
        String sql = """
            CREATE TABLE IF NOT EXISTS empleados (
                legajo INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre_completo TEXT NOT NULL,
                dni TEXT NOT NULL UNIQUE,
                fecha_ingreso DATE NOT NULL,
                tipo_contrato TEXT NOT NULL,
                cuit TEXT,
                categoria TEXT NOT NULL,
                sueldo_basico REAL NOT NULL CHECK (sueldo_basico >= 0),
                obra_social TEXT,
                art TEXT,
                foto_path TEXT,
                estado TEXT NOT NULL DEFAULT 'Activo',
                fecha_baja DATE,
                fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """;
        ejecutarSQL(sql, "empleados");
    }
    
    /**
     * CREA LA TABLA DE TRANSACCIONES
     */
    private void crearTablaTransacciones() {
        String sql = """
            CREATE TABLE IF NOT EXISTS transacciones (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                fecha DATE NOT NULL,
                descripcion TEXT NOT NULL,
                monto_base REAL NOT NULL CHECK (monto_base >= 0),
                monto_iva REAL NOT NULL CHECK (monto_iva >= 0),
                tasa_iva REAL NOT NULL CHECK (tasa_iva >= 0),
                tipo TEXT NOT NULL CHECK (tipo IN ('INGRESO', 'EGRESO')),
                fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """;
        ejecutarSQL(sql, "transacciones");
    }
    
    /**
     * CREA LA TABLA DE PLANES DE RIEGO
     */
    private void crearTablaPlanesRiego() {
        String sql = """
            CREATE TABLE IF NOT EXISTS planes_riego (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                parcela_id INTEGER NOT NULL,
                fecha_creacion DATE NOT NULL,
                frecuencia_dias INTEGER NOT NULL CHECK (frecuencia_dias > 0),
                duracion_horas REAL NOT NULL CHECK (duracion_horas > 0),
                estado TEXT NOT NULL DEFAULT 'Activo',
                fecha_creacion_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (parcela_id) REFERENCES parcelas (id) ON DELETE CASCADE
            )
        """;
        ejecutarSQL(sql, "planes_riego");
    }
    
    /**
     * CREA LA TABLA DE PLANES DE FERTILIZACIÓN
     */
    private void crearTablaPlanesFertilizacion() {
        String sql = """
            CREATE TABLE IF NOT EXISTS planes_fertilizacion (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                parcela_id INTEGER NOT NULL,
                producto_id INTEGER NOT NULL,
                fecha_aplicacion DATE NOT NULL,
                dosis_kg_hectarea REAL NOT NULL CHECK (dosis_kg_hectarea > 0),
                estado TEXT NOT NULL DEFAULT 'Pendiente',
                fecha_creacion_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (parcela_id) REFERENCES parcelas (id) ON DELETE CASCADE,
                FOREIGN KEY (producto_id) REFERENCES productos_agricolas (codigo) ON DELETE CASCADE
            )
        """;
        ejecutarSQL(sql, "planes_fertilizacion");
    }
    
    /**
     * CREA LA TABLA DE TAREAS DE CAMPO
     */
    private void crearTablaTareasCampo() {
        String sql = """
            CREATE TABLE IF NOT EXISTS tareas_campo (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                fecha DATE NOT NULL,
                descripcion TEXT NOT NULL,
                operador TEXT NOT NULL,
                parcela_id INTEGER NOT NULL,
                producto_id INTEGER NOT NULL,
                maquinaria_id INTEGER NOT NULL,
                cantidad_producto REAL NOT NULL CHECK (cantidad_producto > 0),
                fecha_creacion_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (parcela_id) REFERENCES parcelas (id) ON DELETE CASCADE,
                FOREIGN KEY (producto_id) REFERENCES productos_agricolas (codigo) ON DELETE CASCADE,
                FOREIGN KEY (maquinaria_id) REFERENCES maquinaria (id) ON DELETE CASCADE
            )
        """;
        ejecutarSQL(sql, "tareas_campo");
    }
    
    /**
     * CREA LA TABLA DE MOVIMIENTOS DE COSECHA
     */
    private void crearTablaMovimientosCosecha() {
        String sql = """
            CREATE TABLE IF NOT EXISTS movimientos_cosecha (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                fecha DATE NOT NULL,
                parcela_id INTEGER NOT NULL,
                producto TEXT NOT NULL,
                kilos_netos REAL NOT NULL CHECK (kilos_netos > 0),
                numero_remito TEXT,
                transportista TEXT,
                patente_vehiculo TEXT,
                codigo_dtve TEXT,
                destino TEXT,
                fecha_creacion_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (parcela_id) REFERENCES parcelas (id) ON DELETE CASCADE
            )
        """;
        ejecutarSQL(sql, "movimientos_cosecha");
    }
    
    /**
     * EJECUTA UNA SENTENCIA SQL CON MANEJO DE ERRORES
     */
    private void ejecutarSQL(String sql, String tabla) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Tabla '" + tabla + "' creada/verificada");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear tabla '" + tabla + "': " + e.getMessage());
        }
    }
    
    /**
     * VERIFICA SI ES SQLITE
     */
    public boolean isSQLite() {
        return "SQLite".equals(databaseType);
    }
    
    /**
     * VERIFICA SI ES H2
     */
    public boolean isH2() {
        return "H2".equals(databaseType);
    }
    
    /**
     * OBTIENE EL TIPO DE BASE DE DATOS
     */
    public String getDatabaseType() {
        return databaseType;
    }
    
    /**
     * VERIFICA SI LA BASE DE DATOS ESTÁ INICIALIZADA
     */
    public boolean isDatabaseInitialized() {
        return databaseInitialized;
    }
    
    /**
     * MÉTODOS UTILITARIOS
     */
    public void iniciarTransaccion() throws SQLException {
        if (connection != null) {
            connection.setAutoCommit(false);
        }
    }
    
    public void confirmarTransaccion() throws SQLException {
        if (connection != null) {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }
    
    public void deshacerTransaccion() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }
    
    public boolean isConnectionActive() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Conexión a la base de datos cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar conexión: " + e.getMessage());
        }
    }
    
    /**
     * DESTRUCTOR - CIERRA LA CONEXIÓN AL FINALIZAR
     */
    @Override
    protected void finalize() throws Throwable {
        cerrarConexion();
        super.finalize();
    }
}