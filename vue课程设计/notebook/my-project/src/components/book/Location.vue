<template>
  <div>
    <Header :title="title" :back="back"></Header>

      <div id="div1">
        姓&nbsp&nbsp&nbsp名:<input type="text"  v-model='uname' placeholder="你的名字"><br><br>
        <input type="radio" value="男">男
        <input type="radio" value="女">女<br><br>
        详细地址:<input type="text" id="suggestId" name="address_detail" placeholder="请选择" v-model="address_detail" class="input_style">
        <br><br>
        <div id="allmap"></div>
        <br>
        <button @click="tijao">确定</button>

      </div>



    </div>

</template>
<script>
import {MP} from '../../api/local'
import Header from '../header/header'
import Book from './Book'
import Words from "../main/Words";
export default {
  name: 'Location',
  components: {
    Header,
    Book,
    Words
  },
  data () {
    return {
      title: '添加地址',
      back: '/Words',
      uname: '',
      address_detail: null, // 详细地址
      userlocation: {lng: '', lat: ''}
    }
  },
  methods: {
    tijao () {
      // eslint-disable-next-line eqeqeq
      if (this.address_detail == '' || this.uname == '') {
        alert('请输入相关信息')
      } else {
        alert('设置成功')
        this.$router.push({path: '/Words'})
      }
    }
  },
  mounted () {
    this.$nextTick(function () {
      MP('你的ak').then(BMap => {
        var th = this
        var map = new BMap.Map('allmap') // 创建Map实例
        var point = new BMap.Point(116.404, 39.915) // 创建点坐标
        map.centerAndZoom(point, 15)
        map.enableScrollWheelZoom()
        var ac = new BMap.Autocomplete( // 建立一个自动完成的对象
          {'input': 'suggestId',
            'location': map
          })
        var myValue
        ac.addEventListener('onconfirm', function (e) { // 鼠标点击下拉列表后的事件
          var _value = e.item.value
          myValue = _value.province + _value.city + _value.district + _value.street + _value.business
          this.address_detail = myValue
          setPlace()
        })

        function setPlace () {
          map.clearOverlays() // 清除地图上所有覆盖物
          function myFun () {
            th.userlocation = local.getResults().getPoi(0).point // 获取第一个智能搜索的结果
            map.centerAndZoom(th.userlocation, 18)
            map.addOverlay(new BMap.Marker(th.userlocation)) // 添加标注
          }
          var local = new BMap.LocalSearch(map, { // 智能搜索
            onSearchComplete: myFun
          })
          local.search(myValue)
        }
      })
    })
  }
}

</script>
<style scoped>

  #div1{
    margin-top: 55px;
    height: 500px;
    width: 400px;
    position: fixed;
    overflow: scroll;
    left: 300px;

  }
  input{
    margin-left: 20px;
  }
  button{
    background-color: greenyellow;
    width: 250px;
  }
  #allmap{
    height: 250px;
    width: 250px;
    left: 80px;
  }

</style>
