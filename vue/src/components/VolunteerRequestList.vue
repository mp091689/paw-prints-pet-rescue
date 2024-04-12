<template>
    <h1>volunteer request list</h1>
    <div class="volunteerRequest-list">
        <div v-if="isLoading">
            <h1>loading...</h1>
        </div>
        <div v-else>
            <VolunteerCard :person="person" v-for="person in volunteers" :key="person.personId"/>
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
.pet-list {
  display: flexbox;
  justify-content:left;
}
</style>

