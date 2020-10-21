<template>
  <div id="TranslateText">
    <textarea name="" id="textarea2" cols="30" rows="10">{{translatedText}}</textarea><br>

  </div>

</template>
<script>

export default {
  name: 'TranslateText',
  props: [
    'translatedText'
  ],


}
</script>
<style>
  #textarea2{
    width: 300px;
    height: 100px;
    position: fixed;
    right: 100px;
    overflow: scroll;
    background-color: #888888;
  }

</style>
