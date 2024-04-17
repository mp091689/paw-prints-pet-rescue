<template>
  <form action="#" @submit.prevent="submitForm" enctype="multipart/form-data">
    <h2>{{ isSubmitting ? "Submitting new pet..." : "Add New Pet" }}</h2>
    <ul class="errors" v-if="validationMessages.length">
      <li v-for="(msg, idx) in validationMessages" :key="idx">{{ msg }}</li>
    </ul>

    <div class="form-group">
      <label for="name"><span class="required">*</span> Name:</label>
      <input type="text"
             name="name"
             id="name"
             placeholder="Enter pet's name"
             v-model="editPet.name"
             :disabled="isSubmitting"
             required/>
    </div>

    <div class="form-group">
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

    <div class="form-group">
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

    <div class="form-group">
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

    <div class="form-group">
      <label for="breed">Breed:</label>
      <input type="text"
             name="breed"
             id="breed"
             placeholder="Enter pet's breed"
             v-model="editPet.breed"
             :disabled="isSubmitting"/>
    </div>

    <div class="form-group">
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

    <div class="form-group">
      <label for="color">Color:</label>
      <input type="text"
             name="color"
             id="color"
             placeholder="Enter pet's color"
             v-model="editPet.color"
             :disabled="isSubmitting"/>
    </div>

    <div class="form-group">
      <label for="description">Description:</label>
      <textarea name="description"
                id="description"
                cols="30"
                rows="10"
                placeholder="Provide some description"
                v-model="editPet.description"
                :disabled="isSubmitting"></textarea>
    </div>

    <div class="form-group">
      <label for="hasSpecialNeed">Has Special<br/>Medical Needs:</label>
      <input type="checkbox"
             name="hasSpecialNeed"
             id="hasSpecialNeed"
             v-model="editPet.hasSpecialNeed"
             :disabled="isSubmitting"/>
    </div>


    <div class="form-group">
      <label for="isFixed">Is Fixed:</label>
      <input type="checkbox"
             name="isFixed"
             id="isFixed"
             v-model="editPet.isFixed"
             :disabled="isSubmitting"/>
    </div>

    <div class="form-group">
      <label for="isAdopted">Is Adopted:</label>
      <input type="checkbox"
             name="isAdopted"
             id="isAdopted"
             v-model="editPet.isAdopted"
             :disabled="isSubmitting"/>
    </div>

    <div class="form-group">
      <label for="avatar">Select avatar:</label>
      <div class="img-group">
        <input type="file" id="avatar" name="avatar" @change="onChangeAvatar"/>
        <div class="img-container">
          <img :src="getAvatarUrl(editPet)" alt="Pet's avatar" v-if="editPet.mainPhoto"/>
        </div>
      </div>
    </div>

    <div class="form-group">
      <label for="photos">Select photos:</label>
      <div class="img-group">
        <input type="file" id="photos" name="photos" @change="onChangePhotos" multiple ref="photos"/>
        <div class="img-container" v-for="(img, idx) in getPhotosUrl(editPet)" :key="idx">
          <img :src="img" alt="Pet's photo"/>
        </div>
      </div>
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
        mainPhoto: this.pet?.mainPhoto ?? null,
        photos: this.pet?.photos ?? [],
      },
    };
  },
  methods: {
    getAvatarUrl(pet) {
      if (pet.mainPhoto instanceof File) {
        return URL.createObjectURL(pet.mainPhoto);
      }
      return import.meta.env.VITE_REMOTE_API + '/pets/photos/' + pet.mainPhoto;
    },
    getPhotosUrl(pet) {
      if (pet.photos.length) {
        return Array.from(pet.photos).map(photo => {
              if (photo instanceof File) {
                return URL.createObjectURL(photo);
              }
              return import.meta.env.VITE_REMOTE_API + '/pets/photos/' + photo.fileName;
            }
        );
      }
      return [];
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
      this.editPet.mainPhoto = event.target.files[0];
    },
    onChangePhotos(event) {
      this.editPet.photos = event.target.files;
    },
    validateForm() {
      this.validationMessages = [];

      if (this.editPet.mainPhoto?.size >= 1024 * 1024 * 2) {
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

form .form-group {
  margin-bottom: 8px;
  display: grid;
  grid-template-columns: 150px 1fr;
}

form .form-group .img-group {
  display: flex;
  gap: 8px;
}

form .form-group .img-container {
  max-width: 100px;
  max-height: 100px;
  position: relative;
}

form .form-group .img-container i {
  position: absolute;
  top: 0px;
  right: 10px;
  font-size: 30px;
  font-weight: bold;
  color: red;
  cursor: pointer;
  text-shadow: 0px 0px 2px white;
}

form .form-group .img-container i:hover {
  font-size: 35px;
  color: red;
}

form .form-group img {
  max-width: 100%;
  max-height: 100%;
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
