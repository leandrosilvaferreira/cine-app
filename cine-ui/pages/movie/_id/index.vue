<template>
  <v-container v-if="loading">
    <div class="text-xs-center">
      <v-progress-circular indeterminate :size="150" :width="8" color="orange"></v-progress-circular>
    </div>
  </v-container>

  <v-container v-else grid-list-xl fluid my-5 fundo :style="backdrop">
    <v-container fluid style="background-color: #0a1014; opacity: 0.85; ">
      <v-container style="max-width: 1200px;">
        <v-layout align-center justify-center row wrap style="color: #fff;">
          <v-flex sm12 md8>
            <n-link title="Back" class="back" to="/">
              <v-icon medium color="#777">keyboard_backspace</v-icon>
            </n-link>

            <p>
              <span>{{movie.runtime}} Minutes</span>
              <span class="rated" title="Popularity">{{movie.popularity}}</span>
            </p>

            <p>
              <span class="movie-title">{{movie.title}}</span>
              <span>({{movie.release_date | date }})</span>
            </p>

            <div class="mb-4">
              <p class="sub-title">Overview</p>
              <p>{{movie.overview}}</p>
            </div>

            <!--   <v-layout justify-space-between style="padding: 15px;">
              <div class="mb-4">
                <p class="sub-title">Vote Average</p>
                <p>{{movie.vote_average}}</p>
              </div>
              <div class="mb-4">
                <p class="sub-title">Vote Count</p>
                <p>{{movie.vote_count}}</p>
              </div>
            </v-layout>-->

            <v-layout justify-space-between style="padding: 15px;">
              <div>
                <p class="sub-title">Genre</p>
                <p v-for="genre in movie.genres" :key="genre.id">{{ genre.name }}</p>
              </div>

              <div>
                <p class="sub-title">Vote Average</p>
                <p>{{movie.vote_average}}</p>
              </div>

              <div>
                <p class="sub-title">Vote Count</p>
                <p>{{movie.vote_count}}</p>
              </div>
            </v-layout>
          </v-flex>

          <!-- :class="`elevation-white-${hover ? 24 : 2}`" -->

          <v-flex sm12 md4>
            <v-hover>
              <v-img
                slot-scope="{ hover }"
                :class="hover ? 'elevation-white-24' : ''"
                :aspect-ratio="16/9"
                :src="posterPath"
                max-width="342"
                height="513"
              >
                <template v-slot:placeholder>
                  <v-layout fill-height align-center justify-center ma-0>
                    <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                  </v-layout>
                </template>
              </v-img>
            </v-hover>
          </v-flex>
        </v-layout>
      </v-container>
    </v-container>
  </v-container>

  <!-- <div
    v-else
    class="container mt-8 fundo fluid my-5"
    :style="{backgroundImage: 'url(https://image.tmdb.org/t/p/original' + movie.backdrop_path + ')'}"
  >
    
   
    
    
    <div class="fluid" style="background-color: #0a1014; opacity: 0.85; height: 100%">
      <nuxt-link to="/">
        <div class="back">voltar</div>
      </nuxt-link>

      <div class="movie-detail-container">

        <div class="p-4 width-500">
          <p>
            <span>{{movie.runtime}} Minutes</span>
            <span>{{movie.release_date}}</span>

            <span class="rated">{{movie.popularity}}</span>
          </p>

          <p class="title">{{movie.title}}</p>

          <div class="flex mb-4">
            <span>{{movie.vote_average}}</span>
            <span>{{movie.vote_count}}</span>
          </div>

          <div class="mb-4">
            <p class="sub-title">Plot</p>
            <p>{{movie.overview}}</p>
          </div>

          <div class="flex">
            <div>
              <p class="sub-title">Cast</p>
              <p>actors</p>
            </div>

            <div>
              <p class="sub-title">Genre</p>
              <p v-for="genre in movie.genres" :key="genre.id">{{ genre.name }}</p>
            </div>

            <div>
              <p class="sub-title">Director</p>
              <p>Director</p>
            </div>
          </div>
        </div>

        <v-img
          :aspect-ratio="16/9"
          :src="'https://image.tmdb.org/t/p/w342' + movie.poster_path "
          max-width="342"
          height="513"
        ></v-img>
      </div>
  </div>-->

  <!--    <v-parallax dark src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg">
      <v-layout align-center column justify-center>
        <h1 class="display-2 font-weight-thin mb-3">Vuetify.js</h1>
        <h4 class="subheading">Build your application today!</h4>
      </v-layout>
  </v-parallax>-->
  <!-- </div> -->
</template>

<script>
import axios from 'axios'
import lodash from 'lodash'

export default {
  data() {
    return {
      loading: true,
      movie: null
    }
  },
  mounted() {
    axios
      .get(
        'http://localhost:8080/movie/' +
          this.$nuxt._route.params.id +
          '?append_video_to_response=true'
      )
      .then(response => {
        this.movie = response.data
        this.loading = false
      })
      .catch(error => {
        console.log(error)
      })
  },
  computed: {
    posterPath() {
      return _.isNil(this.movie.poster_path)
        ? 'https://via.placeholder.com/342x513'
        : 'https://image.tmdb.org/t/p/w342' + this.movie.poster_path
    },
    backdrop() {
      return _.isNil(this.movie.backdrop_path)
        ? ''
        : 'backgroundImage: url(https://image.tmdb.org/t/p/w1280' +
            this.movie.backdrop_path +
            ')'
    }
  }
}
</script>

// {backgroundImage: 'url(https://image.tmdb.org/t/p/w1280' + movie.backdrop_path + ')'}

<style scoped>
.fundo {
  width: 100%;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: 50% 50%;
  padding: 0px;
}

html {
  font-size: 16px;
}

.movie-title {
  font-size: 45px;
  font-weight: bold;
}

.sub-title {
  font-size: 18px;
  color: #777;
}

.rated {
  padding: 5px;
  background-color: #777;
  color: black;
  margin-left: 5px;
  border-radius: 5px;
}

.back {
  text-decoration: none;
}

.elevation-white-24 {
  box-shadow: 0px 11px 15px -7px rgba(255, 255, 255, 0.2),
    0px 24px 38px 3px rgba(255, 255, 255, 0.14),
    0px 9px 46px 8px rgba(255, 255, 255, 0.12) !important;
}
</style>

