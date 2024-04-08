<template>
  <div v-if="isLoading">
    <h1>loading...</h1>
  </div>
  <pet-form :pet="pet" v-else/>
</template>

<script>
import PetForm from "@/components/PetForm.vue";
import petService from "@/services/PetService";

export default {
  components: {PetForm},
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

<style scoped>

</style>
