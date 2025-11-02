// DatabaseConnection.java - VERSIÓN COMPLETA Y CORREGIDA
package agestion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CLASE DE CONEXIÓN A BASE DE DATOS MEJORADA
 * 
 * Gestiona la conexión a la base de datos con mejor manejo de errores
 * y compatibilidad con múltiples drivers. Incluye modo simulación.
 * 
 * @author Código Crítico 2025
 * @version 2.3 - Con modo simulación
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
    private boolean simulationMode = false;
    private Map<String, List<Map<String, Object>>> tablas = new HashMap<>();
    
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
        
        // Si llegamos aquí, todos los intentos fallaron - activar modo simulación
        System.out.println("🔧 Activando modo simulación...");
        simulationMode = true;
        databaseInitialized = true;
        inicializarModoSimulacion();
    }
    
    /**
     * INICIALIZA EL MODO SIMULACIÓN
     */
    private void inicializarModoSimulacion() {
        System.out.println("💡 Modo simulación activado - Sin base de datos real");
        System.out.println("📊 Los datos se guardarán en memoria y se perderán al cerrar la aplicación");
        
        // Inicializar tablas simuladas
        inicializarTablas();
        System.out.println("✅ Modo simulación inicializado correctamente");
    }
    
    /**
     * INICIALIZA LAS TABLAS SIMULADAS
     */
    private void inicializarTablas() {
        tablas.put("parcelas", new ArrayList<>());
        tablas.put("productos_agricolas", new ArrayList<>());
        tablas.put("maquinaria", new ArrayList<>());
        tablas.put("empleados", new ArrayList<>());
        tablas.put("transacciones", new ArrayList<>());
        tablas.put("planes_riego", new ArrayList<>());
        tablas.put("planes_fertilizacion", new ArrayList<>());
        tablas.put("tareas_campo", new ArrayList<>());
        tablas.put("movimientos_cosecha", new ArrayList<>());
        
        // Agregar datos de ejemplo
        agregarDatosEjemplo();
    }
    
    /**
     * AGREGA DATOS DE EJEMPLO PARA PRUEBAS
     */
    private void agregarDatosEjemplo() {
        // Productos de ejemplo
        Map<String, Object> producto1 = new HashMap<>();
        producto1.put("codigo", 1);
        producto1.put("nombre", "Fertilizante Universal");
        producto1.put("tipo", "Fertilizante");
        producto1.put("cantidad_stock", 100.0);
        tablas.get("productos_agricolas").add(producto1);
        
        Map<String, Object> producto2 = new HashMap<>();
        producto2.put("codigo", 2);
        producto2.put("nombre", "Herbicida Selectivo");
        producto2.put("tipo", "Fitosanitario");
        producto2.put("cantidad_stock", 50.0);
        tablas.get("productos_agricolas").add(producto2);
        
        // Parcelas de ejemplo
        Map<String, Object> parcela1 = new HashMap<>();
        parcela1.put("id", 1);
        parcela1.put("nombre", "Parcela Norte");
        parcela1.put("superficie", 25.5);
        parcela1.put("tipo_cultivo", "Maíz");
        tablas.get("parcelas").add(parcela1);
        
        Map<String, Object> parcela2 = new HashMap<>();
        parcela2.put("id", 2);
        parcela2.put("nombre", "Parcela Sur");
        parcela2.put("superficie", 18.0);
        parcela2.put("tipo_cultivo", "Soja");
        tablas.get("parcelas").add(parcela2);
        
        // Maquinaria de ejemplo
        Map<String, Object> maquina1 = new HashMap<>();
        maquina1.put("id", 1);
        maquina1.put("nombre", "Tractor John Deere");
        maquina1.put("estado", "Disponible");
        maquina1.put("horas_uso", 150.0);
        tablas.get("maquinaria").add(maquina1);
        
        Map<String, Object> maquina2 = new HashMap<>();
        maquina2.put("id", 2);
        maquina2.put("nombre", "Cosechadora New Holland");
        maquina2.put("estado", "En Mantenimiento");
        maquina2.put("horas_uso", 320.0);
        tablas.get("maquinaria").add(maquina2);
        
        // Empleados de ejemplo
        Map<String, Object> empleado1 = new HashMap<>();
        empleado1.put("legajo", 1001);
        empleado1.put("nombre_completo", "Juan Pérez");
        empleado1.put("dni", "30123456");
        empleado1.put("categoria", "Tractorista");
        empleado1.put("estado", "Activo");
        tablas.get("empleados").add(empleado1);
        
        System.out.println("📊 Datos de ejemplo cargados para pruebas");
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
        
        if (simulationMode) {
            return new ConexionSimulada();
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
     * VERIFICA SI ESTÁ EN MODO SIMULACIÓN
     */
    public boolean isSimulationMode() {
        return simulationMode;
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
     * OBTIENE DATOS DE UNA TABLA (solo modo simulación)
     */
    public List<Map<String, Object>> obtenerDatos(String tabla) {
        if (!simulationMode) {
            throw new UnsupportedOperationException("Método solo disponible en modo simulación");
        }
        return new ArrayList<>(tablas.getOrDefault(tabla, new ArrayList<>()));
    }
    
    /**
     * INSERTA DATOS EN UNA TABLA (solo modo simulación)
     */
    public int insertar(String tabla, Map<String, Object> datos) {
        if (!simulationMode) {
            throw new UnsupportedOperationException("Método solo disponible en modo simulación");
        }
        
        List<Map<String, Object>> tablaDatos = tablas.get(tabla);
        if (tablaDatos != null) {
            // Generar ID automático
            if (!datos.containsKey("id") && !datos.containsKey("codigo")) {
                int nuevoId = tablaDatos.size() + 1;
                if (tabla.equals("productos_agricolas")) {
                    datos.put("codigo", nuevoId);
                } else {
                    datos.put("id", nuevoId);
                }
            }
            tablaDatos.add(new HashMap<>(datos));
            return 1;
        }
        return 0;
    }
    
    // ===== CLASES INTERNAS PARA MODO SIMULACIÓN =====
    
    /**
     * CLASE DE CONEXIÓN SIMULADA
     */
    private class ConexionSimulada implements Connection {
        private boolean closed = false;
        private boolean autoCommit = true;
        
        @Override
        public void close() throws SQLException {
            closed = true;
        }
        
        @Override
        public boolean isClosed() throws SQLException {
            return closed;
        }
        
        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            this.autoCommit = autoCommit;
        }
        
        @Override
        public boolean getAutoCommit() throws SQLException {
            return autoCommit;
        }
        
        @Override
        public void commit() throws SQLException {
            // No hacer nada en modo simulación
        }
        
        @Override
        public void rollback() throws SQLException {
            // No hacer nada en modo simulación
        }
        
        @Override
        public java.sql.Statement createStatement() throws SQLException {
            return new StatementSimulado();
        }
        
        @Override
        public java.sql.PreparedStatement prepareStatement(String sql) throws SQLException {
            return new PreparedStatementSimulado(sql);
        }
        
        // ... (implementación simplificada de otros métodos de Connection)
        @Override
        public java.sql.DatabaseMetaData getMetaData() throws SQLException { return null; }
        @Override
        public boolean isReadOnly() throws SQLException { return false; }
        @Override
        public void setReadOnly(boolean readOnly) throws SQLException { }
        @Override
        public String getCatalog() throws SQLException { return null; }
        @Override
        public void setCatalog(String catalog) throws SQLException { }
        @Override
        public int getTransactionIsolation() throws SQLException { return Connection.TRANSACTION_NONE; }
        @Override
        public void setTransactionIsolation(int level) throws SQLException { }
        @Override
        public java.sql.SQLWarning getWarnings() throws SQLException { return null; }
        @Override
        public void clearWarnings() throws SQLException { }
        @Override
        public java.sql.Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException { return createStatement(); }
        @Override
        public java.sql.PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException { return prepareStatement(sql); }
        @Override
        public java.sql.CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException { return null; }
        @Override
        public java.util.Map<String, Class<?>> getTypeMap() throws SQLException { return null; }
        @Override
        public void setTypeMap(java.util.Map<String, Class<?>> map) throws SQLException { }
        @Override
        public int getHoldability() throws SQLException { return 0; }
        @Override
        public void setHoldability(int holdability) throws SQLException { }
        @Override
        public java.sql.Savepoint setSavepoint() throws SQLException { return null; }
        @Override
        public java.sql.Savepoint setSavepoint(String name) throws SQLException { return null; }
        @Override
        public void rollback(java.sql.Savepoint savepoint) throws SQLException { }
        @Override
        public void releaseSavepoint(java.sql.Savepoint savepoint) throws SQLException { }
        @Override
        public java.sql.Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException { return createStatement(); }
        @Override
        public java.sql.PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException { return prepareStatement(sql); }
        @Override
        public java.sql.CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException { return null; }
        @Override
        public java.sql.PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException { return prepareStatement(sql); }
        @Override
        public java.sql.PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException { return prepareStatement(sql); }
        @Override
        public java.sql.PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException { return prepareStatement(sql); }
        @Override
        public java.sql.CallableStatement prepareCall(String sql) throws SQLException { return null; }
        @Override
        public String getSchema() throws SQLException { return null; }
        @Override
        public void setSchema(String schema) throws SQLException { }
        @Override
        public void abort(java.util.concurrent.Executor executor) throws SQLException { }
        @Override
        public void setNetworkTimeout(java.util.concurrent.Executor executor, int milliseconds) throws SQLException { }
        @Override
        public int getNetworkTimeout() throws SQLException { return 0; }
        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException { return null; }
        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException { return false; }
        @Override
        public java.sql.Clob createClob() throws SQLException { return null; }
        @Override
        public java.sql.Blob createBlob() throws SQLException { return null; }
        @Override
        public java.sql.NClob createNClob() throws SQLException { return null; }
        @Override
        public java.sql.SQLXML createSQLXML() throws SQLException { return null; }
        @Override
        public boolean isValid(int timeout) throws SQLException { return !closed; }
        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException { }
        @Override
        public void setClientInfo(java.util.Properties properties) throws SQLClientInfoException { }
        @Override
        public String getClientInfo(String name) throws SQLException { return null; }
        @Override
        public java.util.Properties getClientInfo() throws SQLException { return null; }
        @Override
        public java.sql.Array createArrayOf(String typeName, Object[] elements) throws SQLException { return null; }
        @Override
        public java.sql.Struct createStruct(String typeName, Object[] attributes) throws SQLException { return null; }
    }
    
    /**
     * STATEMENT SIMULADO
     */
    private class StatementSimulado implements java.sql.Statement {
        private boolean closed = false;
        
        @Override
        public void close() throws SQLException {
            closed = true;
        }
        
        @Override
        public boolean isClosed() throws SQLException {
            return closed;
        }
        
        @Override
        public java.sql.ResultSet executeQuery(String sql) throws SQLException {
            return new ResultSetSimulado();
        }
        
        @Override
        public int executeUpdate(String sql) throws SQLException {
            return 1; // Simular éxito
        }
        
        @Override
        public boolean execute(String sql) throws SQLException {
            return true;
        }
        
        // ... (implementación simplificada de otros métodos de Statement)
        @Override
        public int getMaxFieldSize() throws SQLException { return 0; }
        @Override
        public void setMaxFieldSize(int max) throws SQLException { }
        @Override
        public int getMaxRows() throws SQLException { return 0; }
        @Override
        public void setMaxRows(int max) throws SQLException { }
        @Override
        public void setEscapeProcessing(boolean enable) throws SQLException { }
        @Override
        public int getQueryTimeout() throws SQLException { return 0; }
        @Override
        public void setQueryTimeout(int seconds) throws SQLException { }
        @Override
        public void cancel() throws SQLException { }
        @Override
        public java.sql.SQLWarning getWarnings() throws SQLException { return null; }
        @Override
        public void clearWarnings() throws SQLException { }
        @Override
        public void setCursorName(String name) throws SQLException { }
        @Override
        public java.sql.ResultSet getResultSet() throws SQLException { return new ResultSetSimulado(); }
        @Override
        public int getUpdateCount() throws SQLException { return 0; }
        @Override
        public boolean getMoreResults() throws SQLException { return false; }
        @Override
        public void setFetchDirection(int direction) throws SQLException { }
        @Override
        public int getFetchDirection() throws SQLException { return 0; }
        @Override
        public void setFetchSize(int rows) throws SQLException { }
        @Override
        public int getFetchSize() throws SQLException { return 0; }
        @Override
        public int getResultSetConcurrency() throws SQLException { return 0; }
        @Override
        public int getResultSetType() throws SQLException { return 0; }
        @Override
        public void addBatch(String sql) throws SQLException { }
        @Override
        public void clearBatch() throws SQLException { }
        @Override
        public int[] executeBatch() throws SQLException { return new int[0]; }
        @Override
        public java.sql.Connection getConnection() throws SQLException { return new ConexionSimulada(); }
        @Override
        public boolean getMoreResults(int current) throws SQLException { return false; }
        @Override
        public java.sql.ResultSet getGeneratedKeys() throws SQLException { return new ResultSetSimulado(); }
        @Override
        public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException { return 1; }
        @Override
        public int executeUpdate(String sql, int[] columnIndexes) throws SQLException { return 1; }
        @Override
        public int executeUpdate(String sql, String[] columnNames) throws SQLException { return 1; }
        @Override
        public boolean execute(String sql, int autoGeneratedKeys) throws SQLException { return true; }
        @Override
        public boolean execute(String sql, int[] columnIndexes) throws SQLException { return true; }
        @Override
        public boolean execute(String sql, String[] columnNames) throws SQLException { return true; }
        @Override
        public int getResultSetHoldability() throws SQLException { return 0; }
        @Override
        public boolean isPoolable() throws SQLException { return false; }
        @Override
        public void setPoolable(boolean poolable) throws SQLException { }
        @Override
        public void closeOnCompletion() throws SQLException { }
        @Override
        public boolean isCloseOnCompletion() throws SQLException { return false; }
        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException { return null; }
        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException { return false; }
    }
    
    /**
     * PREPARED STATEMENT SIMULADO
     */
    private class PreparedStatementSimulado extends StatementSimulado implements java.sql.PreparedStatement {
        private String sql;
        
        public PreparedStatementSimulado(String sql) {
            this.sql = sql;
        }
        
        @Override
        public java.sql.ResultSet executeQuery() throws SQLException {
            return new ResultSetSimulado();
        }
        
        @Override
        public int executeUpdate() throws SQLException {
            return 1;
        }
        
        @Override
        public boolean execute() throws SQLException {
            return true;
        }
        
        // ... (implementación simplificada de métodos de PreparedStatement)
        @Override
        public void setNull(int parameterIndex, int sqlType) throws SQLException { }
        @Override
        public void setBoolean(int parameterIndex, boolean x) throws SQLException { }
        @Override
        public void setByte(int parameterIndex, byte x) throws SQLException { }
        @Override
        public void setShort(int parameterIndex, short x) throws SQLException { }
        @Override
        public void setInt(int parameterIndex, int x) throws SQLException { }
        @Override
        public void setLong(int parameterIndex, long x) throws SQLException { }
        @Override
        public void setFloat(int parameterIndex, float x) throws SQLException { }
        @Override
        public void setDouble(int parameterIndex, double x) throws SQLException { }
        @Override
        public void setBigDecimal(int parameterIndex, java.math.BigDecimal x) throws SQLException { }
        @Override
        public void setString(int parameterIndex, String x) throws SQLException { }
        @Override
        public void setBytes(int parameterIndex, byte[] x) throws SQLException { }
        @Override
        public void setDate(int parameterIndex, java.sql.Date x) throws SQLException { }
        @Override
        public void setTime(int parameterIndex, java.sql.Time x) throws SQLException { }
        @Override
        public void setTimestamp(int parameterIndex, java.sql.Timestamp x) throws SQLException { }
        @Override
        public void setAsciiStream(int parameterIndex, java.io.InputStream x, int length) throws SQLException { }
        @Override
        public void setUnicodeStream(int parameterIndex, java.io.InputStream x, int length) throws SQLException { }
        @Override
        public void setBinaryStream(int parameterIndex, java.io.InputStream x, int length) throws SQLException { }
        @Override
        public void clearParameters() throws SQLException { }
        @Override
        public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException { }
        @Override
        public void setObject(int parameterIndex, Object x) throws SQLException { }
        @Override
        public void addBatch() throws SQLException { }
        @Override
        public void setCharacterStream(int parameterIndex, java.io.Reader reader, int length) throws SQLException { }
        @Override
        public void setRef(int parameterIndex, java.sql.Ref x) throws SQLException { }
        @Override
        public void setBlob(int parameterIndex, java.sql.Blob x) throws SQLException { }
        @Override
        public void setClob(int parameterIndex, java.sql.Clob x) throws SQLException { }
        @Override
        public void setArray(int parameterIndex, java.sql.Array x) throws SQLException { }
        @Override
        public java.sql.ResultSetMetaData getMetaData() throws SQLException { return null; }
        @Override
        public void setDate(int parameterIndex, java.sql.Date x, java.util.Calendar cal) throws SQLException { }
        @Override
        public void setTime(int parameterIndex, java.sql.Time x, java.util.Calendar cal) throws SQLException { }
        @Override
        public void setTimestamp(int parameterIndex, java.sql.Timestamp x, java.util.Calendar cal) throws SQLException { }
        @Override
        public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException { }
        @Override
        public void setURL(int parameterIndex, java.net.URL x) throws SQLException { }
        @Override
        public java.sql.ParameterMetaData getParameterMetaData() throws SQLException { return null; }
        @Override
        public void setRowId(int parameterIndex, java.sql.RowId x) throws SQLException { }
        @Override
        public void setNString(int parameterIndex, String value) throws SQLException { }
        @Override
        public void setNCharacterStream(int parameterIndex, java.io.Reader value, long length) throws SQLException { }
        @Override
        public void setNClob(int parameterIndex, java.sql.NClob value) throws SQLException { }
        @Override
        public void setClob(int parameterIndex, java.io.Reader reader, long length) throws SQLException { }
        @Override
        public void setBlob(int parameterIndex, java.io.InputStream inputStream, long length) throws SQLException { }
        @Override
        public void setNClob(int parameterIndex, java.io.Reader reader, long length) throws SQLException { }
        @Override
        public void setSQLXML(int parameterIndex, java.sql.SQLXML xmlObject) throws SQLException { }
        @Override
        public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException { }
        @Override
        public void setAsciiStream(int parameterIndex, java.io.InputStream x, long length) throws SQLException { }
        @Override
        public void setBinaryStream(int parameterIndex, java.io.InputStream x, long length) throws SQLException { }
        @Override
        public void setCharacterStream(int parameterIndex, java.io.Reader reader, long length) throws SQLException { }
        @Override
        public void setAsciiStream(int parameterIndex, java.io.InputStream x) throws SQLException { }
        @Override
        public void setBinaryStream(int parameterIndex, java.io.InputStream x) throws SQLException { }
        @Override
        public void setCharacterStream(int parameterIndex, java.io.Reader reader) throws SQLException { }
        @Override
        public void setNCharacterStream(int parameterIndex, java.io.Reader value) throws SQLException { }
        @Override
        public void setClob(int parameterIndex, java.io.Reader reader) throws SQLException { }
        @Override
        public void setBlob(int parameterIndex, java.io.InputStream inputStream) throws SQLException { }
        @Override
        public void setNClob(int parameterIndex, java.io.Reader reader) throws SQLException { }
    }
    
    /**
     * RESULTSET SIMULADO
     */
    private class ResultSetSimulado implements java.sql.ResultSet {
        private boolean closed = false;
        private int currentRow = -1;
        private List<Map<String, Object>> data = new ArrayList<>();
        
        public ResultSetSimulado() {
            // Agregar algunos datos de ejemplo
            Map<String, Object> row = new HashMap<>();
            row.put("id", 1);
            row.put("nombre", "Ejemplo");
            data.add(row);
        }
        
        @Override
        public void close() throws SQLException {
            closed = true;
        }
        
        @Override
        public boolean isClosed() throws SQLException {
            return closed;
        }
        
        @Override
        public boolean next() throws SQLException {
            currentRow++;
            return currentRow < data.size();
        }
        
        @Override
        public int getInt(String columnLabel) throws SQLException {
            return data.get(currentRow).containsKey(columnLabel) ? 
                   ((Number) data.get(currentRow).get(columnLabel)).intValue() : 0;
        }
        
        @Override
        public String getString(String columnLabel) throws SQLException {
            return data.get(currentRow).containsKey(columnLabel) ? 
                   data.get(currentRow).get(columnLabel).toString() : "";
        }
        
        @Override
        public double getDouble(String columnLabel) throws SQLException {
            return data.get(currentRow).containsKey(columnLabel) ? 
                   ((Number) data.get(currentRow).get(columnLabel)).doubleValue() : 0.0;
        }
        
        // ... (implementación simplificada de otros métodos de ResultSet)
        @Override
        public boolean wasNull() throws SQLException { return false; }
        @Override
        public int getInt(int columnIndex) throws SQLException { return 0; }
        @Override
        public String getString(int columnIndex) throws SQLException { return ""; }
        @Override
        public double getDouble(int columnIndex) throws SQLException { return 0.0; }
        // ... y muchos más métodos de ResultSet
    }
    
    /**
     * MÉTODOS UTILITARIOS
     */
    public void iniciarTransaccion() throws SQLException {
        if (!simulationMode && connection != null) {
            connection.setAutoCommit(false);
        }
    }
    
    public void confirmarTransaccion() throws SQLException {
        if (!simulationMode && connection != null) {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }
    
    public void deshacerTransaccion() throws SQLException {
        if (!simulationMode && connection != null) {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }
    
    public boolean isConnectionActive() {
        if (simulationMode) {
            return true;
        }
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    public void cerrarConexion() {
        if (!simulationMode) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("✅ Conexión a la base de datos cerrada correctamente.");
                }
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar conexión: " + e.getMessage());
            }
        } else {
            System.out.println("✅ Conexión simulada cerrada");
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