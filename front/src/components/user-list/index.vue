<template>
  <div>
    <user-item v-for="user of userList" :key="user.id" :user="user"></user-item>
    <el-pagination
      background
      hide-on-single-page
      layout="prev, pager, next"
      :current-page="page"
      :page-size="size"
      @current-change="onPageChange"
      :total="count">
    </el-pagination>
  </div>
</template>

<script>
import UserItem from './user-item'

export default {
  name: 'user-list',
  props: {
    userList: {
      type: Array,
      default () {
        return []
      }
    },
    page: Number,
    size: Number,
    count: Number
  },
  components: {
    UserItem
  },
  methods: {
    onPageChange (page) {
      this.$emit('page-change', page)
    }
  }
}
</script>

<style scoped>

</style>
