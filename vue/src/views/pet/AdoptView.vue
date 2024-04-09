<template>
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
</style>
