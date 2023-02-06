import React from "react";
import { useSelector, useDispatch, useEffect } from "react-redux";
import {
  selectCurrentHamStat,
  increasePhysical,
  increaseArtistic,
  increaseInactive,
  getCurrentStat,
} from "./../../../../hamStatSlice";
import axios from "axios";

const StatCtrl = () => {
  const dispatch = useDispatch();
  const status = useSelector(selectCurrentHamStat);
  const nickname = localStorage.getItem("nickname");
  const physical = () => {
    dispatch(increasePhysical());
    console.log(status);
    axios
      .put(`http://3.35.88.23:8080/api/pet/stat`, {
        artistic: status.artistic,
        energetic: status.energetic,
        etc: status.etc,
        inactive: status.inactive,
        intelligent: status.intelligent,
        pet_id: 2,
        physical: status.physical,
      })
      .then(() => {});
    // axios.get(`http://3.35.88.23:8080/api/pet/${nickname}`).then((res) => {
    //   console.log(res.data[2]);
    //   const physical = res.data[2].physical;
    //   const artistic = res.data[2].artistic;
    //   const intelligent = res.data[2].intelligent;
    //   const inactive = res.data[2].inactive;
    //   const energetic = res.data[2].energetic;
    //   const etc = res.data[2].etc;
    //   const data = {
    //     physical,
    //     artistic,
    //     intelligent,
    //     inactive,
    //     energetic,
    //     etc,
    //   };
    //   console.log(data);
    //   dispatch(increasePhysical({ ...data }));
    // });
  };

  return (
    <>
      (
      <div className="modalBody">
        <h3>StatCtrl</h3>
        <div>
          Physical: {status.physical}
          <button onClick={physical}>+</button>
        </div>
        <div>
          Artistic: {status.artistic}
          <button onClick={increaseArtistic}>+</button>
        </div>
        <div>
          Intelligent: {status.intelligent}
          <button onClick={increasePhysical}>+</button>
        </div>
        <div>
          Inactive: {status.inactive}
          <button onClick={increasePhysical}>+</button>
        </div>
        <div>
          Active: {status.active}
          <button onClick={increasePhysical}>+</button>
        </div>
        <div>
          Etc: {status.etc}
          <button onClick={increasePhysical}>+</button>
        </div>
      </div>
      )
    </>
  );
};

export default StatCtrl;
