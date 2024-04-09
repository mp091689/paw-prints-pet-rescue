<template>
  <!-- <router-link :to="{ name: 'edit-pet', params: {petId: pet.petId} }"> -->
    <div  class="pet-card" :class="{ 'orange-card': pet.petId % 2 == 0, 'blue-card': pet.petId % 2 != 0 }">
      <p><img width="80" src="https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_square.jpg" alt=""></p>
      <p>Name: {{ pet.name }}</p>
      <p>Species: {{ getSpeciesName(pet.speciesId) }}</p>
      <p>age: {{ pet.age ? pet.age : "unknown" }}</p>
      <p>hasSpecialNeed: {{ pet.hasSpecialNeed ? "Yes" : "No" }}</p>
     
      <div class="admin-bar" v-if="$store.state.user.authorities.filter(role => role.name === 'ROLE_ADMIN').length">
        <button @click="$router.push({name: 'edit-pet', params: {id: pet.petId}})">Edit</button>
      </div>
    </div>
</template>

<script>
export default {
  props: ['pet'],
  methods: {
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
