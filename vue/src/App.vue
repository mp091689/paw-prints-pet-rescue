<template>
  <div id="capstone-app">
    <div class="header">
      here is my logo
    </div>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'adopt' }" v-if="$store.state.token != ''">Adopt</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>
  .header{
    top: 0;
    left: 0;
    width: 100%;
    height: 80px;
    background-color: orangered;
  }
  #nav{
    font-size: medium;
  }
</style>
