class PersonaClass: #Creamos una clase
     # EL CONSTRUCTOR EN PYTHON ESTA OCULTO, SE LLAMA POR LENGUAJE
    def __init__(self):  # Se lo llama metodo Init Dunder
        self.nombre = 'Juan'
        self.apellido = 'Zalazar'
        self.edad =  22

persona1  = PersonaClass()
print(persona1.nombre)
print(persona1.apellido)
print(persona1.edad)

#Referencia por init es indirecta por eso no se pone nada en los parentesis ()