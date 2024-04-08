
<template>
  <header>
    <h1 class="title"> Adoptable pets!!!</h1>
    <p class="desc"> So many adoptable pets to choose from! </p>

  </header>
  <div class="pet-list">
    <div class="pet-card blue-card">

      <p>name: </p>
      <p>age: </p>
      <p>species: </p>
      <p>breed: </p>
      <p>size: </p>
      <p>Has Special Needs: </p>
      <p>description: </p>
      <p>color(s): </p>
    </div>

    <div class="pet-card orange-card">
      <p>name: </p>
      <p>age: </p>
      <p>species: </p>
      <p>breed: </p>
      <p>size: </p>
      <p>Has Special Needs: </p>
      <p>description: </p>
      <p>color(s): </p>
    </div>

    <div class="pet-card blue-card">
      <p>name: </p>
      <p>age: </p>
      <p>species: </p>
      <p>breed: </p>
      <p>size: </p>
      <p>Has Special Needs: </p>
      <p>description: </p>
      <p>color(s): </p>
    </div>

    <div class="pet-card orange-card">
      <p>name: </p>
      <p>age: </p>
      <p>species: </p>
      <p>breed: </p>
      <p>size: </p>
      <p>Has Special Needs: </p>
      <p>description: </p>
      <p>color(s): </p>
    </div>



  </div>

</template>

<script>
export default {
};
</script>

<style scoped>
.pet-list {
  display: flexbox;
}
.pet-card {
  border: solid red 1px;
}

.pet-card.orange-card {
  background-color: orange;
}

.pet-card.blue-card {
  background-color: blue;
}
header{
  border: solid orange 2px;
  font-family: 'Courier New', Courier, monospace;
}
.title{
  display: flex;
  justify-content: center;
}
.desc{
  display: flex;
  justify-content: center;

}



</style>