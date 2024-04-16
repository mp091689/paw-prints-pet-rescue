npm run dev<template>
  <div id="capstone-app">

    <div class="header">
      <img src="/pet-photos/Paw Prints Pet Rescue Logo.png" class="company-logo" alt="PAW LOGO">
      <!-- <h1 id="title"> Paw Prints Pet Rescue</h1> -->
    </div>

    <div id="nav">
      <a id="home">
        <router-link v-bind:to="{ name: 'home' }" class="nav-button">Home</router-link> 
      </a>
      <a id="adopt">
        <router-link v-bind:to="{ name: 'adopt' }" class="nav-button">Adopt</router-link> 
      </a>
      <a id="donate">
        <router-link v-bind:to="{name : 'donate'}" class="nav-button">Donate</router-link>
      </a>
      <a id="volunteer">
        <router-link v-bind:to="{name: 'volunteer'}" class="nav-button">Volunteer </router-link>
      </a>
      <a id="login">
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="nav-button">Logout</router-link>
        <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''" class="nav-button">Login</router-link>
      </a>
    </div>

    <router-view />
  </div>
</template>

<style scoped>
  .header{
    top: 0;
    left: 0;
    max-width: 100vw;
    height: 20vh;
    background-color: #FF8811 ;
    border: solid #9DD9D2 3px ;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  .company-logo{
    max-height: 18vh;
    margin: auto;
  }
  #title{
    color: #392F5A ;
    font-size: x-large;
    margin: auto;
  }

  #nav{
    max-width: 100vw;
    padding: 1vh;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
    grid-template-areas: 
    "home adopt donate volunteer login";
  }
  a {
    text-decoration: none;
    font-size: 115%;
    color: #392F5A;
  }
  a:hover {
    text-decoration: underline;
    color: #FF8811;
  }
  #home {
    grid-area: home;
    display: flex;
    justify-content: center;
  }
  #adopt {
    grid-area: adopt;
    display: flex;
    justify-content: center;
  }
  #donate {
    grid-area: donate;
    display: flex;
    justify-content: center;
  }
  #volunteer {
    grid-area: volunteer;
    display: flex;
    justify-content: center;
  }
  #login {
    grid-area: login;
    display: flex;
    justify-content: center;
  }
</style>

<script setup lang="ts">
</script>
