class Persona: #Creamos una clase
    def __init__(self, nombre, apellido, edad): #Se lo llama Dunder
        self.nombre = nombre
        self.apellido = apellido
        self.edad = edad
    def mostrar_detalle(self):
        


persona1 = Persona('Ariel', 'Betacund', 40)  #Necesitamos enviar argumentos
print(f'El objeto1 de la clase Persona: {persona1.nombre} {persona1.apellido} Su edad es: {persona1.edad}')
persona2 = Persona('Osvaldo', 'Giordanini', 45)
print(f'El objeto2 de la calse persona: {persona2.nombre} {persona2.apellido} Su edad es: {persona2.edad}')

persona1.nombre = 'Liliana'
persona1.apellido = 'Bucella'
persona1.edad = 18

#Los atributos son: caracteristicas
#Los metodos son : el comportamiento que van a tener los objetos (acciones)
#Un metodo es una funcion, la diferencia es que al metodo se lo llama porque se asocia con una clase
persona1.mostrar_detalle()
persona2.mostrar_detalle()
