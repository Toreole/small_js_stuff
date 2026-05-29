<template>
    <button @click="getNumber">button</button>
    <p> hier:: {{ count }} ::hier </p>
</template>
<script lang="js">
import { RandomApi } from '../../api';
export default {
    data() {
        return {
            count: 0,
            randomApi: new RandomApi()
        }
    }, 
    methods: {
        getNumber() {
            this.randomApi.getRandomNumber(500).then(
                (x) => this.count = x
            )
        }
    }
}
</script>