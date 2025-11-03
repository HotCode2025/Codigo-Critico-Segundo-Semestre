<template>
  <svg 
    width="100%" 
    height="100%" 
    xmlns="http://www.w3.org/2000/svg"
    preserveAspectRatio="xMidYMid slice" 
    viewBox="0 0 100 100"
  >
    <defs>
      <linearGradient id="main-bg-grad" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" style="stop-color:#0f2027; stop-opacity:1" />
        <stop offset="50%" style="stop-color:#203a43; stop-opacity:1" />
        <stop offset="100%" style="stop-color:#2c5364; stop-opacity:1" />
      </linearGradient>

      <linearGradient id="wave-grad-1" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" style="stop-color:#8e9eab; stop-opacity:0.05" />
        <stop offset="100%" style="stop-color:#eef2f3; stop-opacity:0.1" />
      </linearGradient>

      <linearGradient id="wave-grad-2" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" style="stop-color:#eef2f3; stop-opacity:0.1" />
        <stop offset="100%" style="stop-color:#8e9eab; stop-opacity:0.05" />
      </linearGradient>

      <filter id="subtle-noise">
        <feTurbulence type="fractalNoise" baseFrequency="0.05" numOctaves="1" stitchTiles="stitch" />
        <feColorMatrix type="saturate" values="0" />
        <feComponentTransfer>
          <feFuncA type="linear" slope="0.05" /> 
        </feComponentTransfer>
        <feBlend in="SourceGraphic" in2="BackgroundImage" mode="multiply" />
      </filter>

    </defs>

    <rect width="100%" height="100%" fill="url(#main-bg-grad)" filter="url(#subtle-noise)" />

    <path 
      d="M0,40 C30,70 70,10 100,40 V100 H0 Z" 
      fill="url(#wave-grad-1)" 
      class="animated-wave wave-1" />

    <path 
      d="M0,60 C25,80 75,30 100,50 V100 H0 Z" 
      fill="url(#wave-grad-2)" 
      class="animated-wave wave-2" />

    <path 
      d="M0,80 C40,90 60,60 100,80 V100 H0 Z" 
      fill="url(#wave-grad-1)" 
      class="animated-wave wave-3" />

  </svg>
</template>

<script setup>
// No se necesita script de Vue, todo se maneja con SVG y CSS.
</script>

<style scoped>
svg {
  display: block;
  width: 100%;
  height: 100%;
}

.animated-wave {
  transform-origin: 50% 50%; /* Punto central para la transformación */
  animation: wave-motion 20s ease-in-out infinite alternate;
  opacity: 0.9;
}

/* Ajustes de velocidad y dirección para cada onda */
.wave-1 {
  animation-duration: 25s;
  animation-delay: 0s;
  transform: translateX(-5px) translateY(-5px); /* Posición inicial ligeramente diferente */
}

.wave-2 {
  animation-duration: 20s;
  animation-delay: 2s;
  transform: translateX(5px) translateY(5px);
}

.wave-3 {
  animation-duration: 15s;
  animation-delay: 4s;
  transform: translateX(-10px) translateY(10px);
}

@keyframes wave-motion {
  0% {
    transform: scale(1) translate(0px, 0px);
    opacity: 0.9;
  }
  50% {
    transform: scale(1.02) translate(5px, -5px); /* Ligero zoom y movimiento */
    opacity: 1;
  }
  100% {
    transform: scale(1) translate(0px, 0px);
    opacity: 0.9;
  }
}
</style>