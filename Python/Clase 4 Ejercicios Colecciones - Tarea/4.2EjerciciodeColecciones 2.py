# Ejercicio 2: Operaciones de conjuntos con listas
#
# Escriba un programa que tenga 2 listas y que a continuación
# cree las siguientes listas (en las que no deben haber repetición)

# --- Preparación: Creamos dos listas de ejemplo ---
lista1 = ["python", "java", "javascript", "c++", "python", "php"]
lista2 = ["ruby", "python", "c#", "go", "java", "c++"]

print(f"Lista 1 original: {lista1}")
print(f"Lista 2 original: {lista2}")
print("-" * 30)

# Para no tener repeticiones y hacer las operaciones fácilmente,
# convertimos las listas a conjuntos (sets).
# Un conjunto automáticamente elimina los elementos duplicados.
conjunto1 = set(lista1)
conjunto2 = set(lista2)


# Resolución de los puntos del ejercicio

# 1. Lista de palabras que aparecen en CUALQUIERA de las listas (Unión)
#    Usamos el operador '|' para la unión de conjuntos.
lista_union = list(conjunto1 | conjunto2)
print(f"1. Palabras que aparecen en las listas: {lista_union}")


# 2. Lista de palabras que aparecen en la PRIMERA lista, pero NO en la segunda (Diferencia)
#    Usamos el operador '-' para la diferencia de conjuntos.
lista_solo_en_1 = list(conjunto1 - conjunto2)
print(f"2. Palabras que aparecen en la primera lista, pero no en la segunda: {lista_solo_en_1}")


# 3. Lista de palabras que aparecen en la SEGUNDA lista, pero NO en la primera (Diferencia)
#    Hacemos la diferencia en el orden inverso.
lista_solo_en_2 = list(conjunto2 - conjunto1)
print(f"3. Palabras que aparecen en la segunda lista, pero no en la primera: {lista_solo_en_2}")


# 4. Lista de palabras que aparecen en AMBAS listas (Intersección)
#    Usamos el operador '&' para la intersección de conjuntos.
lista_interseccion = list(conjunto1 & conjunto2)
print(f"4. Palabras que aparecen en ambas listas: {lista_interseccion}")