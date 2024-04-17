<template>
    <!-- <h1> Donate Logic here</h1> -->
    <div class="donate">
        <h1 id="donate-title"> Support our shelter</h1>
        <!-- <p id="donate-desc"> donations of any items that are listed below will help staff and volunteers at the Paw Prints
            Pet Rescue to care for all of the animals we assist!</p> -->
            <p id="donate-desc">Your generous contributions of the items listed below make an immeasurable difference in the lives of the animals under our care at Paw Prints Pet Rescue. With your support, our dedicated staff and volunteers can provide the essential care and comfort needed for every furry friend we assist. Together, we ensure that each animal receives the love, attention, and resources they deserve on their journey to finding their forever homes</p>
        <p> you can either send any of the items to us by our amazon wish list or send to our address
        </p>
        <div class="contact info">
            <b><p> Address: 7100 Euclid Ave Suite #140
                Cleveland, Ohio 44103
            </p>
            <p> Phone number: (877) 606-3203 </p> </b>
        </div>
    </div>
    <!-- Links to wishlists  -->
    <div class="Actual-pet-shelter">
        <p><b>PLEASE NOTE</b> (If you are interested in helping out a real local shelter please check out the Cleveland APL
            animal Rescue! They are such a great resource and they do so much to help out animals in need below is linked
            their amazon wishlist and Chewy wishlist! anything helps them out and would be greatly appreciated!)</p>
        <div class="links-to-wishlists">
            <h4> Cleveland APL Links</h4>
            <a href="https://www.amazon.com/hz/wishlist/ls/2YYF7QL97PN62?ref_=wl_share"> Amazon wishlist</a>
            <a href="https://www.chewy.com/g/cleveland-animal-protective-league_b90342359?utm_source=partnerize_int&utm_medium=shelters&utm_campaign=1011l235796&utm_content=0&clickref=1101lyqMgFEX&utm_term=1101lyqMgFEX">
                Chewy Wishlist</a>
        </div>
    </div>

    <!-- These are 4 different item lists of things that people can donate to our shelter   -->
    <div class="shelter-dog-list">
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
                <img src="public/pet-photos/Watson2.jpg" class="Watson2" alt="Wat.jpg">
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
                <img src="public\pet-photos\jimmy-dean-2.jpg" class="jimmy2" alt="jimmy-dean.jpg">
            </div>
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
            <img src="public\pet-photos\Carson2.jpg" class="Carson2" alt="carson.jpg">
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
            <img src="public\pet-photos\fluffy-rabbit.jpg" class="bunny-photo">
            <img src="public\pet-photos\fluffy-bun-2.jpg" class="bunny2">
        </div>
    </div>
</template>

<style>
/* Donate page title, desc, and wishlist links */
p{
    font-size: large;
}
.donate {
    background-color: #9DD9D2;
    border: solid #392F5A 3px;
}

#donate-title {
    display: flex;
    font-size: larger;
    justify-content: center;
    border: solid #392F5A 3px;
}

/* Link and description to donate to real local shelters in the area  */
.Actual-pet-shelter {
    display: grid;
    grid-template-columns: 1fr 1fr;
    background-color: #FF8811;
    padding: 2px;
}
.links-to-wishlists{
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-left: 150px;
}

/* CSS for Shelter List items */
.shelter-item-list {
    background-color: #9DD9D2;
    border: solid #392F5A 3px;
    margin: auto;
}

.Watson {
    padding-top: 100px;
    padding-left: 100px;
    display: flex;
    justify-content: right;
    max-width: 300px;
    max-height: 400px;
}

.Watson2 {
    padding-top: 100px;
    padding-left: 100px;
    max-width: 300px;
    max-height: 400px;
    display: flex;
    justify-content: right;
}

.shelter-card-content {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    padding-left: 50px;
    width: 100%;
}

/* CSS for Dog list  */

.dog-item-list {
    background-color: #FF8811;
    border: solid #392F5A 3px;
}

.JIMMY {
    padding-top: 25px;
    padding-left: 50px;
    display: flex;
    justify-content: right;
    max-width: 450px;
    max-height: 300px;
}

.jimmy2 {
    padding-top: 25px;
    padding-left: 50px;
    display: flex;
    justify-content: right;
    max-width: 450px;
    max-height: 300px;

}

.card-content {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    padding-left: 50px;
    width: 100%;
}

/* CSS for Cat Item list */
.cat-item-list {
    background-color: #9DD9D2;
    border: solid #392F5A 3px;
}

.Carson {
    padding-left: 100px;
    display: flex;
    justify-content: right;
    max-width: 250px;
    max-height: 400px;
}

.Carson2 {
    padding-left: 100px;
    display: flex;
    justify-content: right;
    max-width: 250px;
    max-height: 400px;
}

.cat-card-content {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    padding-left: 50px;
    width: 100%;
}

/* CSS for bunny Item List  */
.bunny-item-list {
    background-color: #FF8811;
    border: solid #392F5A 3px;
}

.bunny-photo {
    padding-top: 100px;
    padding-left: 50px;
    display: flex;
    justify-content: right;
    max-width: 450px;
    max-height: 450px;
}
.bunny2{
    padding-top: 100px;
    padding-left: 50px;
    display: flex;
    justify-content: right;
    max-width: 450px;
    max-height: 450px;

}

.bunny-card-content {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    padding-left: 50px;
    width: 100%;
    margin: 5px;
}</style>