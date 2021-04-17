<template>
  <div style="border: 1px solid black">
    <monaco-editor theme="vs" language="typescript" :editorOptions="options"></monaco-editor>
  </div>
</template>

<script>
import MonacoEditor from 'vue-monaco-editor'

export default {
  name: 'code-editor',
  components: {
    MonacoEditor
  },
  data () {
    return {
      options: {
        automaticLayout: true
      }
    }
  }
}
</script>

<style scoped>

</style>
