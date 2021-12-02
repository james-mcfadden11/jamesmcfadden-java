<template>
  <book-details v-bind:book="myBook" />
</template>

<script>
import BookDetails from '../components/BookDetails.vue';
export default {
  name: 'book-details-view',
  components: {
    BookDetails
  },
  computed: {
    myBook() {
      const book = this.$store.state.books.find(b => b.isbn == this.$route.params.isbn);
      return book;
    }
  }
}
</script>
