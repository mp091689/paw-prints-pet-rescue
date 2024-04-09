<template>
  <header>
    <h1 class="title"> Adoptable pets!!!</h1>
    <p class="desc"> So many adoptable pets to choose from! </p>
  </header>
  <div v-if="isLoading">
    <h1>loading please wait...</h1>
  </div>
  <div v-else  class="pet-list">
    <div class="admin-bar" v-if="$store.state.user.authorities.filter(role => role.name === 'ROLE_ADMIN').length">
    <button @click="$router.push({name: 'add-pet'})">Add Pet</button>
  </div>
  <pets-list v-else/>
</template>

<script>
import PetsList from "@/components/PetsList.vue";

export default {
  components: {PetsList},
};
</script>

<style scoped>
.pet-list {
  display: flexbox;
}

header {
  border: solid orange 2px;
  font-family: 'Courier New', Courier, monospace;
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
