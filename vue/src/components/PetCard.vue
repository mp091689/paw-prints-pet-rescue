<template>
    <div  class="pet-card" :class="`${$attrs.class}`">

      <div class="img">
        <img class="pet-pic" :src="getMainPhotoUrl(pet.petId)" alt="">
      </div>

      <div class="info">
        <h2 id="pet-name">Meet {{ pet.name }}!</h2>
        <p id="pet-species">Species: {{ getSpeciesName(pet.speciesId) }}</p>
        <p id="pet-age">age: {{ pet.age ? pet.age : "unknown" }}</p>
        <p id="pet-special">Any special requirements?: {{ pet.hasSpecialNeed ? "Yes" : "No" }}</p>
      </div>

      <router-link :to="{name: 'edit-pet', params: {petId: pet.petId}}" v-if="isAuthorized()">Edit</router-link>

    </div>
</template>

<script>
export default {
  props: ['pet'],
  methods: {
    isAuthorized(){
      if(Object.keys(this.$store.state.user).length === 0){
        return false;
      }
      return this.$store.state.user.authorities?.filter(role => role.name === 'ROLE_ADMIN').length;
    },
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
  display: flexbox;
  border: solid #392F5A 3px; 
  justify-content: space-between;
  padding-left: 2px;
  color: #392F5A ;
}
.pet-card .img {
  width: 100%;
  height: 30vw;
}
.pet-pic {
  max-width: 95%;
  max-height: 100%;
  padding-top: 1vh;
  margin: auto;
} 
.pet-card .info {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: 
  "name name"
  "age species"
  "special special";
}
#pet-name {
  grid-area: name;
}
#pet-species {
  grid-area: species;
}
 .pet-card.orange-card {
  background-color: #FF8811 ;
  font-size: medium;
} 
.pet-card.blue-card {
  background-color: #9DD9D2;
  font-size: medium;
}

</style>
