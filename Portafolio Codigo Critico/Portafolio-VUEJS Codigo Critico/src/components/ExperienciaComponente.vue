<script setup>
// Los iconos se reutilizan perfectamente
import icono1 from '/src/assets/agriculture.svg'; // Ícono para AgroGestión
import icono3 from '/src/assets/linux.svg'; // Ícono para Gestión de BBDD/Sistemas
import icono5 from '/src/assets/git.svg'; // Ícono para Git/Colaboración

import { ref } from 'vue';

// Título y fecha actualizados para reflejar un proyecto grupal
const titulo = 'Proyecto Grupal: Agro Gestión Integral';
const fecha = 'Marzo 2025 / Octubre 2025'; // O la fecha que prefieras
const experiencias = ref([
    { 
      id: 1, 
      src: icono1, 
      parrafo: 'Desarrollamos "Agro Gestión Integral", un sistema full-stack en Java (NetBeans) para la gestión agrícola, colaborando desde el diseño inicial hasta la implementación final.' 
    },
    { 
      id: 2, 
      src: icono5, // Usamos el ícono de Git para colaboración
      parrafo: 'Implementamos un flujo de trabajo colaborativo con Git, gestionando ramas (branches), resolviendo conflictos (merges) y utilizando GitHub para la integración del código.' 
    },
    { 
      id: 3, 
      src: icono3, // Usamos el ícono de Linux/Sistemas para la BBDD
      parrafo: 'Diseñamos y administramos la base de datos compartida (MySQL), definiendo el esquema y asegurando la integridad de los datos para todo el equipo.' 
    }
]);
</script>

<template>
    <div class="card">
        <h3 class="titulo">{{ titulo.toLocaleUpperCase() }}</h3>
        <p class="fecha">{{ fecha }}</p>
        <ul class="listado">
            <li class="item" v-for="experencia in experiencias" :key="experencia.id">
                <img class="imagen-svg" :src="experencia.src" width="45rem" :alt="experencia.parrafo">
                <p>{{ experencia.parrafo }}</p>
            </li>
        </ul>
    </div>
</template>

<style scoped>
/* Estilos generales para el layout y la tipografía */
:root {
    --color-primary: coral;
    --color-secondary: burlywood;
    --color-bg-dark: rgb(28, 41, 52);
    --color-text-light: #e0e0e0;
    --color-text-dark: #333;
    --font-sans: 'Roboto', sans-serif; /* Puedes cambiar a otra fuente elegante */
}

.card {
    display: flex;
    flex-direction: column;
    padding: 2.5rem; /* Un poco más de padding */
    background-color: var(--color-bg-dark);
    border-radius: 15px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3); /* Sombra más pronunciada para elegancia */
    max-width: 800px; /* Limitar ancho para mejor legibilidad */
    margin: 2rem auto; /* Centrar la tarjeta */
    font-family: var(--font-sans);
}

.titulo {
    font-size: 1.8rem; /* Título un poco más grande */
    color: var(--color-primary);
    margin-bottom: 0.5rem;
    font-weight: 700; /* Negrita para el título */
    letter-spacing: 0.05em; /* Espaciado de letras sutil */
}

.fecha {
    font-size: 1.1rem; /* Fecha un poco más grande */
    color: var(--color-secondary);
    margin-bottom: 2rem; /* Más espacio debajo de la fecha */
    font-weight: 300; /* Fuente más ligera */
}

.listado {
    display: flex;
    flex-direction: column;
    list-style: none; /* Quitar viñetas predeterminadas */
    padding: 0;
    margin: 0;
}

.item {
    display: flex;
    align-items: flex-start; /* Alinear ítems al inicio */
    padding: 1.2rem 0; /* Padding vertical para cada item */
    gap: 1.5rem;
    border-bottom: 1px solid rgba(255, 255, 255, 0.1); /* Separador sutil */
    transition: background-color 0.3s ease; /* Transición para hover */
}

.item:last-child {
    border-bottom: none; /* No hay borde en el último item */
}

.item:hover {
    background-color: rgba(255, 255, 255, 0.05); /* Efecto hover sutil */
    border-radius: 5px; /* Bordes redondeados en hover */
}

.imagen-svg {
    flex-shrink: 0; /* Asegurar que el SVG no se encoja */
    /* ¡LÍNEA BORRADA! Ya no forzamos el color */
}

.item p {
    font-size: 1rem;
    color: var(--color-text-light);
    line-height: 1.6; /* Mejorar legibilidad del párrafo */
    margin: 0; /* Eliminar margen por defecto del párrafo */
}
</style>