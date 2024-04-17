import axios from "axios";

const path = "/people";

export default {
    getVolunteers() {
        return axios.get(path);
    },
    addVolunteer(volunteer) {
        return axios.post(path, volunteer);
    },
    approveVolunteer(isApproved, volunteerId) {
        let suffix = isApproved ? "/approve" : "/decline";

        return axios.put(path + '/' + volunteerId + suffix);
    },
    makeAdmin(volunteerId) {
        return axios.put(path + '/' + volunteerId + '/make-admin');
    }
}
