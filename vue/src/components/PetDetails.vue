<template>
    <div v-if="isLoading">
        <h1>loading...</h1>
    </div>
    <div v-else>
        <div class="pet-details" >
            <p>color: {{ pet.color }}</p>
            <p>breed: {{ pet.breed }}</p>
            <p>size: {{ pet.size }}</p>
            <p>gender: {{ pet.gender }}</p>
            <p>isAdopted: {{ pet.isAdopted }}</p>
            <p>description: {{ pet.description }}</p>
            <p>isFixed: {{ pet.isFixed }}</p>
        </div>
    </div>
</template>

<script>
import petService from "@/services/PetService";

export default{
    data() {
        return {
            isLoading: true,
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

</style>

