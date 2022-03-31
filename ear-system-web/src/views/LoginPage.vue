<template>
  <label for="username">用户名：</label>
  <input type="text" v-model="username" id="username">
  <br>
  <label for="password">用户名：</label>
  <input type="password" v-model="password" id="password">
  <br>
  <button v-on="login">登录</button>
</template>

<script lang='ts'>
import {Vue,Options} from 'vue-class-component';

@Options({
  components:{}
})

export default class LoginPage extends Vue{
  username='';
  password='';

  login(){
    return 0;
  }
}

</script>
<style>
</style>