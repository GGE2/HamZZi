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
  getPetLevel,
} from "./../../../../hamStatSlice";
import axios from "axios";
import GetPetInfo from "../../../../components/GetPetInfo";
import api from "./../../../../components/api";
import { selectCurrentPoint } from "./../../../../pointSlice";
import { grantPoint } from "./../../../../pointSlice";

const StatCtrl = () => {
  // const [localStat, setLocalStat] = useState();
  const dispatch = useDispatch();
  const status = useSelector(selectCurrentHamStat);
  // setLocalStat({ ...status });
  // console.log(localStat);
  const nickname = localStorage.getItem("nickname");
  const petId = localStorage.getItem("petId");
  const point = useSelector(selectCurrentPoint);

  const initialPetInfo = () => {
    api
      .get(`/api/pet/${nickname}`)
      .then((res) => {
        console.log(res.data.petStat);
        const physical = res.data.petStat.physical;
        const artistic = res.data.petStat.artistic;
        const intelligent = res.data.petStat.intelligent;
        const inactive = res.data.petStat.inactive;
        const energetic = res.data.petStat.energetic;
        const etc = res.data.petStat.etc;
        const level = res.data.pet.level;
        const data = {
          physical,
          artistic,
          intelligent,
          inactive,
          energetic,
          etc,
        };
        dispatch(getCurrentStat(data));
        dispatch(getPetLevel(level));
        console.log("DISPATCHED!!");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handlePhysical = () => {
    dispatch(increasePhysical(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
    });
  };
  const handleArtistic = () => {
    dispatch(increaseArtistic(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
    });
  };
  const handleIntelligent = () => {
    dispatch(increaseIntelligent(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
    });
  };
  const handleInactive = () => {
    dispatch(increaseInactive(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
    });
  };
  const handleEnergetic = () => {
    dispatch(increaseEnergetic(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
    });
  };
  const handleEtc = () => {
    dispatch(increaseEtc(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
    });
  };

  const handleClear = () => {
    dispatch(clearStat(petId));
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
    });
  };
  const handleExp = () => {
    api.put(`/api/pet/exp?pet_id=${petId}&exp=${15}`).then((res) => {
      console.log(res);
      GetPetInfo();
      api.get(`/api/pet/${nickname}`).then((res) => {
        dispatch(getPetLevel(res.data.pet.level));
      });
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
          {point > 0 ? <button onClick={handlePhysical}>+</button> : null}
        </div>
        <div>
          Artistic: {status.artistic}
          {point > 0 ? <button onClick={handleArtistic}>+</button> : null}
        </div>
        <div>
          Intelligent: {status.intelligent}
          {point > 0 ? <button onClick={handleIntelligent}>+</button> : null}
        </div>
        <div>
          Inactive: {status.inactive}
          {point > 0 ? <button onClick={handleInactive}>+</button> : null}
        </div>
        <div>
          Energetic: {status.energetic}
          {point > 0 ? <button onClick={handleEnergetic}>+</button> : null}
        </div>
        <div>
          Etc: {status.etc}
          {point > 0 ? <button onClick={handleEtc}>+</button> : null}
        </div>
        <button onClick={handleClear}>CLEAR</button>
        <button onClick={handleExp}>EXP UP</button>
      </div>
      )
    </>
  );
};

export default StatCtrl;
