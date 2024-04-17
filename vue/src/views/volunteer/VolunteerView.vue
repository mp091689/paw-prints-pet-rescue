<template>
  <h1>Volunteers</h1>
  <div class="description">
    <div class="descript">
      <p><b>Hello! Would you like to apply for volunteer position</b></p>
      <p class="desc">We are constantly seeking compassionate individuals who share our love for animals and are willing to dedicate their time and effort to make a difference in their lives. Your commitment as a volunteer can play a crucial role in our mission to rescue, rehabilitate pets in need. By being a volunteer, you will have the opportunity to:</p>
      <div class="list">
        <li>Saves lives by providing care and shelter for animals in need.</li>
        <li>Advocates for animal welfare and raises awareness in the community.</li>
        <li>Fosters personal fulfillment and skill development.</li>
        <li>Builds relationships with fellow volunteers and animals.</li>
        <li>Makes a tangible difference in the lives of animals and the community.</li>
      </div>

    </div>
    <div>

    </div>
    <div>
      <volunteer-form @person-request-created="loadVolunteers" />
    </div>
  </div>
  <br>
  <div class="volLists">
    <div class="filterVol">
      <div><b>Choose your volunteer filter</b></div>
      <div class="firstName-filter">
        <label for="first_name">First Name:</label>
        <input id="first_name" type="String" v-model="filters.first_name" placeholder="First Name" max="40" min="0">
      </div>
      <div class="lastName-filter">
        <label for="last_name">Last Name:</label>
        <input id="last_name" type="String" v-model="filters.last_name" placeholder="Last Name" max="40" min="0">
      </div>
      <div class="email-filter">
        <label for="email">Email:</label>
        <input id="email" type="email" v-model="filters.email" placeholder="email" max="40" min="0">
      </div>
      <div class="available-filter">
        <label for="available">Availability:</label>
        <select name="available" id="available"  v-model="filters.available">
          <option value="">Choose..</option>
          <option value="all">All Days</option>
          <option value="weekdays">weekdays</option>
          <option value="weekends">weekends</option>
          <option value="none">Not available</option>
        </select>
      </div>
    </div>
      

    <volunteer-list :volunteers="filteredVolunteerRequestList" title="Volunteer Request List" @person-approved="loadVolunteers" v-if="filteredVolunteerRequestList.length && $store.getters.isUserRole('ROLE_ADMIN')"/>
    <volunteer-list :volunteers="filteredVolunteerList" title="Volunteer List" @make-admin="loadVolunteers" v-if="filteredVolunteerList.length"/>
    <volunteer-list :volunteers="filteredVolunteerDeclinedList" title="List of Declined Candidates for Volunteering" v-if="filteredVolunteerDeclinedList.length && $store.getters.isUserRole('ROLE_ADMIN')"/>
  </div>
</template>

<script>
import VolunteerForm from "@/components/VolunteerForm.vue";
import VolunteerList from "@/components/VolunteerList.vue";
import VolunteerService from "@/services/VolunteerService";

export default {
  components: {VolunteerForm, VolunteerList},
  data() {
    return {
      volunteers: [],
      filters: {
        first_name: "",
        last_name: "",
        email: "",
        available: "",
      }
    }
  },
  methods: {
    loadVolunteers() {
      VolunteerService.getVolunteers().then(response => this.volunteers = response.data);
    }
  },
  created() {
    this.loadVolunteers();
  },
  computed: {
    filterVolunteers() {
        return this.volunteers.filter(v => {
          let firstNameMatched = v.firstName.toLowerCase().includes(this.filters.first_name.toLocaleLowerCase());
          let lastNameMatched = v.lastName.toLowerCase().includes(this.filters.last_name.toLocaleLowerCase());
          let emailMatched = v.email.toLowerCase().includes(this.filters.email.toLocaleLowerCase());
          let available = true;
          switch (this.filters.available) {
            case "none" :
              available = !(v.availableWeekdays || v.availableWeekends);
              break;
            case "weekdays" :
              available = v.availableWeekdays;
              break;
            case "weekends" :
              available = v.availableWeekends;
              break;
            case "all" :
            available = v.availableWeekdays && v.availableWeekends;
              break;
          }
          return firstNameMatched && lastNameMatched && emailMatched && available;
        });
    },
    filteredVolunteerRequestList() {
      return this.filterVolunteers.filter(v => v.isApproved === null && this.filterVolunteers);
    },
    filteredVolunteerList() {
      return this.filterVolunteers.filter(v => v.isApproved === true && this.filterVolunteers);
    },
    filteredVolunteerDeclinedList() {
      return this.filterVolunteers.filter(v => v.isApproved === false && this.filterVolunteers);
    },
  }
}
</script>

<style scoped>
.description {
  background-color:#9DD9D2;
  display: grid;
  grid-template-columns: 1fr 20px 1fr;
  padding: 20px;
}
.descript {
  background-color:#F4D06F;
  color: black;
  text-align: left;
  padding: 20px;
  font-size: 18px;
  text-size-adjust: 1fr;
}
.desc{
  display: grid;
  text-align: left;
}
.list {
  padding: 30px;
}
.volLists{
  /* background-color:#9DD9D2;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  padding: 20px; */
}
.filterVol{
  background-color:#9DD9D2;
  display: grid;
  text-align: center;
  font-size: 20px;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  padding: 20px;
}
</style>
