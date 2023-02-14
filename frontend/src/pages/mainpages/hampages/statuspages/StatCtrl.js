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
  getPetType,
} from "./../../../../hamStatSlice";
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
        console.log("스탯");
        console.log(res.data);
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
        dispatch(getPetType(res.data.petInfo.type));
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
      dispatch(getPetType(res.data.petInfo.type));
    });
  };
  const handleArtistic = () => {
    dispatch(increaseArtistic(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
      dispatch(getPetType(res.data.petInfo.type));
    });
  };
  const handleIntelligent = () => {
    dispatch(increaseIntelligent(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
      dispatch(getPetType(res.data.petInfo.type));
    });
  };
  const handleInactive = () => {
    dispatch(increaseInactive(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
      dispatch(getPetType(res.data.petInfo.type));
    });
  };
  const handleEnergetic = () => {
    dispatch(increaseEnergetic(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
      dispatch(getPetType(res.data.petInfo.type));
    });
  };
  const handleEtc = () => {
    dispatch(increaseEtc(petId));
    dispatch(grantPoint());
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
      dispatch(getPetType(res.data.petInfo.type));
    });
  };

  const handleClear = () => {
    dispatch(clearStat(petId));
    api.get(`/api/pet/${nickname}`).then((res) => {
      dispatch(getPetLevel(res.data.pet.level));
      dispatch(getPetType(res.data.petInfo.type));
    });
  };
  const handleExp = () => {
    api
      .put(`/api/pet/exp?pet_id=${petId}&exp=${15}&nickname=${nickname}`)
      .then((res) => {
        console.log("경험치 올리기", res);
        GetPetInfo();
        api.get(`/api/pet/${nickname}`).then((res) => {
          console.log("펫 레벨 가져오기", res.data.pet.level);
          dispatch(getPetLevel(res.data.pet.level));
          dispatch(getPetType(res.data.petInfo.type));
        });
      });
  };

  useEffect(() => {
    initialPetInfo();
  }, []);

  return (
    <>
      (
      <div className="modalbody">
        {/* <div className="StatCtrlBody"> */}
        <h1>스탯창</h1>
        <div className="StatElement">
          Physical
          <div>
            {status.physical}
            {point > 0 ? <button onClick={handlePhysical}>+</button> : null}
          </div>
        </div>
        <div className="StatElement">
          Artistic
          <div>
            {status.artistic}
            {point > 0 ? <button onClick={handleArtistic}>+</button> : null}
          </div>
        </div>
        <div className="StatElement">
          Intelligent
          <div>
            {status.intelligent}
            {point > 0 ? <button onClick={handleIntelligent}>+</button> : null}
          </div>
        </div>
        <div className="StatElement">
          Inactive
          <div>
            {status.inactive}
            {point > 0 ? <button onClick={handleInactive}>+</button> : null}
          </div>
        </div>
        <div className="StatElement">
          Energetic
          <div>
            {status.energetic}
            {point > 0 ? <button onClick={handleEnergetic}>+</button> : null}
          </div>
        </div>
        <div className="StatElement">
          Etc
          <div>
            {status.etc}
            {point > 0 ? <button onClick={handleEtc}>+</button> : null}
          </div>
        </div>
        <button onClick={handleClear}>CLEAR</button>
        <button onClick={handleExp}>EXP UP</button>
        {/* </div> */}
      </div>
      )
    </>
  );
};

export default StatCtrl;
