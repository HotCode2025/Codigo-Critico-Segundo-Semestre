
class PersonaClass:
    def __init__(self, nombre, apellido, DNI, edad, *args, **kwargs):
        self._nombre = nombre
        self._apellido = apellido
        self._DNI = DNI
        self._edad = edad
        self._args = args
        self._kwargs = kwargs

    # --- GETTERS ---
    @property
    def nombre(self):
        return self._nombre

    @property
    def apellido(self):
        return self._apellido

    @property
    def DNI(self):
        return self._DNI

    @property
    def edad(self):
        return self._edad

    # --- SETTERS ---
    @nombre.setter
    def nombre(self, nombre):
        self._nombre = nombre

    @apellido.setter
    def apellido(self, apellido):
        self._apellido = apellido

    @DNI.setter
    def DNI(self, DNI):
        self._DNI = DNI

    @edad.setter
    def edad(self, edad):
        self._edad = edad

    def mostrar_detalle(self):
        print(f'Persona: {self._nombre} {self._apellido}, Edad: {self._edad}, DNI: {self._DNI}, Otros: {self._args}, Detalles: {self._kwargs}')

# Tarea: Crear tres objetos más, utilizando los métodos getter y setter
# para modificar y mostrar los cambios con el método mostrar_detalle.

# 1. Creamos tres objetos nuevos
persona4 = PersonaClass('Juan', 'Perez', 38123456, 30)
persona5 = PersonaClass('Maria', 'Gomez', 40789123, 28)
persona6 = PersonaClass('Carlos', 'Rodriguez', 36456789, 33)

# 2. Mostramos los detalles originales de cada objeto
print("--- DATOS ORIGINALES ---")
persona4.mostrar_detalle()
persona5.mostrar_detalle()
persona6.mostrar_detalle()

# 3. Modificamos los objetos utilizando los métodos SETTER
print("\n--- MODIFICANDO DATOS... ---")
persona4.nombre = "Juan Carlos"
persona4.edad = 31
persona5.apellido = "Gonzalez"
persona6.DNI = 37000111
persona6.nombre = "Carlos Alberto"
print("¡Datos modificados con éxito!\n")

# 4. Mostramos los detalles después de la modificación
print("--- DATOS MODIFICADOS ---")
persona4.mostrar_detalle()
persona5.mostrar_detalle()
persona6.mostrar_detalle()