<template>
  <div class="register">
    <a-row>
      <a-col class="main" :span="6" :offset="9">
        <div class="title">NLS智能语音识别</div>
        <a-form
            :model="registerMember"
            name="basic"
            :wrapper-col="{ span: 24 }"
            autocomplete="off"
            @finish="register"
        >
          <a-form-item
              name="mobile" class="form-item"
              :rules="[{ required: true, message: 'Please input your mobile!' }]"
          >
            <a-input v-model:value="registerMember.mobile" placeholder="手机号" size="large">
              <template #prefix>
                <MobileOutlined style="margin-left: 15px"/>
              </template>
            </a-input>
          </a-form-item>

          <a-form-item
              name="password" class="form-item"
              :rules="[{ required: true, message: 'Please input your password!' }]"
          >
            <a-input-password v-model:value="registerMember.password" placeholder="密码" size="large">
              <template #prefix>
                <LockOutlined style="margin-left: 15px"/>
              </template>
            </a-input-password>
          </a-form-item>

          <a-form-item class="form-item">
            <a-button type="primary" block html-type="submit" class="register-btn" size="large">注&nbsp;册</a-button>
          </a-form-item>
        </a-form>
        <p class="footer">
          <router-link to="/login">返回登录</router-link>
        </p>
      </a-col>
    </a-row>
  </div>
</template>
<script setup>
import {ref} from 'vue';

const registerMember = ref({
  mobile: '',
  password: ''
});
const register = values => {
  console.log('开始注册:', values);
};
</script>