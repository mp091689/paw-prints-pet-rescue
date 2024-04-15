<template>
    <div v-if="isLoading">
        <h1>loading...</h1>
    </div>
    <div class="pets-list" v-else>
        <div v-for="(pet, idx) in pets" :key="pet.id">
            <PetCard :pet="pet" :class="{'orange-card': idx % 2 != 0, 'blue-card': idx % 2 == 0}"/>
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
.pets-list {
  /* display: flexbox;
  justify-content:left; */
  display: grid;
  /* height: 400px; */
  align-content: center;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 10px;
  background-color: #9DD9D2;
  padding: 10px;
}

.pets-list > div {
  background-color:#9DD9D2;
  text-align: center;
  padding: 20px 0;
  font-size: 30px;
}
</style>