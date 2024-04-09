<template>
  <div id="capstone-app">
    <div class="header">
      <img src="public\Paw Prints Pet Rescue Logo.png" class="company-logo">
      <h1 id="title"> Paw Prints pet rescue</h1>
      <p id="description"> </p>
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
    background-color: darkorange;
    display: flex;
  }
  #nav{
    font-size: medium;
  }
  #title{
    color: cornflowerblue;
    margin: auto;
  }
  .company-logo{
    display: flex;
    max-width: 95px;
    max-height: 95px;
    justify-content: left;
  }
  
</style>
