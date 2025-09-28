class PersonaClass:  # Creamos una clase
    # EL CONSTRUCTOR EN PYTHON ESTA OCULTO, SE LLAMA POR LENGUAJE
    def __init__(self, nombre, apellido, edad):  # Se lo llama metodo Init Dunder
        self.nombre = nombre
        self.apellido = apellido
        self.edad = edad


persona1 = PersonaClass('Ariel', 'Mazara', '35') #Necesitamos enviar argumentos
print(persona1.nombre)
print(persona1.apellido)
print(persona1.edad)