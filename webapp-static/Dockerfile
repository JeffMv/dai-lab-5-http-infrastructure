# Base image
FROM nginx:latest

# Copy custom nginx.conf
COPY nginx.conf /etc/nginx/nginx.conf

# Copy static files to the NGINX web directory
COPY html/ /usr/share/nginx/html

# Expose port 80
EXPOSE 80
EXPOSE 8090

