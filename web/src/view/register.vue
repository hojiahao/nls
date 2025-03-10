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
              :rules="[{ required: true, message: 'Please input your mobile!', trigger: 'blur' }, { pattern: /^\d{11}$/, message: '手机号为11位数字', trigger: 'blur' }]"
          >
            <a-input v-model:value="registerMember.mobile" placeholder="手机号" size="large">
              <template #prefix>
                <MobileOutlined style="margin-left: 15px"/>
              </template>
            </a-input>
          </a-form-item>

          <a-form-item name="imageCode" class="form-item"
                       :rules="[{ required: true, message: '请输入图片验证码', trigger: 'blur' }]">
            <a-input v-model:value="registerMember.imageCode" placeholder="图片验证码">
              <template #prefix>
                <SafetyOutlined style="margin-left: 15px"/>
              </template>
              <template #suffix>
                <img v-show="!!imageCodeSrc" :src="imageCodeSrc" alt="验证码" v-on:click="loadImageCode()"/>
              </template>
            </a-input>
          </a-form-item>

          <a-form-item
              name="code" class="form-item"
              :rules="[{ required: true, message: 'Please input your sms code!', trigger: 'blur'}]"
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
              :rules="[{ required: true, message: 'Please input your password!', trigger: 'blur' }, { pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/, message: '密码包含数字和英文，长度6-20', trigger: 'blur' }]"
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
import {useRouter} from "vue-router";

let router = useRouter()
const registerMember = ref({
  mobile: '',
  imageCode: '',
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
    password: hexMd5Key(registerMember.value.password)
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
    mobile: registerMember.value.mobile,
    imageCode: registerMember.value.imageCode,
    imageCodeToken: imageCodeToken.value
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

// ----------- 图形验证码 --------------------
const imageCodeToken = ref()
const imageCodeSrc = ref()
/**
 * 加载图形验证码
 */
const loadImageCode = () => {
  registerMember.value.imageCode = ""
  imageCodeToken.value = Tool.uuid(8)
  imageCodeSrc.value = import.meta.env.VITE_SERVER + '/nls/web/kaptcha/image-code/' + imageCodeToken.value
}
loadImageCode()
</script>