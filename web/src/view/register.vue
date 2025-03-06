<template>
  <div class="register">
    <a-row>
      <a-col class="main" :span="6" :offset="9">
        <div class="title">我要注册</div>
        <a-form
            :model="registerMember"
            name="basic"
            :wrapper-col="{ span: 24 }"
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
              name="code" class="form-item"
              :rules="[{ required: true, message: 'Please input your sms code!' }]"
          >
            <a-input-search
                v-model:value="registerMember.code"
                placeholder="短信验证码"
                :enter-button="sendText"
                @search="sendRegisterSmsCode"
                :loading="sendBtnLoading"
            >
              <template #prefix>
                <MessageOutlined style="margin-left: 15px"/>
              </template>
            </a-input-search>
          </a-form-item>

          <a-form-item
              name="passwordOrigin" class="form-item"
              :rules="[{ required: true, message: 'Please input your password!' }]"
          >
            <a-input-password v-model:value="registerMember.passwordOrigin" placeholder="密码" size="large">
              <template #prefix>
                <LockOutlined style="margin-left: 15px"/>
              </template>
            </a-input-password>
          </a-form-item>

          <a-form-item
              name="passwordConfirmed" class="form-item"
              :rules="[{ required: true, message: 'Please confirm your password!' }]"
          >
            <a-input-password v-model:value="registerMember.passwordConfirmed" placeholder="确认密码" size="large">
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
          <router-link to="/login"><ArrowLeftOutlined/>返回登录</router-link>
        </p>
      </a-col>
    </a-row>
  </div>
</template>
<script setup>
import {ref} from 'vue';
import axios from "axios";
import {message} from "ant-design-vue";
import router from "../router/index.js";

const registerMember = ref({
  mobile: '',
  code: '',
  password: '',
  passwordOrigin: '',
  passwordConfirmed: ''
});

const register = values => {
  console.log('开始注册:', values);
  if (registerMember.value.passwordOrigin !== registerMember.value.passwordConfirmed) {
    message.error("密码和确认密码不一致！")
    return;
  }
  registerMember.value.password = registerMember.value.passwordOrigin;
  axios.post("/nls/web/member/register", {
    mobile: registerMember.value.mobile,
    code: registerMember.value.code,
    password:registerMember.value.password
  }).then((response) => {
    let data = response.data;
    if (data.success) {
      message.success("注册成功！")
      router.push("/login")
    }
    else {
      message.error(data.msg);
    }
  })
};

// ----------- 短信验证码 --------------------
const sendBtnLoading = ref(false);
const sendText = ref("获取验证码");
const COUNTDOWN = 60;
let countdown = ref(COUNTDOWN);
const setTime = () => {
  if (countdown.value === 0) {
    sendBtnLoading.value = false;
    sendText.value = "获取验证码";
    countdown.value = COUNTDOWN;
    return;
  } else {
    sendBtnLoading.value = true;
    sendText.value = "重新发送(" + countdown.value + "s" + ")";
    countdown.value--;
  }
  setTimeout(function () {
    setTime();
  }, 1000);
};

const sendRegisterSmsCode = () => {
  console.log("发送短信验证码:")
  sendBtnLoading.value = true
  axios.post("/nls/web/sms-code/send-for-register", {
    mobile: registerMember.value.mobile
  }).then((response) => {
    let data = response.data;
    if (data.success) {
      setTime();
      message.success("短信发送成功！")
    }
    else {
      sendBtnLoading.value = false
      message.error(data.msg)
    }
  })
}
</script>