<script setup lang="ts">
import type { Message } from '@/views/ProductMessages.vue';
import { computed, toRefs } from 'vue';

const days = ["Lun.", "Mar.", "Miér.", "Jue.", "Vier.", "Sáb.", "Dom."];

const props = defineProps<{
  messageList: Message[]
}>()
const { messageList } = toRefs(props);


const finalMessages = computed(() => messageList.value.slice(0, 2));


function formatSentDate(dateStr: string) {
  const date = new Date(dateStr);
  const hhmmss = `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`
  return `${days[date.getDay()]}, ${hhmmss}.`;
}
</script>
<template>
<div class="msg-group">
  <div>Enviado por {{ messageList[0].name }}</div>

  <div class="msg" v-for="message in finalMessages">
    <div class="msg-ico"></div>
    <div>
      <div class="msg-content">{{ message.content }}</div>
      <div class="msg-date">
        <p v-text="formatSentDate(message.sentAt)"></p>
      </div>
    </div>
  </div>
  <div v-if="messageList.length > 2">
    <span :style="{ textDecoration: 'underline'}">
      Ver todos
    </span> los enviados por este cliente
  </div>
</div>
</template>

<style scoped>
.msg-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  padding-bottom: 1rem;
  
  border-bottom: 2px solid grey;
}
.msg {
  display: flex;
  gap: 0.5rem;
  align-items: center;
  
}
.msg-ico {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: grey;
  align-self: self-start;
  margin-top: 3px;
}
.msg-content {
  background-color: var(--white);
  width: max-content;
  height: auto;
  max-width: 600px;
  padding: 0.5rem 1rem;
  border-radius: 0.3rem;
  text-align: left;
  box-shadow: 0 1px 2px 0 rgb(0 0 0 / 0.05);
}
.msg-date p {
  font-size: 12px;
  margin-left: 3px;
  margin-top: 5px;
}
</style>