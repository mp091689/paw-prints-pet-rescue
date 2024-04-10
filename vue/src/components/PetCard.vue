<template>
    <div  class="pet-card" :class="`${$attrs.class}`">
      <p><img width="80" :src="getMainPhotoUrl(pet.petId)" alt=""></p>
      <p>Name: {{ pet.name }}</p>
      <p>Species: {{ getSpeciesName(pet.speciesId) }}</p>
      <p>age: {{ pet.age ? pet.age : "unknown" }}</p>
      <p>hasSpecialNeed: {{ pet.hasSpecialNeed ? "Yes" : "No" }}</p>

      <router-link :to="{name: 'edit-pet', params: {petId: pet.petId}}" v-if="$store.state.user.authorities.filter(role => role.name === 'ROLE_ADMIN').length">Edit</router-link>
    </div>
</template>

<script>
export default {
  props: ['pet'],
  methods: {
    getMainPhotoUrl(id) {
        return import.meta.env.VITE_REMOTE_API + '/pets/' + id + '/main-photo';
    },
    getSpeciesName(id) {
      if(id == 1) return "Cat";
      if(id == 2) return "Dog";
      if(id == 3) return "Bunny";
      return "Unknown";
    }
  }
}
</script>

<style scoped>
.pet-card {
  border: solid red 1px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  padding-left: 2px;
  color: black;
}

.pet-card.orange-card {
  background-color: orange;
}

.pet-card.blue-card {
  background-color: cornflowerblue;
}
</style>
