# Ejercicio 10: No repetir caracteres
# Hacer un programa que pida una cadena por teclado, luego
# meter los caracteres en una lista sin repetir caracteres

# 1. Pedir al usuario que ingrese una cadena de texto
cadena_original = input("Por favor, ingresa una frase o palabra: ")

# 2. Crear una lista vacía para guardar los caracteres únicos
lista_sin_repeticion = []

# 3. Recorrer cada caracter de la cadena original
for caracter in cadena_original:
  # 4. Verificar si el caracter no está ya en nuestra nueva lista
  if caracter not in lista_sin_repeticion:
    # 5. Si no está, lo agregamos a la lista
    lista_sin_repeticion.append(caracter)

# 6. Mostrar el resultado final
print("\nCadena original:", cadena_original)
print("Lista de caracteres sin repetir:", lista_sin_repeticion)
