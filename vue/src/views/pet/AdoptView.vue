<template>
  <header>
    <h2 class="title"> Adoptable pets!!!</h2>
    <p class="desc"> So many adoptable pets to choose from! </p>
  </header>
  <pets-list/>
</template>

<script>
import PetsList from "@/components/PetsList.vue";

export default {
  components: {PetsList},
};
</script>

<style scoped>
header {
  border: solid red 2px;
  font-family: 'Courier New', Courier, monospace;
  background-color: orange;
}

.title {
  display: flex;
  justify-content: center;
}

.desc {
  display: flex;
  justify-content: center;

}
</style>
