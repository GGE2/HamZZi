import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import GetPetInfo from "./components/GetPetInfo";
import increaseExp from "./components/increaseExp";
import api from './components/api';

const hamStatSlice = createSlice({
  name: "hamStat",
  initialState: {
    physical: 0,
    artistic: 0,
    intelligent: 0,
    inactive: 0,
    energetic: 0,
    etc: 0,
  },
  reducers: {
    clearStat: (state, action) => {
      const petId = action.payload;
      state.physical = 0;
      state.artistic = 0;
      state.intelligent = 0;
      state.inactive = 0;
      state.energetic = 0;
      state.etc = 0;
      axios
        .put(`http://3.35.88.23:8080/api/pet/stat`, {
          artistic: state.artistic,
          energetic: state.energetic,
          etc: state.etc,
          inactive: state.inactive,
          intelligent: state.intelligent,
          pet_id: petId,
          physical: state.physical,
        })
        .then((res) => {
          console.log(res);
          GetPetInfo();
        });
    },
    getCurrentStat: (state, action) => {
      const data = action.payload;
      state.physical = data.physical;
      state.artistic = data.artistic;
      state.intelligent = data.intelligent;
      state.inactive = data.inactive;
      state.energetic = data.energetic;
      state.etc = data.etc;
    },

    increasePhysical: (state, action) => {
      const petId = action.payload;
      state.physical += 1;
      api
        .put(`/api/pet/stat`, {
          artistic: 0,
          energetic: 0,
          etc: 0,
          inactive: 0,
          intelligent: 0,
          pet_id: petId,
          physical: 1,
        })
        .then((res) => {
          console.log(res);
          increaseExp();
          GetPetInfo();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    increaseArtistic: (state, action) => {
      const petId = action.payload;
      state.artistic += 1;
      api
        .put(`/api/pet/stat`, {
          artistic: 1,
          energetic: 0,
          etc: 0,
          inactive: 0,
          intelligent: 0,
          pet_id: petId,
          physical: 0,
        })
        .then(() => {
          increaseExp();
          GetPetInfo();
        });
    },
    increaseIntelligent: (state, action) => {
      const petId = action.payload;
      state.intelligent += 1;
      api
        .put(`/api/pet/stat`, {
          artistic: 0,
          energetic: 0,
          etc: 0,
          inactive: 0,
          intelligent: 1,
          pet_id: petId,
          physical: 0,
        })
        .then(() => {
          increaseExp();
          GetPetInfo();
        });
    },
    increaseInactive: (state, action) => {
      const petId = action.payload;
      state.inactive += 1;
      api
        .put(`/api/pet/stat`, {
          artistic: 0,
          energetic: 0,
          etc: 0,
          inactive: 1,
          intelligent: 0,
          pet_id: petId,
          physical: 0,
        })
        .then(() => {
          increaseExp();
          GetPetInfo();
        });
    },
    increaseEnergetic: (state, action) => {
      const petId = action.payload;
      state.energetic += 1;
      api
        .put(`/api/pet/stat`, {
          artistic: 0,
          energetic: 1,
          etc: 0,
          inactive: 0,
          intelligent: 0,
          pet_id: petId,
          physical: 0,
        })
        .then(() => {
          increaseExp();
          GetPetInfo();
        });
    },
    increaseEtc: (state, action) => {
      const petId = action.payload;
      state.etc += 1;
      api
        .put(`/api/pet/stat`, {
          artistic: 0,
          energetic: 0,
          etc: 1,
          inactive: 0,
          intelligent: 0,
          pet_id: petId,
          physical: 0,
        })
        .then(() => {
          increaseExp();
          GetPetInfo();
        });
    },
  },
});

export const {
  getCurrentStat,
  increasePhysical,
  increaseArtistic,
  increaseIntelligent,
  increaseInactive,
  increaseEnergetic,
  increaseEtc,
  clearStat,
} = hamStatSlice.actions;

export default hamStatSlice.reducer;

export const selectCurrentHamStat = (state) => state.hamStat;
