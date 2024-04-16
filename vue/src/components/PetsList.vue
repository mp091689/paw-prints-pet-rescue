<template>
    <div v-if="isLoading">
        <h1>loading...</h1>
    </div>
    <div v-else>

        <div class="pet-filter">

            <label for="speciesId">Species:</label>
            <select id="speciesId" v-model="filters.speciesId">
                <option value="">All Species</option>
                <option value="1">Cats</option>
                <option value="2">Dogs</option>
                <option value="3">Bunnies</option>
            </select>

            <label for="breed">Breed:</label>
            <input id="breed" v-model="filters.breed" placeholder="Please enter a breed.">

            <label for="age">Age:</label>
            <input id="age" type="number" v-model.number="filters.age" placeholder="Please enter age.">

        </div>

        <div class="pets-list">
            <div v-for="(pet, idx) in filteredPets" :key="pet.id">
                <PetCard :pet="pet" :class="{'orange-card': idx % 2 != 0, 'blue-card': idx % 2 == 0}"/>
            </div>
        </div>
    </div>
    
</template>

<script>
import PetCard from "@/components/PetCard.vue";
import petService from "@/services/PetService";

export default {
    props: {
        isAdopted: {
            type: Boolean,
            required: true
        }
    },
    components: {PetCard},
    data() {
        return {
            isLoading: true,
            pets: [],
            filters: {
                speciesId: '',
                breed: '',
                age: null
            }
        }
    },
    created() {
        petService.getPets(this.isAdopted).then(response => {
            this.pets = response.data;
            this.isLoading = false;
        })
    },

    computed: {
        filteredPets() {
            return this.pets.filter(pet => {
                return (!parseInt(this.filters.speciesId) || pet.speciesId === parseInt(this.filters.speciesId))
                    && (!this.filters.breed || pet.breed.toLowerCase().includes(this.filters.breed.toLowerCase()))
                    && (!this.filters.age || pet.age === this.filters.age);
            });
        }
    }
}
</script>

<style scoped>
.pets-list {
  display: grid;
  align-content: center;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 10px;
  padding: 10px;
}

.pets-list > div {
  text-align: center;
  padding: 20px 0;
  font-size: 30px;
}
</style>