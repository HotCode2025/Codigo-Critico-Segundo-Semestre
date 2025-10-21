#15.2 Creamos la clase Orden: Parte 1

class orden:
    contador_ordenes = 0

    def __int__(self, productos):
        Orden.contador_ordenes += 1
        self.id_orden = Orden.contador_ordenes
        self._productos = list(productos)

    def agregar_producto(self, producto):
        self._productos.append(producto) # esto es para agregar el nuevo producto


#15.3 Creamos la clase Orden: Parte 2
def calcular_total(self):
    total = 0 #variable temporal para almacenar el total temporal
    for producto in self._productos:
        total += producto.precio
        return total

def __str__(self):
    productos_str = ""
    for producto in self_productos:
        productos_str += producto.__str__()+"/"
    return f"Orden: (self.id_orden), \nProducto: (producto_str)"


if __name__ == "__main__":
    producto1 = Producto("Camiseta", 100.00)
    print(producto1)
    producto2 = Producto("Pantalon", 150.00)
    print(producto2)


