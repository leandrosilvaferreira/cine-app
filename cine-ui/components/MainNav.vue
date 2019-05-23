<template>
  <nav>
    <v-toolbar flat dark app color="primary" height="80">
      
      <v-toolbar-title>
        <v-layout align-center row fill-height>
          <a href="/" title="Return To Home">
            <img
              height="75"
              alt
              class="logo"
              style="padding-top: 7px;"
              srcset="@/assets/images/logo_transparent2.png"
            >
          </a>
          <span class="hidden-sm-and-down" style="margin-left:20px">The Largest Source of Movie Information</span>
        </v-layout>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      
    </v-toolbar>
  </nav>
</template>


<style>
@media only screen and (max-width: 960px) {
  .logo {
    position: absolute;
    top: -1px;
    left: 50%;
    transform: translate(-50%, 0%);
  }
}
</style>