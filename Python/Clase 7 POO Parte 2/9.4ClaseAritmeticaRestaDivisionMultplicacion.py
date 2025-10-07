class Aritmetica:
    """
    El nombre de este tipo de comentario es: DocString
    esto es documentacion de la clase en Python
    Vamos a hacer en esta clase algunas operaciones de: suma, resta, multiplicacion y más.
    """

    def __init__(self, operandoA, operandoB):
        self.operandoA = operandoA
        self.operandoB = operandoB

    #Metodo para SUMAR
    def sumar(self):
        return self.operandoA + self.operandoB

    # Metodo para RESTAR
    def resta(self):
        return self.operandoA - self.operandoB

    # Metodo para MULTIPLICAR
    def multiplicacion(self):
        return self.operandoA * self.operandoB

    # Metodo para DIVIDIR
    def dividir(self):
        return  self.operandoA / self.operandoB


aritmetica1 = Aritmetica(7, 9)  #Le pasamos los argumentos para los operandos
print(aritmetica1.sumar())
print(f'La suma de los numeros es: {aritmetsica1.sumar()}')
print(f'La resta de los numeros es: {aritmetica1.resta()}')
print(f'La multiplicacion de los numeros es: {aritmetica1.multiplicacion()}')
print(f'La division de los numeros es: {aritmetica1.dividir():.2f}') #2f solo muestra dos numeros decimales
