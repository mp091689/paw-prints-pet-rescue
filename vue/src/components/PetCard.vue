<template>
    <div  class="pet-card" :class="`${$attrs.class}`">
      <div class="img">
        <img :src="getMainPhotoUrl(pet.petId)" alt="">
      </div>
      <div class="info">
        <p>Name: {{ pet.name }}</p>
        <p>Species: {{ getSpeciesName(pet.speciesId) }}</p>
        <p>age: {{ pet.age ? pet.age : "unknown" }}</p>
        <p>Any special requirements?: {{ pet.hasSpecialNeed ? "Yes" : "No" }}</p>
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
      return this.$store.state.user.authorities?.filter(role => role.name === 'ROLE_ADMIN' || 'ROLE_USER').length;
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


.pet-card .info {
  display: grid;
  grid-template-columns: 1fr 1fr;
}

 .pet-card.orange-card {
  background-color: #FF8811 ;
  font-size: medium;
} 

.pet-card.blue-card {
  background-color: #9DD9D2;
  font-size: medium;
}

img {
  max-width: 100%;
  max-height: 100%;
} 
</style>
