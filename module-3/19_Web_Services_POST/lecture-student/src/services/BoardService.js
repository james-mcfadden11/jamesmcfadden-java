import axios from 'axios';

const http = axios.create({
  baseURL: "https://te-pgh-api.azurewebsites.net/api",
  headers: {
    common: {
      APIKey: '03002'
    }
  }
});

export default {

  getBoards() {
    return http.get('/boards');
  },

  addBoard(newBoard) {
    return http.post('/boards', newBoard);
  },

  deleteBoard(boardID) {
    return http.delete(`/boards/${boardID}`);
  },

  getCards(boardID) {
    return http.get(`/boards/${boardID}`)
  },

  getCard(cardID) {
    return http.get(`/cards/${cardID}`)
  },

  addCard(card) {
    return http.post('/cards', card);
  },

  updateCard(card) {
    return http.put(`/cards/${card.id}`, card);
  },

  deleteCard(cardID) {
    return http.delete(`/cards/${cardID}`);
  }

}
