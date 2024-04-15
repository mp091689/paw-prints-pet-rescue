<template>
  <h1>Volunteers</h1>
  <div class="description">
    <div>
      <p>you can apply for volunteer position</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Esse in laudantium reprehenderit? Asperiores commodi
        delectus deleniti dolorem est officia quaerat, quo suscipit temporibus totam! Adipisci commodi, cupiditate
        doloribus excepturi laboriosam maxime minima quisquam rerum sed suscipit. A ad alias animi aperiam assumenda
        blanditiis, dignissimos dolore doloribus, et eveniet facilis fuga hic iusto modi molestiae neque pariatur
        placeat quisquam, sit sunt tempore totam vero voluptas? Accusantium adipisci alias aliquam at delectus deserunt
        dicta dignissimos, dolores ducimus ea eius et eveniet exercitationem id libero magnam magni, nam neque nesciunt
        obcaecati officia officiis perferendis porro praesentium provident quae quaerat quam quo reprehenderit,
        repudiandae tenetur unde velit vitae. Accusamus, aliquid at consectetur consequuntur error harum incidunt,
        inventore iusto neque, officia pariatur perspiciatis quam quasi quia reiciendis. Expedita libero rerum vero
        vitae voluptates? Aliquam dolores earum fugiat omnis reiciendis. Ab, cupiditate dolor dolores eligendi eos ipsum
        nam natus provident quasi tempore temporibus ullam veritatis voluptatem.</p>
    </div>
    <div>
      <volunteer-form  @person-request-created="loadVolunteers" />
    </div>
  </div>
  <volunteer-request-list :volunteers="volunteerRequestList"/>
</template>

<script>
import VolunteerForm from "@/components/VolunteerForm.vue";
import VolunteerRequestList from "@/components/VolunteerRequestList.vue";
import VolunteerService from "@/services/VolunteerService";

export default {
  components: {VolunteerForm, VolunteerRequestList},
  data() {
    return {
      volunteerRequestList: [],
    }
  },
  methods: {
    loadVolunteers() {
      VolunteerService.getVolunteers(this.isApproved).then(response => this.volunteerRequestList = response.data);
    }
  },
  created() {
    this.loadVolunteers();
  }
}
</script>

<style scoped>
.description {
  display: grid;
  grid-template-columns: 1fr 1fr;
}
</style>
