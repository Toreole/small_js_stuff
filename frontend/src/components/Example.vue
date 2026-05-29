<template>
    <button @click="getNumber">button</button>
    <p> hier:: {{ count }} ::hier </p>
</template>
<script lang="js">
export default {
    data() {
        return {
            count: 0
        }
    }, 
    methods: {
        async getNumber() {
            const response = await fetch("http://localhost:8080/random")
            if (response.ok) {
                this.count = await response.json()
            }
        }
    }
}
</script>