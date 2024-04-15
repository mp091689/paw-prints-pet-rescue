<template>
    <h1>volunteer request list</h1>
    <div class="volunteerRequest-list">
        <div v-if="!volunteers">
            <h1>loading...</h1>
        </div>
        <div v-else>
            <VolunteerCard :person="person" v-for="person in volunteers" :key="person.personId" />
        </div>
    </div>
</template>
<script>
import VolunteerCard from "@/components/VolunteerCard.vue";

export default {
    components: {VolunteerCard},
    props: ['volunteers'],
}
</script>

<style scoped>
</style>

