<template>
  <header>
    <h2 class="title"> Adoptable pets!!!</h2>
    <p class="desc"> So many adoptable pets to choose from! </p>
    <router-link class="desc" :to="{name: 'add-pet'}">Add Pet</router-link>
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
  font-family: 'Courier New', Courier, monospace;
  background-color: #FF8811;
}

.title {
  display: flex;
  justify-content: center;
}

.desc {
  display: flex;
  justify-content: center;

}
</style>
