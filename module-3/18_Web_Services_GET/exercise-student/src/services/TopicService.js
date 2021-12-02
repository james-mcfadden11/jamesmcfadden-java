import axios from 'axios';

const http = axios.create({
  baseURL: "https://te-pgh-api.azurewebsites.net/api",
  headers: {
    get: {
      APIKEY: '03002'
    }
  }
});

export default {

    getTopics() {
      return http.get('/topics');
    },

    getTopicDetails(id) {
      return http.get(`/topics/${id}`);
    }
  
  }