import { createSlice } from "@reduxjs/toolkit";
// import GetPetInfo from "./components/GetPetInfo";
// import increaseExp from "./components/increaseExp";
// import api from "./components/api";
// import { selectCurrentPoint } from "./pointSlice";

// const nickname = localStorage.getItem("nickname");

const shopSlice = createSlice({
  name: "shopShow",
  initialState: {
    hatShow: true,
    clothShow: false,
    decoShow: false,
    myShow: false,
  },
  reducers: {
    getCheckHat: (state, action) => {
      state.hatShow = true;
      state.clothShow = false;
      state.decoShow = false;
      state.myShow = false;
    },

    getCheckCloth: (state, action) => {
      state.hatShow = false;
      state.clothShow = true;
      state.decoShow = false;
      state.myShow = false;
    },

    getCheckDeco: (state, action) => {
      state.hatShow = false;
      state.clothShow = false;
      state.decoShow = true;
      state.myShow = false;
    },
    getCheckMy: (state, action) => {
      state.hatShow = false;
      state.clothShow = false;
      state.decoShow = false;
      state.myShow = true;
    },

   
  },
});

export const {
  getCheckHat,
  getCheckCloth,
  getCheckDeco,
  getCheckMy,
} = shopSlice.actions;

export default shopSlice.reducer;

export const selectCurrentshopShow = (state) => state.shopShow;
// export const selectCurrentHamLevel = (state) => state.shopShow.level;

export const selectCurrenthatShow = (state) => state.shopShow.hatShow;
export const selectCurrentclothShow= (state) => state.shopShow.clothShow;
export const selectCurrentdecoShow = (state) => state.shopShow.decoShow;
export const selectCurrentmyShow = (state) => state.shopShow.myShow;