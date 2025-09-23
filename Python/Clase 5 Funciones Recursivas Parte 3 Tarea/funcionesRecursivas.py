#Argumentos variables para un diccionario
def listarTerminos(**terminos): #En la documentación se utiliza más es **kwargs para recibir llave-valor en forma de argumentos
    for llave, valor in terminos.items(): #kwargs significa: key word argument (llave palabra argumento)
        print(f"{llave}: {valor}")

listarTerminos() #No se va a mostrar nada
listarTerminos(IDE="Integrated Develoment Enviroment", PK="Primaruy Key")
listarTerminos(Nombre= "Leonel Messi")

#Lista de elementos con funciones (convertir)
def desplegarNombres(nombres):
    for nombre in nombres:
        print(nombre)

nombres2 = ["Tito", "Pedro", "Carlos"]
desplegarNombres(nombres2)
desplegarNombres("Carla")
#desplegarNombres(18, 11) #No es un objeto iterable
desplegarNombres((10, 11)) #Se convierte a tupla para poder recorrer los números, en un solo elemento no olvidar la coma
desplegarNombres([22, 55]) #La convertimos en una lista

#Funciones Recursivas
def factorial(numero):
    if numero == 1: #Caso base
        return 1
    else:
        return numero * factorial(numero-1) #Caso recursivo


#resultado = factorial(5) #Lo hacemos en código duro
#print(f"El factorial del número 5 es: {resultado}")

#Tarea: que el usuario ingrese el número para calcular el factorial
numeroEscogido = int(input("Ingrese un número para calcular el factorial: "))
resultado = factorial(numeroEscogido)
print(f"El factorial del numero {numeroEscogido} es: {resultado}")