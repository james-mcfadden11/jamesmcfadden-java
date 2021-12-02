import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:3000"
});

export default {

    list() {
      // returns a Promise
      return http.get('/docs');
    },

    get(id) {
        return http.get(`/docs/${id}`);
    }
  
  }