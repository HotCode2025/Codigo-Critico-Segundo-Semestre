#Tipo Set
planetas = {'Marte', 'Jupiter', 'Venus'}
print(len(planetas)) # Usamos la funcion len = length significa largo

#Revisar si un elemento existe dentro de set
print('Jupiter' in planetas)

#Agregar un elemento
planetas.add('Tierra') # add es una funcion
print(planetas)

#Eliminar elementos, puede arrojar un error si el elemento no existe
planetas.remove('Jupiter') # Esta funcion ante un mal ingreso u inexistencia del elemento da error
print(planetas)
planetas.discard('tierra') #Esta funcion no nos presenta ningun tipo de error
print(planetas)

#Limpiar tipo set o conjunto
planetas.clear()
print(planetas)

#Eliminar set o conjunto
del planetas
