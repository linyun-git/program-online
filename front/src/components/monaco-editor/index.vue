<template>
  <div></div>
</template>

<script>
import monacoLoader from './monaco-loader'
var debounce = require('lodash.debounce')

export default {
  name: 'monaco-editor',
  props: {
    code: {
      type: String,
      default: '// code \n'
    },
    srcPath: { type: String },
    language: {
      type: String,
      default: 'javascript'
    },
    theme: {
      type: String,
      default: 'vs-dark'
    }, // vs, hc-black
    options: {
      type: Object,
      default: () => {
      }
    },
    changeThrottle: {
      type: Number,
      default: 0
    }
  },
  mounted () {
    this.fetchEditor()
  },
  unmounted () {
    this.destroyMonaco()
  },
  computed: {
    editorOptions () {
      return Object.assign({}, this.defaults, this.options, {
        value: this.code,
        language: this.language,
        theme: this.theme
      })
    }
  },
  data () {
    return {
      defaults: {
        selectOnLineNumbers: true,
        roundedSelection: false,
        readOnly: false,
        cursorStyle: 'line',
        automaticLayout: true,
        glyphMargin: true
      }
    }
  },
  watch: {
  },
  methods: {
    editorHasLoaded (editor, monaco) {
      this.editor = editor
      this.monaco = monaco
      this.editor.onDidChangeModelContent(event =>
        this.codeChangeHandler(editor, event)
      )
      this.$emit('mounted', editor)
    },
    codeChangeHandler: function (editor) {
      if (this.codeChangeEmitter) {
        this.codeChangeEmitter(editor)
      } else {
        this.codeChangeEmitter = debounce(
          function (editor) {
            this.$emit('update:code', editor.getValue())
          },
          this.changeThrottle
        )
        this.codeChangeEmitter(editor)
      }
    },
    fetchEditor () {
      monacoLoader.load(this.srcPath, this.createMonaco)
    },
    createMonaco () {
      this.editor = window.monaco.editor.create(this.$el, this.editorOptions)
      this.editorHasLoaded(this.editor, window.monaco)
    },
    destroyMonaco () {
      if (typeof this.editor !== 'undefined') {
        this.editor.dispose()
      }
    }
  }
}
</script>

<style scoped>

</style>
