<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <div id="ac"></div>

</template>

<script lang="ts">
import {Vue} from 'vue-class-component';
import * as echarts from 'echarts'
import { watch } from '@vue/runtime-core';

export default class App extends Vue {
  mychart!:echarts.ECharts;
  chartOptions!:echarts.EChartsOption;

  created(){
    this.chartOptions={
      title: {
            text: 'ECharts 入门示例'
          },
          tooltip: {},
          legend: {
            data: ['销量']
          },
          xAxis: {
            data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
          },
          yAxis: {},
          series: [
            {
              name: '销量',
              type: 'bar',
              data: [5, 20, 36, 10, 10, 20]
            }
          ]
    };
  }
  mounted(){
    this.mychart=echarts.init(document.getElementById("ac") as HTMLElement);
    this.mychart.setOption(this.chartOptions);
    watch(this.chartOptions,()=>{this.mychart.setOption(this.chartOptions)});
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
#ac{
  width: 400px;
  height: 400px;
}
</style>
