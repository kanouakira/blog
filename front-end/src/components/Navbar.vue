<template>
  <div>
    <el-menu
      @select="handleSelect">
<!--      <el-menu-item index="1">
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item> -->
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-edit"></i>
          <span>笔记</span>
        </template>
        <el-menu-item-group v-for="(category, index) in categories" :key="index" :title="category.title">
          <el-menu-item v-for="(tag, index) in category.tags" :key="index" :index="tag.index">{{tag.index}}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="3">
        <i class="el-icon-document"></i>
        <span slot="title">关于我</span>
      </el-menu-item>
    </el-menu>
    <el-card style="margin-top: 15px;" v-if="rank.length > 0" shadow="never">
      <h3>每周热榜</h3>
      <p v-for="(post, index) in rank" @click="redirectToDetail(post.id, post.views)">
        {{index+1}}.{{post.title}}
      </p>
    </el-card>
  </div>
</template>

<script>
  import VueSticky from 'vue-sticky'
  import store from '../store.js'
  export default {
    name: 'Navbar',
    data() {
      return {
        sharedState: store.state,
        categories: [],
        rank: []
      }
    },
    directives:{
      'sticky': VueSticky,
    },
    methods: {
      handleSelect(key, keyPath){
        // console.log(key, keyPath);
        switch(keyPath[0]){
          case "1":
            this.$router.push({path:`/`})
            break;
          case "2":
            this.$router.push({path:`/`, query:{tag:key}})
            break;
          case "3":
            this.$router.push({path:`/aboutme`})
            break;
        }
      },
      // 获取标签tag
      getCategories(){
        const path=`/categories`
        this.$axios.get(path)
          .then(res => {
            // console.log(res.data)
            this.categories = []
            for(var i=0;i<res.data.data.length;i++){
              this.category = {
                index: res.data.data[i].id,
                title: res.data.data[i].category,
                tags: []
              }
              for(var j=0;j<res.data.data[i].tags.length;j++){
                // var index = res.data.data[i].id+"-"+res.data.data[i].tags[j].id
                var name = res.data.data[i].tags[j].name
                // console.log(index+" >>> "+name)
                this.category.tags.push({
                  index: name,
                })
              }
              this.categories.push(this.category)
            }
          })
          .catch(error => {
            console.error(error)
          })
      },
      // 获取每周阅读量排行
      getWeekRank(){
        const path = `/posts/rank`
        this.$axios.get(path)
          .then(res => {
            this.rank = res.data.data
          })
          .catch(error => {
            console.error(error)
          })
      },
      addPostViews(post_id, views) {
        const path = `/posts/${post_id}/addViews`
        this.$axios.put(path)
          .then((res) => {
            // console.log(res)
          })
          .catch((error) => {
            console.error(error);
          })
      },
      redirectToDetail(id, views){
        this.addPostViews(id, views)
        this.$router.push({path:`/post/${id}`})
      }
    },
    created() {
      this.getCategories()
      this.getWeekRank()
    }
  }
</script>

<style>
</style>
