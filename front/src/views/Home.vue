<template>
  <div class="home">
    <MainTab/>
  </div>
</template>

<script>
// @ is an alias to /src
import MainTab from '@/components/MainTab.vue'

export default {
  name: 'Home',
  components: {
    MainTab
  }
}
</script>
