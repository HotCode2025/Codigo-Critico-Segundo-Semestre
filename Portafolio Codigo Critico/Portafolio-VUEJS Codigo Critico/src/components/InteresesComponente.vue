<script setup>
import { ref } from 'vue';

// (Asegúrate de que la ruta a FondoSvg.vue sea correcta)
// Ejemplo: import FondoSvg from './FondoSvg.vue'; 
// O si está en assets: import FondoSvg from '@/assets/FondoSvg.vue';
import FondoSvg from './FondoSvg.vue'; 

// Arreglo reactivo (ref) con los intereses del GRUPO
const intereses = ref([
    'Tecnología y Desarrollo Colaborativo: Nos une una profunda pasión por la programación y la resolución de problemas complejos. Como equipo, disfrutamos explorando nuevas tecnologías y aplicando nuestro conocimiento para crear soluciones de software robustas y eficientes.',
    'Innovación Aplicada al Agro (AgroTech): Nos motiva especialmente la intersección entre la tecnología y el sector agrícola. Buscamos activamente aplicar nuestras habilidades colectivas para desarrollar herramientas innovadoras que optimicen la gestión y mejoren la eficiencia en el campo.',
    'Aprendizaje Continuo y Soluciones Prácticas: Creemos en el poder del trabajo en equipo y el aprendizaje constante. Tenemos un interés genuino en comprender los desafíos reales de los usuarios para diseñar e implementar soluciones tecnológicas que aporten un valor práctico y medible.'
]);
</script>

<template>
    <div class="intereses-contenedor">
        
        <FondoSvg class="fondo-svg-bg" /> 
        
        <ul class="contenedor-lista">
            <li class="item" v-for="interes in intereses" :key="interes">
                {{ interes }}
            </li>
        </ul>
    </div>
</template>

<style scoped>
.intereses-contenedor {
    position: relative; /* Clave para que el fondo absoluto funcione */
    margin: 2rem auto; /* Margen vertical para separarlo */
    max-width: 85%;
    padding: 1rem;
    overflow: hidden; /* Oculta lo que se salga del borde */
    border-radius: 10px; 
    box-shadow: 0 10px 30px rgba(0,0,0,0.3); 
    transition: all 0.3s ease; /* Transición suave */
}

.fondo-svg-bg { /* Esta clase se aplica a tu <FondoSvg /> */
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 0; /* Queda detrás del texto */
    object-fit: cover; /* Cubre todo el espacio sin deformarse */
}


.contenedor-lista {
    position: relative; /* Clave para que z-index funcione */
    list-style-type: none; /* Quita los puntos de la lista */
    padding: 1rem;
    margin-bottom: .5rem;
    /* Color de texto blanco (tomado de tus variables) */
    color: var(--vt-c-white-soft, #f0f0f0); 
    font-size: 1.4rem;
    /* Sombra fuerte para que resalte sobre el fondo */
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.8); 
    z-index: 1; /* Queda adelante del fondo */

    /* Transición para el cambio de fuente */
    transition: all 0.3s ease;
}

.item {
    margin-bottom: 1rem;
}


/* ============================================= */
/* --- ESTE ES EL BLOQUE RESPONSIVO --- */
/* ============================================= */
@media (max-width: 600px) {

  .intereses-contenedor {
    /* En móvil, hacemos que ocupe casi todo el ancho */
    max-width: 95%;
    padding: 0.75rem; /* Reducimos el padding */
    margin: 1rem auto; /* Reducimos el margen vertical */
  }

  .contenedor-lista {
    /* Achicamos la fuente en el celular */
    font-size: 1.1rem; 
    
    /* Hacemos la sombra de texto MUCHO más sutil 
       para que sea más fácil de leer */
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.7);
    padding: 0.5rem;
  }

  .item {
    margin-bottom: 0.75rem;
  }
}

</style>