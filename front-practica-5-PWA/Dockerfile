FROM node:12-alpine as build-step
RUN mkdir -p /app
WORKDIR /app
COPY . .
RUN npm install
RUN npm install -g @angular/cli
CMD ["ng","serve", "--host", "0.0.0.0",  "--disable-host-check"]