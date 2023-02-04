import { createSlice } from "@reduxjs/toolkit";

const hamStatSlice = createSlice({
  name: "hamStat",
  initialState: {
    physical: 10,
    artistic: 20,
    intelligent: 30,
    inactive: 20,
    active: 10,
    etc: 0,
  },
  reducers: {
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
  increasePhysical,
  increaseArtistic,
  increaseIntelligent,
  increaseInactive,
  increaseActive,
  increaseEtc,
} = hamStatSlice.actions;

export default hamStatSlice.reducer;

export const selectCurrentHamStat = (state) => state.hamStat;
