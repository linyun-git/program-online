<template>
  <div class="type-file-body">
    <div class="b-icon type-file-icon" :class="[folder ? 'b-icon_type_folder' : 'b-icon_type_file']">
      .js
    </div>
    <span class="type-file-name">这里是文件名</span>
  </div>
</template>

<script>
export default {
  name: 'type-file',
  props: {
    folder: {
      type: Boolean,
      default: () => false
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
