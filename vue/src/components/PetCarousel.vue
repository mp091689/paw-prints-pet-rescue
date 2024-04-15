<template>
    <div class="carousel">
        <div class="carousel-card" v-for="(pet, index) in pets" v-bind:key="pet.pet.petId" v-show="currentIndex === index">
            <pet-card v-bind:pet="pet"/>
        </div>
    </div>
</template>

<script>
import PetCard from './PetCard.vue';

export default {
    components: {
        PetCard,
    },
    data() {
        return {
            currentIndex: 0,
            interval: null,
        };
    },
    props: {
        pets: Array,
    },
    mounted() {
        this.startRotation();
    },
    beforeUnmount() {
        this.stopRotation();
    },

    methods: {
        startRotation() {
            this.interval = setInterval(() => {
                this.currentIndex = (this.currentIndex + 1) % this.pets.length;
            }, 10000);
        },
        stopRotation() {
            clearInterval(this.interval);
        },
    },
};
</script>

<style>
.carousel {
    display: flex;
    overflow: hidden;
}
.carousel-card {
    flex: 0 0 auto;
    width: 100%;
}
</style>