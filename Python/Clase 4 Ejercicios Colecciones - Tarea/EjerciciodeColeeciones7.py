# Creamos la lista con los números del 1 al 10
numeros = list(range(1, 11))

# Pedimos al usuario 1ue ingrese un numero para multiplicar
multiplicador = int(input("Ingresá un número para multiplicar la lista: "))

# Multiplicamos cada elemento de la lista por el número ingresado
for i in range(len(numeros)):
    numeros[i] = numeros[i] * multiplicador

#Luego mostramos la lista modificada
print("Lista modificada:")
print(numeros)
