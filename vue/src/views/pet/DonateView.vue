<template>
    <!-- <h1> Donate Logic here</h1> -->
    <div class="donate"> 
        <h1 id="donate-title"> Support our shelter</h1>
        <p id="donate-desc"> donations of any items that are listed below will help staff and volunteers at the Paw Prints Pet Rescue to care for all of the animals we assist!</p>
        <p> you can either send any of the items to us by our amazon wish list or send to our address
        </p>
        <p> 7100 Euclid Ave Suite #140
            Cleveland, Ohio 44103
        </p>
    </div>
    <!-- These are 4 different item lists of things that people can donate to our shelter   -->

    <!-- Shelter items -->
    <div class="shelter-item-list">
    <h3 id="shelter-item-title">Shelter Items</h3>
    <p>gift cards</p>
    <P>amazon gift cards</P>
    <p>Cat/Dog food (especially wet food)</p>
    <p>Dog Treats</p>
    <p>Hot dogs (for training)</p>
    <p>Plastic or wire dog crates</p>
    <p>Cat Scratchers</p>
    <p>Gift cards (that can be used at any store)</p>
    <p>Non-dust cat litter</p>
    <p>Towels</p>
    <p>Cat/dog toys</p>
    <p>Bleach</p>
    <p>Automatic dish washing liquid</p>
    <p>Laundry detergent (high efficacy washing machines)</p>
    <p>Clean newspapers or butcher paper</p>
    <p>office supplies</p>
</div>
    <!-- Items for Dogs -->
    <div class="dog-item-list">
        
    <h3 id="dog-item-title">Dog related items</h3>
    <div class="card-content">
        <div>
            <p>Rawhides</p>
            <p>Canned pumpkin</p>
            <p>Chicken/Beef Broth </p>
            <p>Plain Yogurt</p>
            <p>Fleece Fabric </p>
            <p> Unopened bags of uncooked rice </p>
            <p> White tube socks</p>
            <p>Peanut Butter</p>
            <p>Applesauce</p>
            <p>Medium-sized treats</p>  
        </div>
        <img src="/Jimmy Dean-fidos.jpg" class="JIMMY" alt="fidos-dog">
    </div>
</div>
<!-- Items for Cats -->
<div class="cat-item-list">
    <h3 id="cat-item-title"> Cat related items</h3>
</div>
<!-- Items for Rabbits -->
<div class="bunny-item-list">
    <h3 id="bunny-item-title"> Bunny related items</h3>

</div>
<h3> </h3>
    
</template>

<style>
.donate{
    background-color: #F4D06F;
}
#donate-title{
    display: flex;
    font-size: larger;
    justify-content: center;
}
.shelter-item-list{
    background-color: #F4D06F;
    border: solid #392F5A 3px ;
    margin: auto;
}.dog-item-list{
    background-color: #F4D06F;
    border: solid #392F5A 3px ;
    margin: auto;
}
.cat-item-list{
    background-color: #F4D06F;
    border: solid #392F5A 3px ;
}
.bunny-item-list{
    background-color: #F4D06F;
    border: solid #392F5A 3px ;
}
.JIMMY{
    display: flex;
    justify-content: right;
    max-width: 250px;
    max-height: 250px;
}
.card-content {
    display: grid;
    grid-template-columns: 1fr 1fr;
padding-left: 50px ;
    /* display: flex; */
    width: 100%;
}


</style>