<template>
    <div class="About-us">
        <!--this section explains the about us on the home page--> 
        <h2 id="Who-title"> Who are we?</h2>
        <p class="about-desc"> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eu sapien non enim imperdiet vulputate. In rhoncus, purus ac sodales porta, erat felis posuere mi, at ullamcorper lorem libero id urna. Morbi hendrerit quam vitae neque luctus tincidunt. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam dignissim elit ac dolor luctus, sit amet fermentum sem varius. Integer luctus, est ut eleifend tempus, dui libero rhoncus odio, pharetra ultricies tellus velit sit amet magna. Vestibulum non nulla et purus blandit laoreet eget in diam. Praesent vitae quam non eros ullamcorper bibendum. Praesent ullamcorper, tellus sed cursus faucibus, est arcu auctor massa, dictum cursus quam sapien iaculis enim. </p>
    </div>
            <!-- This will toss a title and description on the home page letting the user see the pets that have already been adopted -->
        <h2 class="adopted-pet"> Adopted Pets</h2>
        <p class="adopted-pet"> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eu sapien non enim imperdiet vulputate. In rhoncus, purus ac sodales porta, erat felis posuere mi, at ullamcorper lorem libero id urna. Morbi hendrerit quam vitae neque luctus tincidunt.</p> 
        <pets-list :is-adopted="true"/>
</template>

<script>
import PetsList from "@/components/PetsList.vue";

export default {
    components: {PetsList}
};
</script>
<style>
.About-us{
    border: solid #392F5a 3px;
    /* margin: auto; */
    background-color: #9DD9D2;
    padding: 10px;
    margin-bottom: 5px;
}
#Who-title{
    display: flex;
    justify-content: left;
}
.adopted-pet{
    display: flex;
    justify-content:center;
    background-color: #FF8811;
    border: solid #392F5a 3px;
    margin: auto;
    padding: 5px;
    margin-bottom: 5px;
    color: #392F5A;
}    



</style>