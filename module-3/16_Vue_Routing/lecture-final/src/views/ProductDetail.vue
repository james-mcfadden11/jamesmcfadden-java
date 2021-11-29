<template>
  <div id="app" class="main">
    <h1>Product Reviews for {{ product.name }}</h1>

     <div class="actions">
      <router-link v-bind:to="{ name: 'products-list' }">Back to Products</router-link>&nbsp;|
      <router-link v-bind:to="{ name: 'add-review', params: { id: product.id } }">Add Review</router-link>
    </div>

    <p class="description">{{ product.description }}</p>
    <div class="well-display">
      <average-summary />
      <star-summary rating="1" />
      <star-summary rating="2" />
      <star-summary rating="3" />
      <star-summary rating="4" />
      <star-summary rating="5" />
    </div>
    <review-list />
  </div>
</template>

<script>
import AverageSummary from "@/components/AverageSummary.vue";
import StarSummary from "@/components/StarSummary.vue";
import ReviewList from "@/components/ReviewList.vue";


export default {
  name: "app",
  components: {
    AverageSummary,
    StarSummary,
    ReviewList
  },

  computed: {
      product() {
        return this.$store.state.products.find(
            p => p.id == this.$store.state.activeProduct
        );
      }
  },

  created() {
      this.$store.commit('SET_ACTIVE_PRODUCT', this.$route.params.productId)
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  max-width: 800px;
  margin: 60px auto 0 auto;
}
div.main {
  margin: 1rem 0;
}
div.main div.well-display {
  display: flex;
  justify-content: space-around;
}

div.main div.well-display div.well {
  display: inline-block;
  width: 15%;
  border: 1px black solid;
  border-radius: 6px;
  text-align: center;
  margin: 0.25rem;
}

div.main div.well-display div.well span.amount {
  color: darkslategray;
  display: block;
  font-size: 2.5rem;
}

div.main div.well-display div.well {
  cursor: pointer;
}
</style>
