<template>
  <form v-on:submit.prevent="addNewBook" class="new-book-form">
      <label for="title">Title:</label>
      <input type="text" class="title-input" id="title" v-model="newBook.title">
      <br>
      <br>

      <label for="author">Author:</label>
      <input type="text" class="author-input" id="author" v-model="newBook.author"> 
      <br>
      <br>

      <label for="isbn">ISBN:</label>
      <input type="text" class="isbn-input" id="isbn" v-model="newBook.isbn">
      <br>
      <br>

      <input type="submit" value="Save" />
  </form>
</template>

<script>
export default {
    data() {
        return {
          newBook: {
            title: '', 
            author: '',
            isbn: '', 
            read: false
      },
    };
  },
    methods: {
        addNewBook() {
            this.$store.commit('ADD_NEW_BOOK', this.newBook);
            this.newBook = {};
        }
    }
}
</script>

<style>

</style>