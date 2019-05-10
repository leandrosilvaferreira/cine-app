
<template>
  <v-card>
    <v-bottom-nav color="secondary" class="darken-2" :value="true" dark app>
      <v-btn
        dark
        v-for="link in links"
        :key="link.anchorText"
        :href="link.href"
        :target="link.target"
      >
        <span>{{ link.anchorText }}</span>
        <v-icon>{{ link.icon }}</v-icon>
      </v-btn>
    </v-bottom-nav>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      links: [
        {
          anchorText: 'Github',
          icon: 'fab fa-github',
          href: 'https://github.com/leandrosilvaferreira',
          nuxt: false,
          target: '_blank'
        },
        {
          anchorText: 'LinkedIn',
          icon: 'fab fa-linkedin',
          href: 'https://www.linkedin.com/in/leandrosilvaferreira/',
          nuxt: false,
          target: '_blank'
        },
        {
          anchorText: 'Twitter',
          icon: 'fab fa-twitter',
          href: 'https://twitter.com/evolusiti',
          nuxt: false,
          target: '_blank'
        },
        {
          anchorText: 'Contact',
          icon: 'mail',
          href: 'mailto:leandrosilvaferreira@gmail.com',
          nuxt: false,
          target: '_blank'
        }
      ]
    }
  }
}
</script>