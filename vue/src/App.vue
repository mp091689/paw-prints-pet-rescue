npm run dev<template>
  <div id="capstone-app">

    <div class="header">
      <img src="/Paw Prints Pet Rescue Logo.png" class="company-logo" alt="PAW LOGO">
      <h1 id="title"> Paw Prints Pet Rescue</h1>
    </div>

    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link> | 
      <router-link v-bind:to="{ name: 'adopt' }">Adopt</router-link> |
      <router-link v-bind:to="{name : 'donate'}">Donate</router-link> |
      <router-link v-bind:to="{name: 'volunteer'}">Volunteer </router-link> |
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
    </div>

    <router-view />
  </div>
</template>

<style scoped>
  .header{
    top: 0;
    left: 0;
    width: 100vw;
    height: 15vh;
    background-color: #FF8811 ;
    border: solid #9DD9D2 3px ;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  .company-logo{
    max-height: 10vh;
    padding-top: 2vh;
    margin: auto;
  }
  #title{
    color: #392F5A ;
    font-size: x-large;
    margin: auto;
  }

  #nav{
    font-size: medium;
    padding: 5px;
    display: flex;
    justify-content: space-between;
  }
</style>

<script setup lang="ts">
</script>