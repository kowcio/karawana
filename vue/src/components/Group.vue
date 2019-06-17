<template>
    <div class="group" id="group">
        Group component :
        <h4>{{getResponse}}</h4>
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
            groupName:String,
            group: Object
        },
        methods: {
            // loadUserData:{            }
        },
        mounted() {//mounted:{
            this.$store.dispatch('getGroupInfo');
            // this.group = this.$store.getters.get2();

        },
        computed: {
            getUserInfo: function () {
                return "groupId:" + this.$store.getters.get2.id +
                    "groupName:" +  this.$store.getters.get2.groupName +
                    "users:" +      this.$store.getters.get2.users +
                    "user:" +       this.$store.getters.get2.userName +
                    "user:" +       this.$store.getters.get2.groupName
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
