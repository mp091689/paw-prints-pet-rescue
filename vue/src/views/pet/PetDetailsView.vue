<template>
  <div v-if="isLoading">
    <h1>loading...</h1>
  </div>
  <pet-details :pet="pet" v-else/>
</template>

<style>
</style>

<script>
import PetDetails from "@/components/PetDetails.vue"
import petService from "@/services/PetService";

export default {
  components: {
    PetDetails
},
  data() {
    return {
      isLoading: true,
      pet: {},
    }
  },
  created() {
    let petId = parseInt(this.$route.params.petId);
    if (petId !== 0) {
      petService.getPet(petId)
          .then(response => {
            this.pet = response.data;
            this.isLoading = false;
          })
          .catch(error => console.log(error));
    }
  }
};
</script>