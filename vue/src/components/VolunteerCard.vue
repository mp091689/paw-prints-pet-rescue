<template>
    <div  class="volunteer-card" :class="`${$attrs.class}`">
      <p>First Name: {{ person.firstName }}</p>
      <p>Last Name: {{ person.lastName }}</p>
      <p>Email: {{ person.email }}</p>
      <p>Weekdays availability: {{ person.availableWeekdays ? "Yes" : "No" }}</p>
      <p>Weekends availability: {{ person.availableWeekends ? "Yes" : "No" }}</p>
      <p>Approved: {{ person.isApproved ? "Yes" : "No" }}</p>
      <p>Volunteering Interest: {{ person.volunteeringInterest }}</p>
      <form action="#" @submit.prevent="submitApprove" v-if="!person.isApproved && $store.getters.isUserRole('ROLE_ADMIN')">
        <button type="submit">Approve</button>
      </form>
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
  border: solid red 1px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  padding-left: 2px;
  color: black;
}

.volunteer-card.orange-card {
  background-color: orange;
}

.volunteer-card.blue-card {
  background-color: cornflowerblue;
}
</style>
