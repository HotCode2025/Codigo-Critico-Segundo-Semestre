<template>
  <nav class="navbar">
    <div class="navbar-menu">
      <ul>
        <!-- 
          Esta es la lista de 6 enlaces que tenías
          en el portafolio del equipo
        -->
        <li v-for="nav in navegacion" :key="nav.id">
          <a :href="nav.enlace" class="nav-item">{{ nav.nombre }}</a>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue';

// Esta es la lista de 6 enlaces del portafolio del equipo
const navegacion = ref([
  { id: 1, nombre: 'Equipo', enlace: '#equipo' },
  { id: 2, nombre: 'Educación', enlace: '#educacion' },
  { id: 3, nombre: 'Experiencia', enlace: '#experiencia' },
  { id: 4, nombre: 'Proyectos', enlace: '#proyectos' },
  { id: 5, nombre: 'Habilidades', enlace: '#habilidades' },
  { id: 6, nombre: 'Intereses', enlace: '#intereses' },
]);
</script>

<style scoped>
/* Este es el nuevo código CSS que me proporcionaste,
  aplicado a la barra de navegación del equipo.
*/
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap');

.navbar {
  position: fixed; /* Fija la barra en la parte superior */
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1000; /* Asegura que esté por encima de otro contenido */

  /* Usamos las variables globales definidas in main.css */
  background: var(--cardBgColor);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border-bottom: 2px solid var(--lineColor);

  padding: 0.8rem 1rem;
  font-family: var(--font-sans);
  box-sizing: border-box; /* Asegura que el padding no rompa el ancho */
}

.navbar-menu {
  display: flex;
  justify-content: flex-end; /* Alinea los ítems a la derecha */
  width: 100%;
}

.navbar-menu ul {
  list-style: none;
  display: flex;
  gap: 0.5rem; /* Espacio entre ítems */
  margin: 0;
  padding: 0;
}

/* El <li> es ahora el contenedor del <a> */
.navbar-menu li {
  margin: 0;
  padding: 0;
}

.nav-item {
  color: var(--color-text-light);
  text-decoration: none;
  font-size: 1rem;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border-radius: 6px;
  border: 1px solid transparent;
  transition: all 0.3s ease;

  /* Importante para que el <a> se comporte como bloque */
  display: block;
}

.nav-item:hover {
  /* Efecto Hover de tu paleta */
  background-color: var(--color-primary-accent);
  color: #ffffff;
  border-color: var(--color-primary-accent);
  text-shadow: 0 0 5px rgba(255, 255, 255, 0.5);
}

/* --- RESPONSIVIDAD PARA MÓVIL --- */
@media (max-width: 768px) {
  .navbar-menu {
    justify-content: center; /* Centra los ítems en móvil */
  }

  .navbar {
    padding: 0.5rem;
  }

  .navbar-menu ul {
    /* Asegura que quepan más elementos si son muchos */
    flex-wrap: wrap;
    justify-content: center;
    gap: 0.2rem;
  }

  .nav-item {
    font-size: 0.8rem; /* Texto más pequeño */
    padding: 0.4rem 0.6rem; /* Padding más pequeño */
  }
}
</style>