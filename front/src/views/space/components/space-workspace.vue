<template>
  <div>
    <div class="button-container">
      <a v-for="button in buttons" :class="{active: activeButton === button.key}"
         @click="onButtonClick(button.key)"
         :key="button.key" class="link-button">{{
          button.label
        }}</a>
    </div>
    <workspace-list :workspace-list="list"></workspace-list>
  </div>
</template>

<script>
export default {
  name: 'space-workspace',
  data () {
    return {
      buttons: [
        {
          label: '所有',
          key: 'all'
        },
        {
          label: '公开的',
          key: 'public'
        },
        {
          label: '私有的',
          key: 'private'
        }
      ],
      activeButton: 'all',
      list: []
    }
  },
  methods: {
    onButtonClick (key) {
      if (this.activeButton === key) {
        return
      }
      this.activeButton = key
    },
    query () {
      const params = {
        uid: this.uid
      }
      this.$api.workspace.listByUid(params)
        .then(rep => {
          this.list = rep.data
        })
        .catch(({ msg }) => this.$message.error(msg))
    }
  },
  computed: {
    uid () {
      return parseInt(this.$route.params.uid, 10)
    }
  },
  created () {
    this.query()
  }
}
</script>

<style lang="less" scoped>
.button-container {
  padding-top: 20px;

  .link-button {
    font-size: 14px;
    cursor: pointer;
  }

  .link-button.active {
    color: #409EFF;
  }

  > *:not(:last-child) {
    margin-right: 20px;
  }

}
</style>
