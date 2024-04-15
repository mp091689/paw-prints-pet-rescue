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
    <div class="shelter-card-content">
        <div>  
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
    
    <img src="public\pet-photos\Watson.jpg" class="Watson" alt="Watson.jpg">
</div>
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
        <img src="pet-photos/Jimmy Dean-fidos.jpg" class="JIMMY" alt="fidos-dog">
    </div>
</div>
<!-- Items for Cats -->
<div class="cat-item-list">

    <h3 id="cat-item-title"> Cat related items</h3>
    <div class="cat-card-content">
        <div>
            <p> Single Cat Scratchers </p>
            <p> Plastic Easter Eggs </p>
            <p> Cat toys (hard and soft) </p>
            <p> Interactive cat toys </p>    
            <p> Pipe cleaners </p>
            <p> Toy mice </p>
            <p> Kong treat ball toys for cats </p>
            <p> Kong cat wobblers </p>
            <p> Cat carriers (used or new) </p>
            <p> Cat Food (dry and canned) </p>
    </div>
    <img src="pet-photos/Carson.heic" class="Carson" alt="Carson"> 
</div>
</div>

<!-- Items for Rabbits -->
<div class="bunny-item-list">
    <h3 id="bunny-item-title"> Bunny related items</h3>
    <div class="bunny-card-content"> 
        <div>
            <p> Kitty litter boxes </p>
            <p> Clear Storage Tubs </p>
            <p> Non-Slip Bath Mats </p>
            <p> Fleece Blankets </p>
            <p> Alfalfa hay </p>
            <p> Carrots </p>
            <p> Rabbit feed </p>
            <p> Bird chew toys </p> 
            <p> Cardboard boxes </p>
            <p> Rabbit mazes </p>
            <p> Grass mats </p>
            <p> Bunny tunnels </p>
        </div>
        <img src="public\pet-photos\rabbit.jfif" class="bunny-photo">
    </div>
</div>
    
</template>

<style>
.donate{
    background-color: #F4D06F;
}
#donate-title{
    display: flex;
    font-size: larger;
    justify-content: center;
    border: solid #392F5A 3px ;

}
/*   **** CSS for each list of items ****   */
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
/*  **** Pet pics on donate page ****  */
.JIMMY{
    padding-top: 100px;
    padding-left: 100px;
    display: flex;
    justify-content: right;
    max-width: 250px;
    max-height: 250px;
}
.Carson{
    padding-top: 100px;
    padding-left: 100px;
    display: flex;
    justify-content: right;
    max-width: 250px;
    max-height: 250px;
}
.Watson{
    padding-top: 250px;
    padding-left: 100px;
    display: flex;
    justify-content: right;
    max-width: 250px;
    max-height: 250px;
}
.bunny-photo{
    padding-top: 150px;
    padding-left: 100px;
    display: flex;
    justify-content: right;
    max-width: 250px;
    max-height: 250px;
}
/* card content to organize photos and list of items  */
.shelter-card-content{
    display: grid;
    grid-template-columns: 1fr 1fr ;
    padding-left: 50px;
    width: 100%;
}
.card-content {
    display: grid;
    grid-template-columns: 1fr 1fr;
    padding-left: 50px ;
    width: 100%;
}
.cat-card-content {
    display: grid;
    grid-template-columns: 1fr 1fr;
    padding-left: 50px ;
    width: 100%;
}
.bunny-card-content {
    display: grid;
    grid-template-columns: 1fr 1fr;
    padding-left: 50px ;
    width: 100%;
}


</style>