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
