<template>
    <div>
        Below is map ?
        <div ref="map" class="map" id="map">
        </div>
    </div>
</template>

<script>
    import "./leaflet.css";
    import L from 'leaflet'

    export default {
        // components: {
        //     map:Object;
        // },
        data() {return {
            map: null
        }},
        mounted() {
            this.map = L.map("map").setView([51.959, -8.623], 12);
            L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
                attribution:
                    '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(this.map);
        },
        beforeDestroy() {
            if (this.map) {
                this.map.remove();
            }
        }
    };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>
