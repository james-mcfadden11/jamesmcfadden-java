<template>
  <div>
    <reading-list v-bind:book="myBook"></reading-list>
    <router-link to="/addBook">Add Book to Reading List</router-link>
  </div>
</template>

<script>
import ReadingList from '../components/ReadingList.vue';
export default {
    components: {
        ReadingList
    },
    computed: {
      myBook() {
        const book = this.$store.state.books.find(b => b.isbn == this.$route.params.isbn);
        return book;
      }
    }
}
</script>

<style>

</style>