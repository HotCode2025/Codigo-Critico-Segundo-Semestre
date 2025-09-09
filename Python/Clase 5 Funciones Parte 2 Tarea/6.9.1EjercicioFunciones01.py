# Ejercicio 01: Crear una función para sumar los valores recibidos de tipo
# numéricos, utilizando argumentos variables *args como parametro de la
# Función y agregar como resultado la suma de todos los valores pasados
# como argumentos.

def sumar_valores(*args):
  """
  Esta función recibe un número variable de argumentos numéricos
  y devuelve la suma total de ellos.
  """
  suma = 0  # Inicializamos la variable que guardará la suma en 0
  for valor in args: # Recorremos cada valor pasado en los argumentos
    # Verificamos si el valor es de tipo numérico (entero o flotante) antes de sumar
    if isinstance(valor, (int, float)):
      suma += valor # Sumamos el valor al total
    else:
      print(f"Advertencia: El valor '{valor}' no es numérico y será ignorado.")
  return suma

# --- Ejemplos de uso de la función ---

# 1. Pasando varios números como argumentos
resultado1 = sumar_valores(10, 20, 30, 5, 5)
print(f"El resultado de la primera suma es: {resultado1}")

# 2. Pasando otra cantidad de números
resultado2 = sumar_valores(2, 4, 6)
print(f"El resultado de la segunda suma es: {resultado2}")

# 3. Pasando también números flotantes
resultado3 = sumar_valores(1.5, 3.5, 10)
print(f"El resultado de la tercera suma es: {resultado3}")

# 4. Ejemplo con un valor no numérico (será ignorado)
resultado4 = sumar_valores(10, "holamundo", 20)
print(f"El resultado de la cuarta suma es: {resultado4}")