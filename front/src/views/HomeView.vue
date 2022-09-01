<template>
  <div>
    <!--    按钮-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">增加</el-button>
    </div>
    <!--    按钮-->
    <div style="margin: 10px 0;">
      <el-input v-model="search" clearable placeholder="请输入需要搜寻的内容"></el-input>
      <el-button style="" @click="load">确定</el-button>
    </div>
    <!--    表格-->
    <div>
      <el-table :data="tableData" stripe border>
        <el-table-column label="姓名" prop="name"/>
        <el-table-column label="学号" sortable prop="id"/>
        <el-table-column label="学院" prop="department"/>
        <el-table-column label="日期" prop="tt"/>
        <el-table-column label="时长" prop="stime"/>
        <el-table-column align="right">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-popconfirm title="确定删除吗?">
              <template #reference>
                <el-button @click="handleDelete" size="small">Delete</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <hr class="my-4"/>
      <div class="demo-pagination-block">
        <div class="demonstration"></div>
        <el-pagination v-model:currentPage="currentPage4" v-model:page-size="pageSize4"
                       :page-sizes="[5,10]"
                       :small="small"
                       :disabled="disabled"
                       :background="background"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="total"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
        />
      </div>
    </div>
    <!--    表格-->

    <!--    增加信息-->
    <el-dialog title="" v-model="dialogVisible" width="30%">
      <el-form :model="updatefrom" label-width="120px">
        <el-form-item label="姓名">
          <el-input v-model="updatefrom.name" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="学院">
          <el-input v-model="updatefrom.department" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="updatefrom.id" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="时长">
          <el-input v-model="updatefrom.stime" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="日期">
          <el-input v-model="updatefrom.tt" style="width: 180px"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">确定</el-button>
        </span>
      </template>
    </el-dialog>
    <!--    增加信息-->

    <!--    更新信息-->
    <el-dialog title="请输入更新的信息" v-model="dVisible" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="姓名">
          <el-input v-model="form.name" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="学院">
          <el-input v-model="form.department" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="form.id" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="时长">
          <el-input v-model="form.stime" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="日期">
          <el-input v-model="form.tt" style="width: 180px"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dVisible = false">取消</el-button>
          <el-button type="primary" @click="update">更新</el-button>
        </span>
      </template>
    </el-dialog>
    <!--更新信息-->
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header";
import axios from "axios";

export default {
  name: 'HomeView',
  components: {},
  data() {
    return {
      dVisible: false,
      currentPage4: 1,
      pageSize4: 10,
      tableData: [],
      total: 0,
      search: '',
      dialogVisible: false,
      form: {},
      updatefrom: {},
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() { //加载基础表格
      axios.get("http://localhost:9090/findpage", {
        params: {
          pageNum: this.currentPage4,
          pageSize: this.pageSize4,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.contentList
        this.currentPage4 = res.data.currentPage
        this.total = res.data.totalPage
        // this.pageSize4 = res.data.totalPage
      })
    },

    handleEdit(row) {
      this.updatefrom = JSON.parse(JSON.stringify(row))
      this.dVisible = true
    },

    handleDelete() {
      axios.post("http://localhost:9090/delete", this.from).then(res => {
        console.log(res)
      })
    },

    add() {
      this.dialogVisible = true;
    },
    update() {
      axios.post("http://localhost:9090/update", this.updatefrom).then(res => {
        console.log(res)
        if (res.data.success === true) {
          this.$message({
            type: "success",
            message: "更新成功!"
          })
        }
        this.dVisible = false
        this.from = {}
      })
    },
    save() { //为表格中新增信息
      let that = this
      // 新增
      axios.post("http://localhost:9090/add", that.form).then(res => {
        console.log(res)
        if (res.data.success === true) {
          this.$message({
            type: "success",
            message: "增加成功!"
          })
        } else {
          this.$message({
            type: "false",
            message: "该学号的学生信息已存在！"
          })
        }
      })
      this.form = {}
      this.dialogVisible = false
    }
  }
}
</script>
