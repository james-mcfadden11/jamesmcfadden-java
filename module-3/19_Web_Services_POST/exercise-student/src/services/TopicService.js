import axios from 'axios';

const http = axios.create({
  baseURL: "https://te-pgh-api.azurewebsites.net/api",
  headers: {
    common: {
      APIKey: '00000'
    }
  }
});

export default {

  list() {
    return http.get('/topics');
  },

  get(id) {
    return http.get(`/topics/${id}`);
  }

}
