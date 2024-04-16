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
  <div class="volLists">
    <volunteer-list :volunteers="volunteerRequestList" title="Volunteer Request List" @person-approved="loadVolunteers" v-if="volunteerRequestList.length && $store.getters.isUserRole('ROLE_ADMIN')"/>
    <volunteer-list :volunteers="volunteerList" title="Volunteer List" v-if="volunteerList.length"/>
    <volunteer-list :volunteers="volunteerDeclinedList" title="List of Declined Candidates for Volunteering" v-if="volunteerDeclinedList.length && $store.getters.isUserRole('ROLE_ADMIN')"/>
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
      volunteerRequestList: [],
      volunteerDeclinedList: [],
      volunteerList: [],
    }
  },
  methods: {
    loadVolunteers() {
      let volunteers = [];
      VolunteerService.getVolunteers().then(response => {
        volunteers = response.data
        this.volunteerRequestList = volunteers.filter(v => v.isApproved === null);
        this.volunteerList = volunteers.filter(v => v.isApproved === true);
        this.volunteerDeclinedList = volunteers.filter(v => v.isApproved === false);
      });
    }
  },
  created() {
    this.loadVolunteers();
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
</style>
