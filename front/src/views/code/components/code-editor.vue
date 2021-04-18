<template>
  <monaco-editor :code="code" @update:code="$emit('update:code', $event)" :theme="theme" :language="language"
                 :editorOptions="options" :srcPath="srcPath"></monaco-editor>
</template>

<script>
import MonacoEditor from '@/components/monaco-editor'

export default {
  name: 'code-editor',
  props: {
    language: String,
    theme: String,
    code: String
  },
  components: {
    MonacoEditor
  },
  data () {
    return {
      editor: null,
      options: {},
      srcPath: '/monaco-editor/min'
    }
  },
  methods: {},
  watch: {
    code (value) {
    }
  }
}
</script>

<style scoped>

</style>
