<template>
  <div class="admin-bar" v-if="$store.state.user.authorities.filter(role => role.name === 'ROLE_ADMIN').length">
    <button @click="$router.push({name: 'add-pet'})">Add Pet</button>
  </div>
  <div v-if="isLoading">
    <h1>loading...</h1>
  </div>
  <div v-else>
    <pet-card :pet="pet" v-for="pet in pets" :key="pet.id"/>
  </div>
</template>

<script>
import petService from "@/services/PetService";
import PetCard from "@/components/PetCard.vue";

export default {
  components: {PetCard},
  data() {
    return {
      isLoading: true,
      pets: []
    }
  },
  created() {
    petService.getPets(false).then(response => {
      this.pets = response.data;
      this.isLoading = false;
    })
  }
};
</script>

<style scoped>
</style>
