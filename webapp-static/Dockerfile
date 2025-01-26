FROM node:lts-alpine AS builder

# Build the Vue.js app
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Serve the app with Nginx
FROM nginx:alpine
COPY --from=builder /app/dist /usr/share/nginx/html

# Configure Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
