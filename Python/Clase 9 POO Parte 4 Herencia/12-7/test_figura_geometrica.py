from Rectangulo import Rectangulo

rectangulo1 = Rectangulo(5, 8, 'Rojo')
print(rectangulo1.base)
print(rectangulo1.altura)



#MRO = Method Resolution Order
print(Rectangulo.mro())