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

            <label for="age">Age:</label>
            <input id="age" type="number" v-model.number="filters.age" placeholder="Please enter age.">

            <label for="breed">Breed:</label>
            <input id="breed" v-model="filters.breed" placeholder="Please enter a breed.">

            <label for="color">Color:</label>
            <input id="color" v-model="filters.color" placeholder="Please enter a color.">

            <label for="size">Size:</label>
            <select id="size" v-model="filters.size">
                <option value="">All Sizes</option>
                <option value="XS">XS</option>
                <option value="S">S</option>
                <option value="M">M</option>
                <option value="L">L</option>
                <option value="XL">XL</option>
            </select>

            <label for="gender">Gender:</label>
            <select id="gender" v-model="filters.gender">
                <option value="">All Genders</option>
                <option value="Female">Female</option>
                <option value="Male">Male</option>
            </select>

            <label for="isFixed">Fixed:</label>
            <input id="isFixed" type="checkbox" v-model="filters.isFixed">

            <label for="hasSpecialNeed">Special Needs:</label>
            <input id="hasSpecialNeed" type="checkbox" v-model="filters.hasSpecialNeed">

            <!-- <label for="isAdopted">Show Adopted:</label>
            <input id="isAdopted" type="checkbox" v-model="filters.isAdopted"> -->

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
                age: null,
                color: '',
                size: '',
                gender: '',
                isFixed: null,
                hasSpecialNeed: null
                // isAdopted: false
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
                    && (!this.filters.age || pet.age === this.filters.age)
                    && (!this.filters.color || pet.color.toLowerCase().includes(this.filters.color.toLowerCase()))
                    && (!this.filters.size || pet.size.toLowerCase().includes(this.filters.size.toLowerCase()))
                    && (!this.filters.gender || pet.gender.toLowerCase().includes(this.filters.gender.toLowerCase()))
                    && (!this.filters.isFixed || pet.isFixed === this.filters.isFixed)
                    && (!this.filters.hasSpecialNeed || pet.hasSpecialNeed === this.filters.hasSpecialNeed);
                    // && (!this.filters.isAdopted || pet.isAdopted === this.filters.isAdopted);
            });
        }
    }
}
</script>

<style scoped>
.pet-filter {
    margin-top: 2vh;
    display: flex;
    justify-content: space-evenly;
}

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