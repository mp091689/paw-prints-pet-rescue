<template>
    <h1>volunteer request list</h1>
    <div class="volunteerRequest-list">
        <div v-if="isLoading">
            <h1>loading...</h1>
        </div>
        <!-- <div v-else>
            <VolunteerCard :person="person" v-for="person in volunteers" :key="person.personId"/>
        </div> -->
        <div  v-for="(person, idx) in volunteers" :key="person.personId" v-else>
            <VolunteerCard :person="person" :class="{'orange-card': idx % 2 != 0, 'blue-card': idx % 2 == 0}" 
            />
        </div>
    </div>
</template>
<script>
import VolunteerCard from "@/components/VolunteerCard.vue";
import VolunteerService from "../services/VolunteerService";

export default {
    props: {
        isApproved: {
            type: Boolean,
            required: true
        }
    },
    components: {VolunteerCard},
    data() {
        return {
            isLoading: true,
            volunteers: []
        }
    },
    created() {
        VolunteerService.getVolunteers(this.isApproved).then(response => {
            this.volunteers = response.data;
            this.isLoading = false;
        })
    }
}
</script>

<style scoped>
.volunteerRequest-list {
  display: grid;
  justify-content:left;
  gap: 6px;  
  background-color: #9DD9D2;
  padding: 10px;  
}

.volunteerRequest-list > div {
  background-color:#9DD9D2;
  color: black;
  text-align: left;
  padding: 5px 0;
  font-size: 22px;
}
</style>

