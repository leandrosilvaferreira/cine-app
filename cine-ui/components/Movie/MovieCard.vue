<template>
  <v-hover style="margin-bottom: 20px;">
    <v-card slot-scope="{ hover }" class="mx-auto" color="grey lighten-4" max-width="600">
      <v-img :aspect-ratio="16/9" :src="posterPath">
        <v-expand-transition>
          <div
            v-if="hover"
            class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text align-center"
            style="height: 100%;"
          >
            <div style="text-align: center">
              <nuxt-link class="details-link" :to="'/movie/'+ id">
                <v-icon class="white--text" style="font-size: 56px;">zoom_in</v-icon>Details
              </nuxt-link>
            </div>
          </div>
        </v-expand-transition>
        <template v-slot:placeholder>
          <v-layout fill-height align-center justify-center ma-0>
            <v-progress-circular indeterminate color="orange"></v-progress-circular>
          </v-layout>
        </template>
      </v-img>

      <v-card-text class="pt-4" style="position: relative;">
        <v-btn absolute color="orange" class="white--text" fab large right top :to="'/movie/' + id">
          <h2 title="Vote Average">{{ voteAverage }}</h2>
        </v-btn>

        <div class="font-weight-light grey--text title mb-2">{{ releaseDate | date }}</div>
        <h3 class="display-1 font-weight-light orange--text mb-2">{{ title }}</h3>
        <div class="font-weight-light subtitle mb-2">
          <span v-for="genre in genres" :key="genre.id">{{ genre.name }} </span>
        </div>
      </v-card-text>
    </v-card>
  </v-hover>
</template>


<script>
import lodash from 'lodash'

export default {
  name: 'MovieCard',

  props: {
    id: {
      type: Number,
      required: true
    },
    title: {
      type: String,
      required: true
    },
    poster: {
      type: String,
      required: true
    },
    genres: {
      type: Array,
      required: true
    },
    releaseDate: {
      type: String,
      required: true
    },
    voteAverage: {
      type: Number,
      required: true
    }
  },
  computed: {
    posterPath() {
      return _.isNil(this.poster) || 'null' === this.poster
        ? 'https://via.placeholder.com/500x282'
        : 'https://image.tmdb.org/t/p/w500_and_h282_face' + this.poster
    }
  }
}
</script>



<style scoped>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
}

.details-link {
  text-decoration: none;
  color: white;
}
</style>
