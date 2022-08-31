<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 20px; font-weight: bold; padding-left: 30px">Logo</div>
    <div style="flex: 1"></div>
    <div style="width: 200px; margin: 10px 0">
      <el-dropdown>
        <el-button type="primary">下拉框
          <el-icon class="el-icon--right">
            <arrow-down/>
          </el-icon>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>不知道</el-dropdown-item>
            <el-dropdown-item>没想好</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>

</style>