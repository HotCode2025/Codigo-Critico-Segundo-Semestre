# Ejercicio 2: Función con *args para multiplicar
# Crear una función para multiplicar los valores recibidos
# de tipo numérico, utilizando argumentos variables *args
# como parámetro de la función y regresar como resultado
# la multiplicación de todos los valores pasados como argumento

def multiplicar_valores(*args):
    """
    Esta función recibe una cantidad variable de argumentos numéricos
    y devuelve el producto de todos ellos.
    """
    # Se inicializa la variable 'resultado' en 1.
    # Es importante que sea 1, porque cualquier número multiplicado por 1
    # da como resultado el mismo número. Si fuera 0, el resultado siempre sería 0.
    resultado = 1

    # Se itera sobre cada número pasado en los argumentos (args).
    for numero in args:
        # Se multiplica el resultado actual por el número de la iteración.
        resultado *= numero

    # Se retorna el resultado final de la multiplicación.
    return resultado


# --- Ejecución  ---

# Se llama a la función con varios argumentos.
resultado1 = multiplicar_valores(2, 3, 4, 5)
print(f"El resultado de multiplicar (2, 3, 4, 5) es: {resultado1}")

# Se llama a la función con otros argumentos.
resultado2 = multiplicar_valores(10, 2, 6)
print(f"El resultado de multiplicar (10, 2, 6) es: {resultado2}")

# Se llama a la función con un solo argumento.
resultado3 = multiplicar_valores(7)
print(f"El resultado de multiplicar (7) es: {resultado3}")

# Si no se pasan argumentos, el resultado es 1 (el valor inicial).
resultado4 = multiplicar_valores()
print(f"El resultado sin argumentos es: {resultado4}")