<template>
  <!-- <router-link :to="{ name: 'edit-pet', params: {petId: pet.petId} }"> -->
    <div  class="pet-card" :class="{ 'orange-card': pet.petId % 2 == 0, 'blue-card': pet.petId % 2 != 0 }">
      <p>species: {{ pet.speciesId }}</p>
      <p>name: {{ pet.name }}</p>
      <p>age: {{ pet.age }}</p>
      <p>hasSpecialNeeds: {{ pet.hasSpecialNeeds }}</p>
      <p>color: {{ pet.color }}</p>
      <p>breed: {{ pet.breed }}</p>
      <p>size: {{ pet.size }}</p>
      <p>gender: {{ pet.gender }}</p>
      <p>isAdopted: {{ pet.isAdopted }}</p>
      <p>description: {{ pet.description }}</p>
      <p>isFixed: {{ pet.isFixed }}</p>
      <p>photo: <img v-if="pet.photos" :src="pet.photos[0]" alt=""></p>
      <div class="admin-bar" v-if="$store.state.user.authorities.filter(role => role.name === 'ROLE_ADMIN').length">
        <button @click="$router.push({name: 'edit-pet', params: {id: pet.petId}})">Edit</button>
      </div>
    </div>
  <!-- </router-link> -->
</template>

<script>
export default {
  props: ['pet'],
  methods: {}
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
