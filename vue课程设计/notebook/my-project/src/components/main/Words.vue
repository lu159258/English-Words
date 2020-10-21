<template>
    <vue-drawer-layout
            ref="drawerLayout">
        <div class="drawer" slot="drawer">
            <div class="text">
                <p class="user">{{name}}</p>
                <ul class="drawerList">
                  <li @click="shops">
                    <img src="../../assets/shop.png" width="15px"/>
                    购买书籍
                  </li>
                  <li @click="locals">
                    <img src="../../assets/local.png" width="15px"/>
                    收获地址
                  </li>
                  <li @click="fanyi">
                    <img src="../../assets/tranlate.png" width="15px"/>
                    翻译
                  </li>
                  <li @click="goStudy">
                    <img src="../../assets/study.png" width="15px"/>
                    学习
                  </li>

                    <li @click="modifyPassword">
                        <img src="../../assets/password.png" width="15px"/>
                        修改密码
                    </li>
                    <li @click="quit">
                        <img src="../../assets/exit.png" width="20px"/>
                        安全退出
                    </li>
                </ul>
            </div>
            <a href="javascript:void(0)" class="close"
               @click="handleToggleDrawer">
                <img src="../../assets/return.png" width="18px"/>&nbsp;返回
            </a>
        </div>
        <div class="content" slot="content" ref="viewBox">
            <Header :title="title"></Header>
            <div class="section">
                <ul class="list" v-for="(item,index) in wordAttr">
                    <li >
                        <div>
                            <div>
                                <span id="words">{{item.word}}</span>
                                <span class="syllable">[{{item.speak}}]</span></div>
                            <p class="chinese">{{item.translate}}</p>
                        </div>
                        <div class="btngroud">
                            <button v-on:click="del(item.id)" class="delbtn">删除</button>
                            <button v-on:click="update(item)" class="modifybtn">修改</button>
                          <img v-on:click="read(item.word)" src="../../assets/laba.png" alt="小喇叭" width="20px" height="20px" style="float: right;margin-top: 7px">

                        </div>
                    </li>
                </ul>

            </div>
            <a href="javascript:void(0)" class="btn"
               @click="handleToggleDrawer">
                <img src="../../assets/menu.png" width="20px"/>
            </a>
        </div>
    </vue-drawer-layout>
</template>

<script>
import {setCookie, getCookie, delCookie} from '../../assets/js/cookie.js'
import Header from '../header/header'
import Book from '../book/Book'
import Location from '../book/Location'
import Translate from '../translate/Translate'
import Study from '../study/Study'
// eslint-disable-next-line no-unused-vars
export default {
  name: 'Words',
  components: {
    Header,
    Book,
    Location,
    Translate,
    Study

  },
  inject: ['reload'],
  data () {
    return {
      updWord: {
        id: 0,
        word: '',
        pronounce: '',
        description: ''
      },
      words: [],
      count: 0,
      page: 1,
      num: null,
      name: '',
      title: '我的单词本', // 导航标题内容
      dom: '',
      wordAttr: [] // 将获取到的单词列表存到这个数组
    }
  },
  mounted () {
    let uname = getCookie('userName')
    this.name = uname
    if (uname == '') {
      this.$router.push('/')
    }
    let _this = this
    // 设置一个开关来避免重负请求数据
    let sw = true
    this.dom = this.$refs.viewBox

    // 注册scroll事件并监听，页面是否滚动到底部
    this.dom.addEventListener('scroll', function () {
      // console.log(document.documentElement.clientHeight + '-&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;' + window.innerHeight); // 可视区域高度
      if (document.body.scrollTop + window.innerHeight >= document.body.offsetHeight) {
        // 如果开关打开则加载数据
        if (sw == true) {
          // 将开关关闭
          sw = false
          _this.page++
          _this.show(_this.page)
        }
      }
    })
  },
  methods: {
    /**
             * 修改密码的方法
             *
             **/
    modifyPassword: function () {
      this.$router.push({path: '/UpdatePassword'})
    },

    /**
             * 弹出左侧菜单的方法
             *
             **/
    handleToggleDrawer: function () {
      this.$refs.drawerLayout.toggle()
    },

    /**
             * 退出当前程序
             *
             **/
    quit () {
      this.$http.jsonp('http://app.sencha.com.cn/soya/apps/testdb/server/?action=user.logout')
      delCookie('userName')
      this.$router.push('/')
    },
    goStudy () {
      this.$router.push({path: '/Study'})
    },

    shops () {
      this.$router.push({path: '/Book'})
    },

    locals () {
      this.$router.push({path: '/Location'})
    },
    fanyi () {
      this.$router.push({path: '/Translate'})
    },

    /**
             * 获取数据
             *
             **/
    show: function () {
      let url = 'http://localhost:7000/usermanage/words/list'
      this.axios.get(url).then((res) => {
        console.log(res)
        this.wordAttr = res.data.page
      }).catch((res) => {
        console.log(res)
        // this.$message({ message: '获取失败', type: 'warning' })
      })
    },
    /**
             * 删除当前的单词信息
             *
             **/
    del: function (id) {
      console.log(id)
      let url = 'http://localhost:7000/usermanage/words/delete?ids=' + id
      this.axios.get(url).then((res) => {
        alert('删除成功')
        this.reload()
      }).catch((res) => {
        alert('删除失败')
      })
      this.$forceUpdate()
    },
    read: function (word) {
      let url = '/api/dictvoice?audio=' + word
      let audio = new Audio()
      audio.src = 'http://dict.youdao.com/dictvoice?audio=' + word
      audio.play()
    },

    /**
             * 修改单词方法
             *
             **/
    update: function (item) {
      console.log(item)

      this.$router.push({
        name: 'UpdWord',
        params: {
          item: item
        }
      })
    }

  },
  created () {
    this.show() // 默认显示第一页的内容
  }
}
</script>

<style scoped>
    .user {
        height: 100px;
        line-height: 100px;
        font-size: 20px;
        font-weight: bold;
    }

    .section {
        margin-top: 55px;
        margin-bottom: 40px;
    }

    .list li {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
        background: #ffffff;
        border-radius: 3px;
        margin: 0 10px;
        padding: 12px;
        text-align: left;
        border: 1px #eee solid;
        margin-top: 12px;

    }

    .content {
        height: 100%;
        overflow: auto;
    }

    .list li #words {
        font-size: 16px;
        font-weight: bold;
        margin-right: 8px;
    }

    .list li .syllable {
        font-size: 12px;
        color: #666;
    }

    .list li .chinese {
        font-size: 12px;
        color: #666;
    }

    .list li .delbtn {
        border-radius: 20px;
        width: 48px;
        height: 19px;
        line-height: 16px;
        color: #41b883;
        border: 1px #41b883 solid;
        font-size: 10px;
        text-align: center;
        display: inline-block;
    }

    .list li .modifybtn {
        border-radius: 20px;
        width: 48px;
        height: 19px;
        line-height: 16px;
        color: #ffffff;
        border: 1px #41b883 solid;
        background: #41b883;
        font-size: 10px;
        text-align: center;
        display: inline-block;
    }

    .btn {
        position: fixed;
        left: 10px;
        top: 0;
        top: 12px;
        z-index: 2;
    }

    .btngroud {
        flex: 0 0 100px;
    }

    .drawerList li {
        display: flex;
        align-items: center;
        margin-left: 20px;
        line-height: 45px;
    }

    .drawerList li img {
        margin-right: 10px;
    }

    button {
        background: none;
    }

    .close {
        position: fixed;
        bottom: 10px;
        right: 35%;
        color: #41b883;
        text-decoration: none;
        font-size: 14px;
        align-items: center;
        display: flex;
        font-weight: bold;

    }

    h1, h2 {
        font-weight: normal;
        text-align: center;
    }

    ul li {
        list-style: none;
    }

    .drawer {
        height: 100%;
    }

    .text {
        width: 70%;
        height: 100%;
        background-color: #ffffff;
        box-shadow: 3px 3px 6px 6px #888888;
        font-family: 微软雅黑;
        font-size: 2.1vh;
    }

    .button {

        display: flex;
        flex-direction: row;

        padding-left: 50%;
        padding-top: 2%;
    }

</style>
