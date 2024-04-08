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
      <input type="number" name="age" id="age" min="1" max="100" placeholder="Enter pet's age"
             v-model="editPet.age"
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

    <button>Submit</button>
    <button @click="cancelForm">Cancel</button>
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
      editPet: {
        petId: this.pet?.petId ?? 0,
        speciesId: this.pet?.speciesId ?? "",
        name: this.pet?.name,
        age: this.pet?.age,
        gender: this.pet?.gender ?? "",
        hasSpecialNeeds: this.pet?.hasSpecialNeeds,
        color: this.pet?.color ?? "",
        breed: this.pet?.breed ?? "",
        size: this.pet?.size ?? "",
        isAdopted: this.pet?.isAdopted,
        description: this.pet?.description ?? "",
        isFixed: this.pet?.isFixed,
      },
    };
  },
  methods: {
    submitForm() {
      if (!this.validateForm()) {
        return;
      }

      if (this.editPet.petId === 0) {
        // add
        console.log("add pet")
        petService.addPet(this.editPet)
            .then(response => {
              if (response.status === 201) {
                this.$router.push({name: 'adopt'});
              }
            })
            .catch(error => console.log(error));
      } else {
        console.log("update pet")
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
