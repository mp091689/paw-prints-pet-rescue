<template>

    <div class="about-us">
        <h2 id="about-title">Welcome to Paw Prints Pet Rescue!</h2>
        <p class="about-desc"> 
            We believe every animal deserves a chance at happiness. Founded in 2024 in Cleveland, OH, we're dedicated to rescuing, rehabilitating, and rehoming pets in need. Our community of staff and volunteers works tirelessly to provide care, love, and a second chance for animals, connecting them with families ready to open their hearts and homes. Together, we can ensure every pet finds a path to love and safety.
        </p>
        <img src="/Paw Prints Pet Rescue Logo.png" class="company-logo" alt="PAW LOGO">
    </div>

    <div class="adopted-pet">
        <h2 id="pet-title">Success Stories</h2>
        <p class="adopted-desc">
            These stories inspire community involvement in adoption, underscore the tireless efforts of our staff and volunteers, and emphasize the importance of providing loving homes to needy animals. It's a testament to the power of companionship and the positive impact of giving pets a second chance at happiness.
        </p>    
    </div>
    <div class="pet-box">
        <pets-list :is-adopted="true"/>
    </div>
</template>

<script>
import PetsList from "@/components/PetsList.vue";

export default {
    components: {
        PetsList
    }
};
</script>
<style>
.about-us{
    border: solid #392F5a 3px;
    background-color: #9DD9D2;
    padding: 10px;
    margin-bottom: 5px;
    display: grid;
    grid-template-columns: 3fr 1fr;
    grid-template-areas: 
    "title pic"
    "about pic";
    justify-content: center;
    align-items: center;
}

.company-logo{
    grid-area: pic;
    max-height: 30vh;
    padding-left: 5px;
}
#about-title{
    grid-area: title;
    display: flex;
}
.about-desc {
    grid-area: about;
}


#pet-title{
    display: flex;
    justify-content: left;
}
.adopted-pet{
    border: solid #392F5a 3px;
    background-color: #9DD9D2;
    padding: 10px;
    margin-bottom: 5px;
    background-color: #FF8811;
    padding: 5px;

}
.pet-box{
    border:groove #392F5a 3px;
}




</style>