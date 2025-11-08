let x = 10; // variable de tipo primitiva
console.log(x.length);
console.log('Tipos Primitivos');

// Objeto
let persona = { // abrir bloque llave buenas practicas
    nombre: 'Carlos',
    apellido: 'Gil',
    email: 'cgil@gmail.com',
    edad: 28,
    idioma: 'es',
     get lang(){
        return this.idioma.toUpperCase(); // Convierte las minusculas a mayusculas
     },
     set lang(lang){
        this.idioma = lang.toUpperCase();
     },

    nombreCompleto: function(){ // metodo o funcion en JavaScript   
        return this.nombre+' '+this.apellido;        
     },
     get nombreEdad(){ // Este es el metodo GET
        return 'El nombre es: '+this.nombre+ ', Edad: '+this.edad

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


console.log('Comenzamos a utilizar el metodo GET');
console.log(persona.nombreEdad);

console.log('Comenzamos con el metodo get y set para idiomas');
persona.lang = 'en';
console.log(persona.lang);

function Persona3(nombre, apellido, email){ //Constructor
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.nombreCompleto = function(){
        return this.nombre+' '+this.apellido;
    }
}
let padre = new Persona3('Leo', 'Lopez', 'lopezl@gmail.com');
padre.nombre = 'Luis' // modificamos el nombre
padre.telefono = '5492612525743'; // Una propiedad exlcusiva del objeto padre
console.log(padre);
console.log(padre.nombreCompleto()); // Utilizamos la funcion

let madre = new Persona3('Laura', 'Contreras', 'contrerasl@gmail.com');
console.log(madre);
console.log(madre.telefono); // La propiedad no esta definida
console.log(madre.nombreCompleto());


//Diferentes formas de crear objetos
// Caso Numero 1
let miObjeto = new Object(); //Esta es una opcion formal

//Caso Numero 2
let miObjeto2 = {}; //Esta opcion es breve y recomendada

//Caso String
let miCadena1 = new String('Hola'); //Sintaxis formal

// Caso String 2
let micadena2 = 'Hola'; // Esta es la sintaxis simplificada y recomendada

// Caso con numeros 1
let miNumero = new Number(1); // Es formal no recomendable

// Caso con numeros 2
let miNumero2 = 1; // Sintaxis recomendada

//Caso boolean 1
let miBoolean = new Boolean(false); //Forma 1
// Caso boolean 2
let miBoolean2 = false; // Sintaxis Recomendada

// Caso Arreglos 1
let miArreglo1 = new Array(); //Forma 1
// Caso Arreglos 2
let miArreglo2 =[]; //Sintaxis recomendada

// Caso Function 1
let miFuncion1 = new function(){}; // Todo despues de new es considera objeto

// Caso function 2
let miFuncion2 = function(){}; //Notacion simplificada y recomendada

//Uso de prototype
Persona3.prototype.telefono = '2618383832';
console.log(padre);
console.log(madre.telefono);
madre.telefono = '5492618383832';
console.log(madre.telefono);

//Uso de call
let persona4 = {
    nombre: 'Juan',
    apellido: 'Perez',
    nombreCompleto2: function(titulo, telefono){
     return titulo+': '+this.nombre+' '+this.apellido+' '+telefono;
        // return this.nombre+' '+this.apellido;
    
    }
}

let persona5 = {
    nombre: 'Carlos',
    apellido: 'Lara',
}

console.log(persona4.nombreCompleto2('Lic.', '5492618383834'));
console.log(persona4.nombreCompleto2.call(persona5, 'Ing.', '5492618686867'));

//Metodo Apply necesita que tengamos un arreglos los argumentos necesarios para lo que esta requiriendo.
let arreglo = ['Ing.', '5492618686865'];
console.log(persona4.nombreCompleto2.apply(persona5, arreglo));
