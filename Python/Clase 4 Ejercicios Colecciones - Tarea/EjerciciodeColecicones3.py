#Ejercicio 3: Agregar personajes a una lista
#Escriba un programa donde cree una lista con los siguientes persnajes del señor de los anillos
#Nombre : Argon
#Clase : Guerrero
# Raza : Dunadan del norte

#Nombre : Gandalf
#Clase : Magooo
# Raza: Istar


#Nombre : Legolas
# Clase: Arquero
#Raza: Elfo Sindar.



personajes = []

# Agregamos los personajes como un diccionario
personajes.append({
    "nombre": "Aragorn",
    "clase": "Guerrero",
    "raza": "Dúnadan del norte"
})

personajes.append({
    "nombre": "Gandalf",
    "clase": "Mago",
    "raza": "Istari"
})

personajes.append({
    "nombre": "Legolas",
    "clase": "Arquero",
    "raza": "Elfo Sindar"
})

# Mostramos la lista completa
for personaje in personajes:
    print(f"{personaje['nombre']} "
          f" Clase: {personaje['clase']},"
          f" Raza: {personaje['raza']}")
