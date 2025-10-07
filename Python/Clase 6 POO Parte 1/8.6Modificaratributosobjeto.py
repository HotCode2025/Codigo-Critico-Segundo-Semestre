class PersonaClass:  # Creamos una clase
    # EL CONSTRUCTOR EN PYTHON ESTA OCULTO, SE LLAMA POR LENGUAJE
    def __init__(self, nombre, apellido, edad):  # Se lo llama metodo Init Dunder
        self.nombre = nombre
        self.apellido = apellido
        self.edad = edad


persona1 = PersonaClass('Ariel', 'Mazara', '35')  # Necesitamos enviar argumentos
print(persona1.nombre)
print(persona1.apellido)
print(persona1.edad)
print(f'El objeto1 de la clase persona: {persona1.nombre} {persona1.apellido} Su edad es:  {persona1.edad}')



persona2 = PersonaClass('Osvaldo', 'Giordanini', '45')
print(f'El objeto2 de la clase persona: {persona2.nombre} {persona2.apellido} Su edad es:  {persona2.edad}')

persona1.nombre = 'Liliana'
persona1.apellido = 'Buccella'
persona1.edad = 40
print(f'El objeto1 MODIFICADO de la clase persona: {persona1.nombre} {persona1.apellido} Su edad es:  {persona1.edad}')

#SE PUEDEN MODIFICAR OBJETOS SIN PROBLEMAS EN PYTHON