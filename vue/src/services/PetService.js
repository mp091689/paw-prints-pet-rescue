import pets from "@/services/dummy/pets";
import axios from "axios";

const path = "/pets";

export default {
    getPets(isAdopted) {
        return axios.get(path);
    },
    getPet(petId) {
        return new Promise((resolve, reject) => {
            setTimeout(() => resolve({data: pets[0]}), 1000);
        });
        // return axios.get(`${path}/${petId}`)
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
