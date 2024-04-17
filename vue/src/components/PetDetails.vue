<template>
    <div v-if="isLoading">
        <h1>loading...</h1>
    </div>
    <div v-else>

        <div class="pet-details">

            <div class="pet-img">
                <img class="pet-pic" :src="getMainPhotoUrl(pet.petId)" alt="">
            </div>

            <div class="pet-info">
                <h2 id="pet-name">I'm {{ pet.name }}!</h2>
                <div class="group-one">
                    <p id="pet-age">{{ pet.age ? pet.age : "unknown" }}</p>
                    <p id="pet-breed">{{ pet.breed }}</p>
                    <p id="pet-species">{{ getSpeciesName(pet.speciesId) }}</p>
                </div>
                <div class="group-two">
                    <p id="pet-gender">{{ pet.gender }}</p>
                    <p id="pet-fixed">{{ pet.isFixed ? "Fixed" : "Not Fixed"}}</p>
                    <p id="pet-color">{{ pet.color }}</p>
                    <p id="pet-size">{{ pet.size }}</p>
                </div>
                <p id="pet-special">{{ pet.hasSpecialNeed ? "I have special needs." : "" }}</p>
                <p id="pet-adopted">{{ pet.isAdopted ? "Adopted" : "Adopt Me!"}}</p>
                <p id="pet-desc">{{ pet.description }}</p>
            </div>

        </div>

    </div>

</template>

<script>
import petService from "@/services/PetService";

export default {
    props: {
        pet: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            isLoading: true,
        }
    },
    methods: {
        getMainPhotoUrl(id) {
            return import.meta.env.VITE_REMOTE_API + '/pets/' + id + '/main-photo';
        },
        getSpeciesName(id) {
            if (id == 1) return "Cat";
            if (id == 2) return "Dog";
            if (id == 3) return "Bunny";
            return "Unknown";
        }
    },
    created() {
        petService.getPets(this.pets).then(response => {
            this.pets = response.data;
            this.isLoading = false;
        })
    },
}

</script>

<style scoped>
.pet-details {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas:
        "pic info";
    border: solid #392F5A 3px;
    background-color: #9DD9D2;
}

.pet-img {
    grid-area: pic;
    display: flex;
    justify-content: center;
    align-items: center;
}

.pet-pic {
    max-height: 75vh;
    max-width: 75vw;
    padding: 1vh ;
}

.pet-info {
    grid-area: info;
    display: grid;
    grid-template-columns: 1fr;
    grid-template-areas:
        "name" 
        "one"
        "two"
        "special"
        "adopted"
        "desc";
    font-size: 150%;
}
#pet-name {
    grid-area: name;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
}
.group-one {
    grid-area: one;
    display: flex;
    justify-content: space-between;
}
.group-two {
    grid-area: two;
    display: flex;
    justify-content: space-between;
}
/* #pet-species {
    grid-area: species;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
}
#pet-age {
    grid-area: age;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
}
#pet-gender{
    grid-area: gender;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
} */
#pet-special {
    grid-area: special;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
}
/* #pet-color {
    grid-area: color;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
}
#pet-breed {
    grid-area: breed;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
}
#pet-size {
    grid-area: size;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
} */
#pet-adopted {
    grid-area: adopted;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
}
#pet-desc {
    grid-area: desc;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
}
/* #pet-fixed {
    grid-area: fixed;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
} */
</style>

