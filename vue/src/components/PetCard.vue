<template>
  <div>
    <p>id: {{ pet.petId }}</p>
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
</template>

<script>
export default {
  props: ['pet'],
}
</script>

<style scoped>
div {
  background: lightgrey;
  margin-bottom: 8px;
  padding: 8px 12px;
  display: grid;
  grid-template-columns: auto auto auto auto;
}

div > p {
  margin: 0;
  padding: 0;
}
</style>
