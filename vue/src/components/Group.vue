<template>
    <div class="group" id="group">
        Groups component :
        <h1>{{getResponse}}</h1>
        <h1>{{getUserInfo}}</h1>
    </div>
</template>

<script>
    // import Vue from 'vue'
    import store from "./vuex/UserDataStore.js"
    // Vue.use(store, {store}) //store

    export default {
        components: {}, store,
        name: 'Group',
        props: {
            log: String,
            userId: Number,
            groupId: Number,
            response: Object
        },
        methods: {
            // loadUserData:{            }
        },
        mounted() {//mounted:{
            this.$store.dispatch('getGroupInfo');
            this.$store.getters.getGroupInfo();


        },
        computed: {
            getUserInfo: function () {
                return "user:" + this.userId + ", group:" + this.groupId + ", session:" + this.sessionId + " ,resp:" + this.response;
            },
            getResponse: function () {
                // return store.state;
                return store.getters.get2;

            }
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

    .log {
        Margin-bottom: 40px;
        position: fixed;
    }
</style>
