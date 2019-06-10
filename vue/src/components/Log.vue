<template>
  <div class="log">
    <h1>{{log}}</h1>

  </div>
</template>

<script>
export default {
  name: 'Log',
  props: {
    log: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.log {
  Margin-bottom:40px;
  position:fixed;
}
</style>
