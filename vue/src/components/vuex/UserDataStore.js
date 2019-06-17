import Vue from 'vue'
import axios from "axios";
import Vuex from "vuex";

Vue.use(Vuex)


export default new Vuex.Store({
    // "State" is the application data your components
    // will subscribe to
    strict: true,
    state: {
        myValue: 0,
        userId: Number,
        groupId: Number,
        sessionID: String,
        response: Object
    },
    actions: {
        getGroupInfo ({commit}) {
            // state.response = axios.get("http://127.0.0.1:8080/");
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            axios('http://127.0.0.1:8081/api/changeGroup/vueTestGroup', {
                method: 'GET',
                headers: {'Access-Control-Allow-Origin': '*'}
            }).then(response => {
                commit("changeGroup", {groupData : response.data});
                return true;
        }, (err) => {
                console.log(err);
            })
        }
    },
    mutations: {
        increment(state, value) {
            state.myValue += value;
        },
        changeGroup(state, groupData){
            state.response=groupData;
        }

    },
    getters: {
        getGroupInfo: function(state) {
            return state.response;
        },
        get2: state => state.response
    }
})
;
