import { createApp } from 'vue'
import Antd from 'ant-design-vue'
import './style.css'
import 'ant-design-vue/dist/reset.css'
import App from './App.vue'
import * as Icons from '@ant-design/icons-vue'
import router from './router'
import axios from "axios";

const app = createApp(App)
app.use(Antd).use(router).mount('#app')

// 全局使用图标
const icons = Icons
for (const icon of icons) {
    app.component(icon.name, icon)
}

/**
 * axios拦截器
 */
axios.interceptors.request.use((config) => {
    console.log("请求参数：", config)
    return config
}, error => {
    return Promise.reject(error)
})

axios.interceptors.response.use((response) => {
    console.log("返回结果：", response)
    return response
}, error => {
    console.log("返回错误：", error)
    return Promise.reject(error)
})