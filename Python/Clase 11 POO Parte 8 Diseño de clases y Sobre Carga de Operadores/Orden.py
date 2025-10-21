#15.2 Creamos la clase Orden: Parte 1

class orden:
    contador_ordenes = 0

    def __int__(self, productos):
        Orden.contador_ordenes += 1
        self.id_orden = Orden.contador_ordenes
        self._productos = list(productos)

    def agregar_producto(self, producto):
        self._productos.append(producto) # esto es para agregar el nuevo producto


