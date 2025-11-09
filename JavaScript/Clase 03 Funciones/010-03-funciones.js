miFuncion(8,2) //Aparece el del proimer llamado
//esto se conoce como hosting
function miFuncion(a, b){
   //console.log("Sumamos: "+ (a+b));
   return a + b;
}

//Lllamando la funcion
miFuncion(5,4);
let resultado = miFuncion(6,7)
console.log(resultado);

//Declaramos una funcions de tipo expresion o anonima
let x = function(a,b){return a + b}; //necesita cierre con punto y coma
resultado = x(5,6) // al llamarla se pone la variable y parentecis
console.log(resultado);


(function(a,b){
    console.log('Ejecutamos la funcion: '+ (a+b));
})(9,6)

console.log(typeof miFuncion);
function miFuncionDos(a, b){
   console.log(arguments.length);

}
miFuncionDos(5,7,7,3);
//toString
var miFuncionTexto = miFuncionDos.toString();
console.log(miFuncionTexto);

