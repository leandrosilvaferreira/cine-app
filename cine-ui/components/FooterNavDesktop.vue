<template>
  <v-footer
    height="auto"
    color="secondary darken-1"
  >
    <v-layout
      justify-center
      row
      wrap
    >

      <v-btn 
        v-for="link in links"
        :href="link.href"
        :key="link.anchorText"
        color="white"
        flat
        :nuxt="link.nuxt"
        :target="link.target"
      >
        
        <v-icon>{{ link.icon }}</v-icon>
        <span class="ml-2">{{ link.anchorText }}</span>
        
      </v-btn>
      <v-flex
        grey
        lighten-2
        py-3
        text-xs-center
        dark
        xs12
      >
        &copy;2019 — <strong>LSF</strong>
      </v-flex>
    </v-layout>
  </v-footer>
</template>

<script>
export default {
    data () {
        return {
            links: [
              {
              'anchorText': 'Github',
              'icon': 'fab fa-github',
              'href': 'https://github.com/leandrosilvaferreira',
              'nuxt': false,
              'target': '_blank'
            },
              {
              'anchorText': 'LinkedIn',
              'icon': 'fab fa-linkedin',
              'href': 'https://www.linkedin.com/in/leandrosilvaferreira/',
              'nuxt': false,
              'target': '_blank'
            },
            {
              'anchorText': 'Twitter',
              'icon': 'fab fa-twitter',
              'href': 'https://twitter.com/evolusiti',
              'nuxt': false,
              'target': '_blank'
            },
            {
              'anchorText': 'Contact',
              'icon': 'mail',
              'href': 'mailto:leandrosilvaferreira@gmail.com',
              'nuxt': false,
              'target': '_blank'
            }
          ]
        }
    }
}
</script>