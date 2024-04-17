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
        <input type="file" id="avatar" name="avatar" @change="onChangeAvatar" ref="mainPhoto"/>
        <div class="img-container">
          <svg @click="removeMainPhoto" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
               fill="currentColor" viewBox="0 0 24 24">
            <path fill-rule="evenodd"
                  d="M8.586 2.586A2 2 0 0 1 10 2h4a2 2 0 0 1 2 2v2h3a1 1 0 1 1 0 2v12a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V8a1 1 0 0 1 0-2h3V4a2 2 0 0 1 .586-1.414ZM10 6h4V4h-4v2Zm1 4a1 1 0 1 0-2 0v8a1 1 0 1 0 2 0v-8Zm4 0a1 1 0 1 0-2 0v8a1 1 0 1 0 2 0v-8Z"
                  clip-rule="evenodd"/>
          </svg>
          <img :src="getPhotoUrl(editPet.mainPhoto)" alt="Pet's avatar" v-if="editPet.mainPhoto"/>
        </div>
      </div>
    </div>

    <div class="form-group">
      <label for="photos">Select photos:</label>
      <div class="img-group">
        <input type="file" id="photos" name="photos" @change="onChangePhotos" multiple ref="photos"/>
        <div class="img-container" v-for="photo in editPet.photos" :key="photo.photoId">
          <img :src="getPhotoUrl(photo)" alt="Pet's photo"/>
        </div>
      </div>

      <div class="form-group">
        <label>Current photos:</label>
        <div class="img-group">
          <div class="img-container" v-for="photo in currentPhotos" :key="photo.photoId">
            <svg @click="removePhoto(photo.photoId)" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" width="24"
                 height="24" fill="currentColor" viewBox="0 0 24 24">
              <path fill-rule="evenodd"
                    d="M8.586 2.586A2 2 0 0 1 10 2h4a2 2 0 0 1 2 2v2h3a1 1 0 1 1 0 2v12a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V8a1 1 0 0 1 0-2h3V4a2 2 0 0 1 .586-1.414ZM10 6h4V4h-4v2Zm1 4a1 1 0 1 0-2 0v8a1 1 0 1 0 2 0v-8Zm4 0a1 1 0 1 0-2 0v8a1 1 0 1 0 2 0v-8Z"
                    clip-rule="evenodd"/>
            </svg>
            <img :src="getPhotoUrl(photo)" alt="Pet's photo"/>
          </div>
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
        photos: [],
      },
      currentPhotos: this.pet?.photos ?? [],
    };
  },
  methods: {
    removeMainPhoto() {
      this.$refs.mainPhoto.value = null;
      petService.removeMainPhoto(this.editPet)
          .then(response => {
            if (response.status === 200) {
              this.editPet.mainPhoto = response.data;
            }
          }).catch(error => console.log(error));
    },
    removePhoto(id) {
      this.$refs.photos.value = null;
      petService.removePhoto(id)
          .then(response => {
            if (response.status === 200) {
              this.editPet.photos = response.data;
            }
          }).catch(error => console.log(error));
    },
    getPhotoUrl(file) {
      if (file instanceof File) {
        return URL.createObjectURL(file);
      }
      if (typeof file === 'string' || file instanceof String) {
        return import.meta.env.VITE_REMOTE_API + '/pets/photos/' + file;
      }
      return import.meta.env.VITE_REMOTE_API + '/pets/photos/' + file.fileName;
    },
    submitForm() {
      if (!this.validateForm()) {
        return;
      }

      this.isSubmitting = true;

      if (this.editPet.petId === 0) {
        petService.addPet(this.editPet)
            .then(response => {
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

form .form-group img {
  max-width: 100px;
  max-height: 100px;
}

form .form-group .img-container {
  max-width: 100px;
  max-height: 100px;
  position: relative;
}

form .form-group .img-container svg {
  position: absolute;
  top: 0;
  right: 0;
  color: darkred;
  cursor: pointer;
}

form .form-group .img-container svg:hover {
  color: red;
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
