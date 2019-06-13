<template>
  <div id="app">
    <!-- import component with all that is needed -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
          integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
          crossorigin=""/>
    <!-- Make sure you put this AFTER Leaflet's CSS -->
    <img alt="Vue logo" src="./assets/logo.png">
    <Top msg="Welcome to Your Vue.js App"/>
    <Map></Map>
    <Group />
  </div>
</template>

<script>

import Top from './components/HelloWorld.vue'
import Map from './components/Group.vue'
import Group from './components/Group.vue'



export default {
  name: 'app',
  components: {
    Top,
    Map,
    Group

  }
}
</script>





<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
#map { height: 180px; }
</style>
