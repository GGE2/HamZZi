<<<<<<< HEAD
import React, { useState } from "react";
=======
import React from "react";
>>>>>>> feature/mobile/homepage
import HamExp from "./statuspages/HamExp";
import HamLevel from "./statuspages/HamLevel";
import HamName from "./statuspages/HamName";
import "../../../styles/HamStatus.css";
<<<<<<< HEAD
import Chart from "react-apexcharts";

const HamStatus = () => {
  const [stat, setStat] = useState({
    physical: 10,
    artistic: 20,
    intelligent: 30,
    inactive: 20,
    active:10,
    etc: 0
  })

  const state = {
    options: {
      colors:['#3f8744'],
      chart: {
        // id: "basic-bar",
        toolbar: {
          show: false,
        },
      },
      xaxis: {
        categories: ["육체적", "예술적", "지능적", "비활동적", "활동적", "기타"],
      },
      yaxis: {
        show: false,
      },
    },
    ///////////////////////////////////////
    series: [
      {
        id: 'stat',
        data: [stat.physical, stat.artistic, stat.intelligent, stat.inactive, stat.active, stat.etc],
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
        <Chart
          options={state.options}
          series={state.series}
          type="radar"
          height={"100%"}
        />
      </div>
=======

const HamStatus = () => {
  return (
    <div className="HamStatus">
      HamStatus
      <HamExp />
      <HamLevel />
      <HamName />
>>>>>>> feature/mobile/homepage
    </div>
  );
};

export default HamStatus;
