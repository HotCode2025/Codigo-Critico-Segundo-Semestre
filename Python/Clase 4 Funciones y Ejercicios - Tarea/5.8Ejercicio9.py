# Ejercicio 9: Mostrar una frase sin espacios y contar su longitud
# Hacer un programa donde el usuario ingrese una frase, se le
# devolverá la misma frase pero sin espacios en blanco, y
# además un contador de cuántos caracteres tiene la frase
# (sin contar los espacios en blanco)
# Ejemplo:     frase = vivir por siempre en paz
#              frase final = vivirporsiempreenpaz
#              N° de caracteres = 20.

frase = input("Ingresá una frase: ")

frase_sin_espacios = frase.replace(" ", "") #Quitar los espacios con replace
cantidad_caracteres = len(frase_sin_espacios) #len cuenta cuantos caracteres tiene la frase sin espacios
print("frase: ", frase)
print("Frase final:", frase_sin_espacios)
print("Número de caracteres:", cantidad_caracteres)
