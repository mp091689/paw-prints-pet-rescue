import pets from "@/services/dummy/pets";
import axios from "axios";

const path = "/pets";

export default {
    getPets(isAdopted) {
        return new Promise((resolve, reject) => {
            setTimeout(() => resolve({
                data: pets.filter(pet => pet.isAdopted === isAdopted)
            }), 1000);
        });
        // return axios.get(path, {params: {isAdopted}});
    },
    getPet(petId) {
        return new Promise((resolve, reject) => {
            setTimeout(() => resolve({data: pets[0]}), 1000);
        });
        // return axios.get(`${path}/${petId}`)
    },
    addPet(pet) {
        let form = new FormData();
        for (let key in pet) {
            form.append(key, pet[key]);
        }
        return axios.post(path, form);
    },
    updatePet(pet) {
        console.log("put")
        return axios.put(`${path}/${pet.id}`, pet);
    },
}
