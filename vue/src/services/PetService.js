import axios from "axios";

const path = "/pets";

export default {
    getPets(isAdopted) {
        return axios.get(path, {params: {isAdopted}});
    },
    getPet(petId) {
        return axios.get(`${path}/${petId}`);
    },
    addPet(pet) {
        let formData = new FormData();
        for (let key in pet) {
            formData.append(key, pet[key]);
        }
        return axios.post(path, formData);
    },
    updatePet(pet) {
        let formData = new FormData();
        for (let key in pet) {
            formData.append(key, pet[key]);
        }
        return axios.put(`${path}/${pet.petId}`, formData);
    },
}
