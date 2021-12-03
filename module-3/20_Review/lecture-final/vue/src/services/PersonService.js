import axios from 'axios';

export default {

  getPerson(id) {
    return axios.get(`/person/${id}`)
  }

}
