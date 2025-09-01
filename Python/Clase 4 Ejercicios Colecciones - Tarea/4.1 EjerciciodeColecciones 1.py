# Ejercicio 1: Eliminar duplicados de una lista
# Escriba un programa donde tenga una lista y que a continuación
# elimine los elementos repetidos, por último mostrar la lista.

# Creamos una lista con elementos duplicados para el ejemplo.
# Puedes cambiarla por la lista que tú quieras.
lista_original = [1, 2, 3, "Python", 4, 2, 5, "Hola", 3, "Python"]

print(f"Lista Original: {lista_original}")

# 1. Convertimos la lista a un conjunto (set) para eliminar los duplicados automáticamente.
# 2. Luego, volvemos a convertir el conjunto a una lista.
lista_sin_duplicados = list(set(lista_original))

# Por último, mostramos la nueva lista sin los elementos repetidos.
print(f"Lista sin duplicados: {lista_sin_duplicados}")