class Orden {
  static contadorOrdenes = 0;
  static MAX_PRODUCTOS = 5;

  constructor() {
    this.idOrden = ++Orden.contadorOrdenes;
    this.productos = [];
  }

  agregarProducto(producto) {
    if (this.productos.length < Orden.MAX_PRODUCTOS) {
      this.productos.push(producto);
    } else {
      console.log("No se pueden agregar más productos a esta orden.");
    }
  }

  calcularTotal() {
    return this.productos.reduce((total, producto) => total + producto.getPrecio(), 0);
  }

  mostrarOrden() {
    let productosStr = this.productos.map(p => p.toString()).join("\n");
    return `Orden [ID: ${this.idOrden}]\nProductos:\n${productosStr}\nTotal: $${this.calcularTotal()}`;
  }
}
