<template>
  <div class="login">
    <a-row>
      <a-col class="main" :span="6" :offset="9">
        <div class="title">NLS智能语音</div>
        <a-form
            :model="loginMember"
            name="basic"
            :wrapper-col="{ span: 24 }"
            autocomplete="off"
            @finish="login"
        >
          <a-form-item
              name="mobile" class="form-item"
              :rules="[{ required: true, message: 'Please input your mobile!' }]"
          >
            <a-input v-model:value="loginMember.mobile" placeholder="手机号" size="large">
              <template #prefix>
                <MobileOutlined style="margin-left: 15px"/>
              </template>
            </a-input>
          </a-form-item>

          <a-form-item
              name="password" class="form-item"
              :rules="[{ required: true, message: 'Please input your password!' }]"
          >
            <a-input-password v-model:value="loginMember.password" placeholder="密码" size="large">
              <template #prefix>
                <LockOutlined style="margin-left: 15px"/>
              </template>
            </a-input-password>
          </a-form-item>

          <a-form-item class="form-item">
            <a-button type="primary" block html-type="submit" class="login-btn" size="large">登&nbsp;录</a-button>
          </a-form-item>
        </a-form>
        <p class="footer">
          <router-link to="/register">我要注册</router-link>
          <router-link class="pull-right" to="/reset">忘记密码</router-link>
        </p>
      </a-col>
    </a-row>
  </div>
</template>
<script setup>
import {ref} from 'vue';

const loginMember = ref({
  mobile: '',
  password: ''
});
const login = values => {
  console.log('开始登录:', values);
};
</script>