# Ejercicio 4: Sumar números pares dentro de un rango
# Hacer un programa para sumar números pares dentro
# de un rango, por ejemplo:
#
#                 suma de números pares del 2 al 30
#                 suma = 240
#

# 1. Definimos el rango con el que vamos a trabajar.
inicio = 2
fin = 30

# 2. Inicializamos una variable en cero para almacenar la suma.
suma_pares = 0

# 3. Usamos un bucle 'for' para recorrer cada número desde el inicio hasta el fin.
#    Se pone 'fin + 1' porque range() no incluye el último número.
for numero in range(inicio, fin + 1):

    # 4. Verificamos si el número actual es par.
    #    Un número es par si al dividirlo por 2, el residuo (o módulo) es 0.
    if numero % 2 == 0:
        # 5. Si es par, lo agregamos a nuestra suma.
        suma_pares += numero  # Esto es lo mismo que: suma_pares = suma_pares + numero

# 6. Al final del bucle, imprimimos el resultado.
print(f"La suma de los números pares del {inicio} al {fin} es:")
print(f"Suma = {suma_pares}")