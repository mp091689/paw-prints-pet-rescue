<template>
  <pet-form :pet="pet"/>
</template>

<script>
import PetForm from "@/components/PetForm.vue";

export default {
  components: {PetForm},
  data() {
    return {
      pet: {}
    }
  }
};
</script>

<style scoped>

</style>
