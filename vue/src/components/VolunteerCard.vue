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
            <p><b>Is Approved:</b> {{ person.isApproved === null ? "pending" : person.isApproved ? "Yes" : "No" }}</p>
        </div>
        <div class="columns">
            <p><b>Volunteering Interest:</b> {{ person.volunteeringInterest }}</p>
        </div>
        <div v-if="person.isApproved == null && $store.getters.isUserRole('ROLE_ADMIN')">
          <form action="#" @submit.prevent="submitApprove(true)">
            <button type="submit" :disabled="isSubmitting">Approve</button>
          </form>
          <form action="#" @submit.prevent="submitApprove(false)">
            <button type="submit" :disabled="isSubmitting">Decline</button>
          </form>
        </div>
    </div>
</template>

<script>
import VolunteerService from "@/services/VolunteerService";

export default {
  props: ['person'],
  emits: ['person-approved'],
  data() {
    return {
      isSubmitting: false,
    }
  },
  methods: {
    submitApprove(isApproved) {
      this.isSubmitting = true;
      VolunteerService.approveVolunteer(isApproved, this.person.personId).then(response => {
        if (response.status === 200) {
          this.$emit('person-approved');
          this.isSubmitting = false;
        }
      }).catch(error => {
        this.isSubmitting = false;
        console.log(error);
      });
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
