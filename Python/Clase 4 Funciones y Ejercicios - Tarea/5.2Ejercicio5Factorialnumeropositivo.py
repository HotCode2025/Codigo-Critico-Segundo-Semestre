# Ejercicio 5: Factorial de un número positivo
# Hacer un programa para calcular el factorial de un número positivo

# 1. Pedimos al usuario que ingrese un número y lo convertimos a entero.
numero = int(input("Digite un número para calcular su factorial: "))

# 2. Verificamos si el número es negativo.
if numero < 0:
    print("Error: El factorial no está definido para números negativos.")

# 3. El caso especial: el factorial de 0 es 1.
elif numero == 0:
    print("El factorial de 0 es 1")

# 4. Si el número es positivo, calculamos el factorial.
else:
    # Inicializamos una variable para guardar el resultado. La empezamos en 1.
    factorial = 1
    # Creamos un bucle que vaya desde 1 hasta el número ingresado (inclusive).
    for i in range(1, numero + 1):
        # En cada paso, multiplicamos el valor actual de 'factorial' por el número del bucle.
        factorial = factorial * i
    # 5. Imprimimos el resultado final.
    print(f"El factorial de {numero} es: {factorial}")