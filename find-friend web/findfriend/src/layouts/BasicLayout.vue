<template>
  <van-nav-bar
      :title="title"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
      id="nav"
  >
    <template #right>
      <van-icon name="search" size="18"/>
    </template>
  </van-nav-bar>

  <div id="content">
    <router-view/>
  </div>
  <van-tabbar route @change="onChange"   active-color="#FF0000" inactive-color="#1E90FF" id="tabbar">
    <van-tabbar-item to="/" icon="home-o" name="index">主页</van-tabbar-item>
    <van-tabbar-item to="/team" icon="flag-o" name="team">队伍</van-tabbar-item>
    <van-tabbar-item to="/user" icon="user-o" name="user">个人</van-tabbar-item>
  </van-tabbar>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
import {ref} from "vue";
import routes from "../config/route";

const router = useRouter();
const DEFAULT_TITLE = '寻友App';
const title = ref(DEFAULT_TITLE);

/**
 * 根据路由切换标题
 */
router.beforeEach((to, from) => {
  const toPath = to.path;
  const route = routes.find((route) => {
    return toPath == route.path;
  })
  title.value = route?.title ?? DEFAULT_TITLE;
})

const onClickLeft = () => {
  router.back();
};

const onClickRight = () => {
  router.push('/search')
};


</script>

<style scoped>
#content {
  padding-bottom: 50px;
  background-color:	#FFFFE0 ;
}
#tabbar{
  background-color: #CAFF70;
}
#nav{
  background-color: #CAFF70;
}


</style>
