import axios from "axios";

const path = "/people";

export default {
    getVolunteers(isApproved = false) {
        return axios.get(path,{params: {isApproved: isApproved}});
    },
    addVolunteer(volunteer) {
        return axios.post(path, volunteer);
    },
    approveVolunteer(isApproved, volunteerId) {
        let suffix = isApproved ? "/approve" : "/decline";

        return axios.put(path + '/' + volunteerId + suffix);
    }
}
