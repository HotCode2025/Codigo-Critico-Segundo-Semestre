class Producto{
    static contadorProductos = 0;
    constructor(nombre, precio){
        this._idProducto = ++Producto.contadorProductos;
        this._nombre = nombre;
        this._precio = precio;
    }
    get idProducto(){
        return this._idProducto;
    }

    get nombre (){
        return this._nombre;
    }

    set nombre(nombre){
        this._nombre = nombre;
    }


    get precio(){
        return this._precio;
    }

    set precio(precio){
        this._precio  = precio;
    }

    toString(){ // Se llama Template Literals: Nos permite insertar código dinamicamente
        return `idProducto : ${this._idProducto}, nombre: ${this._nombre}, precio: $${this._precio}`;
    }


} // Fin de la Clase Producto. (Buena Practica)

class Orden{
    static contadorOrdenes = 0;
    static getMAX_PRODUCTOS(){ //Simula una constante
        return 5;
    }

    constructor(){
        this._idOrden = ++Orden.contadorOrdenes;
        this._productos = [];
        this._contadorProductosAgregados = 0;
    }

    get idOrden(){
        return this._idOrden;
    }

    agregarProducto(producto){
        if(this._productos.length < Orden.getMAX_PRODUCTOS()){
          this._productos.push(producto);  //Tenemos 2 tipos de sintanxis: 1
          // this.productos[this._contadorProductosAgregados++] = producto; // Segunda Sintaxis
        }
        else{
            console.log("No se pueden agregar mas productos");
        }
    } // Fin del metodo agregarProducto

    calcularTotal(){
        let totalVenta =0;
        for(let producto of this._productos){
            totalVenta += producto.precio
        } // Fin del Ciclo for
        return totalVenta;
    } // Fin del metodo calcularTotal

    mostrarOrden(){
        let productoOrden = ' ';
        for(let producto of this._productos){
            productoOrden += '\n{ '+producto.toString()+' }';
        } // Fin del ciclo for
        console.log(`Orden: ${this._idOrden}, Total: $${this.calcularTotal()}, Productos: ${productoOrden}`);
    } // Fin metodo mostrarOrden

} // Fin de la clase Orden

let producto1 = new Producto('Pantalon', 200);
let producto2 = new Producto('Camisa', 150);
let producto3 = new Producto('Cinturon', 50);
let orden1 = new Orden();
let orden2 = new Orden();
orden1.agregarProducto(producto1); // este es el metodo por eso va a dentro del producto
orden1.agregarProducto(producto2);
orden1.agregarProducto(producto3);
orden1.agregarProducto(producto1);
orden1.agregarProducto(producto2); 
orden2.agregarProducto(producto3);
orden1.mostrarOrden();
orden2.mostrarOrden();

