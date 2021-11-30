<template>
    <div>
        <best-seller-list></best-seller-list>
        <new-releases-list></new-releases-list>
    </div>
</template>

<script>
import BestSellerList from '../components/BestSellerList';
import NewReleasesList from '../components/NewReleasesList';

export default {
    components: {
        BestSellerList,
        NewReleasesList
    }
}
</script>

<style>

</style>