# Ejercicio 3: Función recursiva para imprimir números descendentes

def imprimir_descendente(n):
    if n <= 0:   # Caso base
        return
    print(n)
    imprimir_descendente(n - 1)  # Llamada recursiva

# ejemplo
imprimir_descendente(5)  # Esto imprime 5, 4, 3, 2, 1

