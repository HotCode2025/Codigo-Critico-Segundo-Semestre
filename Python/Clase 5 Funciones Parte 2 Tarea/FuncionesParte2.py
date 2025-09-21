"""
Una función en python es un bloque de código
que vamos a poder llamar N cantidad de veces
"""
from difflib import restore


#Comenzamos con funciones
#mi_funcion() #No se puede llamar antes de definir una función, ya que el compilador va de arriba hacia abajo
#Definimos una función
def mi_funcion(): #Para identificar a la función utilizamos paréntesis
    print("Saludos a todos los alumnos de la Tecnicatura")

mi_funcion() #Llamamos a la función
mi_funcion() #Se puede llamar N cantidad de veces

#Desempaquetado de listas o list Unpacking
def show(name, lastName):
    print(name+" "+lastName)
person = ["Ariel", "Betancud"]
show(person[0], person[1]) #Pasamos uno por uno los datos de la lista a la función
show(*person) #Esto es lo mismo que lo anterior pero le pasamos todo junto
person2 = ("Osvaldo", "Giovani") #Desempaquetamos a través de una tupla
show(*person2)
person3 = {"lastName": "Lucero", "name": "Natalia"} #Desempaquetamos a través de un diccionario
show(**person3) #Se pone 2 asteriscos porque es clave y valor

#Repaso del ciclo for else
numbers = [1, 2, 3, 4, 5] #Aún con la lista vacía se va a ejecutar el else
for n in numbers:
    print(n)
    if n == 3:
        break #Esta es la unica manera de que no se ejecute el else
else:
    print("Esto se terminó")

#List comprehension, lista de comprensión
#Tomar solo lo necesario de una lista sin hacer ninguna modificación
names = ["Paolo", "Rodrigo", "Lupe", "Pepe"]
alongP = [p for p in names if p[0] == "P"] #Esto regresa una nueva lista con la condición
print(alongP)

bottlecC = [{"name": "Quilmes", "country": "Arg"},
            {"name": "Corona", "country": "Mx"},
            {"name": "Stella Artois", "country": "Belgium"},
            ]
Arg = [b for b in bottlecC if b["country"] == "Arg"]
print(Arg)
print(bottlecC)

#Paso de argumentos (Funciones)
def mi_funcion2(name, lastName):
    print("Saludos a todos los que me ven a través del canal de You Tube")
    print(f"Nombre: {name}, Apellido: {lastName}")
mi_funcion2("Jorge", "Lucero")
mi_funcion2("Ariel", "Betancud")
mi_funcion2("Analia", "Pedrosa")

#Palabra return en Funciones
#Creamos una función para sumar
def sumar(a, b):
    return a + b
#resultado = sumar(78, 22)
#print(f"El resultado de la suma es: {resultado}")
print(f"El resultado de la suma es: {sumar(55, 45)}") #Manera más directa

#Valores por defecto en los parámetros de una función
def sumar2(a = 0, b = 0): #Le damos un valor por default
    return a + b
resultado = sumar2()
print(f"Resultado de la suma: {resultado}")
print(f"Resultado de la suma: {sumar2(22, 66)}") #Le pasamos valores

#Argumentos, variables en funciones
def listasNombres(*nombres): #Se utiliza * cuando no se sabe cuantos en este caso nombres se van a pasar, normalmente se utiliza *args
    for nombre in nombres: #Se va convertir en una tupla 
        print(nombre)
listasNombres("Lucas", "José", "Claudia", "Rosa", "Maria")
listasNombres("Marcos", "Daniel", "Romina", "Pepe", "Marcela", "Carlos")

