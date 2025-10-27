<script setup>
import NavBar from './components/NavBar.vue';
import DatosPersonales from './components/DatosPersonales.vue';
import Educacion from './components/EducacionComponente.vue';
import ExperienciaComponente from './components/ExperienciaComponente.vue';
import ProyectosComponente from './components/ProyectosComponente.vue';
import HabilidadesAriel from './components/HabilidadesAriel.vue';
import InteresesComponente from './components/InteresesComponente.vue';

// 1. AQUÍ IMPORTAMOS EL NUEVO COMPONENTE DEL EQUIPO
import TeamComponente from './components/TeamComponente.vue'; 
</script>

<template>
  <header>
    <NavBar />
    <DatosPersonales/>
  </header>

  <main>
    
    <TeamComponente id="equipo" /> 
    
    <section id="educacion"><h2> Educación - Cursos</h2></section>
    <Educacion/>

    <section id="experiencia"><h2>Experiencia</h2></section>
    <ExperienciaComponente/>
    
    <section id="proyectos"><h2>Proyectos</h2></section>
    <ProyectosComponente/>

    <section id="habilidades"><h2>Habilidades</h2></section>
    <HabilidadesAriel/>
    
    <section id="intereses"><h2>Intereses</h2></section>
    <InteresesComponente/>
      
  </main>

  <footer>
      <a href="#top">Inicio</a>
      <p>© 2025 CODIGO CRITICO - Portafolio WEB</p>
  </footer>

</template>


<style scoped>
/*
  Este es el estilo general para TODOS los títulos de sección (h2).
  Les da un color oscuro (#333) para que se vean bien sobre el fondo blanco.
*/
section h2 {
  text-align: center;
  font-size: 2rem;
  margin: 2rem 0; /* Un poco de espacio arriba y abajo */
  color: #333333; /* <-- ESTE ES EL COLOR OSCURO */
  font-weight: 600; /* Hacemos la letra un poco más gruesa (opcional) */
}

/* AQUÍ AGREGAMOS LOS ESTILOS DEL FOOTER
  (Los movimos para que estén dentro del <style scoped>)
*/
footer {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 2rem;
    font-size: 1.3rem;
}
</style>