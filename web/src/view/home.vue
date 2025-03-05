<template>
  <a-layout>
    <the-header></the-header>
    /
    <a-layout>
      <the-sider></the-sider>
      <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>Home</a-breadcrumb-item>
          <a-breadcrumb-item>List</a-breadcrumb-item>
          <a-breadcrumb-item>App</a-breadcrumb-item>
        </a-breadcrumb>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          Content {{ resp }}
          <a-input v-model:value="resp" @change="onChange" placeholder="Basic usage"/>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>
</template>
<script setup>
import {ref} from 'vue';
import axios from "axios";
import TheHeader from "../components/the-header.vue";
import TheSider from "../components/the-sider.vue";
import {message} from "ant-design-vue";

const resp = ref()

axios.get("http://localhost:8080/nls/query", {
  params: {
    mobile: "2"
  }
}).then((response) => {
  let data = response.data;
  if (data.success) {
    resp.value = data.content;
  } else {
    message.error(data.msg);
  }
})

const onChange = () => {
  console.log(resp.value);
}
</script>
<!--<script>-->
<!--import {ref, defineComponent} from 'vue';-->
<!--import axios from "axios";-->
<!--import {message} from "ant-design-vue";-->
<!--import TheSider from "../components/the-sider.vue";-->
<!--import TheHeader from "../components/the-header.vue";-->

<!--export default defineComponent({-->
<!--  components: {TheSider, TheHeader},-->
<!--  setup() {-->
<!--    const resp = ref({});-->
<!--    axios.get("http://localhost:8080/nls/query", {-->
<!--      params: {-->
<!--        mobile: "1"-->
<!--      }-->
<!--    }).then((response) => {-->
<!--      let data = response.data-->
<!--      if (data.success) {-->
<!--        resp.value = data.content-->
<!--      } else {-->
<!--        message.error(data.msg)-->
<!--      }-->
<!--    })-->
<!--    const onChange = () => {-->
<!--      console.log(resp.value)-->
<!--    }-->

<!--    return {-->
<!--      resp,-->
<!--      onChange-->
<!--    }-->
<!--  }-->
<!--})-->
<!--</script>-->
<style scoped>
.logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>