<script setup>
// 1. Importa el ícono desde la ruta correcta (src/assets/github_icon.svg)
import githubIcon from '@/assets/github_icon.svg'; 
</script>

<template>
  <div class="datos-personales">
    <h1>CODIGO CRITICO</h1>
    <h2>Equipo de Estudiantes de Programación - UTN</h2>
    <p>
      Hola, bienvenido a nuestro portafolio. Somos un equipo de estudiantes de la Tecnicatura Universitaria en Programación,
      enfocados en aplicar nuestros conocimientos para crear soluciones de software innovadoras y funcionales.
    </p>

    <div class="social-links">
      
      <a href="https://github.com/HotCode2025/Codigo-Critico-Segundo-Semestre" target="_blank" rel="noopener noreferrer" title="Repositorio del Equipo">
        
        <img :src="githubIcon" alt="GitHub" class="social-icon">

      </a>
    </div>
    
    </div> </template>

<style scoped>
/* 3. Estilos actualizados */
.datos-personales {
  background-color: #2c3e50; /* Color de fondo oscuro */
  color: #ecf0f1; /* Color de texto claro */
  padding: 4rem 2rem;
  text-align: center;
  border-radius: 12px;
  margin: 2rem auto;
  max-width: 900px; /* Ancho máximo */
}

h1 {
  font-size: 3rem;
  font-weight: 700;
  margin-bottom: 0.5rem;
  color: #ffffff; /* Título principal en blanco */
}

h2 {
  font-size: 1.75rem;
  font-weight: 400;
  margin-bottom: 1.5rem;
  color: #bdc3c7; /* Subtítulo un poco más suave */
}

p {
  font-size: 1.1rem;
  line-height: 1.6;
  max-width: 700px;
  margin: 0 auto 2rem auto;
}

.social-links {
  margin-bottom: 1.5rem;
}

.social-links a {
  margin: 0 0.75rem;
  display: inline-block; /* Para que la transición funcione bien */
  transition: transform 0.3s ease;
}

.social-links a:hover {
  transform: scale(1.1); /* Efecto de zoom */
}

/* Estilos para la imagen del ícono */
.social-icon {
  width: 40px;  /* Ajusta el tamaño (ej: 40px) */
  height: 40px; /* Ajusta el tamaño (ej: 40px) */
  vertical-align: middle;
}
</style>