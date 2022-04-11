<template>
    <header>
        <nav>
            <div id="nav_child0">
                <div class="nav_item">
                    <router-link to="/">
                        <img src="../assets/logo.png" id="shouye">
                    </router-link>
                </div>
                <div class="nav_item">
                    <router-link to="">某些功能</router-link>
                </div>
            </div>
            <div id="nav_child1">
                <div class="nav_item" v-if="!userStatus.isEnabled">
                    <router-link to="/login">登录</router-link>
                </div>
                <div class="nav_item" v-if="userStatus.isEnabled">
                    <router-link to="/user" v-text="userStatus.username"></router-link>
                </div>
                <div class="nav_item" v-if="userStatus.isEnabled">
                    <a @click="logout">退出</a>
                </div>
            </div>

        </nav>
    </header>
</template>

<script lang='ts'>
import axios from 'axios';
import {Vue,Options} from 'vue-class-component';

@Options({
    components:{}
})

export default class Header extends Vue{
    userStatus={
        username:'未知',                    //用户名
        isEnabled:false,                //是否已激活
        isAccountNonExpired:false,      //账号是否过期
        isCredentialsNonExpired:false,  //密码是否过期
        isAccountNonLocked:false,       //账号是否锁定
        authorities:[]                  //权限
    };

    logout(){
        axios.get('/api/logout');
    }
}

</script>
<style>
#shouye{
    width: 50px;
    height: 50px;
    border-radius: 25px;
}

nav{
    display: flex;
    justify-content: space-between;
    padding: 10px;
}
.nav_item{
    width: 100px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    /*flex-direction: row;*/
}
#nav_child0{
    justify-content: flex-start;
    display: flex;
}
#nav_cild1{
    justify-content: flex-end;
    display: flex;
}

</style>