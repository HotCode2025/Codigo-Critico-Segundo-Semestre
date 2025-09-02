# Ejercicio 3: Insertar elementos y ordenarlos

# 1. Crear una lista vacía para almacenar los números
numeros = []

print("Introducir números para agregar a la lista (escribe 0 para terminar):")

# 2. Usamos un ciclo infinito que se romperá cuando se cumpla una condición
while True:
    try:
        # 3. Pedir al usuario que introduzca un número
        entrada = int(input("Número: "))

        # 4. Comprobamos si el número es 0
        if entrada == 0:
            print("Se ha introducido un 0, el programa finaliza.")
            break  # Si es 0, salimos del ciclo while

        # 5. Si no es 0, lo agregamos a la lista
        else:
            numeros.append(entrada)

    except ValueError:
        print("Error: Por favor, introducir solo números enteros.")

# 6. Ordenamos la lista de números de menor a mayor
numeros.sort()

# 7. Mostramos la lista ordenada
print("\nLista de números ordenada:")
print(numeros)