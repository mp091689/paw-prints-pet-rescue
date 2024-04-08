<template>
  <h1>Edit Pet Info</h1>
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
      pet: {}
    }
  },
  created() {
    let petId = parseInt(this.$route.params.id);
    if (petId !== 0) {
      petService
          .getPet(petId)
          .then(response => {
            this.pet = response.data;
            this.isLoading = false;
          })
          .catch(error => {
            if (error.response && error.response.status === 404) {
              this.$store.commit('SET_NOTIFICATION', `Error getting pet ${petId}. This pet may have been deleted or you have entered an invalid pet ID.`);
              this.$router.push({name: 'home'});
            }
          });
    }
  }
};
</script>

<style scoped>

</style>
