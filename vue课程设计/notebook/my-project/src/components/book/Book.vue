<template>
  <div id="books">
    <Header :title="title" :back="back"></Header>
    <div v-if="books.length">
      <table>
        <thead>
        <tr>
          <th></th>
          <th>书籍名称</th>
          <th>出版日期</th>
          <th>价格</th>
          <th>购买数量</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item,index) in books">
          <td >{{item.id}}</td>
          <td >{{item.name}}</td>
          <td >{{item.data}}</td>
          <td >{{item.price|showPrice}}</td>
          <td >
            <button @click="decrement(index)" :disabled="item.count<=1">-</button>
            {{item.count}}
            <button @click="increment(index)">+</button>
          </td>
          <td><button @click="removeTd(index)">移除</button></td>
        </tr>

        </tbody>
      </table>
      <h2>总价格{{totalPrice | showPrice}}</h2>

    </div>
    <span v-else>为空</span>
    <button id="btn" @click="buy">购买</button>
  </div>

</template>

<script>
  import Header from "../header/header"
  import Location from "./Location";
export default {
  name: 'Book',
  components: {
    Header,
    Location
  },
  data: function () {
    return {
      title: '学习书籍',
      back: '/Words',
      books: [
        {
          id: 1,
          name: '英语四级',
          data: '2006-9',
          price: 85.00,
          count: 1
        },
        {
          id: 2,
          name: '牛津词典',
          data: '2007-9',
          price: 59.00,
          count: 1
        },
        {
          id: 3,
          name: '单词大全',
          data: '2010-9',
          price: 39.00,
          count: 1
        },
        {
          id: 4,
          name: '英语书',
          data: '2019-9',
          price: 55.00,
          count: 1
        }

      ]
    }
  },
  // 过滤器
  filters: {
    showPrice (price) {
      return '￥' + price.toFixed(2)
    }
  },
  methods: {
    decrement (index) {
      this.books[index].count--
    },
    increment (index) {
      this.books[index].count++
    },
    removeTd (index) {
      this.books.splice(index, 1)
    },

    buy(){
      if (this.books.length>0)
      {
        alert("购买成功");
      }
      else {
        alert("没有物品可购买")
      }



    }
  },
  computed: {
    totalPrice () {
      let totalPrice = 0
      for (let i = 0; i < this.books.length; i++) {
        totalPrice += this.books[i].price * this.books[i].count
      }
      return totalPrice
    },

  },

}
</script>

<style scoped>
  #books{
    margin-top: 55px;
  }
  table{

    border: 1px solid ;
    border-collapse: collapse;
    border-spacing: 0;

    margin: 0 auto;
  }
  th,td{
    padding: 8px 16px;
    border: 1px solid;
    text-align: left;

  }
  th{
    background-color: #760cff;
    color: beige;
    font-weight: 600;
  }
  h2{
    position: fixed;
    left: 200px;

    bottom: 55px;
  }
  #btn{
    position: fixed;
   right: 200px;

    bottom: 55px;
  }
  #btn1{
    position: fixed;
    right: 300px;
    bottom: 55px;
  }


</style>
