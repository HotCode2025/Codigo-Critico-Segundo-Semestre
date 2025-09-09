# Ejercicio 5: Convertidor de temperaturas
# Realizar dos funciones para convertir de grados celsius
# a fahrenheit y viseversa.

# --- Definición de las funciones ---

def celsius_a_fahrenheit(celsius):
    """
    Esta función convierte una temperatura de grados Celsius a Fahrenheit.
    Fórmula: (celsius * 9/5) + 32
    """
    return (celsius * 9 / 5) + 32


def fahrenheit_a_celsius(fahrenheit):
    """
    Esta función convierte una temperatura de grados Fahrenheit a Celsius.
    Fórmula: (fahrenheit - 32) * 5/9
    """
    return (fahrenheit - 32) * 5 / 9


# --- Bloque principal para probar el código ---
# Este código se ejecutará solo si corres este archivo directamente.
if __name__ == "__main__":
    # --- Prueba 1: Convertir Celsius a Fahrenheit ---
    # Solicitamos al usuario que ingrese los grados Celsius
    grados_celsius_input = float(input("Introduce los grados Celsius a convertir: "))

    # Llamamos a la función para hacer la conversión
    resultado_fahrenheit = celsius_a_fahrenheit(grados_celsius_input)

    # Mostramos el resultado formateado a dos decimales
    print(f"{grados_celsius_input}°C equivalen a {resultado_fahrenheit:.2f}°F")

    print("-" * 30)  # Imprime una línea para separar

    # --- Prueba 2: Convertir Fahrenheit a Celsius ---
    # Solicitamos al usuario que ingrese los grados Fahrenheit
    grados_fahrenheit_input = float(input("Introduce los grados Fahrenheit a convertir: "))

    # Llamamos a la función para hacer la conversión
    resultado_celsius = fahrenheit_a_celsius(grados_fahrenheit_input)

    # Mostramos el resultado formateado a dos decimales
    print(f"{grados_fahrenheit_input}°F equivalen a {resultado_celsius:.2f}°C")