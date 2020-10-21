<template>
  <div>

    <!--加上页面修饰符，提交时就不回再重载页面-->
    <form v-on:submit.prevent="formSubmit">

      <select v-model="to">
        <option value ="en">英文</option>
        <option value ="ko">韩文</option>
        <option value ="fr">法文</option>
        <option value ="ru">俄文</option>
      </select>
      <input  id="input1" type="submit" value="翻译"/>
      <input type="button" id="tts_btn" @click="doTTS(text)" value="播放">
      <br>
      <textarea id="textarea1" v-model="text"  data-height="70" placeholder="输入需要翻译的内容">

      </textarea>
    </form>

  </div>

</template>
<script>
export default {
  name: 'TranslateForm',
  components:{

  },
  data: function () {
    return {
      text: '',
      to: 'en'
    }
  },
  methods: {
    formSubmit: function () {
      this.$emit('formSubmit', this.text, this.to)
    },
    doTTS(text) {
      let url = '/api/dictvoice?audio=' + text
      let audio = new Audio()
      audio.src = 'http://dict.youdao.com/dictvoice?audio=' + text
      audio.play()
    }

  }
}
</script>
<style>
  select{
    width: 80px;
    position: fixed;
    left: 200px;
  }
  #input1{
    background-color: #19bbed;
    position: fixed;
    right: 200px;
    width: 100px;
  }

  #textarea1{
    width: 300px;
    height: 100px;
    position: fixed;
    left: 100px;
    overflow: scroll;
  }
</style>
