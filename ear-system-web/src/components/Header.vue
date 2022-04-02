<template>
    <header>
        <nav>
            <div id="nav_child0">
                <div class="nav_item">
                    <router-link to="/">
                        <img src="../assets/logo.png" id="shouye">
                    </router-link>
                </div>
            </div>
            <div id="nav_child1">
                <div class="nav_item">
                    <router-link to="/login">登录</router-link>
                </div>
            </div>

        </nav>
    </header>
</template>

<script lang='ts'>
import {Vue,Options} from 'vue-class-component';

@Options({
    components:{}
})

export default class Header extends Vue{

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
    width: 50px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    /*flex-direction: row;*/
}
#nav_child0{
    justify-content: start;
    display: flex;
}
#nav_cild1{
    justify-content: end;
    display: flex;
}
</style>