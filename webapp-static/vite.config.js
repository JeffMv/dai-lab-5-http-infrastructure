import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/api': {
        target: 'http://apibackend:7070', // L'adresse de votre backend
        changeOrigin: true, // Permet de changer l'origine de la requête
        rewrite: (path) => path.replace(/^\/api/, ''), // Supprime le préfixe /api
      },
    },
  },
});


