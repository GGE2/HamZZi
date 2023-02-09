import axios from "axios";

const api = axios.create({
  baseURL: "http://i8d209.p.ssafy.io:3000",
});

export default api;