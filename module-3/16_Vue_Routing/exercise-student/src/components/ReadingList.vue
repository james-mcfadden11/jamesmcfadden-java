<template>
  <div class="book-container">

    <router-link :to="{name: 'book-details', params: {isbn: book.isbn} }" v-for="book in $store.state.books" v-bind:key="book.isbn">
      <book-card v-bind:book="book" />
    </router-link>
  </div>
</template>

<script>
import BookCard from '@/components/BookCard.vue';

export default {
    name: 'reading-list',
    props: ['book'],
    components: {
        BookCard
    }
}
</script>

<style>
.book-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>