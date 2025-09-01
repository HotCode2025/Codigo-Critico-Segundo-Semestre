#Ejercicio 4.6 colecciones llenar una lista
#LLenar una lista con los numeros del 1 al 50, luego mostrar
#la lista con el bucle de fooorr, los elementos deben mostrarse
#de la siguiente forma:
#1-2-3-4-5-....50


for i in range(1, 51):
    if i < 50:
        print(i, end = "-")
    else:
        print(i)

