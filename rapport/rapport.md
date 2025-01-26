## **Rapport de Projet : Gestionnaire de Tâches Conteneurisé**

#### **1. Introduction**
Ce projet est un système complet de gestion de tâches, conçu avec une architecture moderne et conteneurisée. Il combine un frontend réactif développé avec **Vue.js**, un backend robuste basé sur **Javalin** (framework Java) et un orchestrateur réseau avancé grâce à **Traefik**. Le déploiement est entièrement automatisé et scalable grâce à **Docker Compose**.

L'objectif principal est de proposer un outil facile à utiliser pour créer, mettre à jour, consulter et supprimer des tâches, tout en adoptant une infrastructure professionnelle adaptée aux environnements de production.

---

#### **2. Architecture Générale**
Le projet est structuré autour de plusieurs services conteneurisés :

1. **Frontend : Webapp**
   - **Technologies :** Vue.js + Nginx.
   - **Fonction :** Fournit une interface utilisateur ergonomique et moderne.
   - **Points importants :**
     - Utilisation de **Vite** comme outil de build et serveur de développement.
     - Déployé dans un conteneur basé sur Nginx pour distribuer les fichiers statiques.
     - Un proxy est configuré dans Vite pour diriger les requêtes API vers le backend (`/api`).
     - Utilise `PathPrefix` pour un routage propre via Traefik.

2. **Backend : API de gestion des tâches**
   - **Technologies :** Javalin (framework Java) + OpenJDK.
   - **Fonction :** Gère la logique métier et expose une API RESTful pour les opérations CRUD (Create, Read, Update, Delete) sur les tâches.
   - **Endpoints :**
     - `GET /api/task/` : Liste toutes les tâches.
     - `GET /api/task/{id}` : Récupère une tâche par ID.
     - `GET /api/task/date/{date}` : Liste les tâches correspondant à une date donnée.
     - `POST /api/task/` : Ajoute une nouvelle tâche.
     - `PUT /api/task/{id}` : Met à jour une tâche existante.
     - `DELETE /api/task/{id}` : Supprime une tâche.
   - **Déploiement :**
     - Packagé sous forme de fichier JAR et exécuté dans un conteneur OpenJDK.
     - Le serveur écoute sur le port `7070`.

3. **Traefik : Reverse Proxy et Load Balancer**
   - **Technologie :** Traefik.
   - **Fonction :** Assure la gestion des entrées réseau et le routage vers les différents services.
   - **Caractéristiques :**
     - Activation de HTTPS pour sécuriser toutes les communications.
     - Configuration de règles de routage :
       - `/` pour le frontend (Webapp).
       - `/api` pour le backend (API de gestion des tâches).
       - `/portainer` pour l’interface de gestion des conteneurs.
     - Gestion de la scalabilité avec du load balancing pour le frontend et le backend.
     - Configuration de sticky sessions pour maintenir les utilisateurs connectés à une instance backend spécifique.

4. **Portainer : Gestion des Conteneurs**
   - **Technologie :** Portainer (édition communautaire).
   - **Fonction :** Fournit une interface graphique pour la gestion et la surveillance des conteneurs.
   - **Accès :** Disponible via Traefik sur le chemin `/portainer`.

---

#### **3. Description Technique**
##### **a. Infrastructure réseau**
- Tous les services sont connectés à un réseau Docker appelé `traefik-network`.
- Cela permet une communication privée et sécurisée entre les conteneurs.

##### **b. Scalabilité**
- Le frontend (Vue.js/Nginx) est déployé avec 3 répliques pour répondre à un grand volume de requêtes utilisateur.
- Le backend (Javalin) est configuré avec 5 répliques pour gérer efficacement les opérations lourdes ou fréquentes.

##### **c. Sécurité**
- HTTPS est activé via Traefik pour toutes les routes accessibles publiquement.
- Sticky sessions sont activées pour garantir une meilleure expérience utilisateur côté backend.

##### **d. Stockage persistant**
- Les données de Portainer sont stockées dans un volume Docker (`./portainer-data`) pour assurer leur persistance.
- Les certificats et fichiers de configuration de Traefik sont également stockés dans des volumes dédiés.

##### **e. Automatisation**
- Le fichier `docker-compose.yml` centralise la configuration et permet un déploiement automatisé de l’ensemble des services avec la commande :
  ```bash
  docker-compose up --build
  ```

---

#### **4. Technologies Utilisées**
| Composant       | Technologie       | Rôle                                      |
|------------------|-------------------|-------------------------------------------|
| **Frontend**     | Vue.js, Vite, Nginx | Interface utilisateur dynamique et réactive. |
| **Backend**      | Javalin, Java      | Gestion de l'API RESTful.                 |
| **Proxy**        | Traefik           | Reverse proxy, gestion du routage et TLS. |
| **Gestionnaire** | Portainer         | Gestion graphique des conteneurs Docker.  |
| **Orchestration**| Docker Compose    | Déploiement automatisé et orchestration.  |

---

#### **5. Processus de Déploiement**
1. Construire et lancer les conteneurs :
   ```bash
   docker-compose up --build
   ```
2. Accéder aux différents services :
   - **Frontend :** `https://<domaine-ou-localhost>/`
   - **Backend (API) :** `https://<domaine-ou-localhost>/api`
   - **Portainer :** `https://<domaine-ou-localhost>/portainer`

---

#### **6. Points Forts**
- **Modularité** : Chaque service est isolé et peut être modifié ou mis à jour indépendamment.
- **Scalabilité** : Configuration pour le scaling horizontal (augmentation du nombre de répliques).
- **Simplicité de déploiement** : Utilisation de Docker Compose pour orchestrer les conteneurs.
- **Sécurité intégrée** : HTTPS activé pour tous les services grâce à Traefik.
- **Expérience utilisateur** : Sticky sessions configurées pour garantir une navigation fluide.

---

#### **7. Perspectives et Améliorations Futures**
- Ajouter une base de données (comme PostgreSQL ou MongoDB) pour persister les données des tâches.
- Implémenter des tests unitaires et d'intégration pour le backend et le frontend.
- Configurer un pipeline CI/CD pour automatiser le déploiement.
- Activer la gestion des logs dans Traefik et Portainer pour un suivi plus détaillé des erreurs.