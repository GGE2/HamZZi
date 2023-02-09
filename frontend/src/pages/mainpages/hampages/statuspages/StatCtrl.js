import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  selectCurrentHamStat,
  increasePhysical,
  increaseArtistic,
  increaseInactive,
  increaseIntelligent,
  increaseEtc,
  increaseEnergetic,
  clearStat,
  getCurrentStat,
} from "./../../../../hamStatSlice";
import axios from "axios";
import GetPetInfo from "../../../../components/GetPetInfo";
import api from './../../../../components/api';

const StatCtrl = () => {
  // const [localStat, setLocalStat] = useState();
  const dispatch = useDispatch();
  const status = useSelector(selectCurrentHamStat);
  // setLocalStat({ ...status });
  // console.log(localStat);
  const nickname = localStorage.getItem("nickname");
  const petId = localStorage.getItem("petId");

  const initialPetInfo = () => {
    api
      .get(`/api/pet/${nickname}`)
      .then((res) => {
        console.log(res.data[2]);
        const physical = res.data[2].physical;
        const artistic = res.data[2].artistic;
        const intelligent = res.data[2].intelligent;
        const inactive = res.data[2].inactive;
        const energetic = res.data[2].energetic;
        const etc = res.data[2].etc;
        const data = {
          physical,
          artistic,
          intelligent,
          inactive,
          energetic,
          etc,
        };
        dispatch(getCurrentStat(data));
        console.log("DISPATCHED!!");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handlePhysical = () => {
    dispatch(increasePhysical(petId));
  };
  const handleArtistic = () => {
    dispatch(increaseArtistic(petId));
  };
  const handleIntelligent = () => {
    dispatch(increaseIntelligent(petId));
  };
  const handleInactive = () => {
    dispatch(increaseInactive(petId));
  };
  const handleEnergetic = () => {
    dispatch(increaseEnergetic(petId));
  };
  const handleEtc = () => {
    dispatch(increaseEtc(petId));
  };

  const handleClear = () => {
    dispatch(clearStat(petId));
  };
  const handleExp = () => {
    api
      .put(`/api/pet/exp?pet_id=${petId}&exp=${15}`)
      .then((res) => {
        console.log(res);
        GetPetInfo();
      });
  };

  useEffect(() => {
    initialPetInfo();
  }, []);

  return (
    <>
      (
      <div className="modalBody">
        <h3>StatCtrl</h3>
        <div>
          Physical: {status.physical}
          <button onClick={handlePhysical}>+</button>
        </div>
        <div>
          Artistic: {status.artistic}
          <button onClick={handleArtistic}>+</button>
        </div>
        <div>
          Intelligent: {status.intelligent}
          <button onClick={handleIntelligent}>+</button>
        </div>
        <div>
          Inactive: {status.inactive}
          <button onClick={handleInactive}>+</button>
        </div>
        <div>
          Energetic: {status.energetic}
          <button onClick={handleEnergetic}>+</button>
        </div>
        <div>
          Etc: {status.etc}
          <button onClick={handleEtc}>+</button>
        </div>
        <button onClick={handleClear}>CLEAR</button>
        <button onClick={handleExp}>EXP UP</button>
      </div>
      )
    </>
  );
};

export default StatCtrl;
