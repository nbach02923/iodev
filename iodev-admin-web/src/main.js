import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import router from './router'
import axios from "axios"

axios.post("http://103.9.211.82:8080/api/authenticate", { "username": "trung.dk070384@gmail.com", "password": "123456" });

createApp(App).use(router).mount('#app')
