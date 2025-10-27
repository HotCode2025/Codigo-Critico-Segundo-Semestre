<script setup>
// Se importa ref de vue para poder usarlo
import { ref } from 'vue';

// --- Imports de iconos (Solo los que usás en tu lista) ---
import img1 from '@/components/icons/js.svg';
import img2 from '@/components/icons/python.svg';
import img3 from '@/components/icons/html-css.svg';
import img4 from '@/components/icons/java.svg';
import img5 from '@/components/icons/sql.svg';
import img7 from '@/components/icons/vue.svg';
import img8 from '@/components/icons/node.svg';
import img11 from '@/components/icons/git.svg';
import img15 from '@/components/icons/visual.svg';
import img17 from '@/components/icons/mysql.svg';
import img19 from '@/components/icons/arg.svg';
import img20 from '@/components/icons/eeuu.svg';


//Se crea un array de objetos con TUS habilidades
const habilidades = ref([
    {// Se crea un objeto con id, nombre y habilidades
        id: 1, nombre: 'Lenguajes de Programación', habilidades: [
            { id: 1, nombre: 'JavaScript', nivel: 'Básico', icono: img1 },
            { id: 2, nombre: 'Python', nivel: 'Básico', icono: img2 },
            { id: 3, nombre: 'HTML/CSS', nivel: 'Básico', icono: img3 },
            { id: 4, nombre: 'Java', nivel: 'Básico', icono: img4 },
            { id: 5, nombre: 'SQL', nivel: 'Básico', icono: img5 }
        ]
    },
    {
        id: 2, nombre: 'Frameworks y Librerías', habilidades: [
            { id: 1, nombre: 'Vue.js', nivel: 'Básico', icono: img7 },
            { id: 2, nombre: 'Node.js', nivel: 'Básico', icono: img8 },
        ]
    },
    {
        id: 3, nombre: 'Herramientas y Software', habilidades: [
            { id: 1, nombre: 'Git', nivel: 'Intermedio', icono: img11 },
            { id: 2, nombre: 'Visual Studio Code', nivel: 'Básico', icono: img15 }
        ]
    },
    {
        id: 4, nombre: 'Bases de Datos', habilidades: [
            { id: 1, nombre: 'MySQL', nivel: 'Básico', icono: img17 },
        ]
    },
    {
        id: 5, nombre: 'Idiomas', habilidades: [
            { id: 1, nombre: 'Español', nivel: 'Nativo', icono: img19 },
            { id: 2, nombre: 'Inglés', nivel: 'Elemental (A2)', icono: img20 }
        ]
    }
]);

</script>


<template>
    <div class="skills-contenedor">
        <div v-for="habilidad in habilidades" :key="habilidad.id" class="skills-categoria">
            <h3>{{ habilidad.nombre }}</h3>
            <ul class="skills">
                <li v-for="skill in habilidad.habilidades" :key="skill.id" class="skill">
                    <img :src="skill.icono" :alt="skill.nombre">
                    <span>{{ skill.nombre }}</span>
                </li>
            </ul>
        </div>
    </div>
</template>

<style scoped>
/* --- ESTILO PROFESIONAL Y MODERNO --- */

.skills-contenedor {
    padding: 2rem;
    background: linear-gradient(-45deg, #001f3f, #0074D9, #001f3f, #1a1a1a);
    background-size: 400% 400%;
    /* Animación más sutil y lenta */
    animation: gradientBG 30s ease infinite;
}

@keyframes gradientBG {
    0% {
        background-position: 0% 50%;
    }
    50% {
        background-position: 100% 50%;
    }
    100% {
        background-position: 0% 50%;
    }
}

.skills-categoria {
    margin-bottom: 2.5rem; 
}

.skills-categoria h3 {
    display: flex;
    justify-content: left; 
    margin-bottom: 1.5rem; 
    font-size: 1.5em;
    color: aliceblue;
    font-weight: bold;
    border-bottom: 1px solid #444; 
    padding-bottom: 0.5rem;
}

.skills {
    display: flex;
    justify-content: left; 
    flex-wrap: wrap;
    gap: 15px; 
}

.skill {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
    
    /* Efecto "GLASSMORPHISM" */
    background-color: rgba(255, 255, 255, 0.08); /* Fondo semitransparente */
    border: 1px solid rgba(255, 255, 255, 0.15); /* Borde sutil */
    backdrop-filter: blur(5px); /* Efecto "blur" de cristal */
    
    padding: 0.5em 1em; 
    border-radius: 20px; 
    flex: 0 1 auto; 
    
    /* Transición suave para el efecto de "elevar" */
    transition: all 0.3s ease;
}

.skill:hover {
    /* EFECTO DE ELEVACIÓN */
    transform: scale(1.2); /* Se agranda sutilmente */
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.3); /* Sombra 3D */
    border-color: rgba(255, 255, 255, 0.3); /* Borde más brillante */
    background-color: rgba(255, 255, 255, 0.15); /* Fondo más opaco */
}

.skill img {
    width: 24px; 
    height: 24px;
}

.skill span {
    font-size: 1em;
    color: #f0f0f0; 
}

@media (max-width: 768px) {
    .skills-categoria h3 {
        justify-content: center; 
    }

    .skills {
        justify-content: center; 
    }
}
</style>