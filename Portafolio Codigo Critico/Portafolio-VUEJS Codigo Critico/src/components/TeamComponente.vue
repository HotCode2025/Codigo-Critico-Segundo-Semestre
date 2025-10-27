<template>
  <section class="team-section">
    <h2>Nuestro Equipo: <span>CODIGO CRITICO</span></h2>
    <div class="team-grid">
      
      <div v-for="member in team" :key="member.name" class="team-card">
        <img v-if="member.photo" :src="member.photo" :alt="member.name" class="member-photo">
        <h3>{{ member.name }}</h3>
        <p v-if="member.role" class="member-role">{{ member.role }}</p>
      </div>

    </div>
  </section>
</template>

<script>
export default {
  name: 'TeamComponente',
  data() {
    return {
      team: [
        // ¡IMPORTANTE! Reemplaza '/path/to/photo.jpg' con las rutas reales de tus imágenes.
        // Asegúrate de que las fotos existan en la carpeta 'public' o 'src/assets'
        { name: 'Mazara Ariel', role: 'Team Lead', photo: '/img/ariel-photo.jpg' },
        { name: 'Zuñiga Agustina', role: 'Diseñadora UI/UX', photo: '/img/agustina-photo.jpg' },
        { name: 'Silva Daniel', role: 'Frontend Developer', photo: '/img/daniel-photo.jpg' },
        { name: 'Gonzalez Joel', role: 'DevOps Engineer', photo: '/img/joel-photo.jpg' },
        { name: 'Baz Samira', role: 'Scrum Master', photo: '/img/samira-photo.jpg' },
        { name: 'Mendez Oscar', role: 'Quality Assurance', photo: '/img/oscar-photo.jpg' },
        { name: 'Mamani Santino', role: 'Frontend Developer', photo: '/img/santino-photo.jpg' },
        { name: 'Ponce de Leon Damian', role: 'Backend Developer', photo: '/img/damian-photo.jpg' }
        // Si un miembro no tiene foto o rol, simplemente omite esas propiedades
        // { name: 'Otro Miembro', role: 'Junior Developer' }
      ]
    };
  }
}
</script>

<style scoped>
.team-section {
  text-align: center;
  padding: 4rem 2rem;
  background-color: #f9f9f9;
}

.team-section h2 {
  font-size: 2.5rem;
  margin-bottom: 2.5rem;
  color: #000000; /* <-- AÑADIDO: Color de texto principal a negro */
}

.team-section h2 span {
  /* color: #42b883; */   /* <-- COMENTADO: Quitamos el color de acento verde */
  color: inherit;       /* <-- AÑADIDO: Hereda el color negro del h2 */
}

.team-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); /* Ajustado para más espacio con fotos */
  gap: 1.5rem;
  max-width: 1200px;
  margin: 0 auto;
}

.team-card {
  background: #ffffff;
  padding: 1.5rem 1rem;
  border-radius: 12px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.05);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  display: flex; /* Para centrar contenido verticalmente */
  flex-direction: column; /* Apilar elementos */
  align-items: center; /* Centrar horizontalmente */
  justify-content: center; /* Centrar verticalmente */
  text-align: center; /* Asegurar que el texto también esté centrado */
}

.team-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.1);
}

.member-photo {
  width: 100px; /* Tamaño fijo para las fotos */
  height: 100px; /* Asegura que la foto sea cuadrada */
  border-radius: 50%; /* Hace que las fotos sean circulares */
  object-fit: cover; /* Asegura que la imagen cubra el área sin distorsionarse */
  margin-bottom: 1rem; /* Espacio debajo de la foto */
  border: 3px solid #42b883; /* Borde de color de acento */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Sombra para la foto */
}

.team-card h3 {
  font-size: 1.35rem; /* Un poco más grande para el nombre */
  margin: 0.5rem 0 0.25rem 0; /* Espacio alrededor del nombre */
  color: #333;
}

.member-role {
  font-size: 0.95rem;
  color: #666; /* Un color más suave para el rol */
  margin-top: 0;
}
</style>