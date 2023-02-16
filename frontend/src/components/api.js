import axios from "axios";

const api = axios.create({
  baseURL: "https://i8d209.p.ssafy.io:9090",
});

export default api;
