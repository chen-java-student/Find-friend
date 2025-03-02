<template>
  <div>
    <van-swipe :autoplay="3000" lazy-render id="swipe">
      <van-swipe-item>欢迎试用寻友APP，作者陈毓轩</van-swipe-item>
      <van-swipe-item v-for="image in images" :key="image">
        <img :src="image" />
      </van-swipe-item>

      <van-swipe-item>广告位招租</van-swipe-item>
    </van-swipe>
  </div>
  <van-cell center title="寻找最匹配的朋友">
    <template #right-icon>
      <van-switch v-model="isMatchMode" size="24" />
    </template>
  </van-cell>
  <user-card-list :user-list="userList" :loading="loading"/>
  <van-empty v-if="!userList || userList.length < 1" description="数据为空"/>
</template>

<script setup lang="ts">
import {ref, watchEffect} from "vue";
import {useRoute} from "vue-router";
import {Toast} from "vant";

import myAxios from "../plugins/myAxios.ts";
import UserCardList from "../components/UserCardList.vue";
import {UserType} from "../models/user";

const route = useRoute();
const {tags} = route.query;

const userList = ref([]);

const isMatchMode = ref<boolean>(false);
const loading = ref(true);

/**
 * 加载数据
 */
const loadData = async () => {
  let userListData;
  loading.value = true;
  //心动模式
  if (isMatchMode.value){
    const  num = 8;
    userListData = await myAxios.get('user/match',{
      params: {
        num,
      },
    })
        .then(function (response) {
          console.log('/user/match succeed',response);
          Toast.success('请求成功');
          return response?.data;
        })
        .catch(function (error) {
          console.log('/user/match error',error);
          Toast.fail('请求失败');
        });
  }else {
    //普通用户使用分页查询
    userListData = await  myAxios.get('/user/recommend',{
      params: {
        pageSize: 15,
        pageNum: 1,
      },
    })
        .then(function (response) {
          console.log('/user/recommend succeed', response);
          Toast.success('请求成功');
          return response?.data?.records;
        })
        .catch(function (error) {
          console.log('/user/recommends error',error);
          Toast.fail('请求失败');
        });

  }
  if (userListData){
    userListData.forEach((user: UserType) =>{
      if (user.tags){
        user.tags = JSON.parse(user.tags);
      }
    })
    userList.value = userListData;
  }
  loading.value = false;
}

watchEffect(() =>{
  loadData();
})
const images = [
  'https://www.2008php.com/2011_Website_appreciate/11-03-09/20110309203959.jpg',
  'https://n.sinaimg.cn/sinacn10116/600/w1920h1080/20190326/adec-hutwezf6832324.jpg',
  'https://ts1.cn.mm.bing.net/th/id/R-C.e3f16bd3118bf26faef9a799860973ba?rik=03qfEIXzB4sVig&riu=http%3a%2f%2fpic.bizhi360.com%2fbbpic%2f68%2f3968.jpg&ehk=inn4NRcd8l50xFrMSB6yoYldUnOizNvdMFPPseAsI7A%3d&risl=&pid=ImgRaw&r=0',
];


</script>
<style scoped>
#swipe{
  color: #fff;
  font-size: 20px;
  line-height: 150px;
  text-align: center;
  background-color: #39a9ed;
  max-width: 100%;
  max-height: 200px;
  min-width: 100%;
}
img{
  max-width: 100%;
  max-height: 200px;
  min-width: 100%;
}
</style>
