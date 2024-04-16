<template>
  <div class="form">
    <form action="#" @submit.prevent="submitForm">
      <h2>Volunteer Application Form</h2>
      <ul class="errors" v-if="validationMessages.length">
        <li v-for="(msg, idx) in validationMessages" :key="idx">{{msg}}</li>
      </ul>

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
      <div class="buttons">
        <button :disabled="isSubmitting">Submit</button>
        <button @click="cancelForm" :disabled="isSubmitting">Cancel</button>
      </div>
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
      validationMessages: [],
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
      if (!this.validateForm()) {
        return;
      }

      this.isSubmitting = true;
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
            if (error.response?.status === 409) {
              this.validationMessages.push(`Volunteer with email: "${this.volunteer.email}" already exists.`);
            } else {
              this.validationMessages.push("Oops, something went wrong, please try again.");
            }
            if (typeof(error.response) === 'undefined') {
              this.validationMessages.push("Oops, something went wrong, please try again.");
            }
            this.isSubmitting = false;
          });
    },
    cancelForm() {
      this.$router.push({name: 'volunteer'});
    },
    validateForm() {
      this.validationMessages = [];

      this.volunteer.firstName = this.volunteer.firstName.trim();
      if (this.volunteer.firstName.length < 3) {
        this.validationMessages.push("Volunteer name should be at least 3 characters.");
      }
      this.volunteer.lastName = this.volunteer.lastName.trim();
      if (this.volunteer.lastName.length < 3) {
        this.validationMessages.push("Volunteer last name should be at least 3 characters.");
      }
      this.volunteer.email = this.volunteer.email.trim();
      this.volunteer.volunteeringInterest = this.volunteer.volunteeringInterest.trim();

      return !this.validationMessages.length;
    },
  },
}
</script>

<style scoped>
form {
  background: lightgrey;
  padding: 12px;
}

form ul.errors {
  color: red;
}

form .buttons {
  display: flex;
  width: 100%;
  justify-content: end;
  gap: 10px;
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
