import axios from "axios";

const path = "/people";

export default {
    getVolunteers(isApproved) {
        // return new Promise((resolve, reject) => {
        //     setTimeout(() => resolve({
        //         data: [
        //             {
        //                 "personId": 0,
        //                 "userId": 0,
        //                 "firstName": "John",
        //                 "lastName": "Smith",
        //                 "email": "JohnSmith@volunteer.com",
        //                 "volunteeringInterest": "A sweet cat only",
        //                 "isApproved": false,
        //                 "token": null,
        //                 "availableWeekends": true,
        //                 "availableWeekdays": false
        //             },
        //             {
        //                 "personId": 0,
        //                 "userId": 0,
        //                 "firstName": "Jane",
        //                 "lastName": "Doe",
        //                 "email": "JaneTheAdmin@boss.com",
        //                 "volunteeringInterest": "I love animals and cannot wait to make a difference in the world!",
        //                 "isApproved": true,
        //                 "token": null,
        //                 "availableWeekends": true,
        //                 "availableWeekdays": true
        //             },
        //             {
        //                 "personId": 0,
        //                 "userId": 0,
        //                 "firstName": "Tom",
        //                 "lastName": "Bright",
        //                 "email": "tbright@boss.com",
        //                 "volunteeringInterest": "I love animals and cannot wait to make a difference in the world! Give me one more love!",
        //                 "isApproved": true,
        //                 "token": null,
        //                 "availableWeekends": true,
        //                 "availableWeekdays": false
        //             }
        //         ].filter(i => i.isApproved == isApproved)
        //     }), 1000);
        // });
        return axios.get(path, {params: {isApproved}});
    },
    getVolunteer(personId) {
        return axios.get(`${path}/${personId}`);
    },
    addVolunteerApp(person) {
        let formData = new FormData();
        for (let key in person) {
            formData.append(key, person[key]);
            // person.is_approved = true;
        }
        return axios.post(path, formData);
    },
    updateVolunteer(person) {
        let formData = new FormData();
        for (let key in person) {
            formData.append(key, person[key]);
        }
        return axios.put(`${path}/${person.personIdId}`, formData);
    },
}