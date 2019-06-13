<template>
    <div class="group" id="group">


        <h1>{{getUserInfo}}</h1>

    </div>
</template>

<script>

    import axios from "axios";

    export default {
        name: 'GroupInfo',
        props: {
            log: String,
            userId:Number,
            groupId:Number,
            response:Object
        },
        methods:{
            // savePosition:{}
        },
        mounted(){//mounted:{
            this.response=axios.get("http://127.0.0.1:8080/");

        },
        computed:{
            getUserInfo:function(){
                return "user:" + this.userId +", group:"+this.groupId+", session:"+this.sessionId+",resp"+this.response;
            }
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

    .log {
        Margin-bottom:40px;
        position:fixed;
    }
</style>
