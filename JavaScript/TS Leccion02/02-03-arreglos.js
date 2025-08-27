
// Creacion de Array o arreglos
//let autos = new Array('Ferrari', 'Renault', 'Bm'); esta es la sitaxis vieja
const autos = ['Ferrari', 'Renault', 'Bm'];
console.log(autos);

//Recorremos los elementos de un arreglo
console.log(autos[0]);
console.log(autos[2]);

for(let i = 0; i< autos.length; i++){
    console.log(i+' : ' +autos[i])
}

//Modificamos los elementos del arreglo
autos[1] = 'Volvo';
console.log(autos [1]);

//Agregamos nuevos valores al arreglo
autos.push('Audi');//Agregamos el elementos al final
console.log(autos);

//Otras formas de agregar elementos al arreglos
autos[autos.length] = 'Porche';
console.log(autos)

//Tercera forma de agregar elementos teniendo CUIDADO
autos[5] ='Renault';
console.log(autos);

//Como preguntar si es un Array O Arreglo
console.log(Array.isArray(autos)); //Dwvuelve un resultado booleano


console.log(autos instanceof Array);// Preguntamos si la variable es una instancia de la clase Array

