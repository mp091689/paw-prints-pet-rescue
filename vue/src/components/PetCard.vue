<template>
    <div  class="pet-card" :class="`${$attrs.class}`">

      

      <div class="img">
        <img class="pet-pic" :src="getMainPhotoUrl(pet.mainPhoto)" alt="">
      </div>

      <div class="info">
        <h2 id="pet-name">Meet {{ pet.name }}!</h2>
        <p id="pet-species">{{ getSpeciesName(pet.speciesId) }}</p>
        <p id="pet-age">{{ pet.age ? pet.age : "unknown" }} years old</p>
        <p id="pet-gender">{{ pet.gender }}</p>
        <p id="pet-special">{{ pet.hasSpecialNeed ? "I have special needs." : "" }}</p>
      </div>
      
      <a id="edit-link">
        <router-link id="pet-edit" :to="{name: 'edit-pet', params: {petId: pet.petId}}" v-if="isAuthorized()">Edit</router-link>
      </a>
      
      <a id="details-link">
        <router-link id="pet-details" :to="{name: 'pet-details', params: {petId: pet.petId}}">Learn More About Me!</router-link>
      </a>

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
    getMainPhotoUrl(fileName) {
        return import.meta.env.VITE_REMOTE_API + '/pets/photos/' + fileName;
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
  display: flex;
  flex-direction: column;
  border: solid #392F5A 3px; 
  justify-content: space-between;
  padding-left: 2px;
  color: #392F5A ;
  height: 100%;
}
.pet-card .img {
  width: 100%;
  height: 30vw;
}
.pet-pic {
  max-width: 95%;
  max-height: 95%;
  padding-top: 1vh;
  margin: auto;
} 
.pet-card .info {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: 
  "name name"
  "age species"
  "gender special";
}
#pet-name {
  grid-area: name;
  margin: auto;
  padding-top: 2vh;
  font-size: 150%;
}
#pet-species {
  grid-area: species;
  margin: auto;
  font-size: 150%;
}
#pet-age {
  grid-area: age;
  margin: auto;
  font-size: 150%;
}
#pet-gender {
  grid-area: gender;
  margin: auto;
  font-size: 150%;
}
#pet-special {
  grid-area: special;
  margin: auto;
  font-size: 150%;
}
#edit-link {
  align-self: flex-end;
  padding: 1vh 1vw; 
  text-align: right;
}
#details-link {
  align-self: center;
  padding-bottom: 1vh;
  font-size: 150%;
}

a {
    text-decoration: none;
    color: #392F5A;
  }
a:hover {
  text-decoration: underline;
  color: #7d450d;
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
