<template>
    <div v-if="isLoading">
        <h1>loading...</h1>
    </div>
    <div v-else>

        <div id="filter-toggle">
            <button id="toggle-button" @click="toggleFilters">Filter Pets</button>
        </div>

        <div v-if="showFilters" class="pet-filter">
            <div class="species-filter">
                <label for="speciesId">Species: </label>
                <select id="speciesId" v-model="filters.speciesId">
                    <option value="">All Species</option>
                    <option value="1">Cats</option>
                    <option value="2">Dogs</option>
                    <option value="3">Bunnies</option>
                </select>
            </div>
            
            <div class="age-filter">
                <label for="age">Age: </label>
                <input id="age" type="number" v-model.number="filters.age" placeholder="Age" max="30" min="0">
            </div>

            <div class="breed-filter">
                <label for="breed">Breed: </label>
                <input id="breed" v-model="filters.breed" placeholder="Breed">
            </div>

            <div class="color-filter">
                <label for="color">Color: </label>
                <input id="color" v-model="filters.color" placeholder="Color">
            </div>

            <div class="size-filter">
                <label for="size">Size: </label>
                <select id="size" v-model="filters.size">
                    <option value="">All Sizes</option>
                    <option value="XS">XS</option>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                </select>
            </div>

            <div class="gender-filter">
                <label for="gender">Gender: </label>
                <select id="gender" v-model="filters.gender">
                    <option value="">All Genders</option>
                    <option value="Female">Female</option>
                    <option value="Male">Male</option>
                </select>
            </div>

            <div class="fixed-filter">
                <label for="isFixed">Fixed: </label>
                <input id="isFixed" type="checkbox" v-model="filters.isFixed">
            </div>

            <div class="special-filter">
                <label for="hasSpecialNeed">Special Needs: </label>
                <input id="hasSpecialNeed" type="checkbox" v-model="filters.hasSpecialNeed">
            </div>
            
            <div class="reset-filters">
                <button id="reset-button" @click="resetFilters">Clear Filters</button>
            </div>

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
            showFilters: false,
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
    methods: {
        toggleFilters() {
            this.showFilters = !this.showFilters;
        },
        resetFilters() {
            this.filters = {};
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
                    && (!this.filters.size || pet.size.toLowerCase() === (this.filters.size.toLowerCase()))
                    && (!this.filters.gender || pet.gender.toLowerCase() === this.filters.gender.toLowerCase())
                    && (!this.filters.isFixed || pet.isFixed === this.filters.isFixed)
                    && (!this.filters.hasSpecialNeed || pet.hasSpecialNeed === this.filters.hasSpecialNeed);
                    // && (!this.filters.isAdopted || pet.isAdopted === this.filters.isAdopted);
            });
        }
    }
}
</script>

<style scoped>
#filter-toggle {
    display: flex;
    justify-content: end;
    padding: 1vh;
}
#toggle-button {
    background-color: #F4D06F;
    border: solid #392F5A 2px;
    color: #392F5A;
    text-align: center;
    text-decoration: none;
    font-family: Dekko;
    font-size: 115%;
}
#toggle-button:hover {
    background-color: #9DD9D2;
}


.pet-filter {
    margin-top: 2vh;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas: 
    "species age gender fixed"
    "size breed color special"
    "reset reset reset reset";
    margin: auto;
}
.species-filter {
    grid-area: species;
    display: flex;
    justify-content: center;
    padding-bottom: 1vh;
}
.age-filter {
    grid-area: age;
    display: flex;
    justify-content: center;
    padding-bottom: 1vh;
}
.gender-filter {
    grid-area: gender;
    display: flex;
    justify-content: center;
    padding-bottom: 1vh;
}
.fixed-filter {
    grid-area: fixed;
    display: flex;
    justify-content: center;
    padding-bottom: 1vh;
}
.size-filter {
    grid-area: size;
    display: flex;
    justify-content: center;
}
.breed-filter {
    grid-area: breed;
    display: flex;
    justify-content: center;
}
.color-filter {
    grid-area: color;
    display: flex;
    justify-content: center;
}
.special-filter {
    grid-area: special;
    display: flex;
    justify-content: center;
}
.reset-filters {
    grid-area: reset;
    display: flex;
    justify-content: flex-end;
}
#reset-button {
    background-color: #F4D06F;
    border: solid #392F5A 2px;
    color: #392F5A;
    text-align: center;
    text-decoration: none;
    font-family: Dekko;
    font-size: 95%;
}


.pets-list {
  display: grid;
  align-content: center;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 10px;
  padding-left: 10px;
  padding-right: 10px;
}

.pets-list > div {
  text-align: center;
  padding: 5px 0;
  font-size: 30px;
}
</style>