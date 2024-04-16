<template>
    <pet-details :pet = "pet"/>
</template>

<style>
</style>

<script>
import PetDetails from '../../components/PetDetails.vue';

export default {
    components: {
        PetDetails
    }
}
</script>