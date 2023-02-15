import { createSlice } from "@reduxjs/toolkit";
// import GetPetInfo from "./components/GetPetInfo";
// import increaseExp from "./components/increaseExp";
// import api from "./components/api";
// import { selectCurrentPoint } from "./pointSlice";

// const nickname = localStorage.getItem("nickname");

const ExpSlice = createSlice({
  name: "Exp",
  initialState: {
    exp: 0,
  },
  reducers: {
    getExp: (state, action) => {
        const expp = action.payload;
        console.log(123123123123)
        console.log(expp)
        state.exp = expp;
    },


   
  },
});

export const {
    getExp,

} = ExpSlice.actions;

export default ExpSlice.reducer;

// export const selectCurrentshopShow = (state) => state.shopShow;
// export const selectCurrentHamLevel = (state) => state.shopShow.level;

export const selectCurrentExp = (state) => state.Exp.exp;
// export const selectCurrentclothShow= (state) => state.shopShow.clothShow;
// export const selectCurrentdecoShow = (state) => state.shopShow.decoShow;
// export const selectCurrentmyShow = (state) => state.shopShow.myShow;