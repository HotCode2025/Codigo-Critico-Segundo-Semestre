#Repaso de conjuntos
#para definir un conjunto
conjunto2 = set() #unica manera de iniciarlizar en vacio es con set
conjunto1 = {'bye'} #Con las llaves sola no se puede comenzar un conjunto
conjunto2.add(7)
conjunto2.add('Hola')
print(conjunto2)

conjunto1.add('Hola')
print(conjunto1)
print(3 not in conjunto1) #Preguntamos si el numero 3 NO essta en el conjunto1

#Como hacer la igualdad de conjuntos
print(conjunto1 ==conjunto2) # Nos devuelve como respuesta un booleano

#Operaciones en conjuntos
conjunto3 = conjunto1 | conjunto2 #La linea une los dos conjuntos
print(conjunto3)

#Interseccion de conjuntos elementos en comun

conjunto3 = conjunto1 & conjunto2 #Que elemento tienen en comun
print(conjunto3)

conjunto3 = conjunto1 - conjunto2 #Asigna el valor que esta en el conjunto1 y no en el conjunto2
print(conjunto3)

conjunto3 = conjunto2 - conjunto1
print(conjunto3)

#Diferencia asimetrica
conjunto3 = conjunto1 ^ conjunto2 #elementos que no comparten o que son diferentes entre ambos
print(conjunto3)

conjunto3 = conjunto1 | conjunto2
print(conjunto1.issubset(conjunto3)) #Aqui preguntamos si un conjunto es un subconjunto dentro de otro
print(conjunto1.issubset(conjunto3))
print(conjunto3.issubset(conjunto1))
print(conjunto3.issubset(conjunto2))

print(conjunto3.issuperset(conjunto1)) #Preguntamos si los elementos del conjunto1 estan dentro del 3
print(conjunto3.issuperset(conjunto2))  #Si es verdadero quiere decir que el conjunto3 es un superconjunto
print(conjunto2.issuperset(conjunto3))

#Como saber si ambos conjuntos son disconexos, esto es si no comparten elementos en comun
print(conjunto1.isdisjoint(conjunto2)) #No hay cosas en comun

#Convertir un conjunto totalmente inmutable
conjunto1 = frozenset #Esto hace que el conjunto sea totalmente inmutable
#No se puede agregar, modificar ni elementos elementos del conjunto.


