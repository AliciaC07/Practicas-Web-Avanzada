FROM node:12-alpine as build-step
RUN mkdir -p /app
WORKDIR /app
COPY . .
RUN npm install
CMD ["npm","start"]