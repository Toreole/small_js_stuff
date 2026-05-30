<template>
    <button @click="getNumber">button</button>
    <input type="number" v-model="maximum"></input>
    <p> hier:: {{ count }} ::hier </p>
</template>
<script lang="js">
import { RandomApi } from '../../api';
export default {
    data() {
        return {
            count: 0,
            maximum: 100,
            randomApi: new RandomApi() 
            // falls wir einen anderen Server haben
            // new RandomApi(new ApiClient("http://server.addresse"))
        }
    }, 
    methods: {
        getNumber() {
            this.randomApi.getRandomNumber({max: this.maximum}).then(
                (x) => this.count = x
            )
        }
    }
}
</script>