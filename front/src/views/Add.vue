<template>
  <v-container>
    <v-row class="text-center mt-4 mb-2 col-8">
      <h2 class="col-12">Add task</h2>
      <v-text-field
        label="Name"
        hide-details="auto"
        class="col-12"
        v-model="newState.name"
      ></v-text-field>
      <v-select
        :items="states"
        label="States"
        class="col-12"
        v-model="newState.state"
      ></v-select>
      <v-btn block class="col-12" color="primary" @click="addTask">
        Save
      </v-btn>
    </v-row>
  </v-container>
</template>
<script>
import axios from "axios";
export default {
  props: {
    id: {
      type: [String, Number],
      defalut: 0,
    },
  },
  data() {
    return {
      states: ["CREATED", "RUNNING", "DONE"],
      newState: {
        name: "",
        state: "",
      },
    };
  },
  methods: {
    addTask() {
      const axiosInstance = axios.create({
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      });
      axiosInstance
        .post(process.env.VUE_APP_BACK_URL, this.newState)
        .then(() => {
          this.newState.name = "";
          this.newState.state = "";
          this.$router.replace("/");
        });
    },
  },
  created() {
    if (this.id) {
      const axiosInstance = axios.create({
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      });
      axiosInstance
        .get(process.env.VUE_APP_BACK_URL + this.id)
        .then((response) => {
          this.newState = response.data;
        })
        .catch((err) => console.log(err));
    }
  },
  watch: {
    id(oldId, newId) {
      console.log(oldId, newId);
      if (this.id) {
        const axiosInstance = axios.create({
          headers: {
            "Access-Control-Allow-Origin": "*",
          },
        });
        axiosInstance
          .get(process.env.VUE_APP_BACK_URL + this.id)
          .then((response) => {
            this.newState = response.data;
          })
          .catch((err) => console.log(err));
      }
    },
  },
};
</script>