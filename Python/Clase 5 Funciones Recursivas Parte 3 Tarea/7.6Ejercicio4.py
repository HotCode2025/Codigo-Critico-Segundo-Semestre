# Ejercicio 4: Calculadora de Impuestos

def calcular_pago_total(pago_sin_impuesto, impuesto):
    pago_total = pago_sin_impuesto + pago_sin_impuesto * (impuesto / 100)
    return pago_total

# ejemplo
pago_sin_impuesto = float(input("Ingrese el pago sin impuesto: "))
impuesto = float(input("Ingrese el monto del impuesto (%): "))

total = calcular_pago_total(pago_sin_impuesto, impuesto)
print(f"Pago con impuesto: {total}")
