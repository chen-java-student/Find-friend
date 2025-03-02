<template>
  <template v-if="user">
    <van-cell title="当前用户" :value="user?.username" >
      <template #icon>
        <van-icon name="user-o" size="20" />
      </template>
    </van-cell>
    <van-divider/>
    <van-cell title="修改信息" is-link to="/user/update" >
    <template #icon>

      <van-icon name="edit" size="20" />
    </template>
    </van-cell>
    <van-divider/>
    <van-cell title="我创建的队伍" is-link to="/user/team/create" >
      <template #icon>
        <van-icon name="flag-o" size="20" />
      </template>
    </van-cell>
    <van-divider/>
    <van-cell title="我加入的队伍" is-link to="/user/team/join" >
      <template #icon>
        <van-icon name="logistics" size="20" />
      </template>
    </van-cell>
    <van-divider/>
  </template>
  <div class="button">
      <van-button round type="danger" size="large"  @click="quit" >注销当前账户</van-button>
  </div>

</template>

<script setup lang="ts">
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import myAxios from "../plugins/myAxios";
import {Toast} from "vant";
import {getCurrentUser} from "../services/user";


const user = ref();

const router = useRouter();

onMounted(async () => {
  user.value = await getCurrentUser();
})
const quit = async () => {
  const res = await myAxios.post("/user/logout");
  if (res.code == 0){
    Toast("注销成功");
    router.push("/");
  }else {
    Toast("注销失败");
  }
}
const toEdit = (editKey: string, editName: string, currentValue: string) => {
  router.push({
    path: '/user/edit',
    query: {
      editKey,
      editName,
      currentValue,
    }
  })
}
</script>

<style scoped>
body {
  position: relative;
  min-height: 100vh;
  background-color: #FFFFE0 ;
}
.button{
  margin-top: 200px;
}
</style>
