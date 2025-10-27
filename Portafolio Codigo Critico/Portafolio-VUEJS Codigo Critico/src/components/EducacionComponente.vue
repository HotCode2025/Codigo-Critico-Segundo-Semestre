<script setup>
import { ref } from 'vue';

const fechaColor = ref([]);
/* Ajustado a 4 colores para los 4 items de educación */
fechaColor.value = [
  {color: '#41516c'}, // Color para UTN
  {color: '#34A853'}, // Color para Bootcamp Fullstack (verde)
  {color: '#FF6B6B'}, // Color para DevOps (rojo suave)
  {color: '#FBCA3E'}  // Color para Ciberseguridad (amarillo)
];

/* Modificado con la UTN + cursos inventados */
const educacion = ref([
  {
    fecha: '2025', // Año actual
    title: 'Tecnicatura Universitaria en Programación', 
    descripcion: 'Cursando en la Universidad Tecnológica Nacional (UTN) - Facultad Regional Mendoza. Adquiriendo habilidades en desarrollo de software, lenguajes de programación y análisis de sistemas.', 
    enlace:'https://www4.frm.utn.edu.ar/tecnicatura-superior-programacion/'
  },
  {
    fecha: '2024', // Año finalizado
    title: 'Bootcamp Desarrollo Full Stack (MERN)', 
    descripcion: 'Programa intensivo de 600 horas enfocado en tecnologías modernas: MongoDB, Express, React y Node.js. Desarrollo de proyectos E2E, APIs RESTful y aplicaciones web SPA.', 
    enlace:'#' // Puedes poner un enlace a un proyecto de GitHub aquí
  },
  {
    fecha: '2024', // Año finalizado
    title: 'Certificación en DevOps y Cloud Computing', 
    descripcion: 'Curso especializado en integración continua (CI/CD) con Jenkins y GitHub Actions, orquestación de contenedores con Docker y Kubernetes, y gestión de infraestructura como código (IaC) en AWS.', 
    enlace:'#'
  },
  {
    fecha: '2023', // Año finalizado
    title: 'Introducción a la Ciberseguridad y Ethical Hacking', 
    descripcion: 'Formación sobre principios de seguridad informática, análisis de vulnerabilidades, pentesting web (OWASP Top 10) y técnicas de defensa de redes y sistemas.', 
    enlace:'#'
  }
]);
</script>

<template>
    <ul>
        <li v-for="(item, index) in educacion" :key="index" :style="{ '--fecha-color': fechaColor[index % fechaColor.length].color}">
        <div class="fecha">{{ item.fecha }}</div>
        <h3 class="title">{{ item.title }}</h3>
        <div class="descripcion">{{ item.descripcion }}</div>
        <a v.if="item.enlace && item.enlace !== '#'" class="enlace" :href="item.enlace" target="_blank">Saber más</a>
    </li>
    </ul>
</template>

<style scoped>
/* Estilos generales */
/* Importa una fuente moderna. 'Inter' es excelente para UI. */
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap');

/* Reseteo de estilos básicos para todos los elementos y pseudo-elementos */
*,
*::before,
*::after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* Nota: Si ya tienes estos estilos en un archivo global (como body, *, etc.), 
   puedes borrarlos de aquí para no duplicar código.
   Los he dejado para que el componente funcione por sí solo. 
*/
body {
  /* Paleta de colores más sobria y profesional */
  --color: #333; /* Texto principal oscuro */
  --bgColor: #f8f9fa; /* Fondo muy claro */
  --cardBgColor: #ffffff; /* Fondo de las tarjetas de eventos */
  --lineColor: #e0e0e0; /* Color de la línea de tiempo */
  --shadowColor: rgba(0, 0, 0, 0.08); /* Sombra sutil */
  --hoverShadow: rgba(0, 0, 0, 0.15); /* Sombra al pasar el mouse */

  min-height: 100vh;
  display: grid;
  align-content: center;
  gap: 2rem;
  padding: 2rem;
  font-family: 'Inter', sans-serif; /* Nueva fuente */
  color: var(--color);
  background: var(--bgColor);
}

/* Estilos para la lista (timeline) */
ul {
  margin-top: 2rem;
  --col-gap: 3rem; /* Mayor espacio entre columnas */
  --row-gap: 3rem; /* Mayor espacio entre filas */
  --line-w: 0.2rem; /* Línea más delgada */
  display: grid;
  grid-template-columns: var(--line-w) 1fr;
  grid-auto-columns: max-content;
  column-gap: var(--col-gap);
  list-style: none;
  width: min(70rem, 95%); /* Ancho ligeramente mayor */
  margin-inline: auto;
  position: relative; /* Para el ::before */
}

/* Estilo para la línea vertical que conecta los elementos de la lista */
ul::before {
  content: "";
  grid-column: 1;
  grid-row: 1 / span 20;
  background: var(--lineColor); /* Color de línea más suave */
  border-radius: calc(var(--line-w) / 2);
  position: absolute; /* Para que no afecte el flujo del contenido */
  left: 0; /* Asegura que esté en la primera columna */
  top: 0;
  bottom: 0;
  width: var(--line-w);
}

/* Estilo para los elementos de la lista que no son el último */
ul li:not(:last-child) {
  margin-bottom: var(--row-gap);
}

/* Estilo para cada ítem de la lista */
ul li {
  grid-column: 2;
  --inlineP: 1.5rem;
  margin-inline: var(--inlineP); /* Mantiene un margen para la "fecha" */
  grid-row: span 2;
  display: grid;
  grid-template-rows: min-content min-content min-content auto; /* Añade auto para el enlace */
  background: var(--cardBgColor);
  border-radius: 8px; /* Bordes redondeados */
  box-shadow: 0 4px 12px var(--shadowColor); /* Sombra suave */
  transition: transform 0.3s ease, box-shadow 0.3s ease; /* Transición para efectos hover */
  padding: 1.5rem; /* Padding interno para el contenido */
  position: relative; /* Para el círculo */
}

/* Efecto hover en los ítems */
ul li:hover {
    transform: translateY(-5px); /* Pequeño levantamiento */
    box-shadow: 0 8px 20px var(--hoverShadow); /* Sombra más pronunciada */
}

/* Estilo para la fecha dentro de cada ítem */
ul li .fecha {
  --dateH: 2.5rem; /* Altura de fecha ligeramente menor */
  height: var(--dateH);
  line-height: var(--dateH); /* Centra verticalmente el texto */
  text-align: center;
  background-color: var(--fecha-color);
  color: white;
  font-size: 1rem; /* Tamaño de fuente ligeramente menor */
  font-weight: 600; /* Texto seminegrita */
  border-radius: 6px; /* Bordes redondeados para la fecha */
  position: absolute; /* Posiciona absolutamente */
  top: 1.5rem; /* Ajusta la posición para que esté dentro de la tarjeta */
  left: calc(-1 * var(--inlineP)); /* Mueve la fecha hacia afuera */
  padding: 0 1rem; /* Espaciado interno */
  min-width: 6rem; /* Ancho mínimo para la fecha */
  z-index: 1; /* Para que esté por encima del contenido */
}

/* Quitamos el pseudo-elemento ::before de la fecha para un look más limpio. */
ul li .fecha::before {
    content: none;
}


/* Estilo para el círculo que conecta la fecha con la línea */
ul li .fecha::after {
  content: "";
  position: absolute;
  width: 0.8rem; /* Círculo ligeramente más pequeño */
  aspect-ratio: 1;
  background: var(--cardBgColor); /* Usa el color de fondo de la tarjeta */
  border: 0.25rem solid var(--fecha-color); /* Borde un poco más delgado */
  border-radius: 50%;
  top: 50%;
  transform: translate(-50%, -50%); /* Centra el círculo en el borde de la fecha */
  /* Ajusta su posición para la línea. Es (padding + la mitad del ancho de la línea) */
  left: calc(var(--inlineP) * -1 - (var(--line-w) / 2) );
  z-index: 2; /* Para que esté por encima de la línea */
}

/* Estilos para el título, descripción y enlace dentro de cada ítem */
ul li .title {
  padding-block-start: 0.5rem; /* Ajusta el padding */
  padding-block-end: 0.5rem;
  font-weight: 600; /* Título un poco más grueso */
  font-size: 1.25rem; /* Tamaño de fuente del título */
  margin-top: 2rem; /* Espacio para la fecha que se superpone */
  color: #212529; /* Color más oscuro para el título */
}

ul li .descripcion {
  padding-block-end: 1rem;
  font-weight: 400; /* Descripción con peso normal */
  line-height: 1.6; /* Mejor interlineado para lectura */
  color: #6c757d; /* Color gris para la descripción */
  flex-grow: 1; /* Permite que la descripción ocupe el espacio disponible */
}

/* Enlace "Saber más" */
ul li .enlace {
    display: inline-block; /* Para poder aplicar padding y margin */
    margin-top: 1rem;
    padding: 0.5rem 1rem;
    background-color: var(--fecha-color);
    color: white;
    text-decoration: none;
    border-radius: 4px;
    font-size: 0.9rem;
    font-weight: 500;
    transition: filter 0.3s ease, transform 0.2s ease;
    align-self: start; /* Se alinea al inicio */
    justify-self: start; /* Se alinea al inicio */
}

ul li .enlace:hover {
    filter: brightness(1.15); /* Aclara el color al pasar el mouse */
    transform: translateY(-2px);
}


/* Quitamos los pseudo-elementos ::before del título y la descripción */
ul li .title::before,
ul li .descripcion::before {
  content: none;
}


/* Media query para pantallas anchas (40rem o más) */
@media (min-width: 40rem) {
  ul {
    grid-template-columns: 1fr var(--line-w) 1fr;
  }
  ul::before {
    grid-column: 2;
    left: 50%; /* Centra la línea */
    transform: translateX(-50%); /* Centrado exacto */
  }
  ul li:nth-child(odd) {
    grid-column: 1;
    /* Ajusta margen para que el contenido no se pegue a la línea */
    margin-inline-end: var(--col-gap); 
    margin-inline-start: 0;
  }
  ul li:nth-child(even) {
    grid-column: 3;
    /* Ajusta margen para que el contenido no se pegue a la línea */
    margin-inline-start: var(--col-gap);
    margin-inline-end: 0;
  }

  /* Esto soluciona un pequeño bug de alineación en grid */
  ul li:nth-child(2) {
    grid-row: 2; /* Asegura que el segundo item empiece en la segunda fila */
  }

  /* Ajustes específicos para los ítems impares (lado izquierdo) */
  ul li:nth-child(odd) .fecha {
    left: auto;
    right: calc(-1 * var(--inlineP)); /* Mueve la fecha a la derecha del ítem */
    border-radius: 6px;
  }

  ul li:nth-child(odd) .fecha::after {
    transform: translate(50%, -50%); /* Ajusta el círculo para el lado izquierdo */
    left: auto;
    /* Ajusta la posición: (padding + la mitad del ancho de la línea) */
    right: calc(var(--inlineP) * -1 - (var(--line-w) / 2) );
  }

  /* Ajustes para el padding de las tarjetas en responsive */
  ul li:nth-child(odd) {
      padding-right: calc(1.5rem + var(--inlineP)); /* Más espacio a la derecha para la fecha */
      padding-left: 1.5rem;
  }
  ul li:nth-child(even) {
      padding-left: calc(1.5rem + var(--inlineP)); /* Más espacio a la izquierda para la fecha */
      padding-right: 1.5rem;
  }
}
</style>