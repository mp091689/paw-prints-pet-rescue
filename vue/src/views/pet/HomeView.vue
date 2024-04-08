<template>
  <div v-if="isLoading">
    <h1>loading...</h1>
  </div>
  <div v-else>
    <PetCard :pet="pet" v-for="pet in pets" :key="pet.id"/>
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
    petService.getPets(true).then(response => {
      this.pets = response.data;
      this.isLoading = false;
    })
  }
};
</script>
