<template>
  <Header/>
  <router-view/>
  <div id="mychart"></div>
  <Footer></Footer>
</template>
<script lang='ts'>
import {Vue,Options} from 'vue-class-component';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import * as echarts from 'echarts';


@Options({
    components:{
      Header,Footer
    }
})

export default class App extends Vue{

  echartsinit(){
    let mychart=echarts.init(document.getElementById('mychart') as HTMLElement)
    let js=require("./resource/geojson.json");
    echarts.registerMap('test', js, {});
    let option={
      series: [
        {
          type: 'map',
          mapType: 'test',//名称需要echarts.registerMap('linyi',linyiMap,{})中的名称一致
          label: { show: true }, //显示文字
          roam: true,
          data: [],
        },
      ]
    }
    mychart.setOption(option)
  }

  mounted(){
    this.echartsinit();
  }
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

#mychart{
  width: 800px;
  height: 800px;
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
