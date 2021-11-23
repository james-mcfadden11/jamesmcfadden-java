<template>
  <div class="main">
      <h2>Product Reviews For {{ name }}</h2>

      <p class="description">{{ description }}</p>

      <div class="well-display">
          <div class="well">
              <span class="amount">{{ averageRating }}</span>
              Average rating
          </div>
          <div class="well">
              <span class="amount">{{ numberOfOneStarReviews }}</span>
              1 Star reviews
          </div>
          <div class="well">
              <span class="amount">{{ numberOfTwoStarReviews }}</span>
              2 Star reviews
          </div>
          <div class="well">
              <span class="amount">{{ numberOfThreeStarReviews }}</span>
              3 Star reviews
          </div>
          <div class="well">
              <span class="amount">{{ numberOfFourStarReviews }}</span>
              4 Star reviews
          </div>
          <div class="well">
              <span class="amount">{{ numberOfFiveStarReviews }}</span>
              5 Star reviews
          </div>
      </div>

      <div class="review" v-bind:class="{ favorited: r.isFavorited }" v-for="r in reviews" v-bind:key="r.id">
          <h4>{{ r.reviewer }}</h4>
          <div class="rating">
              <img class="review" src="../assets/star.png" alt="" v-bind:title="r.rating" v-for="n in r.rating" v-bind:key="n">
          </div>
          <h3>{{ r.title }}</h3>
          <p>{{ r.review }}</p>

          <p>Favorited? <input type="checkbox" v-model="r.isFavorited"></p>
      </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            name: "Cigar Parties for Dummies",
            description: "Host and plan the perfect cigar party.",
            reviews: [
                {
                    id: 1,
                    reviewer: 'Malcolm Gladwell',
                    title: 'What a book!',
                    rating: 3,
                    review: 'It is certainly a book.',
                    isFavorited: false
                }, 
                {
                    id: 2,
                    reviewer: 'Jim Gladwell',
                    title: 'What a bad book!',
                    rating: 4,
                    review: 'It is most certainly a book.',
                    isFavorited: true
                }
            ]
        }
    },

    computed: {
        averageRating() {
            let sum = this.reviews.reduce((accumulator, currentValue) => accumulator + currentValue.rating, 0);
            return sum / this.reviews.length;
        },

        numberOfOneStarReviews() {
            const filteredReviews = this.reviews.filter(r => r.rating === 1);
            return filteredReviews.length;
        },

        numberOfTwoStarReviews() {
            const filteredReviews = this.reviews.filter(r => r.rating === 2);
            return filteredReviews.length;
        },
        
        numberOfThreeStarReviews() {
            const filteredReviews = this.reviews.filter(r => r.rating === 3);
            return filteredReviews.length;
        },
        
        numberOfFourStarReviews() {
            const filteredReviews = this.reviews.filter(r => r.rating === 4);
            return filteredReviews.length;
        },
        
        numberOfFiveStarReviews() {
            const filteredReviews = this.reviews.filter(r => r.rating === 5);
            return filteredReviews.length;
        }
    }
}
</script>


<style scoped>
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

div.main div.review {
  border: 1px black solid;
  border-radius: 6px;
  padding: 1rem;
  margin: 10px;
}

div.main div.review div.rating {
  height: 2rem;
  display: inline-block;
  vertical-align: top;
  margin: 0 0.5rem;
}

div.main div.review div.rating img {
  height: 100%;
}

div.main div.review p {
  margin: 20px;
}

div.main div.review h3 {
  display: inline-block;
}

div.main div.review h4 {
  font-size: 1rem;
}

div.main div.review.favorited {
    background-color: lightyellow;
}

</style>