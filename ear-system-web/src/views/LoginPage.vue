<template>
  <form id="form_login">
    <div class="form_login_item">
      <label for="username">用户名</label>
      <input type="text" v-model="username" id="username">
    </div>
    <div class="form_login_item">
      <label for="password">密码</label>
      <input type="password" v-model="password" id="password">
    </div>
    <div class="form_login_item">
      <button v-on="login" id="button_login">登录</button>
      <router-link to="/register">去注册</router-link>
    </div>

  </form>
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
#form_login{
  height: 600px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.form_login_item{
  height: 40px;
  margin-left: auto;
  margin-right: auto;
}
#button_login{
  margin-right: 80px;
}
label{
  display: inline-block;
	width: 80px;
	text-align: justify;
	text-align-last: justify;
  margin-right: 10px;
}
</style>