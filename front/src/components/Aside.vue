<template>
  <el-col :span="12">
    <h5 class="mb-2"></h5>
    <el-menu style="width: 200px; min-height: calc(100vh - 50px)" default-active="2" class="el-menu-vertical-demo">
      <el-sub-menu index="1">
        <template #title>
          <el-icon>
            <location/>
          </el-icon>
          <span>button1</span>
        </template>
        <el-sub-menu index="1-1">
          <template #title>item four</template>
        </el-sub-menu>
      </el-sub-menu>
      <el-menu-item index="2">
        <el-icon>
          <icon-menu/>
        </el-icon>
        <span>button2</span>
      </el-menu-item>
      <el-menu-item index="3" disabled>
        <el-icon>
          <document/>
        </el-icon>
        <span>button3</span>
      </el-menu-item>
    </el-menu>
  </el-col>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>