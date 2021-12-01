<template>
  <div>
    <h1>{{ card.title }}</h1>
    <p>{{ card.description }}</p>

    <div class="loading" v-if="isLoading">
      <img src="../assets/ping_pong_loader.gif" />
    </div>
    <comments-list v-bind:comments="card.comments" v-else />
  
    <div class="board-actions" v-if="!isLoading">
      <router-link
        v-bind:to="{ name: 'Board', params: { id: $route.params.boardID } }"
        >Back to Board</router-link
      >
    </div>
  </div>
</template>

<script>
import boardService from '@/services/BoardService';
import CommentsList from '@/components/CommentsList.vue';

export default {
  name: "card-detail",
  
  components: {
    CommentsList
  },

  data() {
    return {
      card: {
        title: '',
        description: '',
        status: '',
        comments: []
      },

      isLoading: true
    }
  },

  created() {
    boardService
      .getCard(this.$route.params.boardID, this.$route.params.cardID)
      .then((card) => {
        this.card = card;
        this.isLoading = false;
      })
  }
};
</script>
