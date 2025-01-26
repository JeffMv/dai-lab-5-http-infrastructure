<template>
    <div id="app">
      <!-- Ici, tu peux ajouter un header global si nécessaire -->
      <header>
        <nav>
          <ul>
            <li><router-link to="/">Accueil</router-link></li>
            <li><router-link to="/count">Counter</router-link></li>
            <li><router-link to="/task">Task</router-link></li>
          </ul>
        </nav>
      </header>
  
      <!-- Contenu de l'application affiché ici selon les routes -->
      <main>
        <router-view />
      </main>
      
    </div>
  </template>

  <script>
  // Importer des composants si nécessaire (non obligatoire si tu utilises juste des routes simples)
  export default {
    name: 'App',
  };
  </script>
  
  <style>
  /* style global ici */
  #app {
    font-family: Arial, sans-serif;
  }
  
  header {
    background-color: #333;
    color: white;
    padding: 10px;
  }
  
  nav ul {
    display: flex;
    list-style-type: none;
    margin: 0;
    padding: 0;
  }
  
  nav li {
    margin-right: 15px;
  }
  
  nav a {
    color: white;
    text-decoration: none;
  }
  
  footer {
    background-color: #333;
    color: white;
    text-align: center;
    padding: 10px;
    position: fixed;
    bottom: 0;
    width: 100%;
  }
  </style>
  