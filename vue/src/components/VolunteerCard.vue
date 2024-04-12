<template>
    <div  class="volunteer-card" :class="`${$attrs.class}`">
      <p>First Name: {{ person.firstName }}</p>
      <p>Last Name: {{ person.lastName }}</p>
      <p>Email: {{ person.email }}</p>
      <p>Weekdays availability: {{ person.availableWeekdays }}</p>
      <p>Weekends availability: {{ person.availableWeekends }}</p> 
      <p>isApproved: {{ person.isApproved }}</p> 
      <p>volunteeringInterest: {{ person.volunteeringInterest }}</p> 
      <!-- <router-link :to="{name: 'edit-volunteer', params: {personId: person.personId}}" v-if="isAuthorized()">Edit</router-link> -->
    </div>
</template>

<script>
export default {
  props: ['person'],
  methods: {
    isAuthorized(){
      if(Object.keys(this.$store.state.user).length === 0){
        return false;
      }
      return this.$store.state.user.authorities?.filter(role => role.name === 'ROLE_ADMIN').length;
    },
    getMainPhotoUrl(id) {
        return import.meta.env.VITE_REMOTE_API + '/volunteers/' + id + '/main-photo';
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
