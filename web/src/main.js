import { createApp } from 'vue'
import Antd from 'ant-design-vue'
import './style.css'
import 'ant-design-vue/dist/reset.css'
import App from './App.vue'
import * as Icons from '@ant-design/icons-vue'

const app = createApp(App)
app.use(Antd).mount('#app')

// 全局使用图标
const icons = Icons
for (const icon of icons) {
    app.component(icon.name, icon)
}