//hosting es pisar o alzar el objeto. No funciona como lo hace con las funciones
// NO se puede crear objeto antes de iniciarlizar la clase. No se permite antes de que esta la clase creada


// Let persona3 = new Persona('Carla', 'Ponce');  esto NO se debe hacer: Persona is not defined 
// IMPORTANTE: Definir la clase y de ahi crear los objtetos y sus metodos


class Persona{  //Clase PADRE

    static contadorObjetosPersona = 0; //Atributo estatico
    static contadorPersonas = 0;
    // email = 'Valor default email'; //Atributo no estatico

    static get MAX_OBJ(){ //Este metodo simula una constante
        return 5; 
    }

    constructor(nombre, apellido){
        this._nombre = nombre;
        this. _apellido = apellido;
        if(Persona.contadorPersonas < Persona.MAX_OBJ){
                this.idPersona = ++Persona.contadorPersonas;
        }
        else{
            console.log('Se ha superado el maximo de objetos permitidos');
        }
        Persona.contadorObjetosPersona++;
    
        // console.log('Se incrementa el contador: ' +Persona.contadorObjetosPersona);
    }

    get nombre(){
        return this._nombre;
    }

    set nombre(nombre){
        this._nombre = nombre;
    }
// Linea de Apellido agregadas por la tarea
    get apellido(){
        return this._apellido;
    }

    set apellido(apellido){
        this._apellido = apellido;
    }

    nombrecompleto(){
        return this.idPersona+' '+this._nombre+' '+this._apellido;
    }
    
    //Sobreescribiendo el metodo de la clase padre (Object)
    toString(){ //Regres un String
        // Se aplica el polimorfismo que significa = multiples formas en tiempo de ejecucion
        //El metodo que se ejecuta depende si es una referencia de tipo padre o hija
        return this.nombrecompleto();
    }

    static saludar(){
        console.log('Saludos desde este metodo static');
    }

    static saludar2(persona){
        console.log(persona.nombre+' '+persona.apellido);
    }
    
}

class Empleado extends Persona{  //Clase HIJA
    constructor(nombre, apellido, departamento){
        super(nombre, apellido )
        this._departamento = departamento;
    }

    get departamento(){
        return this._departamento;
    }

    set departamento(departamento){
        this._departamento = departamento;
    }

    // SOBREESCRITURA MODIFICAR EL COMPORTAMIENTO DE ALGUN METODO DE LA CLASE PADRE
    nombrecompleto(){
        return super.nombrecompleto()+', '+this._departamento;
    }

}

let persona1 = new Persona('Martin', 'Perez');
console.log(persona1.nombre);
persona1.nombre = 'Juan Carlos'
console.log(persona1.nombre);
//console.log(persona1);

let persona2 = new Persona ('Carlos', 'Lara');
console.log(persona2.nombre);
persona2.nombre + 'Maria Laura';
console.log(persona2.nombre);
//console.log(persona2);


// Uso del GEt y SET para el Apellido

console.log('Apellido Original:', persona1.apellido);
persona1.apellido = 'Gomez';
console.log('Apellido Modificado:', persona1.apellido);

console.log('Apellido Original:', persona2.apellido);
persona2.apellido = 'Mazara';
console.log('Apellido Modificado:', persona2.apellido);

//Comentario console.log muestran primero el apellido original
//(Perez) y luego de usar el SET muestran el apellido ya modificado
//(Gomez), demostrando que los cambios se aplicaron correctamente

let empleado1 = new Empleado('Maria', 'Gimenez', 'Sistemas');
console.log(empleado1);
console.log(empleado1.nombrecompleto());

//Object.prototype.toString Esta es la manera de acceder a atributos y metodos de manera dinamica
console.log(empleado1.toString());
console.log(persona1.toString());

// persona1.saludar(); NO SE UTILIZA DESDE EL OBJETO
Persona.saludar(); // si aparece con la clase Persona siempre con clases metodo Static
Persona.saludar2(persona1);

Empleado.saludar();
Empleado.saludar2(empleado1);

// console.log(persona1.contadorObjetosPersona);
console.log(Persona.contadorObjetosPersona);
console.log(Empleado.contadorObjetosPersona);

console.log(persona1.email);
console.log(empleado1.email);
// console.log(Persona.email); NO SE PUEDE ACCEDER DESDE LA CLASE EN SI PORQUE NO ES ESTATICO.

console.log(persona1.toString());
console.log(persona2.toString());
console.log(empleado1.toString());
console.log(Persona.contadorPersonas);

let persona3 = new Persona('Carla', 'Pertosi');
console.log(persona3.toString());
console.log(Persona.contadorPersonas);

console.log(Persona.MAX_OBJ);
// Persona.MAX_OBJ = 10; NO se puede modificar, ni alterea
console.log(Persona.MAX_OBJ);

let persona4 = new Persona('Franco', 'Díaz');
console.log(persona4.toString());

let persona5 = new Persona('Liliana', 'Paz');
console.log(persona5.toString());
