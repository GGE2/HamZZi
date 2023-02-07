import React, { useEffect, useState } from "react";
import HamOutfit from "./hampages/HamOutfit";
import HamStatus from "./hampages/HamStatus";
import HamHeader from "./hampages/HamHeader";
import "../../styles/Ham.css";
import axios from "axios";

const Ham = () => {
  const nickname = localStorage.getItem("nickname");
  const [petInfo, setPetInfo] = useState();
  const [petStat, setPetStat] = useState();
  const [petData, setPetData] = useState();
  const [stat, setStat] = useState({});

  // useEffect(()=>{
  //   onGetPetInfo()
  // },
  // [stat])

  // 펫 정보 가져오기 api
  // 닉네임으로 가져옴. nickname
  const onGetPetInfo = async () => {
    await axios
      .get(`http://3.35.88.23:8080/api/pet/${nickname}?nickname=${nickname}`)
      .then((res) => {
        console.log(" 펫 정보 가져오기 api");
        console.log(res.data[0]);
        console.log(res.data[2]);
        // setPetInfo(res.data[0])
        // setPetStat(res.data[2])
        setPetData(res.data);
        const physical = res.data[2].physical;
        const artistic = res.data[2].artistic;
        const intelligent = res.data[2].intelligent;
        const inactive = res.data[2].inactive;
        const energetic = res.data[2].energetic;
        const etc = res.data[2].etc;
        const petStat_id = res.data[2].petStat_id;
        const data = {
          physical,
          artistic,
          intelligent,
          inactive,
          energetic,
          etc,
          petStat_id,
        };
        setStat(data);
      });
  };
  useEffect(() => {
    // onGetPetInfo();
  }, []);

  return (
    <div className="Ham">
      <HamHeader />

      <HamOutfit petInfo={petInfo} />
      <HamStatus
        petInfo={petInfo}
        petStat={petStat}
        stat={stat}
        petData={petData}
        setStat={setStat}
      />
    </div>
  );
};

export default Ham;
