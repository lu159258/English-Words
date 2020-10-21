<template>
    <div class="wrapper">
        <Header :title="title" :back="back"></Header>
        <div class="section">
            <ul>
                <li>
                    <span>单词</span>
                    <input type="text" v-model="form.word" placeholder="请输入改正后的单词（可选）" class="text1"/>
                </li>
                <li>
                    <span>中文</span><input type="text" v-model="form.translate" placeholder="请输入修改的汉语意思（可选）"
                                          class="text1"/>

                </li>
              <li>
                <span>例句</span><input type="text" v-model="form.exp" placeholder="请输入修改后的例句（可选）"
                                      class="text1"/>

              </li>
              <li>
                <span>发音</span><input type="text" v-model="form.speak" placeholder="请输入修改后的发音（可选）"
                                      class="text1"/>

              </li>
            </ul>
        </div>
        <button v-on:click="update(form.word,form.translate,form.exp,form.speak)" class="button1">确认修改</button>

    </div>
</template>

<script>
import Header from '../header/header'

export default {
  name: 'UpdWord',
  components: {
    Header
  },
  data () {
    return {
      /* newWord: {
                    word: '',
                    translate: '',
                    exp: '',
                    speak:''
                }, */
      title: '修改单词',
      back: '/Words'

    }
  },
  methods: {
    getByWordInfo: function () {
      this.form = this.$route.params.item // 路由绑定参数
      console.log(this.$route.params.item)
    },
    update (word, translate, exp, speak) {
      var url = 'http://localhost:7000/usermanage/words/update?word=' + word + '&translate=' + translate +
                '&exp=' + exp + '&speak=' + speak + '&id=' + this.form.id
      this.axios.get(url).then((res) => {
        console.log(res)
        if (res.data.code == 555) {
          alert('发生异常,修改失败')
        } else {
          alert('修改成功')
          this.$router.push({path: '/words'})
        }
      }).catch((res) => {
        alert('请求失败')
      })
    }
  },
  created () {
    this.getByWordInfo()
  }
}

</script>

<style scoped>
    .wrapper {
        margin-top: 55px;
    }

    .wordsWrapper {
        background: #ffffff;
        margin-bottom: 12px;
        padding: 20px 0;
        color: #666;
    }

    .wordsWrapper .words {
        font-size: 25px;
        color: #44c293;
        font-weight: bold;
        padding: 10px 0;
    }
    .sp{
        font-size: 12px;
        margin-bottom: 5px;
    }
    .chinese{
        font-size: 12px;
    }

    .text {

        padding-left: 80px;
        text-align: left;

    }

    .section {
        background: #ffffff;
        padding: 0 23px;
        text-align: left;
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
        width: 80%;
    }

    .button1 {
        border: 0;
        background: #41b883;
        border-radius: 3px;
        height: 42px;
        width: calc(100% - 66px);
        margin: 10% 33px 10% 33px;
        font-size: 16px;
        font-weight: bold;
        color: #ffffff;
    }

</style>
