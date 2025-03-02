<template>
  <form action="/">
    <van-search
        v-model="searchText"
        show-action
        placeholder="请输入要搜索的标签"
        @search="onSearch"
        @cancel="onCancel"
    />
  </form>
  <van-divider content-position="left">已选标签</van-divider>
  <div v-if="activeIds.length === 0">请选择标签</div>
  <van-row gutter="16" style="padding: 0 16px">
    <van-col v-for="tag in activeIds">
      <van-tag closeable size="small" type="primary" @close="doClose(tag)">
        {{ tag }}
      </van-tag>
    </van-col>
  </van-row>
  <van-divider content-position="left">选择标签</van-divider>
  <van-tree-select
      v-model:active-id="activeIds"
      v-model:main-active-index="activeIndex"
      :items="tagList"
  />
  <van-divider/>
  <van-space direction="vertical" fill>
    <van-button v-if="userId" block type="primary" @click="updateTag">修改
    </van-button>
    <van-button block v-else type="primary" @click="doSearchResult">搜索</van-button>
  </van-space>
</template>

<script setup lang="ts">
import {onMounted, ref, watch} from "vue";
import {Toast} from "vant";
import {useRouter, useRoute} from "vue-router";
import myAxios from "../plugins/myAxios";
import { getCurrentUser } from "../services/user";

const router = useRouter()
const route = useRoute()

const searchText = ref('');
const userId = ref();
const userTags = ref([]);

const originTagList = [{
  text: '性别',
  children: [
    {text: '男', id: '男'},
    {text: '女', id: '女'},
  ],
},
  {
    text: '年级',
    children: [
      {text: '大一', id: '大一'},
      {text: '大二', id: '大二'},
      {text: '大三', id: '大三'},
      {text: '大四', id: '大四'},
    ],
  },
  {
    text: '语言',
    children: [
      {text: 'Java', id: 'Java'},
      {text: 'C++', id: 'C++'},
      {text: 'Python', id: 'Python'},
      {text: 'Go', id: 'go'},
    ],
  },
  {
    text: '目标',
    children: [
      {text: '竞赛', id: '竞赛'},
      {text: '考研', id: '考研'},
      {text: '交友', id: '交友'},
      {text: '摸鱼', id: '摸鱼'},
    ],
  },
]

// 标签列表
let tagList = ref(originTagList);

/**
 * 搜索过滤
 * @param val
 */
const onSearch = (val) => {
  tagList.value = originTagList.map(parentTag => {
    const tempChildren = [...parentTag.children];
    const tempParentTag = {...parentTag};
    tempParentTag.children = tempChildren.filter(item => item.text.includes(searchText.value));
    return tempParentTag;
  });

}
const onCancel = () => {
  searchText.value = '';
  activeIds.value = [];
};

// 已选中的标签
const activeIds = ref([]);
const activeIndex = ref(0);

// 移除标签
const doClose = (tag) => {
  activeIds.value = activeIds.value.filter(item => {
    return item !== tag;
  })
}

/**
 * 执行搜索
 */
const doSearchResult = () => {
  router.push({
    path: '/user/list',
    query: {
      tags: activeIds.value
    }
  })
}

const updateTag = async () => {
  const update = await myAxios.post("/user/update/tags", {
    id: userId.value,
    tagList: activeIds.value
  })
  if (update && update.data === true) {
    await router.push("/user/update")
  }
}
onMounted(() => {
  const {id, tags} = route.query
  if (id && tags) {
    userId.value = id;
    userTags.value = tags
    activeIds.value = tags
  }
  getCurrentUser()
})
watch(activeIds, (newList) => {
  if (newList.length > 12) {
    Toast.fail("最多只能选择12个标签")
    activeIds.value = activeIds.value.slice(0, 12)
  }
});

</script>

<style scoped>

</style>
