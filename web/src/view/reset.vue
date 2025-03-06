<template>
  <div class="register">
    <a-row>
      <a-col class="main" :span="6" :offset="9">
        <div class="title">重置密码</div>
        <a-form
            :model="resetMember"
            name="basic"
            :wrapper-col="{ span: 24 }"
            autocomplete="off"
            @finish="reset"
        >
          <a-form-item
              name="mobile" class="form-item"
              :rules="[{ required: true, message: 'Please input your mobile!' }]"
          >
            <a-input v-model:value="resetMember.mobile" placeholder="手机号" size="large">
              <template #prefix>
                <MobileOutlined style="margin-left: 15px"/>
              </template>
            </a-input>
          </a-form-item>

          <a-form-item
              name="mobile" class="form-item"
              :rules="[{ required: true, message: 'Please input your sms code!' }]"
          >
            <a-input-search
                v-model:value="resetMember.code"
                placeholder="短信验证码"
                :enter-button="sendText"
                @search="sendResetSmsCode"
            >
              <template #prefix>
                <MessageOutlined style="margin-left: 15px"/>
              </template>
            </a-input-search>
          </a-form-item>

          <a-form-item
              name="password" class="form-item"
              :rules="[{ required: true, message: 'Please input your password!' }]"
          >
            <a-input-password v-model:value="resetMember.passwordOrigin" placeholder="密码" size="large">
              <template #prefix>
                <LockOutlined style="margin-left: 15px"/>
              </template>
            </a-input-password>
          </a-form-item>

          <a-form-item
              name="password" class="form-item"
              :rules="[{ required: true, message: 'Please confirm your password!' }]"
          >
            <a-input-password v-model:value="resetMember.passwordConfirmed" placeholder="确认密码" size="large">
              <template #prefix>
                <LockOutlined style="margin-left: 15px"/>
              </template>
            </a-input-password>
          </a-form-item>

          <a-form-item class="form-item">
            <a-button type="primary" block html-type="submit" class="reset-btn" size="large">重置密码</a-button>
          </a-form-item>
        </a-form>
        <p class="footer">
          <router-link to="/login">
            <ArrowLeftOutlined/>
            返回登录
          </router-link>
        </p>
      </a-col>
    </a-row>
  </div>
</template>
<script setup>
import {ref} from 'vue';

const resetMember = ref({
  mobile: '',
  code: '',
  password: '',
  passwordOrigin: '',
  passwordConfirmed: ''
});
const sendText = ref("获取验证码")
const reset = values => {
  console.log('开始重置密码:', values);
};
const sendResetSmsCode = () => {
  console.log("发送短信验证码")
}
</script>