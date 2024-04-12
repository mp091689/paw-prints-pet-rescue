<template>
    <div class="volForm">
        
        <form>
            <h2>Volunteer Application Form</h2>
            <div>
                <label for="volName">First Name</label>
                <input type="text" name="first_name" id="volName" placeholder="Enter Volunteer's first name" required
                    v-model="editVolunteer.first_name"/>
            </div>
            <div>
                <label for="volName">Last Name</label>
                <input type="text" name="last_name" id="volName" placeholder="Enter Volunteer's last name" required
                    v-model="editVolunteer.last_name"/>
            </div>
            <div>
                <label for="email">Email</label>
                <input type="text" name="email" id="email" placeholder="Enter Volunteer's email" required
                    v-model="editVolunteer.email"/>
            </div>
            <div>
                <label for="isAvailableWeekends">Available weekends</label>
                <input type="checkbox" name="isAvailableWeekends" id="isFixed" v-model="editVolunteer.is_available_weekends"/>
            </div>
            <div>
                <label for="isAvailableWeekdays">Available weekdays</label>
                <input type="checkbox" name="isAvailableWeekdays" id="isFixed" v-model="editVolunteer.is_available_weekdays"/>
            </div>
            <div>
                <label for="volunteering_interest">What is your interest in volunteering</label>
                <textarea name="volunteering_interest" id="volunteering_interest" cols="30" rows="10" placeholder="Provide your interest"
                            v-model="editVolunteer.volunteering_interest"></textarea>
            </div>
            <button :disabled="isSubmitting">Submit</button>
            <button @click="cancelForm" :disabled="isSubmitting">Cancel</button>
        </form>
    </div>
</template>
<script>
import VolunteerService from '../services/VolunteerService';

//import
export default {
  props: {
    person: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
    editVolunteer: {
        person_id: this.pet?.petId ?? 0,
        user_id: 1,
        first_name: "Dan",
        last_name: "Cool",
        email: "dcool@abc.com",
        is_available_weekends: true,
        is_available_weekdays: true,
        volunteering_interest: "loving people and pets",
        is_approved: false,
      },
    };
  },
  methods: {
    submitForm() {
      if (!this.validateForm()) {
        return;
      }

      if (this.editVolunteer.personId === 0) {
        // add
        console.log("add volunteer")
        VolunteerService
            .addVolunteerApp(this.editVolunteer)
            .then(response => {
              if (response.status === 201) {
                // this.$store.commit(
                //     'SET_NOTIFICATION',
                //     {
                //       message: 'A new volunteer application was added.',
                //       type: 'success'
                //     }
                // );
                this.$router.push({name: 'volunteer'});
              }
            })
            .catch(error => {
              // this.handleErrorResponse(error, 'adding');
            });
      } else {
        console.log("update volunteeer")
        VolunteerService
            .updateVolunteer(this.editVolunteer)
            .then(response => {
              if (response.status === 200) {
                // this.$store.commit(
                //     'SET_NOTIFICATION',
                //     {
                //       message: `Volunteer ${this.editVolunteer.person_id} was updated.`,
                //       type: 'success'
                //     }
                // );
                this.$router.push({name: 'volunteer'});
              }
            })
            .catch(error => {
              // this.handleErrorResponse(error, 'updating');
            });
      }
    },
    cancelForm() {
      this.$router.push({name: 'volunteer'});
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.$store.commit('SET_NOTIFICATION',
            "Error " + verb + " pet. Response received was '" + error.response.statusText + "'.");
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " volunteer. Server could not be reached.");
      } else {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " volunteer. Request could not be created.");
      }
    },
    validateForm() {
      let msg = '';

      this.editVolunteer.first_name = this.editVolunteer.first_name.trim();
      this.editVolunteer.last_name = this.editVolunteer.last_name.trim();
      this.editVolunteer.email = this.editVolunteer.email.trim();
      this.editVolunteer.is_available_weekdays = this.editVolunteer.is_available_weekdays.trim();
      this.editVolunteer.is_available_weekends = this.editVolunteer.is_available_weekends.trim();
      this.editVolunteer.is_approved = this.editVolunteer.is_approved.trim();
      this.editVolunteer.volunteering_interest = this.editVolunteer.volunteering_interest.trim();

      if (msg.length > 0) {
        this.$store.commit('SET_NOTIFICATION', msg);
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
}
</style>
