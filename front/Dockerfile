FROM node:20 AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build -- --output-path=./dist/front
RUN ls -la /app/dist/front/browser

FROM nginx:alpine
COPY --from=build /app/dist/front/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
RUN cat /etc/nginx/conf.d/default.conf
EXPOSE 80