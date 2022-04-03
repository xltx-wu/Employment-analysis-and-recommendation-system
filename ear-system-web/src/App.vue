<template>
  <Header/>
  <router-view/>
  <Footer></Footer>
</template>
<script lang='ts'>
import {Vue,Options} from 'vue-class-component';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';


@Options({
    components:{
      Header,Footer
    }
})

export default class App extends Vue{

}

</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  display: flex;
  flex-direction: column;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  width: 50px;
  height: 50px;
  text-align: center;
  line-height: 50px;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
