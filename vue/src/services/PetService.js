import pets from "@/services/dummy/pets";
import axios from "axios";

const path = "/pets1";

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
        return new Promise((resolve, reject) => {
            setTimeout(() => resolve({status: 201}), 1000);
        });
        // return axios.post(path, pet);
    },
    updatePet(pet) {
        return new Promise((resolve, reject) => {
            setTimeout(() => resolve({status: 201}), 1000);
        });
        // return axios.put(`${path}/${pet.id}`, pet);
    },
}
