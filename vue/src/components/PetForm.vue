<template>
  <form action="#" @submit.prevent="submitForm" enctype="multipart/form-data">
    <div>
      <label for="name">Name</label>
      <input type="text" name="name" id="name" placeholder="Enter pet's name" required
             v-model="editPet.name"/>
    </div>

    <div>
      <label for="species">Choose a species:</label>
      <select name="species" id="species" v-model="editPet.speciesId" required>
        <option value="" disabled selected hidden>Choose pet's species...</option>
        <option value=1>Cat</option>
        <option value=2>Dog</option>
        <option value=3>Bunny</option>
      </select></div>

    <div>
      <label for="breed">Breed</label>
      <input type="text" name="breed" id="breed" placeholder="Enter pet's breed" v-model="editPet.breed"/>
    </div>

    <div>
      <label for="gender">Choose a gender:</label>
      <select name="gender" id="gender" v-model="editPet.gender" required>
        <option value="" disabled selected hidden>Choose pet's gender...</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
      </select>
    </div>

    <div>
      <label for="age">Age</label>
      <input type="number" name="age" id="age" min="1" max="100" placeholder="Enter pet's age" v-model="editPet.age"
             required/>
    </div>

    <div>
      <label for="hasSpecialNeeds">Has Special Medical Needs</label>
      <input type="checkbox" name="hasSpecialNeeds" id="hasSpecialNeeds" v-model="editPet.hasSpecialNeeds"/>
    </div>

    <div>
      <label for="color">Color</label>
      <input type="text" name="color" id="color" placeholder="Enter pet's color" v-model="editPet.color"/>
    </div>

    <div>
      <label for="size">Choose a size:</label>
      <select name="size" id="size" v-model="editPet.size" required>
        <option value="" disabled selected hidden>Choose pet's size...</option>
        <option value="xs">Extra Small</option>
        <option value="s">Small</option>
        <option value="m">Medium</option>
        <option value="l">Large</option>
        <option value="xl">Extra Large</option>
      </select>
    </div>

    <div>
      <label for="description">Description</label>
      <textarea name="description" id="description" cols="30" rows="10" placeholder="Provide some description"
                v-model="editPet.description"></textarea>
    </div>

    <div>
      <label for="isFixed">Is Fixed</label>
      <input type="checkbox" name="isFixed" id="isFixed" v-model="editPet.isFixed"/>
    </div>

    <div>
      <label for="isAdopted">Is Adopted</label>
      <input type="checkbox" name="isAdopted" id="isAdopted" v-model="editPet.isAdopted"/>
    </div>

    <div>
      <label for="avatar">Select avatar:</label>
      <input type="file" id="avatar" name="avatar" @change="onChangeAvatar"/>
      <img :src="avatar" width="100" v-if="avatar" alt="Pet's avatar"/>
    </div>

    <div>
      <label for="photos">Select photos:</label>
      <input type="file" id="photos" name="photos" multiple @change="onChangePhotos"/>
      <img v-for="(photo, idx) in photos" :src="photo" width="100" alt="Pet's avatar" :key="idx"/>
    </div>

    <button>Submit</button>
    <button @click="cancelForm">Cancel</button>
  </form>
</template>

<script>
import petService from "@/services/PetService";
import {ref} from "vue";

export default {
  props: {
    pet: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      avatar: ref(""),
      photos: ref([]),
      editPet: {
        petId: this.pet?.petId ?? 0,
        speciesId: 1,
        name: "aaaa",
        age: 12,
        gender: "male",
        hasSpecialNeeds: true,
        color: "red",
        breed: "labrador",
        size: "xl",
        isAdopted: true,
        description: "test description",
        isFixed: true,
        avatar: this.pet?.avatar,
        // photos: this.pet?.photos ?? [],
      },
      // editPet: {
      //   petId: this.pet?.petId ?? 0,
      //   speciesId: this.pet?.speciesId ?? "",
      //   name: this.pet?.name,
      //   age: this.pet?.age,
      //   gender: this.pet?.gender ?? "",
      //   hasSpecialNeeds: this.pet?.hasSpecialNeeds,
      //   color: this.pet?.color ?? "",
      //   breed: this.pet?.breed ?? "",
      //   size: this.pet?.size ?? "",
      //   isAdopted: this.pet?.isAdopted,
      //   description: this.pet?.description ?? "",
      //   isFixed: this.pet?.isFixed,
      //   avatar: this.pet?.avatar,
      //   photos: this.pet?.photos ?? [],
      // },
    };
  },
  methods: {
    onChangeAvatar(event) {
      this.editPet.avatar = event.target.files[0];
      this.avatar = URL.createObjectURL(event.target.files[0]);
    },
    onChangePhotos(event) {
      this.editPet.photos = event.target.files;
      let urls = [];
      [...event.target.files].forEach((file) => urls.push(URL.createObjectURL(file)));
      this.photos = urls;
    },
    submitForm() {
      if (!this.validateForm()) {
        return;
      }

      if (this.editPet.petId === 0) {
        // add
        console.log("add pet")
        petService
            .addPet(this.editPet)
            .then(response => {
              if (response.status === 201) {
                // this.$store.commit(
                //     'SET_NOTIFICATION',
                //     {
                //       message: 'A new pet was added.',
                //       type: 'success'
                //     }
                // );
                this.$router.push({name: 'adopt'});
              }
            })
            .catch(error => {
              // this.handleErrorResponse(error, 'adding');
            });
      } else {
        console.log("update pet")
        petService
            .updatePet(this.editPet)
            .then(response => {
              if (response.status === 200) {
                // this.$store.commit(
                //     'SET_NOTIFICATION',
                //     {
                //       message: `Pet ${this.editPet.id} was updated.`,
                //       type: 'success'
                //     }
                // );
                this.$router.push({name: 'adopt'});
              }
            })
            .catch(error => {
              // this.handleErrorResponse(error, 'updating');
            });
      }
    },
    cancelForm() {
      this.$router.push({name: 'adopt'});
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.$store.commit('SET_NOTIFICATION',
            "Error " + verb + " pet. Response received was '" + error.response.statusText + "'.");
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " pet. Server could not be reached.");
      } else {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " pet. Request could not be created.");
      }
    },
    validateForm() {
      let msg = '';

      this.editPet.name = this.editPet.name.trim();
      this.editPet.breed = this.editPet.breed.trim();
      this.editPet.color = this.editPet.color.trim();
      this.editPet.description = this.editPet.description.trim();

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
