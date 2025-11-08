class Persona{ // Con mayusucula para reconocer las clases facilmente
    constructor(nombre, apellido){
        this.nombre = nombre;
        this.apellido = apellido;
    }
} 

let persona1 = new Persona('Martín', 'Perez');
console.log(persona1);

let persona2 = new Persona('Carlos', 'Lara');
console.log(persona2);
