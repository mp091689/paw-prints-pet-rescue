<template>
    <h1>{{ title }}</h1>
    <div class="volunteer-list">
        <div v-if="!volunteers">
            <h1>loading...</h1>
        </div>
        <div  v-for="(person, idx) in volunteers" :key="person.personId" v-else>
            <VolunteerCard
                :person="person"
                :class="{'orange-card': idx % 2 != 0, 'blue-card': idx % 2 == 0}"
                @person-approved="$emit('person-approved')"
            />
        </div>
    </div>
</template>
<script>
import VolunteerCard from "@/components/VolunteerCard.vue";

export default {
    components: {VolunteerCard},
    props: ['volunteers', 'title'],
    emits: ['person-approved'],
}
</script>

<style scoped>
.volunteer-list {
  display: grid;
  justify-content:left;
  gap: 6px;
  background-color: #9DD9D2;
  padding: 10px;
}

.volunteer-list > div {
  background-color:#9DD9D2;
  color: black;
  text-align: left;
  padding: 5px 0;
  font-size: 22px;
}
</style>

