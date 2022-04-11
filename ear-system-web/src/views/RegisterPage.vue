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
            <button id="button_register" @click="register">注册</button>
        </div>
    </form>
</template>

<script lang='ts'>
import {Vue,Options} from 'vue-class-component';
import axios from 'axios';

@Options({
    components:{}
})

export default class RegisterPage extends Vue{
    username='';
    password0='';
    password1='';
    register(){
        if (this.password0!==this.password1) {
            alert('两次密码不一致！');
            return -1;
        }
        axios.post("/api/register",{
            username:this.username,
            password:this.password0
        }).then((response)=>{
            alert(response.data);
            console.log(response.config);
        }).catch((error)=>{
            console.log(error);
        });
        return 1;
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