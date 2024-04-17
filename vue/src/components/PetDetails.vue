<template>
    <div v-if="isLoading">
        <h1>loading...</h1>
    </div>
    <div v-else>

        <div class="pet-details">

            <div class="pet-img">
                <img class="pet-pic" :src="getMainPhotoUrl(pet.mainPhoto)" alt="">
            </div>

            <div class="pet-info">
                <h2 id="pet-name">I'm {{ pet.name }}!</h2>
                <div class="group-one">
                    <p id="pet-age">{{ pet.age ? pet.age : "unknown" }}</p>
                    <p> || </p>
                    <p id="pet-breed">{{ pet.breed }}</p>
                    <p> || </p>
                    <p id="pet-species">{{ getSpeciesName(pet.speciesId) }}</p>
                </div>
                <div class="group-two">
                    <p id="pet-gender">{{ pet.gender }}</p>
                    <p> || </p>
                    <p id="pet-fixed">{{ pet.isFixed ? "Fixed" : "Not Fixed"}}</p>
                    <p> || </p>
                    <p id="pet-color">{{ pet.color }}</p>
                    <p> || </p>
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
            return import.meta.env.VITE_REMOTE_API + '/pets/photos/' + id;
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
    font-size: 150%;
    overflow: hidden;
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
    display: flex;
    flex-direction: column;
}
#pet-name {
    display: flex;
    justify-content: center;
    align-items: center;
}
.group-one {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
}
.group-two {
    display: flex;
    justify-content: space-around;
}
#pet-special {
    margin-top: 1vh;
    display: flex;
    justify-content: center;
    align-items: center;
}
#pet-adopted {
    margin-top: 1vh;
    display: flex;
    justify-content: center;
    align-items: center;
}
#pet-desc {
    margin-top: 1vh;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>

