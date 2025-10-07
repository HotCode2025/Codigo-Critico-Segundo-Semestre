class Cubo:#se crea la clase cubo
    
    def __init__(self, lado: float):#Inicializa un objeto Cubo con el valor del lado.
        self.lado = lado

    def calcular_volumen(self) -> float:#Calcula el volumen del cubo usando la fórmula: volumen = lado^3.
        return self.lado ** 3

    def calcular_area_superficial(self) -> float: #Calcula el área superficial del cubo usando la fórmula: área = 6 * lado^2.
        return 6 * (self.lado ** 2)


# programa Main
if __name__ == "__main__":
    print("Cálculo de propiedades de un cubo")
    lado = float(input("Ingrese la longitud del lado: "))
    cubo = Cubo(lado)

    print("\n--- Resultados ---")
    print(f"Lado del cubo: {cubo.lado}")
    print(f"Volumen: {cubo.calcular_volumen()}")
    print(f"Área superficial: {cubo.calcular_area_superficial()}")