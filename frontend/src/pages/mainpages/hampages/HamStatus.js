import React, { useState, useEffect } from "react";
import HamExp from "./statuspages/HamExp";
import HamLevel from "./statuspages/HamLevel";
import HamName from "./statuspages/HamName";
import "../../../styles/HamStatus.css";
import Chart from "react-apexcharts";
import { IoStatsChart } from "react-icons/io5";
import PetStatCtrl from "./PetStatCtrl";

const HamStatus = ({ petInfo, petStat, stat, petData, setStat }) => {
  const [isModal, setIsModal] = useState(false);
  console.log("펫데이터 출력");
  console.log(petData);
  console.log("펫 스탯 출력");
  console.log(stat);
  // const [petstat, setpetstat] = useState([petData[2]])
  // const [stat, setStat] = useState({
  // physical: petstat.physical,
  // artistic: petstat.artistic,
  // intelligent: petstat.intelligent,
  // inactive:petstat.inactive,
  // energetic: petstat.energetic,
  // etc: petstat.etc,
  // physical:0,
  // artistic:0,
  // intelligent: 0,
  // inactive: 0,
  // energetic: 0,
  // etc:0,
  // });

  useEffect(() => {
    // setpetstat(petData[2])
  }, []);

  const state = {
    options: {
      fill: {
        opacity: 0.5,
        colors: ["#3f8744"]
      },
      dataLabels: {
        enabled: true,
        background: {
          enabled: true,
          borderRadius:2,
        }
      },
      // colors: ["#3f8744"],
      
      chart: {
        toolbar: {
          show: false,
        },
      },
      xaxis: {
        categories: [
          "육체적",
          "예술적",
          "지능적",
          "비활동적",
          "활동적",
          "기타",
        ],
      },
      yaxis: {
        show: false,
      },
    },
    ///////////////////////////////////////
    series: [
      {
        id: 'stat',
        data: [
          3,0,20,0,20,30
        ],
      },
    ],
  };

  const qwe = () => {
    console.log(123);
  };

  // 모달창 노출
  const onPetStatCtrl = () => {
    // setIsCreate();
    setIsModal(true);
  };

  return (
    <div className="HamStatus">
      {/* {isModal && (
        <PetStatCtrl
          petStat={petStat}
          petInfo={petInfo}
          setIsModal={setIsModal}
          setStat={setStat}
          stat={stat}
        />
      )} */}
      <HamName />

      <div>
        <div className="Expbar">
          <HamExp />
          <HamLevel />
        </div>
      </div>

      <div className="HamChart">
        <Chart
          options={state.options}
          series={state.series}
          type="radar"
          height={"100%"}
          onClick={qwe}
        />
        <button className="StatButton">
          <IoStatsChart onClick={onPetStatCtrl} size={"100%"} color={"green"} />
        </button>
      </div>
    </div>
  );
};

export default HamStatus;
