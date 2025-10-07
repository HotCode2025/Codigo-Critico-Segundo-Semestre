# class Persona:  #Esa clase hereda de la clase Object
  #  def __init__(self, nombre, edad):
   #     self.nombre = nombre
    #    self.edad = edad


# class Empleado(Persona):  #Esta clase es hija de la clase persona
  #  def __init__(self, nombre, edad, sueldo):
   #     super().__init__(nombre, edad)
    #    self.sueldo = sueldo


#empleado1 = Empleado('Ariel', 36, 75000)
#print(empleado1.nombre)
#print(empleado1.edad)
#print(empleado1.sueldo)

#Tarea: encapsular los atributos y agregar los metodos getters and setters
#Crear otro objeto, pasar los datos para nombre, edad y sueldo
#Mostrar estos datos, luego modificar y mostrar nuevamente

class Persona:  # Esta clase hereda de la clase Object
    def __init__(self, nombre, edad):
        self._nombre = nombre  # Atributo encapsulado
        self._edad = edad      # Atributo encapsulado

    # Métodos Getter (para obtener el valor)
    def get_nombre(self):
        return self._nombre

    def get_edad(self):
        return self._edad

    # Métodos Setter (para modificar el valor)
    def set_nombre(self, nombre):
        self._nombre = nombre

    def set_edad(self, edad):
        self._edad = edad


class Empleado(Persona):  # Esta clase es hija de la clase Persona
    def __init__(self, nombre, edad, sueldo):
        super().__init__(nombre, edad)
        self._sueldo = sueldo  # Atributo encapsulado/protegido

    # Getter para sueldo
    def get_sueldo(self):
        return self._sueldo

    # Setter para sueldo
    def set_sueldo(self, sueldo):
        self._sueldo = sueldo

#  INICIO DE LA TAREA

# 1. Creo otro objeto, para pasar los datos para nombre, edad y sueldo
empleado2 = Empleado('Ariel', 35, 90000)

# 2. Mostrar estos datos usando los getters
print("---- Datos iniciales del Empleado 2 ----")
print(f'Nombre: {empleado2.get_nombre()}')
print(f'Edad: {empleado2.get_edad()}')
print(f'Sueldo: {empleado2.get_sueldo()}')

# 3. Modificar los datos usando los setters
empleado2.set_nombre('Ariel Mazara')
empleado2.set_edad(36)
empleado2.set_sueldo(95500)

# 4. Mostrar nuevamente los datos para ver los cambios
print("\n---- Datos modificados del Empleado 2 ----")
print(f'Nombre: {empleado2.get_nombre()}')
print(f'Edad: {empleado2.get_edad()}')
print(f'Sueldo: {empleado2.get_sueldo()}')

#  FIN DE LA TAREA