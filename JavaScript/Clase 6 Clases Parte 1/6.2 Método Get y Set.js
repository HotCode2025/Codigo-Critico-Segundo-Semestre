class Persona{
    constructor(nombre, apellido){
        this._nombre = nombre;
        this. _apellido = apellido;
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