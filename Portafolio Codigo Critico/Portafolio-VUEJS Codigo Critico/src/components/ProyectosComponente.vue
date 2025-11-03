<script setup>
// 1. Importa la imagen de tu proyecto. Asegúrate de tener una imagen en esa ruta.
import proyectoAgro from '/src/assets/agrogestionintegral.png';

// 1.b. Importa el logo de Tregar. 
// ¡Asegúrate de descargar un logo de Tregar y guardarlo en esa ruta!
import proyectoTregar from '/src/assets/tregar-logo.png'; // <-- Reemplaza con la ruta real si es diferente

// Este componente muestra una galería de proyectos con información sobre cada proyecto
import { ref } from 'vue';
//.ref es una función que se utiliza para crear una referencia reactiva en Vue 3
var misProyectos = ref([]);

// 2. Define tu proyecto "AgroGestiónIntegral" dentro del array
misProyectos.value = ([
  {
    id: 1,
    src: proyectoAgro, // Usa la imagen importada
    titulo: "AgroGestiónIntegral (2025)",
    descripcion: "Sistema de gestión modular para el sector agrícola, enfocado en la administración de cultivos intensivos.",
    projectoLink: "https://github.com/HotCode2025/Codigo-Critico-Segundo-Semestre/tree/main/Proyecto%20Final", // Reemplaza '#' con el enlace real si lo tienes desplegado
    githubLink: "https://github.com/HotCode2025/Codigo-Critico-Segundo-Semestre/tree/main/Proyecto%20Final" // Reemplaza con el enlace a tu repositorio
  },

  // --- PROYECTO TREGAR AÑADIDO ---
  {
    id: 2,
    src: proyectoTregar, // Usa la imagen importada de Tregar
    titulo: "Control de Stock TREGAR (PseInt)",
    descripcion: "Control de inventario para lácteos Tregar bajo lógica FIFO (Primero Entrado, Primero Salido). Desarrollado con PseInt.",
    projectoLink: "https://drive.google.com/drive/folders/1ScL0blcf2h6euA3oBCGzh9fcmfsQebwW?usp=sharing", // Link a tu Google Drive
    githubLink: "https://drive.google.com/drive/folders/1ScL0blcf2h6euA3oBCGzh9fcmfsQebwW?usp=sharing" // Link a tu Google Drive
  }
  // ----------------------------------

  // Puedes agregar más proyectos aquí en el futuro si lo deseas
])

</script>

<template>
  <div class="galeria">
    <div class="proyecto" v-for="proyecto in misProyectos" :key="proyecto.id">
      <img :src="proyecto.src" :alt="proyecto.titulo">
      <div class="proyecto-info">
        <h3>{{ proyecto.titulo }}</h3>
        <p>{{ proyecto.descripcion }}</p>
        <div class="proyecto-links">
          <a :href="proyecto.projectoLink" class="btn-ver-mas" target="_blank" rel="noopener noreferrer">Ver
            Proyecto</a>
          <a :href="proyecto.githubLink" class="github-link" target="_blank" rel="noopener noreferrer">Ver
            Código</a>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Estilos Fondo Animado de Css de la galería */
.galeria {
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap; /* <-- Esto ya lo hace responsivo */
  gap: 20px;
  padding: 20px;
  justify-content: center;
  background: linear-gradient(-45deg, #021526, #03346E, #6EACDA, #E2E2B6);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
  box-sizing: border-box; /* Asegura que el padding no afecte el ancho total */
}

@keyframes gradient {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

/* La clase 'proyecto' es el contenedor individual de cada proyecto */
.proyecto {
  display: flex;
  flex-direction: column;
  border: 2px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  background-color: #f9f9f9;
  
  /* --- ESTA ES LA CORRECCIÓN RESPONSIVA --- */
  /* Esta línea lo hace todo: */
  flex: 1 1 280px; 
  /* Crece a más de 280px si hay espacio (móvil) */
  /* Se encoge si hay menos de 280px (móvil chico) */
  /* Mide 280px como base (escritorio) */
  box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* Sombra suave */
}

/* Estilo para las imágenes dentro del contenedor 'proyecto' */
.proyecto img {
  width: 100%;
  height: auto; 
  /* 'aspect-ratio' hace que todas las imágenes
     tengan la misma altura (formato 16:9).
     Esto hace que el diseño se vea mucho más ordenado.
  */
  aspect-ratio: 16 / 9;
  object-fit: cover; /* Hace que la imagen llene el espacio sin deformarse */
  display: block;
  border-bottom: 1px solid #eee; /* Separador sutil */
}

/* La clase 'proyecto-info' contiene la información del proyecto */
.proyecto-info {
  padding: 15px;
  text-align: center;
  /* Hacemos que esta sección crezca para que los botones
     siempre queden alineados abajo. */
  display: flex;
  flex-direction: column;
  flex-grow: 1; 
}

/* Estilo para los títulos de los proyectos */
.proyecto-info h3 {
  margin: 10px 0;
  font-size: 1.3em;
  color: #333;
}

/* Estilo para los párrafos de los proyectos */
.proyecto-info p {
  margin: 10px 0;
  font-size: 1em;
  color: #666;
  flex-grow: 1; /* Empuja los links hacia abajo */
  min-height: 60px; /* Alto mínimo para que el texto se alinee */
}

/* La clase 'proyecto-links' organiza los enlaces y botones del proyecto */
.proyecto-links {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 20px; /* Espacio antes de los botones */
}

/* Estilo para el botón 'Ver Más' */
.proyecto-links .btn-ver-mas {
  background-color: #007BFF;
  color: #fff;
  padding: 10px 15px;
  border-radius: 5px;
  text-decoration: none;
  transition: background-color 0.3s;
}

.proyecto-links .btn-ver-mas:hover {
  background-color: #0056b3;
}

/* Estilo para el enlace a GitHub */
.proyecto-links .github-link {
  color: #333;
  text-decoration: none;
  font-size: 0.9em;
}

.proyecto-links .github-link:hover {
  text-decoration: underline;
}

/* ============================================= */
/* --- PULIDO PARA CELULARES --- */
/* ============================================= */
@media (max-width: 600px) {
  .galeria {
    /* Reducimos el padding y el gap en móviles */
    padding: 1rem;
    gap: 1rem;
  }

  .proyecto {
    /* Hacemos que la base sea un poco más pequeña
       para que se ajuste mejor, pero igual crecerá */
    flex-basis: 260px;
  }

  .proyecto-info p {
    min-height: auto; /* Quitamos el alto mínimo en móvil */
  }
}
</style>