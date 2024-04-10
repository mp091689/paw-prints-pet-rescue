<template>
    <div class="pets-list">
        <div v-if="isLoading">
            <h1>loading...</h1>
        </div>
        <div v-else>
            <PetCard :pet="pet" v-for="(pet, idx) in pets" :key="pet.id" :class="{'orange-card': idx % 2 != 0, 'blue-card': idx % 2 == 0}"/>
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
            pets: []
        }
    },
    created() {
        petService.getPets(this.isAdopted).then(response => {
            this.pets = response.data;
            this.isLoading = false;
        })
    }
}
</script>

<style scoped>
.pet-list {
  display: flexbox;
  justify-content:left;
}
</style>