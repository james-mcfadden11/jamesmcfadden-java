<template>
  <div id="sideNav">
    <h1>My Kanban Boards</h1>
    <div class="boards">
      <div class="status-message error" v-show="errorMsg !== ''">{{errorMsg}}</div>
      <div class="loading" v-if="isLoading">
        <img src="../assets/ping_pong_loader.gif" />
      </div>
      <router-link :to="{ name: 'Board', params: { id: board.id } }"
        class="board"
        v-for="board in this.$store.state.boards"
        v-bind:key="board.id"
        v-bind:style="{ 'background-color': board.backgroundColor }"
        v-else
        tag="div"
      >
        {{ board.title }}
      </router-link>
      <button class="btn addBoard" v-if="!isLoading && !showAddBoard" v-on:click="showAddBoard = !showAddBoard">Add Board</button>
      <form v-if="showAddBoard" v-on:submit.prevent="saveNewBoard">
        Board Title:
        <input type="text" class="form-control" v-model="newBoard.title" />
        Background Color:
        <input type="text" class="form-control" v-model="newBoard.backgroundColor" />
        <button type="submit" class="btn btn-submit">Save</button>
        <button class="btn btn-cancel" v-on:click="showAddBoard = !showAddBoard">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import boardsService from '../services/BoardService';

export default {
  data() {
    return {
      isLoading: true,
      showAddBoard: false,
      newBoard: {
        title: '',
        backgroundColor: this.randomBackgroundColor()
      },
      errorMsg: ''
    };
  },
  created() {
    this.retrieveBoards();
  },
  methods: {
    retrieveBoards() {
      boardsService
        .getBoards()
        .then(response => {
          this.$store.commit("SET_BOARDS", response.data);
          this.isLoading = false;
        })
        .catch(error => {
          if (error.response) {
            this.errorMsg = `Error retrieving boards. Response received was ' ${error.response.statusText}'.`;                "'.";
          } else if (error.request) {
            this.errorMsg = "Error retrieving boards. Server could not be reached.";
          } else {
            this.errorMsg = "Error retreiving boards. Request could not be created.";
          }

          this.isLoading = false;
        });
    },
    saveNewBoard() {


      /*
        - need a board object to save
        - save it to the database in Azure (API call)
        - update the store with the added board
      */

    this.isLoading = true;
    boardsService
      .addBoard(this.newBoard)
      .then(response => {
        if(response && response.status == 201) {
          // update the store with the added board
          this.showAddBoard = false; 
          this.resetBoardForm();
          setTimeout(() => {
            this.retrieveBoards();
            this.isLoading = false;
          }, 2000);
          
        }
      })
      .catch(error => {
        // log the error
        if (error.response) {
          this.errorMsg = "Error submitting new board. Response received was '" + error.response.statusText + "'.";

        } else if (error.request) {
          this.errorMsg = "Error submitting new board. Server could not be reached.";

        } else {
          this.errorMsg = "Error submitting new board. Request could not be created.";
        }

        this.isLoading = false;
      });

    },
    randomBackgroundColor() {
      return "#" + this.generateHexCode();
    },
    generateHexCode() {
      var bg = Math.floor(Math.random()*16777215).toString(16);
      if (bg.length !== 6) bg = this.generateHexCode();
      return bg;
    },
    resetBoardForm() {
      this.newBoard = {
        title: '',
        backgroundColor: this.randomBackgroundColor()
      };
    }
  }
};
</script>

<style scoped>
div#sideNav {
  height: 100%;
  width: 20%;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  padding-top: 20px;
  padding-bottom: 20px;
  overflow-x: hidden;
  border-right: solid lightgrey 1px;
}

h1 {
  text-align: center;
}

.boards {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
.board {
  color: #f7fafc;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 10px;
  text-align: center;
  cursor: pointer;
  width: 60%;
}
.addBoard {
  color: #f7fafc;
  border-radius: 10px;
  background-color: #28a745;
  font-size: 16px;
  width: 60%;
  margin: 10px;
  padding: 20px;
  cursor: pointer;
}
.form-control {
  margin-bottom: 10px;
}
.btn {margin-bottom: 35px;}
.loading {
  flex: 3;
}
.board:hover:not(.router-link-active), .addBoard:hover {
  font-weight: bold;
}
.router-link-active {
  font-weight: bold;
  border: solid blue 5px;
}
</style>
