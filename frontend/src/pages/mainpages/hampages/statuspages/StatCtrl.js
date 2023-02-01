import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { selectCurrentHamStat } from "./../../../../hamStatSlice";
import {
  increasePhysical,
  increaseArtistic,
  increaseIntelligent,
  increaseInactive,
  increaseActive,
  increaseEtc,
} from "./../../../../hamStatSlice";
import {
  grantPoint,
  receivePoint,
  selectCurrentPoint,
} from "./../../../../pointSlice";

const StatCtrl = () => {
  const hamStat = useSelector(selectCurrentHamStat);
  const point = useSelector(selectCurrentPoint);
  console.log(point);
  const dispatch = useDispatch();

  const handlePhysical = () => {
    dispatch(increasePhysical());
    dispatch(grantPoint());
  };
  const handleArtistic = () => {
    dispatch(increaseArtistic());
    dispatch(grantPoint());
  };
  const handleIntelligent = () => {
    dispatch(increaseIntelligent());
    dispatch(grantPoint());
  };
  const handleInactive = () => {
    dispatch(increaseInactive());
    dispatch(grantPoint());
  };
  const handleActive = () => {
    dispatch(increaseActive());
    dispatch(grantPoint());
  };
  const handleEtc = () => {
    dispatch(increaseEtc());
    dispatch(grantPoint());
  };
  const getPoint = () => {
    dispatch(receivePoint(1));
  };

  return (
    <div>
      StatCtrl
      <button onClick={getPoint}>테스트용 POINT 받기</button>
      <div>
        육체적: {hamStat.physical}
        {point > 0 ? <button onClick={handlePhysical}>+</button> : ""}
      </div>
      <div>
        예술적: {hamStat.artistic}
        {point > 0 ? <button onClick={handleArtistic}>+</button> : ""}
      </div>
      <div>
        지능적: {hamStat.intelligent}
        {point > 0 ? <button onClick={handleIntelligent}>+</button> : ""}
      </div>
      <div>
        비활동적: {hamStat.inactive}
        {point > 0 ? <button onClick={handleInactive}>+</button> : ""}
      </div>
      <div>
        활동적: {hamStat.active}
        {point > 0 ? <button onClick={handleActive}>+</button> : ""}
      </div>
      <div>
        기타: {hamStat.etc}
        {point > 0 ? <button onClick={handleEtc}>+</button> : ""}
      </div>
    </div>
  );
};

export default StatCtrl;
