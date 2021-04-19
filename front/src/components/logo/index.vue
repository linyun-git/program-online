<template>
  <a v-if="linked" href="/" :style="{'font-size': fontSize, 'display': display}" class="logo"></a>
  <div v-else :style="{'font-size': fontSize, 'display': display}" class="logo"></div>
</template>

<script>
const FONT_SIZE = {
  small: '20px',
  normal: '60px',
  large: '100px'
}

export default {
  name: 'logo',
  props: {
    size: {
      type: String,
      default: () => 'small'
    },
    block: {
      type: Boolean,
      default: () => false
    },
    linked: {
      type: Boolean,
      default: () => true
    }
  },
  computed: {
    fontSize () {
      return FONT_SIZE[this.size]
    },
    display () {
      return this.block ? 'block' : 'inline'
    }
  }
}
</script>

<style lang="less" scoped>
</style>
