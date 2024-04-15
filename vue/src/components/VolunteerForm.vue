<template>
  <div class="form">
    <form action="#" @submit.prevent="submitForm">
      <h2>Volunteer Application Form</h2>
      <div>
        <label for="volName">First Name</label>
        <input type="text" name="first_name" id="volName" placeholder="Enter Volunteer's first name" required
               v-model="volunteer.firstName"/>
      </div>
      <div>
        <label for="volName">Last Name</label>
        <input type="text" name="last_name" id="volName" placeholder="Enter Volunteer's last name" required
               v-model="volunteer.lastName"/>
      </div>
      <!-- <div class="{error : isEmailDuplicated}"> -->
      <div class="error">
        <label for="email">Email</label>
        <input type="email" name="email" id="email" placeholder="Enter Volunteer's email" required
               v-model="volunteer.email"/>
      </div>
      <div class="isButton">
        <label for="isAvailableWeekends">Available weekends</label>
        <input type="checkbox" name="isAvailableWeekends" id="isFixed" v-model="volunteer.isAvailableWeekends"/>
      </div>
      <div class="isButton">
        <label for="isAvailableWeekdays">Available weekdays</label>
        <input type="checkbox" name="isAvailableWeekdays" id="isFixed" v-model="volunteer.isAvailableWeekdays"/>
      </div>
      <div>
        <label for="volunteering_interest">What is your interest in volunteering</label>
        <textarea name="volunteering_interest" id="volunteering_interest" cols="30" rows="10"
                  placeholder="Provide your interest"
                  v-model="volunteer.volunteeringInterest"></textarea>
      </div>
      <button :disabled="isSubmitting"> Submit</button>
      <button @click="cancelForm" :disabled="isSubmitting">   Cancel</button>
      <!-- <div class="buttons">
        
      </div> -->
    </form>
  </div>
</template>
<script>
import VolunteerService from '../services/VolunteerService';

//import
export default {
  emits: ['person-request-created'],
  data() {
    return {
      isSubmitting: false,
      isEmailDuplicated: false,
      volunteer: {
        firstName: "",
        lastName: "",
        email: "",
        volunteeringInterest: "",
        isAvailableWeekends: false,
        isAvailableWeekdays: false,
        isApproved: false,
      },
    };
  },
  methods: {
    submitForm() {
      this.isSubmitting = true;
      if (!this.validateForm()) {
        return;
      }

      VolunteerService
          .addVolunteer(this.volunteer)
          .then(response => {
            if (response.status === 201) {
              this.$emit('person-request-created');
              this.volunteer = {
                firstName: "",
                lastName: "",
                email: "",
                volunteeringInterest: "",
                isAvailableWeekends: false,
                isAvailableWeekdays: false,
                isApproved: false,
              };
              this.isSubmitting = false;
            }
          }).catch(
          error => {
            if (error.response.status === 409) {
              this.isEmailDuplicated = true;
              setTimeout(() => {
                this.isEmailDuplicated = false
              }, 5000);
            }
            this.isSubmitting = false;
          });
    },
    cancelForm() {
      this.$router.push({name: 'volunteer'});
    },
    validateForm() {
      let msg = '';

      this.volunteer.firstName = this.volunteer.firstName.trim();
      if (this.volunteer.firstName < 3) {
        msg += "Volunteer name should be at least 3 characters. ";
      }
      this.volunteer.lastName = this.volunteer.lastName.trim();
      if (this.volunteer.lastName < 3) {
        msg += "Volunteer last name should be at least 3 characters. ";
      }
      this.volunteer.email = this.volunteer.email.trim();
      this.volunteer.volunteeringInterest = this.volunteer.volunteeringInterest.trim();

      if (msg.length > 0) {
        console.log(msg);
        return false;
      }
      return true;
    },
  },
}
</script>

<style scoped>
form {
  background: lightgrey;
  padding: 12px;
}

form > div {
  margin-bottom: 8px;
  display: grid;
  grid-template-columns: 1fr 2fr;
  font-size: 20px;
  
}
.buttonForm{
  padding: 50px;

}
.isButton{
  justify-items: left; 
}
/* .buttons{
  justify-items: end; 
  grid-template-columns: 1fr;
} */
.error {
  color: black;
}
</style>
