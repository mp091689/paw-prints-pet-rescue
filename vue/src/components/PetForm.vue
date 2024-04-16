<template>
  <form action="#" @submit.prevent="submitForm" enctype="multipart/form-data">
    <h2>{{ isSubmitting ? "Submitting new pet..." : "Add New Pet" }}</h2>
    <ul class="errors" v-if="validationMessages.length">
      <li v-for="(msg, idx) in validationMessages" :key="idx">{{msg}}</li>
    </ul>

    <div>
      <label for="name"><span class="required">*</span> Name:</label>
      <input type="text"
             name="name"
             id="name"
             placeholder="Enter pet's name"
             v-model="editPet.name"
             :disabled="isSubmitting"
             required/>
    </div>

    <div>
      <label for="species"><span class="required">*</span> Species:</label>
      <select name="species"
              id="species"
              v-model="editPet.speciesId"
              :disabled="isSubmitting"
              required>
        <option value="" disabled selected hidden>Choose pet's species...</option>
        <option value=1>Cat</option>
        <option value=2>Dog</option>
        <option value=3>Bunny</option>
      </select></div>

    <div>
      <label for="gender"><span class="required">*</span> Gender:</label>
      <select name="gender"
              id="gender"
              v-model="editPet.gender"
              :disabled="isSubmitting"
              required>
        <option value="" disabled selected hidden>Choose pet's gender...</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
      </select>
    </div>

      <div>
          <label for="size"><span class="required">*</span> Size:</label>
          <select name="size"
                  id="size"
                  v-model="editPet.size"
                  :disabled="isSubmitting"
                  required>
              <option value="" disabled selected hidden>Choose pet's size...</option>
              <option value="XS">Extra Small</option>
              <option value="S">Small</option>
              <option value="M">Medium</option>
              <option value="L">Large</option>
              <option value="XL">Extra Large</option>
          </select>
      </div>

      <div>
          <label for="breed">Breed:</label>
          <input type="text"
                 name="breed"
                 id="breed"
                 placeholder="Enter pet's breed"
                 v-model="editPet.breed"
                 :disabled="isSubmitting"/>
      </div>

    <div>
      <label for="age">Age:</label>
      <input type="number"
             name="age"
             id="age"
             min="0"
             max="100"
             placeholder="Enter pet's age"
             v-model="editPet.age"
             :disabled="isSubmitting"
             required/>
    </div>

    <div>
      <label for="color">Color:</label>
      <input type="text"
             name="color"
             id="color"
             placeholder="Enter pet's color"
             v-model="editPet.color"
             :disabled="isSubmitting"/>
    </div>

    <div>
      <label for="description">Description:</label>
      <textarea name="description"
                id="description"
                cols="30"
                rows="10"
                placeholder="Provide some description"
                v-model="editPet.description"
                :disabled="isSubmitting"></textarea>
    </div>

      <div>
          <label for="hasSpecialNeed">Has Special<br/>Medical Needs:</label>
          <input type="checkbox"
                 name="hasSpecialNeed"
                 id="hasSpecialNeed"
                 v-model="editPet.hasSpecialNeed"
                 :disabled="isSubmitting"/>
      </div>


      <div>
      <label for="isFixed">Is Fixed:</label>
      <input type="checkbox"
             name="isFixed"
             id="isFixed"
             v-model="editPet.isFixed"
             :disabled="isSubmitting"/>
    </div>

    <div>
      <label for="isAdopted">Is Adopted:</label>
      <input type="checkbox"
             name="isAdopted"
             id="isAdopted"
             v-model="editPet.isAdopted"
             :disabled="isSubmitting"/>
    </div>

      <div>
        <label for="avatar">Select avatar:</label>
        <input type="file" id="avatar" name="avatar" @change="onChangeAvatar"/>
        <img :src="getMainPhotoUrl(editPet.petId)" width="100" alt="Pet's avatar" v-if="editPet.petId !== 0 || avatar != null"/>
      </div>

    <button :disabled="isSubmitting">Submit</button>
    <button @click="cancelForm" :disabled="isSubmitting">Cancel</button>
  </form>
</template>

<script>
import petService from "@/services/PetService";

export default {
  props: {
    pet: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      validationMessages: [],
      isSubmitting: false,
      avatar: null,
      editPet: {
        petId: this.pet?.petId ?? 0,
        speciesId: this.pet?.speciesId ?? "",
        name: this.pet?.name ?? "",
        age: this.pet?.age ?? 0,
        gender: this.pet?.gender ?? "",
        hasSpecialNeed: this.pet?.hasSpecialNeed ?? false,
        color: this.pet?.color ?? "",
        breed: this.pet?.breed ?? "",
        size: this.pet?.size ?? "",
        isAdopted: this.pet?.isAdopted ?? false,
        description: this.pet?.description ?? "",
        isFixed: this.pet?.isFixed ?? false,
        avatar: this.pet?.avatar ?? null,
      },
    };
  },
  methods: {
      getMainPhotoUrl(id) {
          if (this.avatar != null) {
              return this.avatar
          }
          return import.meta.env.VITE_REMOTE_API + '/pets/' + id + '/main-photo';
      },
    submitForm() {
      if (!this.validateForm()) {
        return;
      }

      this.isSubmitting = true;

      if (this.editPet.petId === 0) {
        petService.addPet(this.editPet)
            .then(response => {
                console.log(response);
              if (response.status === 201) {
                this.$router.push({name: 'adopt'});
              }
            })
            .catch(error => console.log(error));
      } else {
        petService.updatePet(this.editPet)
            .then(response => {
              if (response.status === 200) {
                this.$router.push({name: 'adopt'});
              }
            })
            .catch(error => console.log(error));
      }
    },
    cancelForm() {
      this.$router.push({name: 'adopt'});
    },
    onChangeAvatar(event) {
        this.editPet.avatar = event.target.files[0];
        this.avatar = URL.createObjectURL(event.target.files[0]);
    },
    validateForm() {
      this.validationMessages = [];

      if (this.editPet.avatar.size >= 1024 * 1024 * 2) {
        this.validationMessages.push("Avatar size is too large, it should be 2MB maximum.");
      }
      this.editPet.name = this.editPet.name.trim();
      this.editPet.breed = this.editPet.breed.trim();
      this.editPet.color = this.editPet.color.trim();
      this.editPet.description = this.editPet.description.trim();

      return !this.validationMessages.length;
    },
  },
}
</script>

<style scoped>
form {
  background: #9dd9d2;
  color: #392f5a;
  padding: 12px 48px;
}

form ul.errors {
  color: red;
}

form > div {
  margin-bottom: 8px;
  display: grid;
  grid-template-columns: 150px 1fr;
}
span.required {
    color: red;
}
label {
    justify-self: end;
    margin-right: 8px;
}
input[type=checkbox] {
    justify-self: start;
}
</style>
