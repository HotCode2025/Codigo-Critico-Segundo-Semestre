# lista = Ariel , Liliana, Natalia, Osvaldo
#Coleccion den Python

#Las listas en lo que se conoce en otros lenguajes como arreglos o vectores

nombres=['Naty', 'Osvaldo', 'Lily', 'Ariel']

print(nombres)
print(nombres[0:2]) # Solo muestra el indice 0, 1 pero no el indice 2
# ir del inicio de la lista al indice (sin incluirlo)
print(nombres[ :3]) # Indices a mostrar 0, 1, 2
# Desde el indice indicado hasta el final
print(nombres[1: ])
# Modificamos un valor
nombres[2] = 'Liliana'
nombres[0] = 'Natalia'
print(nombres)
# Iterar una lista
for nombre in nombres: # nombre es singular, la lista es plural
    print(nombre)
else:
    print('Se acabaron los elementos de la lista')
