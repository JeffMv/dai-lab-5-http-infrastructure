<script>
export default {
    name: 'Task',
    data() {
        return {
            tasks: []
        }
    },
    // fonction Vue.js pour le démarage
    async created() {

        fetch('/api/task')
            .then(response => response.json())
            .then(data => this.tasks = data);
        
    }
}
</script>

<template>
  
  <div v-for="el in tasks ">

    <strong>{{ el.title }}</strong> 
    <p><em> {{ el.dueDate }}</em></p>
    <p :class="el.priority.toLowerCase()">{{ el.priority }}</p>
    <p>{{ el.description }}</p>

  </div>
</template>

<style scoped>

/* Style général */
body {
  font-family: 'Arial', sans-serif;
  background-color: #f5f5f5;
  color: #333;
  margin: 0;
  padding: 20px;
}

/* Carte des tâches */
.task-card {
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 20px;
  margin-bottom: 15px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.task-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15);
}

/* Titre de la tâche */
.task-title {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 10px;
  text-transform: capitalize;
}

/* Date de la tâche */
.task-date {
  font-size: 0.9rem;
  color: #666;
  margin-bottom: 10px;
}

/* Description de la tâche */
.task-description {
  font-size: 1rem;
  line-height: 1.5;
  color: #555;
}

/* Priorité */
.task-priority {
  font-size: 1rem;
  font-weight: bold;
  padding: 5px 10px;
  border-radius: 5px;
  text-align: center;
  display: inline-block;
  margin-bottom: 15px;
}

.task-priority.high {
  background-color: #ffcccc;
  color: #d93025;
}

.task-priority.medium {
  background-color: #fff4cc;
  color: #f9a825;
}

.task-priority.low {
  background-color: #ccffcc;
  color: #0b8043;
}


</style>