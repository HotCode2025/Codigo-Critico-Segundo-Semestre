# Ejercicio 6: Tabla de multiplicar

# 1. Pedir el número al usuario
# Usamos int() para convertir el texto que introduce el usuario en un número entero.
numero = int(input("Por favor, introduce un número para ver su tabla de multiplicar: "))

# 2. Crear una lista vacía para guardar los resultados
tabla_de_multiplicar = []

# 3. Usar un bucle para recorrer los números del 1 al 10
# range(1, 11) genera una secuencia de números desde el 1 hasta el 10.
for i in range(1, 11):
    # Calcular la multiplicación
    resultado = numero * i
    # Añadir el resultado a la lista
    tabla_de_multiplicar.append(resultado)

# 4. Mostrar la lista con el resultado final
print(f"La tabla de multiplicar del {numero} es:")
print(tabla_de_multiplicar)