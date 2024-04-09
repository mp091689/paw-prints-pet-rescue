<template>
    <div class="pets-list">
        <div v-if="isLoading">
            <h1>loading...</h1>
        </div>
        <div v-else>
            <PetCard :pet="pet" v-for="pet in pets" :key="pet.id"/>
        </div>
    </div>
</template>

<script>
import PetCard from "@/components/PetCard.vue";
import petService from "@/services/PetService";

export default {
    components: {PetCard},
    data() {
        return {
            isLoading: true,
            pets: []
        }
    },
    created() {
        petService.getPets(true).then(response => {
            this.pets = response.data;
            this.isLoading = false;
        })
    }
}
</script>

<style scoped>

</style>