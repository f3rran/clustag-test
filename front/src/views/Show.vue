<template>
  <v-container>
    <h1>SHOW: {{ id }}</h1>
    <v-card class="mx-auto" max-width="400" tile>
      <v-list-item two-line>
        <v-list-item-content>
          <v-list-item-title>Name</v-list-item-title>
          <v-list-item-subtitle>{{ task.name }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-list-item two-line>
        <v-list-item-content>
          <v-list-item-title>State</v-list-item-title>
          <v-list-item-subtitle>{{ task.state }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-list-item three-line>
        <v-list-item-content>
          <v-list-item-title>More info</v-list-item-title>
          <v-list-item-subtitle>
            Secondary line text Lorem ipsum dolor sit amet,
          </v-list-item-subtitle>
          <v-list-item-subtitle>
            consectetur adipiscing elit.
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-card>
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
      task: {
        name: "",
        state: "",
      },
    };
  },
  created() {
    const axiosInstance = axios.create({
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    });
    axiosInstance
      .get(process.env.VUE_APP_BACK_URL + this.id)
      .then((response) => {
        this.task = response.data;
      })
      .catch((err) => console.log(err));
  },
  watch: {
    id(oldId, newId) {
      console.log(oldId, newId);
      const axiosInstance = axios.create({
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      });
      axiosInstance
        .get(process.env.VUE_APP_BACK_URL + this.id)
        .then((response) => {
          this.task = response.data;
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>

<style>
</style>