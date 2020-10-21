<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="word">
      <el-input v-model="dataForm.word" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="translate">
      <el-input v-model="dataForm.translate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="exp">
      <el-input v-model="dataForm.exp" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="speak">
      <el-input v-model="dataForm.speak" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          word: '',
          translate: '',
          exp: '',
          speak: ''
        },
        dataRule: {
          word: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          translate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          exp: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          speak: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/usermanage/words/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.word = data.words.word
                this.dataForm.translate = data.words.translate
                this.dataForm.exp = data.words.exp
                this.dataForm.speak = data.words.speak
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/usermanage/words/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'word': this.dataForm.word,
                'translate': this.dataForm.translate,
                'exp': this.dataForm.exp,
                'speak': this.dataForm.speak
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
