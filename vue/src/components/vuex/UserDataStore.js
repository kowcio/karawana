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
        // response: Object
    },
    action: {
        getGroupInfo: function() {
            // state.response = axios.get("http://127.0.0.1:8080/");
            return axios.get('http://127.0.0.1:8081/');
        }
    },
    mutations: {
        // increment(state, value) {
        //     state.myValue += value;
        // }

    },
    getters: {
        getGroupInfo: function() {
            // state.response = axios.get("http://127.0.0.1:8080/");
            axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
            return axios('http://127.0.0.1:8081/', {
                method: 'GET',
                headers: {
                    'Access-Control-Allow-Origin': '*'
                }
            }).then(response => {
                console.log(response);
            })
        }
    }
})
;
