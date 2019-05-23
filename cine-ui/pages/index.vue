<template>
  <div id="app">
    <v-app id="inspire">
      <v-container v-if="loading">
        <div class="text-xs-center">
          <v-progress-circular indeterminate :size="150" :width="8" color="orange"></v-progress-circular>
        </div>
      </v-container>

      <v-layout justify-center my-5 v-else>
        <v-flex sm12 md12 lg8>
          <v-flex xs12 text-xs-center>
            <v-text-field
              flat
              solo-inverted
              prepend-icon="search"
              label="Search for a movie"
              v-model="search"
              @input="typing = true"
            ></v-text-field>
          </v-flex>

          <v-flex xs12 text-xs-left>
            <h4 class="display-1 font-weight-bold font-italic title-movies">{{title_movies}}</h4>
          </v-flex>

          <v-card>
            <v-flex xs12 text-xs-center>
              <v-pagination v-model="page" :length="total_pages" :total-visible="7"></v-pagination>
            </v-flex>
          </v-card>

          <v-card style="background-color: lightgray;">
            <v-container fluid grid-list-xl>
              <v-layout row wrap justify-center>

                <v-flex v-if="movies.length === 0" xs12>
                  <p class="subheading">No movies found for your search! Try again with other keyword :)</p>
                </v-flex>


                <v-flex v-for="movie in movies" :key="movie.id" xs12 sm12 md6 lg6 xl4 mx-auto>
                  <MovieCard
                    :id="movie.id"
                    :title="movie.title"
                    :poster="fixPosterPath(movie.poster_path)"
                    :release-date="movie.release_date"
                    :vote-average="movie.vote_average"
                    :genres="movie.genres"
                  />
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>

          <v-card>
            <v-flex xs12 text-xs-center>
              <v-pagination v-model="page" :length="total_pages" :total-visible="7"></v-pagination>
            </v-flex>
          </v-card>
        </v-flex>
      </v-layout>
    </v-app>
  </div>
</template>




<script>
import Vuetify from 'vuetify'
import axios from 'axios'
import lodash from 'lodash'
import MovieCard from '~/components/Movie/MovieCard.vue'

export default {
  components: {
    MovieCard
  },
  data: () => ({
    dialog: false,
    movies: [],
    loading: true,
    page: 1,
    total_pages: 0,
    search: '',
    isSearch: false,
    title_movies: 'Upcoming Movies'
  }),
  mounted() {
    this.fetchUpcomingMovies()
  },
  watch: {
    page: function(newVal, oldVal) {
      this.loading = true
      if (this.isSearch) {
        this.searchMovies()
      } else {
        this.fetchUpcomingMovies()
      }
    },
    search: _.debounce(function() {
      if (this.search != '') {
        this.isSearch = true
        this.page = 1
        this.searchMovies()
      }
    }, 3000)
  },
  methods: {
    fetchUpcomingMovies() {
      axios
        .get('http://localhost:8080/movie/upcoming?page=' + this.page)
        .then(response => {
          this.page = response.data.page
          this.total_pages = response.data.total_pages
          this.movies = response.data.results
          this.title_movies = 'Upcoming Movies'
          this.loading = false
        })
        .catch(error => {
          console.log(error)
        })
    },
    searchMovies() {
      axios
        .get(
          'http://localhost:8080/movie/search?query=' +
            this.search +
            '&page=' +
            this.page
        )
        .then(response => {
          this.page = response.data.page
          this.total_pages = response.data.total_pages
          this.movies = response.data.results
          this.title_movies = 'Results from search for ' + this.search + '...'
          this.loading = false
        })
        .catch(error => {
          console.log(error)
        })
    },
    fixPosterPath(path) {
      return _.isNil(path) ? 'null' : path
    }
  }
}
</script>

<!-- color: rgb(99, 98, 98); -->

<style scoped>
.title-movies {
  color: #ff9800;
  margin-bottom: 10px;
}
</style>
