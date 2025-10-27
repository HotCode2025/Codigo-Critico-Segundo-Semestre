// Ejercicio 1: Función que valide una contraseña (mínimo 8 caracteres, 1 número, 1 mayúscula)
function validatePassword(password) {
  // 1. Verificar la longitud mínima
  const hasMinLength = password.length >= 8;

  // 2. Verificar si contiene al menos un número
  // /\d/ es una expresión regular que busca cualquier dígito (0-9)
  const hasNumber = /\d/.test(password);

  // 3. Verificar si contiene al menos una mayúscula
  // /[A-Z]/ es una expresión regular que busca cualquier letra de la A a la Z
  const hasUppercase = /[A-Z]/.test(password);

  // La función devuelve true solo si las TRES condiciones se cumplen
  return hasMinLength && hasNumber && hasUppercase;
}

console.log(`"Abc12345": ${validatePassword("Abc12345")}`); // true
console.log(`"weak": ${validatePassword("weak")}`); // false
console.log(`"nouppercase123": ${validatePassword("nouppercase123")}`); // false
console.log(`"NoNumber": ${validatePassword("NoNumber")}`); // false