<template>
  <div class="wrapper">
    <Header :title="title" :back="back"></Header>
    <p class="title">录入新单词</p>
    <div class="section">
      <ul>
        <li>
          <span>单词</span>
          <input type="text" v-model="newWord.word" placeholder="请输入一个单词" class="text1"/>
        </li>
        <li>
          <span>发音</span><input type="text" v-model="newWord.speak" placeholder="请输入此单词的发音" class="text1"/>

        </li>
        <li>
          <span>中文</span><input type="text" v-model="newWord.translate" placeholder="请输入汉语意思" class="text1"/>

        </li>
        <li>
          <span>例句</span><input type="text" v-model="newWord.exp" placeholder="请输入例句" class="text1"/>

        </li>
      </ul>
    </div>
    <button v-on:click="addWord(newWord)" class="button1">确认添加</button>
  </div>
</template>

<script>
import Header from '../header/header'

export default {
  name: 'AddWord',
  components: {
    Header
  },
  data () {
    return {
      newWord: {
        word: '',
        translate: '',
        exp: '',
        speak: ''
      },
      title: '添加新单词',
      back: '/Words'
    }
  },
  methods: {
    addWord: function (newWord) {
      console.log(newWord.word)
      if (newWord.word == '') {
        alert('输入的单词不能为空！')
      } else if (newWord.translate == '') {
        alert('输入的中文意思不能为空！')
      } else if (newWord.speak == '') {
        alert('输入的单词发音不能为空！')
      } else if (newWord.exp == '') {
        alert('输入的例句不能为空！')
      } else {
        var url = 'http://localhost:7000/usermanage/words/save?word=' + newWord.word + '&translate=' + newWord.translate +
          '&exp=' + newWord.exp + '&speak=' + newWord.speak // 录入单词传参
        this.axios.get(url).then((res) => {
          console.log(res)
          if (res.data.code == 555) {
            alert('发生异常,录入失败')
          } else {
            alert('录入成功')
            this.$router.push({path: '/words'})
          }
        }).catch((res) => {
          alert('请求失败')
        })
      }
    }
  }
}

</script>

<style scoped>
  .wrapper {
    margin-top: 55px;
    text-align: left;
  }

  .title {
    padding-left: 23px;
    margin-bottom: 10px;
    color: #999;
  }

  .section {
    background: #ffffff;
    padding: 0 23px;
  }

  .section li {
    height: 55px;
    line-height: 55px;
    border-bottom: 1px #eee solid;
    font-size: 16px;
  }

  .section input {
    border: none;
    margin-left: 16px;
    font-size: 14px;
  }

  .button1 {
    border: 0;
    background: #41b883;
    border-radius: 3px;
    height: 42px;
    width: calc(100% - 66px);
    margin: 15% 33px 10% 33px;
    font-size: 16px;
    font-weight: bold;

    color: #ffffff;
  }
</style>
