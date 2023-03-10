import { createSlice } from "@reduxjs/toolkit";
import api from "./components/api";

const pointSlice = createSlice({
  name: "point",
  initialState: { stat: 0 },
  reducers: {
    receivePoint: (state, action) => {
      const received = action.payload;
      state.stat = received;
    },
    grantPoint: (state, action) => {
      state.stat -= 1;
    },
    shopPoint: (state, action) => {
      const cost = action.payload;
      state.stat -= cost;
    },
  },
});

export const { receivePoint, grantPoint, shopPoint } = pointSlice.actions;

export default pointSlice.reducer;

export const selectCurrentPoint = (state) => state.point.stat;
