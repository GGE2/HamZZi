import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const hamStatSlice = createSlice({
  name: "hamStat",
  initialState: {
    physical: 0,
    artistic: 0,
    intelligent: 0,
    inactive: 0,
    active: 0,
    etc: 0,
  },
  reducers: {
    getCurrentStat: (state, action) => {
      const data = action.payload;
      state.physical = data.physical;
      state.artistic = data.artistic;
      state.intelligent = data.intelligent;
      state.inactive = data.inactive;
      state.active = data.active;
      state.etc = data.etc;
    },

    increasePhysical: (state, action) => {
      state.physical += 1;
    },
    increaseArtistic: (state, action) => {
      state.artistic += 1;
    },
    increaseIntelligent: (state, action) => {
      state.intelligent += 1;
    },
    increaseInactive: (state, action) => {
      state.inactive += 1;
    },
    increaseActive: (state, action) => {
      state.active += 1;
    },
    increaseEtc: (state, action) => {
      state.etc += 1;
    },
  },
});

export const {
  getCurrentStat,
  increasePhysical,
  increaseArtistic,
  increaseIntelligent,
  increaseInactive,
  increaseActive,
  increaseEtc,
} = hamStatSlice.actions;

export default hamStatSlice.reducer;

export const selectCurrentHamStat = (state) => state.hamStat;
