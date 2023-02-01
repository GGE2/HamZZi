import React, { useState } from "react";
import HamExp from "./statuspages/HamExp";
import HamLevel from "./statuspages/HamLevel";
import HamName from "./statuspages/HamName";
import "../../../styles/HamStatus.css";
import Chart from "react-apexcharts";
import StatCtrl from "./statuspages/StatCtrl";
import { Link } from "react-router-dom";
import { selectCurrentHamStat } from "./../../../hamStatSlice";
import { useSelector } from "react-redux";

const HamStatus = () => {
  const hamStat = useSelector(selectCurrentHamStat);
  // const [stat, setStat] = useState({
  //   physical: 10,
  //   artistic: 20,
  //   intelligent: 30,
  //   inactive: 20,
  //   active: 10,
  //   etc: 0,
  // });
  // const hamStat = selectCurrentHamStat();

  // console.log(stat);

  const state = {
    options: {
      colors: ["#3f8744"],
      chart: {
        // id: "basic-bar",
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
        // id: 'stat',
        data: [
          // stat.physical,
          // stat.artistic,
          // stat.intelligent,
          // stat.inactive,
          // stat.active,
          // stat.etc,
          hamStat.physical,
          hamStat.artistic,
          hamStat.intelligent,
          hamStat.inactive,
          hamStat.active,
          hamStat.etc,
        ],
      },
    ],
  };

  return (
    <div className="HamStatus">
      <HamName />
      {/* <ApexChart type="radar"  /> */}
      <div>
        <div className="Expbar">
          <HamExp />
          <HamLevel />
        </div>
      </div>
      <div className="HamChart">
        <Link
          to={{
            pathname: "/statctrl",
          }}
        >
          스탯 관리
        </Link>
        <Chart
          options={state.options}
          series={state.series}
          type="radar"
          height={"100%"}
        />
      </div>
    </div>
  );
};

export default HamStatus;
