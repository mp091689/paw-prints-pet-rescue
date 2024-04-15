<template>
    <div  class="volunteer-card" :class="`${$attrs.class}`">
        <div class="columns">
            <p><b>First Name:</b> {{ person.firstName }}</p>
            <p><b>Last Name:</b> {{ person.lastName }}</p>
            <p><b>Email:</b> {{ person.email }}</p>
        </div>
        <div class="columns">
            <p><b>Is available weekdays:</b> {{ person.availableWeekdays ? "Yes" : "No"  }}</p>
            <p><b>Is available weekends:</b> {{ person.availableWeekends ? "Yes" : "No"  }}</p>
            <p><b>Is Approved:</b> {{ person.isApproved ? "Yes" : "No"  }}</p>
        </div>
        <div class="columns">
            <p><b>Volunteering Interest:</b> {{ person.volunteeringInterest }}</p>
        </div>
      <form action="#" @submit.prevent="submitApprove" v-if="!person.isApproved && $store.getters.isUserRole('ROLE_ADMIN')">
        <button type="submit">Approve</button>
      </form>
      <!-- <router-link :to="{name: 'edit-volunteer', params: {personId: person.personId}}" v-if="isAuthorized()">Edit</router-link> -->
    </div>
</template>

<script>
import VolunteerService from "@/services/VolunteerService";

export default {
  props: ['person'],
  methods: {
    submitApprove() {
      VolunteerService.approveVolunteer(this.person).then(response => {
        if (response.status === 200) {
          window.location.reload();
        }
      }).catch(error => console.log(error));
    }
  }
}
</script>

<style scoped>
.volunteer-card {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    border: solid red 1px;
    flex-wrap: wrap;
    justify-content: space-evenly;
    padding-left: 2px;
    color: black;
}
.columns{
  display: grid;
  justify-content: space-around;
  align-items: flex-start;
  padding: 1px;
  flex-wrap: wrap;
}
/* */
.volunteer-card.orange-card  {
  background-color: orange;
  color: rgb(212, 18, 18);
}

.volunteer-card.blue-card {
  background-color: cornflowerblue;
}
</style>
