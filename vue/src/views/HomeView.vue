<template>

    <div class="about-us">
        <h2 id="about-title">Welcome to Paw Prints Pet Rescue!</h2>
        <p class="about-desc"> 
            We believe every animal deserves a chance at happiness. Founded in 2024 in Cleveland, OH, we're dedicated to rescuing, rehabilitating, and rehoming pets in need. Our community of staff and volunteers works tirelessly to provide care, love, and a second chance for animals, connecting them with families ready to open their hearts and homes. Together, we can ensure every pet finds a path to love and safety.
        </p>
    </div>

    <div class="adopted-pet">
        <div class="about-adopted">
            <h2 id="pet-title">Success Stories</h2>
            <p class="adopted-desc">
                These stories inspire community involvement in adoption, underscore the tireless efforts of our staff and volunteers, and emphasize the importance of providing loving homes to needy animals. It's a testament to the power of companionship and the positive impact of giving pets a second chance at happiness.
            </p>
        </div>
        <div class="pet-box">
        <!-- <pets-list :is-adopted="true"/> -->
            <img class="adopted-pic" src="../assets/Dog1.jpg" />
            <h2 class="pet-name">Enzo</h2>
            <p class="adoption-story">
                Enzo, a sweet-tempered german shepherd, has found his forever home! This gentle soul was patiently waiting at our shelter when he captured the hearts of a kind couple looking to expand their family. After getting to know Enzo, they decided to adopt him. His new family is thrilled to begin a new chapter with Enzo, filled with long walks and relaxing evenings together. We are overjoyed to see Enzo embark on this wonderful new journey with his adopters, who promise to provide him with a safe and caring environment. Congratulations to Enzo and her new family!
            </p>
    </div>
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
    padding: 5px;
    display: grid;
    grid-template-columns: 2fr 1fr;
    grid-template-areas: 
    "pet about"
    "pet about";
    justify-content: center;
    align-items: center;

}
.about-adopted {
    grid-area: about;
    background-color: #FF8811;
    display: flexbox;
    border: solid #392F5A 3px; 
}
.pet-box{
    border: solid #392F5A 3px; 
    justify-content: space-between;
    padding-left: 2px;
    grid-area: pet;
    background-color: #FF8811;
    margin-right: 2vw;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas: 
    "pic name"
    "story story";
    justify-content: center;
    align-items: center;
}
.adopted-pic {
    grid-area: pic;
    max-height: 30vh;
}
.pet-name {
    grid-area: name;
}
.adoption-story {
    grid-area: story;
}


</style>