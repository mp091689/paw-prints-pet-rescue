<template>
  <header>
    <h2 class="title"> Adoptable pets!!!</h2>
    <p class="desc"> So many pets to choose from! </p>
    <router-link class="desc" :to="{name: 'add-pet'}" v-if="$store.state.token != ''">Add Pet</router-link>
  </header>
  <pets-list :is-adopted="false"/>
</template>

<script>
import PetsList from "@/components/PetsList.vue";

export default {
  components: {PetsList},
};
</script>

<style scoped>
header {
  border: solid #392F5A  1px;
  background-color: #FF8811;
  font-size: 115%;
}

.title {
  display: flex;
  justify-content: center;
  margin: auto;
}

.desc {
  display: flex;
  justify-content: center;
  margin: auto;
}
a {
    text-decoration: none;
    color: #392F5A;
  }
a:hover {
  text-decoration: underline;
  color: #7d450d;
}
</style>
