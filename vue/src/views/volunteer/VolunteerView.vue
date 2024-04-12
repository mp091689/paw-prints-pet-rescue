<template>
    <volunteer-form/>
    <volunteer-request-list :isApproved="false"/>
    <volunteer-list  :isApproved="true"/>
</template>

<script>
import VolunteerForm from "@/components/VolunteerForm.vue";
import VolunteerRequestList from "@/components/VolunteerRequestList.vue";
import VolunteerList from "@/components/VolunteerList.vue";
export default {
    components: {VolunteerForm, VolunteerRequestList, VolunteerList}
}
</script>

<style>

</style>