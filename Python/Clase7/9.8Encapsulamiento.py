class PersonaClass:  # Creamos una clase

    def __init__(self, nombre, apellido, DNI, edad, *args, **kwargs):  # Se lo llama metodo Init Dunder
        self.nombre = nombre
        self.apellido = apellido
        self._DNI = DNI #Este atributo esta encapsulado de una manera sugerida
        self.edad = edad
        self.args =  args
        self.kwargs = kwargs

    def mostrar_detalle(self): # self es igual a this.  Su uso es identico a la palabra DES en JAVA. Se puede cambiar pero no es recomendable segun documentacion de Pytho0n
        print(f'La clase Persona tiene los siguientes datos: {self.nombre} {self.apellido} {self._DNI} {self.edad} La direccion es: {self.args}, Los datos importantes son: {self.kwargs} ')

persona1 = PersonaClass('Ariel', 'Mazara', 35036739, '35')  # Necesitamos enviar argumentos
print(persona1.nombre)
print(persona1.apellido)
print(persona1.edad)
print(f'El objeto1 de la clase persona: {persona1.nombre} {persona1.apellido} Su edad es:  {persona1.edad}')

persona2 = PersonaClass('Osvaldo', 'Giordanini', 30321456,'45')
print(f'El objeto2 de la clase persona: {persona2.nombre} {persona2.apellido} Su edad es:  {persona2.edad}')

persona1.nombre = 'Liliana'
persona1.apellido = 'Buccella'
persona1.edad = 40
print(f'El objeto1 MODIFICADO de la clase persona: {persona1.nombre} {persona1.apellido} Su edad es:  {persona1.edad}')

#Los atributos son: CARACTERISTICAS
#Los metodos son: el comportamiento que van a tener los objetos (acciones)
#Metodo es igual que una funcion. Solo que el metodo se asocia con una Clase y la funcion no, sino que es propia de si misma.

persona1.mostrar_detalle() #La referencia en este caso se pasa de manera automatica.
persona2.mostrar_detalle()

# PersonaClass.mostrar_detalle() #Debemos ponerle una referencia para el Self o dará error.

#9.2 Crear atributos desde un objeto
persona1.telefono = '2612525743'
print(f'Este es el telefono de: {persona1.nombre} {persona1.telefono}') #Hemos creado un atributo desde un objeto

# print(persona2.telefono) #El objeto persona2 no tiene este atributo, da error.

persona3 = PersonaClass('Rogelio', 'Romero', 35789456, 22, 'Telefono', '2622539357', 'Calle López', 823, 'Manzana', 77, 'Casa', 18, Altura=1.83, Peso=105, CFavorito= 'Azul', Auto= 'Citroen', Modelo= 2021)
persona3.mostrar_detalle()
print(persona3._DNI) #Esto no se debe utilizar  (esta encapsulado),esto dice que lo desconocemos en Python.

#PARTE 2 Doble Encapsulamiento con doble guion bajo __ no se puede modificar ninguno de los valores dentro del atributo.
# persona3.__nombre #Esta totalmente encapsulado porque tiene doble guion bajo __