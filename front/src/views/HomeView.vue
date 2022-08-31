<template>
  <div>
    <!--    按钮-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">增加</el-button>
    </div>
    <!--    按钮-->

    <!--    表格-->
    <div>
      <el-table :data="tableData" stripe border>
        <el-table-column label="姓名" prop="name"/>
        <el-table-column label="学号" prop="id"/>
        <el-table-column label="学院" prop="department"/>
        <el-table-column label="日期" prop="tt"/>
        <el-table-column label="时长" prop="stime"/>
        <el-table-column align="right">
          <template #header>
            <el-input v-model="search" size="small" placeholder="Type to search"/>
          </template>
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-popconfirm title="Are you sure to delete this?">
              <template #reference>
                <el-button @click="handleDelete" size="small">Delete</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--    表格-->

    <!--    增加信息-->
    <el-dialog title="" v-model="dialogVisible" width="30%">
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
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">确定</el-button>
        </span>
      </template>
    </el-dialog>
    <!--    增加信息-->

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
      tableData: [],
      search: '',
      dialogVisible: false,
      form: {},
    }
  },
  methods: {
    handleEdit() {

    },
    handleDelete() {

    },
    add() {
      this.dialogVisible = true;
      this.form = {}; //输入之后清空信息
    },
    save() {
      axios.post("http://localhost:9090/add",this.form).then(res=>{
        console.log(res)
      })
      this.form = {}
      this.dialogVisible = false
    }
  }
}
</script>
