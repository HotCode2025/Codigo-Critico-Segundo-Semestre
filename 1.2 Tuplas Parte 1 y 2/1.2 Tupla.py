# Definimos una tupla
cocina = ('cuchara','cuchillo','tenedor')
print(len(cocina))

#Acceder un elemento, para esto utilizaremos corchetos no parentesis
print(cocina[0])

#Mostrar de manera inversa
print(cocina[-1])

#Acceder a un rango
print(cocina[0:2])  #Esto es una tupla se representa así con parentesis
#Ejemplo
verduras = ('papa',) #Esto es un string NO TUPLA porque falta la coma y más elementos.
# UNA TUPLA NECESITA AUNQUE SEA UN ELEMENTO "LA COMA"
# DE LO CONTRARIO SOLO SERIA UN STRING TIPO CADENA

#Recorremos los elementos de una Tupla
for cocinar in cocina:
    print(cocinar, end=' ') #Print esta usando \n para saltos de linea. Es parte de la funcion Print
    #usamos end= para eliminar saltos de linea

cocinaLista = list(cocina)
cocinaLista[0] = 'plato'
cocina = tuple(cocinaLista)
print('\n',cocina)
#arriba conversion de tupla a lista

# del cocina esto es para eliminar un tupla
