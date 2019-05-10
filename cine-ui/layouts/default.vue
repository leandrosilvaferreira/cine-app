<template>
  <v-app class="grey lighten-4">
    <MainNav />
    <v-content>
      <router-view></router-view>
    </v-content>
    <FooterNavMobile class="hidden-sm-and-up"/>
    <FooterNavDesktop class="hidden-xs-only"/>
  </v-app>
</template>

<script>
import MainNav from '@/components/MainNav'
import FooterNavMobile from '@/components/FooterNavMobile'
import FooterNavDesktop from '@/components/FooterNavDesktop'

export default {
  components: { MainNav, FooterNavMobile, FooterNavDesktop  },
  name: 'App',
  data () {
    return {
      
    }
  }
}
</script>