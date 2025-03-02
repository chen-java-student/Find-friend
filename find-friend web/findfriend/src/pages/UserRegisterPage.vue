<template>
  <div style=" text-align: center">
    <img src="../assets/img_1.png" height="47" width="199"/>
  </div>

  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <van-field
          v-model="userAccount"
          name="userAccount"
          label="账号"
          placeholder="请输入账号"
          :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
          v-model="userPassword"
          type="password"
          name="userPassword"
          label="密码"
          placeholder="请输入密码"
          :rules="[{ required: true, message: '请填写密码' }]"
      />
      <van-field
          v-model="checkPassword"
          type="password"
          name="checkPassword"
          label="确认密码"
          placeholder="请输入确认密码"
          :rules="[{ required: true, message: '请输入确认密码' }]"
      />
      <van-field
          v-model="planetCode"
          type="planetCode"
          name="planetCode"
          label="编号"
          placeholder="请输入编号"
          :rules="[{ required: true, message: '请输入小于6位的编号' }]"
      />
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        注册提交
      </van-button>
    </div>
  </van-form>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import myAxios from "../plugins/myAxios";
import {Toast} from "vant";

const router = useRouter();
const route = useRoute();

const userAccount = ref('');
const userPassword = ref('');
const checkPassword = ref('');
const planetCode = ref('');
const onSubmit = async () => {
  Toast.loading("注册中");


  if(userPassword.value!=checkPassword.value){
    Toast.fail("密码不一致，请重新输入！");
    return;
  }
  if(userAccount.value.length<4){
    Toast.fail("账号长度需要大于等于4位")
    return;
  }
  if(userPassword.value.length<8||checkPassword.value.length){
    Toast.fail("密码长度需要大于等于8位")
    return;
  }
  const res = await myAxios.post("/user/register", {
    userAccount: userAccount.value,
    userPassword: userPassword.value,
    checkPassword: checkPassword.value,
    planetCode: planetCode.value
  })
  // console.log(res)
  if (res.code === 0 && res.data) {
    Toast.success("注册成功！");
    window.location.href = '/user/login';
    // router.replace("/");
  } else {
    Toast.fail("注册失败！" + res.description);
  }
};

</script>

<style scoped>
img{
  text-align: center
}
</style>
