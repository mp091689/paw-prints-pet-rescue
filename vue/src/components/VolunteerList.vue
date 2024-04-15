<template>
    <h1>Volunteer list</h1>
    <div class="volunteer-list" >
        <div v-if="isLoading">
            <h1>loading...</h1>
        </div>
        <div  v-for="(person, idx) in volunteers" :key="person.personId" v-else>
            <VolunteerCard 
            :person="person" 
            :class="{'orange-card': idx % 2 != 0, 'blue-card': idx % 2 == 0}" 
            @person-approved="loadVolunteers"
            />
        </div>
    </div>
</template>
<script>
import VolunteerCard from "@/components/VolunteerCard.vue";
import VolunteerService from "../services/VolunteerService";

export default {
    components: {VolunteerCard},
    data() {
        return {
            isLoading: true,
            volunteers: []
        }
    },
    methods: {
        loadVolunteers() {
            VolunteerService.getVolunteers(true).then(response => {
                this.volunteers = response.data;
                this.isLoading = false;
            });
        }
    },
    created() {
        this.loadVolunteers();
    }
}
</script>

<style scoped>
.volunteer-list {
  display: grid;
  justify-content:left;
  gap: 6px;  
  padding: 10px;  
}

.volunteer-list > div {
  background-color:#9DD9D2;
  color: black;
  text-align: left;
  padding: 5px 0;
  font-size: 22px;
}
</style>
