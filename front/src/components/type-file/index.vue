<template>
  <div class="type-file-body">
    <div class="b-icon type-file-icon"
         :class="[folder ? 'b-icon_type_folder' : 'b-icon_type_file', 'b-icon_ext_' + suffix]">
      {{ folder ? '' : suffix.charAt(0) }}
    </div>
    <span class="type-file-name" @click="onFileNameClick">{{ fileName }}</span>
  </div>
</template>

<script>
export default {
  name: 'type-file',
  props: {
    folder: {
      type: Boolean,
      default: () => false
    },
    fileName: {
      type: String,
      default: () => ''
    }
  },
  computed: {
    suffix () {
      const arr = this.fileName.split('.')
      if (arr.length < 2) {
        return 't'
      }
      return arr[arr.length - 1]
    }
  },
  methods: {
    onFileNameClick () {
      this.$emit('onFileNameClick')
    }
  }
}
</script>

<style lang="less" scoped>
.type-file-body {
  display: inline-flex;
  align-items: center;

  .type-file-icon {
    font-size: 6px;
    margin: 0;
    user-select: none;
  }

  .type-file-name {
    font-size: 14px;
    margin-left: 5px;
    cursor: pointer;
  }
}
</style>
