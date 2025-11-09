let x = 10; // variable de tipo primitiva
console.log(x.length);
console.log('Tipos Primitivos');

// Objeto
let persona = { // abrir bloque llave buenas practicas
    nombre: 'Carlos',
    apellido: 'Gil',
    email: 'cgil@gmail.com',
    edad: 30,
    nombreCompleto: function(){ // metodo o funcion en JavaScript   
        return this.nombre+' '+this.apellido;        
        }
}

console.log(persona.nombre); 
console.log(persona.apellido);
console.log(persona.email);
console.log(persona.edad);
console.log(persona) // muestra todo lo que tiene el objeto

console.log('Ejecutando con un Objeto');
console.log(persona.nombreCompleto())


// Creamos otro objeto de otra forma
let persona2 = new Object(); // Debe crear un nuevo objeto en memoria
persona2.nombre = 'Juan';
persona2.direccion = 'Salada 14';
persona2.telefono = '2622539357';
console.log(persona2.telefono); // hay que tener cuidado con mayusculas y minisculas en JS key senstitive
console.log('Creamos un nuevo Objeto');
console.log(persona.apellido); // Accedemos como si fuera un arreglo
console.log('Usamos el Ciclo for in');
// for in y accedemos al objeto como si fuera un arreglo
for(propiedad in persona){
    console.log(propiedad);
    console.log(persona[propiedad]);
}

console.log('Cambiamos y eliminmos un error');
persona.apellida = 'Zuniga' // Cambiamos dinamicamente un valor del objeto
delete persona.apellida // Eliminamos el error
console.log(persona);

//Distintas formas de imprimir un objeto
// Numero 1; La mas sencilla: Concatenar cada valor de cada propiedad
console.log('Distintas formas de imprimir un objeto: Forma 1');
console.log(persona.nombre+', '+persona.apellido);

//Numero 2: A traves del ciclo for in
console.log('Distintas formas de imprimir un objeto: Forma 2');
for(nombrePropiedad in persona){
    console.log(persona[nombrePropiedad]);
}

// Numero 3; La funcion Object.values()
console.log('Distintas formas de imprimir un objeto: Forma 3');
let personaArray = Object.values(persona);
console.log(personaArray);

// Numero 4: Utilizaremos el metodo JSON.stringify
console.log('Distintas formas de imprimir un objeto: Forma 4');
let personaString = JSON.stringify(persona);
console.log(personaString); //Serializacion hace JSON => objeto a cadena(string)