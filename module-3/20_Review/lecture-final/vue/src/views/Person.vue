<template>
  <div>
    {{ $store.state.person.name }}
  </div>
</template>

<script>
export default {
  created() {
    this.$store.dispatch('GET_PERSON', 1);
  }

}
</script>
