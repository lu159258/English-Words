<template>
  <div id="f">
    <Header :title="title" :back="back"></Header>
  <div class="wordStudy">


      <ul v-for="(item,index) in wordAttr" :key="index">
        <li id="one">单词:{{item.word}}</li>
        <li>翻译:{{item.translate}}</li>
        <li>例句:{{item.exp}}</li>
      </ul>
    <button @click="sub" :disabled="currentPage<=1">上一个</button>
    <button @click="add" :disabled="currentPage>=total">下一个</button>
  </div>
  </div>
</template>

<script>
/* 单词学习 */

import Header from "../header/header";


export default {
  name: 'Study',
  components: {
    Header,
  },
  data () {
    return {
      title: '学习',
      back: '/Words',
      currentPage: 1, // 当前页
      pageSize: 1,
      wordAttr: [], // 将获取到的单词列表存到这个数组
      total: 0
    }
  },
  methods: {
    // 显示单词解释
    add: function () {
      this.currentPage++
      if (this.currentPage < this.total + 1) {
        this.show()
      }
    },
    sub: function () {
      if (this.currentPage === 0) {
        this.currentPage = 0
        this.show()
      } else {
        this.currentPage -= 1
        this.show()
      }
    },

    /* 分页显示单词 */
    show: function (currentPage) {
      var url = 'http://localhost:7000/usermanage/words/listPages?currentPage=' + this.currentPage + '&pageSize=' + 1
      this.axios.get(url).then((res) => {
        this.wordAttr = res.data.page.records // 获取第一页数据
        this.total = res.data.page.total // 获取总记录数
      }).catch((res) => {
        alert('获取失败')
        // this.$message({message: '获取失败', type: 'warning' })
      })
    },

  },
  created () {
    this.show()
  }

}
</script>

<style scoped>
  /*ul
  {
    list-style-type:none;
    padding:0px;
    margin:0px;
  }*/
  #f{
    margin-top: 55px;
  }

   li
  {
     display: flex;
     align-items: center;
     margin-left: 550px;
     line-height: 45px;
     font-family: 微软雅黑;
     font-size: 3vh;

  }
  #one{
    color: red;
  }

</style>
