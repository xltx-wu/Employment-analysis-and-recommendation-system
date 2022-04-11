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
      <button @click="login" id="button_login">登录</button>
      <router-link to="/register">去注册</router-link>
    </div>

  </form>
</template>

<script lang='ts'>
import {Vue,Options} from 'vue-class-component';
import Header from '../components/Header.vue';
import axios from 'axios';
import HomeView from './HomeView.vue';
@Options({
  components:{Header,HomeView}
})

export default class LoginPage extends Vue{
  username='';
  password='';


  login(){
    alert("进入login");
    console.log("进入login");
    if(this.username==''||this.password==''){
      alert("用户名或密码为空！");
      return -1;
    }

    alert("构造请求数据");
    let data=new FormData();
    data.append("username",this.username);
    data.append("password",this.password);
    alert("开始请求");
    axios.request({
      url:'/api/login',
      method:'POST',
      headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      },
      data:data
    }).then((response)=>{
      let redata=JSON.parse(response.data);
      console.log(redata);
      if (redata.success) {
        Header.prototype.userStatus=redata.userdetails;
        console.log(Header.prototype.userStatus);

      }
      else{
        alert(data);
        console.log(this.username);
        console.log(this.password);
      }
      console.log("请求成功");
    }).catch((error)=>{
      console.log(error);
    });
    alert("请求完成");
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