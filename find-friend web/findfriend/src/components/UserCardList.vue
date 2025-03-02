<template>
  <!--  <van-skeleton title avatar :row="3" :loading="props.loading" v-for="user in props.userList">-->
  <!-- 加上 props 可以更好的区分这个 userList是从 props 中取出来的，也可以不加    -->
  <van-card
      v-for="user in props.userList"
      :desc="user.profile"
      :title="`${user.username}`"
      :thumb="user.avatarUrl"
  >
    <template #tags>
      <van-tag plain type="primary" v-for="tag in user.tags" style="margin: 5px">
        {{tag}}
      </van-tag>
    </template>
    <template #footer>
      <van-button size="mini" @click="showPopup(user)">联系方式</van-button>
    </template>
  </van-card>
  <van-popup v-model:show="show" :style="{}"
             :overlay-style="{backgroundColor: 'rgba(0,0,0,0.1)'}">
    <van-image :src="clickUser.avatarUrl"  />
    <van-cell :value="clickUser.username" center title="用户名:"/>
    <van-cell :value="clickUser.profile" center title="自我介绍:"/>
    <van-cell :value="phoneText" center/>
  </van-popup>
  <!--  </van-skeleton>-->
</template>

<script setup lang="ts">

import {UserType} from "../models/user";
import {onMounted, ref} from "vue";
import {ImagePreview} from "vant";

interface UserCardListProps{
  loading: boolean;
  userList: UserType[];
}

const show = ref(false);
const clickUser = ref<UserType>();
const phoneText = ref('暂无联系方式');

const props = withDefaults(defineProps<UserCardListProps>(), {
  //@ts-ignore
  userList: [] as UserType[],
  loading: true,
})

const showPopup = (user: UserType) => {
  clickUser.value = user;
  if (user.phone){
    phoneText.value = '联系方式：' + user.phone;
  }else {
    phoneText.value = '暂无联系方式';
  }
  show.value = true;
};

</script>

<style scoped>
van-popup > van-overlay{
  position: fixed;
  top: 0;
  left: 0;
  z-index: var(--van-overlay-z-index);
  width: 100%;
  height: 100%;
  background-color: rgba(231, 219, 219, 0.45);
}
</style>