<template>
  <nav class="navbar">
    <div class="navbar-menu">
      <ul class="nav-list">
        <li v-for="nav in navegacion" :key="nav.id">
          <a :href="nav.enlace" class="nav-item">{{ nav.nombre }}</a>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue';
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
.navbar {
  /* --- CAMBIO PRINCIPAL --- */
  background-color: #2c3e50; /* <-- El azul que querías */

  color: #fff;
  padding: 0.5rem 1rem;
  align-items: center;
  width: 100%;
  box-sizing: border-box; /* Para que el padding no afecte el ancho */
}

/* Contenedor que empuja la lista a la derecha */
.navbar-menu {
  display: flex;
  justify-content: flex-end; /* Alinea la lista a la derecha */
  width: 100%;
}

/* La lista <ul> */
.nav-list {
  display: flex; /* Pone los <li> en fila */
  flex-wrap: wrap; /* Permite que se ajusten en pantallas pequeñas */
  list-style: none; /* Quita los puntos de la lista */
  padding: 0;
  margin: 0;
}

/* El enlace <a> */
.nav-item {
  color: #ecf0f1; /* Un blanco un poco más suave */
  text-decoration: none;
  font-weight: 500;
  padding: 8px 12px; /* Un padding más cómodo */
  margin: 0 0.25rem; /* Espacio entre elementos */
  display: inline-block; /* Para que el padding se aplique bien */
  
  /* Tu estilo de borde y transición */
  border: 1px solid transparent; /* Transparente para que no "salte" en hover */
  border-radius: 5px;
  transition: 0.4s;
}

/* Tu estilo de hover */
.nav-item:hover {
  background-color: hsla(160, 100%, 37%, 0.2);
  border-color: hsla(160, 100%, 37%, 0.4); /* Un borde sutil verde */
  color: #ffffff; /* Blanco puro al pasar el mouse */
}

@media (max-width: 768px) {
  /* En pantallas pequeñas, es mejor centrar los enlaces */
  .navbar-menu {
    justify-content: center;
  }
  .nav-list {
    justify-content: center;
  }
  .nav-item {
    padding: 6px 8px; /* Un poco más pequeño en móvil */
    margin: 2px;
  }
}
</style>