<template>
    <div  class="volunteer-card" :class="`${$attrs.class}`">
      <div class="three-columns-grid">
        <div class="column">
            <p><b>First Name:</b> {{ person.firstName }}</p>
            <p><b>Last Name:</b> {{ person.lastName }}</p>
            <p><b>Email:</b> {{ person.email }}</p>
        </div>
        <div class="column">
            <p><b>Is available weekdays:</b> {{ person.availableWeekdays ? "Yes" : "No"  }}</p>
            <p><b>Is available weekends:</b> {{ person.availableWeekends ? "Yes" : "No"  }}</p>
            <p><b>Is Approved:</b> {{ person.isApproved === null ? "pending" : person.isApproved ? "Yes" : "No" }}</p>
        </div>
        <div class="column">
            <p><b>Volunteering Interest:</b> {{ person.volunteeringInterest }}</p>
        </div>
      </div>
      <div class="buttons" v-if="person.isApproved == null && $store.getters.isUserRole('ROLE_ADMIN')">
          <form action="#" @submit.prevent="submitApprove(true)">
            <button type="submit" :disabled="isSubmitting">Approve</button>
          </form>
          <form action="#" @submit.prevent="submitApprove(false)">
            <button type="submit" :disabled="isSubmitting">Decline</button>
          </form>
      </div>
      <div class="buttons" v-if="person.isApproved == true && person.role === 'ROLE_USER' && $store.getters.isUserRole('ROLE_ADMIN')">
          <form action="#" @submit.prevent="submitAdmin()">
            <button type="submit" :disabled="isSubmitting">Make an Admin</button>
          </form>
      </div>
      <!-- <div class="buttons" v-if="person.isApproved == false && $store.getters.isUserRole('ROLE_ADMIN')">
          <form action="#" @submit.prevent="submitApprove(null)">
            <button type="submit" :disabled="isSubmitting">Approve for Volunteer</button>
          </form>
      </div> -->
    </div>
</template>

<script>
import VolunteerService from "@/services/VolunteerService";

export default {
  props: ['person'],
  emits: ['person-approved','make-admin'],
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
    },
    submitAdmin() {
      this.isSubmitting = true;
      VolunteerService.makeAdmin(this.person.personId).then(response => {
        if (response.status === 200) {
          this.$emit('make-admin');
          this.isSubmitting = false;
        }
      }).catch(error => {
        this.isSubmitting = false;
        console.log(error);
      });
    }
    // submitVolunteer(isApproved) {
    //   this.isSubmitting = true;
    //   VolunteerService.approveVolunteer(isApproved, this.person.personId).then(response => {
    //     if (response.status === 200) {
    //       this.$emit('person-approved');
    //       this.isSubmitting = false;
    //     }
    //   }).catch(error => {
    //     this.isSubmitting = false;
    //     console.log(error);
    //   });
    // }
  }
}
</script>

<style scoped>
.volunteer-card {
    flex-wrap: wrap;
    justify-content: space-evenly;
    padding: 0 20px;
    color: black;
}
.three-columns-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
}
.three-columns-grid .column{
  justify-content: space-around;
  align-items: flex-start;
  flex-wrap: wrap;
}

.three-columns-grid .column p {
  padding: 0;
  margin: 10px 0;
}
/* */
.volunteer-card.orange-card  {
  background-color: orange;
  color: rgb(212, 18, 18);
}

.volunteer-card.blue-card {
  background-color: cornflowerblue;
}
.buttons {
  display: flex;
  width: 100%;
  justify-content: end;
  gap: 10px;
}
</style>
