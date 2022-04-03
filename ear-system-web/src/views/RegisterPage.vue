<template>
    <form id="form_register">
        <div class="form_register_item">
            <label for="username">用户名</label>
            <input type="text" v-model="username" id="username">
        </div>
        <div class="form_register_item">
            <label for="password0">输入密码</label>
            <input type="password" v-model="password0" id="password0">
        </div>
        <div class="form_register_item">
            <label for="password1">确认密码</label>
            <input type="password" v-model="password1" id="password1">
        </div>
        <div class="form_register_item">
            <label for="phone">电话号码</label>
            <input type="tel" v-model="phone" id="phone">
        </div>
        <div class="form_register_item">
            <label for="email">E-mail</label>
            <input type="email" v-model="email" id="email">
        </div>
        <div class="form_register_item">
            <button id="button_register" v-on="register">注册</button>
        </div>
    </form>
</template>

<script lang='ts'>
import {Vue,Options} from 'vue-class-component';

@Options({
    components:{}
})

export default class RegisterPage extends Vue{
    username='';
    password0='';
    password1='';
    phone='';
    email='';
    register(){
        return 0;
    }
}

</script>
<style>
#form_register{
  height: 600px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.form_register_item{
  height: 40px;
  margin-left: auto;
  margin-right: auto;
}
#button_register{
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