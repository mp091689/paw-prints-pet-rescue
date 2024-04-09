<template>
    <div class="pet-details" >
<p>color: {{ pet.color }}</p>
<p>breed: {{ pet.breed }}</p>
<p>size: {{ pet.size }}</p>
<p>gender: {{ pet.gender }}</p>
<p>isAdopted: {{ pet.isAdopted }}</p>
<p>description: {{ pet.description }}</p>
<p>isFixed: {{ pet.isFixed }}</p>
</div>
</template>

<script>
export default{
    props:['pet'],
    methods:{}
}

</script>

<style scoped>

</style>

