<script setup>
import { ref } from 'vue';

// (Asegúrate de que la ruta a FondoSvg.vue sea correcta)
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
    position: relative; 
    margin: 0 auto;
    max-width: 85%;
    padding: 1rem;
    overflow: hidden; 
    border-radius: 10px; 
    box-shadow: 0 10px 30px rgba(0,0,0,0.3); 
}

.fondo-svg-bg { /* Esta clase se aplica a tu <FondoSvg /> */
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 0; /* Queda detrás */
    object-fit: cover; 
}


.contenedor-lista {
    /* --- ¡ESTA ES LA LÍNEA CLAVE QUE FALTA! --- */
    position: relative; 
    /* ----------------------------------------- */

    list-style-type: none;
    padding: 1rem;
    margin-bottom: .5rem;
    /* Tu color blanco (var o #f0f0f0) está perfecto */
    color: var(--vt-c-white-soft, #f0f0f0); 
    font-size: 1.4rem;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.8); 
    z-index: 1; /* Ahora sí, queda adelante */
}

.item {
    margin-bottom: 1rem;
}
</style>