class Producto:
    contador_productos = 0  #Varianle de Clase

    def __init(self, nombre, precio):
        Producto.contador_productos += 1 #Aumento para variable de clase
        self._id_producto = Producto.contador_productos # Asignación desde la variable de clase
        self._nombre = nombre
        self._precio = precio

     # Metodos setters and getters
    @property
    def nombre(self):
        return self._nombre

    @nombre.setter
     def nombre(self, nombre):
        self._nombre = nombre

    @property
     def precio(self):
     return self._precio

    @precio.setter
    def precio(self, precio):
        self._precio = precio

    #Sobre escribimos el metodo str
    def__str__(self):
    return f´_Id Producto: (self._id_producto), Nombre: (self._nombre), Precio: (self._precio)´

if__name__ == ´__main__´: #solo será visible si la prueba se ejecuta desd aqui
    producto1 = Producto("Camiseta", 100.00)
    prodcuto2 = Producto("Pantalon", 150.00)



