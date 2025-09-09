# Ejercicio 11: Agenda telefonica
# Hacer un programa que simule una agenda de contactos. Crear un
# diccionario donde la clave sea el nombre del usuario y el valor
# sea el telefono, el programa tendrá el siguiente menú de opciones:
#
#    1. Nuevo contacto
#    2. Borrar contacto
#    3. Ver contactos existentes
#    4. Salir

# 1. Inicializamos el diccionario que funcionará como nuestra agenda.
#    Comienza vacío.
agenda = {}

# 2. Creamos un bucle infinito 'while True' que mantendrá el programa
#    corriendo hasta que el usuario decida salir.
while True:
    # 3. Mostramos el menú de opciones en cada repetición del bucle.
    print("\n..::MENÚ DE AGENDA::..")
    print("1. Nuevo contacto")
    print("2. Borrar contacto")
    print("3. Ver contactos existentes")
    print("4. Salir")

    # 4. Pedimos al usuario que elija una opción.
    try:
        opcion = int(input("Digite una opción del menú: "))
    except ValueError:
        print("Error: Por favor, ingrese solo un número.")
        continue # Vuelve al inicio del bucle

    print() # Agrega un espacio para mayor legibilidad

    # 5. Usamos condicionales (if/elif/else) para ejecutar el código
    #    correspondiente a la opción elegida.

    if opcion == 1:
        # Opción 1: Agregar un nuevo contacto
        nombre = input("Digite el nombre del contacto: ")
        telefono = input("Digite el número de teléfono: ")

        if nombre not in agenda:
            agenda[nombre] = telefono
            print(f"¡Contacto '{nombre}' agregado exitosamente!")
        else:
            print(f"El contacto '{nombre}' ya existe en la agenda.")

    elif opcion == 2:
        # Opción 2: Borrar un contacto existente
        nombre = input("¿Qué contacto desea borrar?: ")

        if nombre in agenda:
            del agenda[nombre] # 'del' elimina el elemento del diccionario
            print(f"Contacto '{nombre}' ha sido borrado.")
        else:
            print(f"El contacto '{nombre}' no existe.")

    elif opcion == 3:
        # Opción 3: Mostrar todos los contactos
        print("--- Lista de Contactos Sistema Código Critico  ---")
        if agenda: # Esto comprueba si el diccionario tiene al menos un elemento
            # Usamos un bucle 'for' para recorrer el diccionario
            for nombre, telefono in agenda.items():
                print(f"Nombre: {nombre} | Teléfono: {telefono}")
        else:
            print("No hay contactos en la agenda.")
        print("--------------------------")

    elif opcion == 4:
        # Opción 4: Salir del programa
        print("Gracias por utilizar la agenda diseñada por Código Crítico. ¡Hasta pronto!")
        break # 'break' rompe el bucle 'while' y termina el programa

    else:
        # Si el usuario ingresa un número no válido
        print("Opción no válida. Por favor, elija una opción del 1 al 4.")