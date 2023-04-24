<script setup lang="ts">
import MessagesContainer from '@/components/MessagesContainer.vue';
import { getAll } from '@/services/messages';
import { computed } from '@vue/reactivity';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

export type Message = {
  sentAt: string;
  name: string;
  content: string;
}

export type GroupMessage = Record<string, Message[]>;

const productId = useRoute().params.productId as string;
const groupedMessages = ref<GroupMessage>({});

async function fetchMessages() {
  try {
    const req = await getAll(productId);
    groupedMessages.value = req.data;
    console.log(req.data);
  } catch (error) {
    console.log(error);
  }
}

onMounted(() => {
  fetchMessages();
})
</script>

<template>
  <div class="msg-container">
    <MessagesContainer v-for="group in groupedMessages"
      :message-list="group" />
  </div>
</template>

<style scoped>
.msg-container {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

</style>
