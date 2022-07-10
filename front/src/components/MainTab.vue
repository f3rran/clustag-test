<template>
  <v-container>
    <v-row class="text-center mt-4 mb-2">
      <router-link
        to="/add"
        style="text-decoration: none !important; color: white !important"
      >
        <v-btn color="success"> + add </v-btn></router-link
      >
    </v-row>
    <v-row class="text-center">
      <v-col cols="12">
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-center">Name</th>
                <th class="text-center">State</th>
                <th class="text-center">Actions</th>
              </tr>
            </thead>
            <tbody v-if="tasks.length > 0">
              <tr v-for="item in tasks" :key="item.id">
                <td>
                  <router-link
                    :to="{ name: 'Show', params: { id: item.id } }"
                    >{{ item.name }}</router-link
                  >
                </td>
                <td>{{ item.state }}</td>
                <td>
                  <router-link :to="{ name: 'Edit', params: { id: item.id } }"
                    ><v-btn depressed color="primary" class="mr-2">
                      Edit
                    </v-btn></router-link
                  >
                  <v-btn
                    color="red"
                    dark
                    @click.stop="
                      dialog = true;
                      deleteId = item.id;
                    "
                  >
                    Delete
                  </v-btn>
                </td>
              </tr>
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="2">Empty....</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-card-title class="text-h5"> Delete confirmation </v-card-title>

        <v-card-text> Are you sure you want to delete this task? </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="green darken-1" text @click.stop="closeDialog">
            Cancel
          </v-btn>

          <v-btn color="green darken-1" text @click="deleteTask(deleteId)">
            Confirm
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  name: "MainTab",
  data() {
    return {
      tasks: [],
      dialog: false,
      deleteId: 0,
    };
  },
  created: function () {
    const axiosInstance = axios.create({
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    });
    axiosInstance
      .get(process.env.VUE_APP_BACK_URL)
      .then((response) => {
        this.tasks = [...response.data];
      })
      .catch((err) => console.log(err));
  },
  methods: {
    goAddView() {
      this.$router.go("add");
    },
    deleteTask(id) {
      const axiosInstance = axios.create({
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      });
      axiosInstance
        .delete(process.env.VUE_APP_BACK_URL + id)
        .then(() => {
          axiosInstance
            .get(process.env.VUE_APP_BACK_URL)
            .then((response) => {
              this.tasks = [...response.data];
            })
            .catch((err) => console.log(err));
        })
        .catch((err) => console.log(err));
      this.dialog = false;
    },
    closeDialog() {
      try {
        this.dialog = false;
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>
