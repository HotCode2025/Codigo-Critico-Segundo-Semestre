<template>
  <section class="team-section">
    <h2>Nuestro Equipo: <span>CODIGO CRITICO</span></h2>
    <div class="team-grid">
      
      <div v-for="member in team" :key="member.name" class="team-card">
        <img v-if="member.photo" :src="member.photo" :alt="member.name" class="member-photo">
        
        <h3>
          <a v-if="member.githubUrl" :href="member.githubUrl" target="_blank" rel="noopener noreferrer">
            {{ member.name }}
          </a>
          <span v-else>
            {{ member.name }}
          </span>
        </h3>
        <p v-if="member.role" class="member-role">{{ member.role }}</p>
      </div>

    </div>
  </section>
</template>

<script setup>
import { ref } from 'vue';

// 1. Se define la variable 'team' directamente con 'ref()'.
const team = ref([
  // ¡Rellena aquí las URLs reales de GitHub!
  // Asegúrate de que las rutas de las 'photo' sean correctas.
  // Por ejemplo, si están en 'src/assets/img/', la ruta sería '/src/assets/img/ariel-photo.jpg'
  { 
    name: 'Mazara Ariel', 
    role: 'Team Lead', 
    photo: '/img/ariel-photo.jpg', // Revisa esta ruta
    githubUrl: 'https://portafolioarielmazaravue.netlify.app/' 
  },
  { 
    name: 'Zuñiga Agustina', 
    role: 'Diseñadora UI/UX', 
    photo: '/img/agustina-photo.jpg', // Revisa esta ruta
    githubUrl: 'https://github.com/agustina-zuniga' 
  },
  { 
    name: 'Silva Daniel', 
    role: 'Frontend Developer', 
    photo: '/img/daniel-photo.jpg', // Revisa esta ruta
    githubUrl: 'https://github.com/daniel-silva' 
  },
  { 
    name: 'Gonzalez Joel', 
    role: 'DevOps Engineer', 
    photo: '/img/joel-photo.jpg', // Revisa esta ruta
    githubUrl: 'https://github.com/joel-gonzalez' 
  },
  { 
    name: 'Baz Samira', 
    role: 'Scrum Master', 
    photo: '/img/samira-photo.jpg', // Revisa esta ruta
    githubUrl: 'https://github.com/samira-baz' 
  },
  { 
    name: 'Mendez Oscar', 
    role: 'Quality Assurance', 
    photo: '/img/oscar-photo.jpg', // Revisa esta ruta
    githubUrl: 'https://github.com/oscar-mendez' 
  },
  { 
    name: 'Mamani Santino', 
    role: 'Frontend Developer', 
    photo: '/img/santino-photo.jpg', // Revisa esta ruta
    githubUrl: 'https://github.com/santino-mamani' 
  },
  { 
    name: 'Ponce de Leon Damian', 
    role: 'Backend Developer', 
    photo: '/img/damian-photo.jpg', // Revisa esta ruta
    githubUrl: 'https://github.com/damian-ponce' 
  }
]);
</script>


<style scoped>
/* Estilo para el fondo azul, 
  copiado de tu componente DatosPersonales 
*/
.team-section {
  background-color: #2c3e50; /* El mismo color de fondo oscuro */
  color: #ecf0f1;           /* Color de texto claro por defecto */
  padding: 4rem 2rem;
  text-align: center;
  border-radius: 12px;
  margin: 2rem auto;
  max-width: 900px; /* El mismo ancho máximo para consistencia */
  transition: padding 0.3s ease, margin 0.3s ease; /* Transición suave */
}

/* Cambiamos el título a blanco para que contraste 
*/
.team-section h2 {
  font-size: 2.5rem;
  margin-bottom: 2.5rem;
  color: #ffffff; /* <-- COLOR BLANCO */
  font-family: 'Inter', sans-serif; /* (Opcional, para mantener la fuente) */
  font-weight: 700;
  transition: font-size 0.3s ease; /* Transición suave */
}

.team-section h2 span {
  /* Hacemos que "CODIGO CRITICO" tenga un color de acento si quieres */
  color: #42b883; /* (Verde de Vue, puedes cambiarlo) */
}

.team-grid {
  display: grid;
  
  /* --- ESTA ES LA LÍNEA QUE LO HACE RESPONSIVO --- */
  /* Crea tantas columnas como quepan (auto-fit) */
  /* Cada columna mide mínimo 220px y máximo 1 fracción (1fr) */
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));

  gap: 1.5rem;
  max-width: 1200px; 
  margin: 0 auto;
}

.team-card {
  background: #ffffff; /* Fondo blanco para las tarjetas */
  padding: 1.5rem 1rem;
  border-radius: 12px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.05);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  display: flex; 
  flex-direction: column; 
  align-items: center; 
  justify-content: flex-start; 
  text-align: center; 
  min-height: 250px; 
}

.team-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.1);
}

.member-photo {
  width: 100px; 
  height: 100px; 
  border-radius: 50%; 
  object-fit: cover; /* Asegura que la imagen llene el círculo sin deformarse */
  margin-bottom: 1rem; 
  border: 3px solid #42b883; /* Borde verde */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
}

.team-card h3 {
  font-size: 1.35rem; 
  margin: 0.5rem 0 0.25rem 0; 
  color: #333; /* Texto oscuro para la tarjeta blanca */
}

.member-role {
  font-size: 0.95rem;
  color: #666; 
  margin-top: 0.25rem;
}

/* Estilos para el enlace del nombre
*/
.team-card h3 a {
  color: inherit; /* Hereda el color #333 del h3 */
  text-decoration: none; 
  transition: color 0.3s ease;
}

.team-card h3 a:hover {
  color: #42b883; /* Al pasar el mouse, toma el color verde */
  text-decoration: underline; 
}


/* ============================================= */
/* --- ESTE BLOQUE PULE EL DISEÑO EN CELULARES --- */
/* ============================================= */
/* "Si la pantalla mide 600px o menos..." */
@media (max-width: 600px) {
  
  .team-section {
    /* Achicamos los espacios en el celular */
    padding: 2.5rem 1rem;
    margin: 1rem auto; 
  }

  .team-section h2 {
    /* Achicamos el título en el celular */
    font-size: 2rem; 
    margin-bottom: 2rem;
  }

  .team-grid {
    /* Reducimos el espacio entre tarjetas */
    gap: 1rem;
  }
}
</style>