# Ejercicio 8: Menú interactivo - Cajero automático
# Hacer un programa que simule un cajero automático con un saldo
# inicial de 1000$ y tendrá el siguiente menú de opciones:
#     1. Ingresar dinero en la cuenta
#     2. Retirar dinero de la cuenta
#     3. Mostrar dinero disponible
#     4. Salir.

saldo = 1000

while True:
    print("\n--- MENÚ CAJERO AUTOMÁTICO ---")
    print("1. Ingresar dinero en la cuenta")
    print("2. Retirar dinero de la cuenta")
    print("3. Mostrar dinero disponible")
    print("4. Salir")

    opcion = input("Seleccioná una opción (1-4): ")

    if opcion == "1":
        monto = float(input("¿Cuánto querés ingresar?: "))
        saldo += monto
        print(f" Se ingresaron ${monto}. Saldo actual: ${saldo}")

    elif opcion == "2":
        monto = float(input("¿Cuánto querés retirar?: "))
        if monto <= saldo:
            saldo -= monto
            print(f"Se retiraron ${monto}. Saldo actual: ${saldo}")
        else:
            print("Fondos insuficientes.")

    elif opcion == "3":
        print(f"Saldo disponible: ${saldo}")

    elif opcion == "4":
        print("Gracias por usar el cajero. Hasta luego!")
        break

    else:
        print("Error. Elegí entre 1 y 4.")
