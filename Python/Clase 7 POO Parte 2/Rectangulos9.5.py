class Rectangulo:
    """
    Clase que representa un rectangulo.
    
    Atributos:
        base (float): La longitud de la base del rectángulo.
        altura (float): La altura del rectangulo.
    """
    
    def __init__(self, base: float, altura: float):
        """
        Inicializa un objeto Rectangulo con base y altura.

        Args:
            base (float): La longitud de la base.
            altura (float): La altura.
        """
        self.base = base
        self.altura = altura

    def calcular_area(self) -> float:
        """
        Calcula el area del rectangulo usando la fórmula:
        área = base * altura.

        Returns:
            float: area del rectangulo.
        """
        return self.base * self.altura


# programa principla
if __name__ == "__main__":
    rectangulos = []

    # se crean 3 objetos ingresados por el usuario
    for i in range(1, 4):
        print(f"\nRectangulo {i}")
        base = float(input("Ingrese la base: "))
        altura = float(input("Ingrese la altura: "))
        rect = Rectangulo(base, altura)
        rectangulos.append(rect)

    # mostrar áreas de cada rectangulo
    print("\n--- areas de los Rectangulos ---")
    for i, rect in enumerate(rectangulos, start=1):
        print(f"Área del rectangulo {i}: {rect.calcular_area()}")
