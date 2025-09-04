# Diccionario inicial con los jugadores
seleccionArgentina = {
    10: {'Nombre': 'Lionel Messi', 'Edad': 35, 'Altura': 1.70, 'Precio': '50 Millones', 'Posicion': 'Extremo Derecho'},
    11: {'Nombre': 'Angel Di Maria', 'Edad': 34, 'Altura': 1.80, 'Precio': '12 Millones', 'Posicion': 'Extremo Derecho'},
    24: {'Nombre': 'Paulo Dybala', 'Edad': 28, 'Altura': 1.77, 'Precio': '35 Millones', 'Posicion': 'Media Punta'},
    19: {'Nombre': 'Nicolás Otamendi', 'Edad': 34, 'Altura': 1.83, 'Precio': '3.5 Millones', 'Posicion': 'Defensa Central'},
    1: {'Nombre': 'Franco Armani', 'Edad': 35, 'Altura': 1.89, 'Precio': '3.5 Millones', 'Posicion': 'Portero'}
}

# Tarea: agregar por lo menos 4 Jugadores mas al diccionario

nuevos_jugadores = {
    7: {'Nombre': 'Rodrigo De Paul', 'Edad': 28, 'Altura': 1.80, 'Precio': '40 Millones', 'Posicion': 'Mediocampista'},
    23: {'Nombre': 'Emiliano Martínez', 'Edad': 30, 'Altura': 1.95, 'Precio': '28 Millones', 'Posicion': 'Portero'},
    13: {'Nombre': 'Cristian Romero', 'Edad': 24, 'Altura': 1.85, 'Precio': '60 Millones', 'Posicion': 'Defensa Central'},
    9: {'Nombre': 'Julián Álvarez', 'Edad': 23, 'Altura': 1.70, 'Precio': '50 Millones', 'Posicion': 'Delantero'}
}

seleccionArgentina.update(nuevos_jugadores)


# Imprimimos la lista completa para verificar que se agregaron
print("--- Lista Completa de Jugadores de la Selección Argentina ---")
for llave, valor in seleccionArgentina.items():
    print(llave, valor)

# Imprimimos la cantidad total de jugadores
print("\n-------------------------------------")
print('Tenemos cargados en el diccionario la cantidad de jugadores: ', end = '')
print(len(seleccionArgentina))

#Cómo recorrer un diccionario con el ciclo for, para imprimir tanto las llaves como los valores
for i in seleccionArgentina:
    print(f"{i} -> {seleccionArgentina[i]}")