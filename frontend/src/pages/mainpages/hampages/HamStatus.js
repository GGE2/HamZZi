import React, { useState } from "react";
import HamExp from "./statuspages/HamExp";
import HamLevel from "./statuspages/HamLevel";
import HamName from "./statuspages/HamName";
import "../../../styles/HamStatus.css";
import Chart from "react-apexcharts";
import { IoStatsChart } from "react-icons/io5";

const HamStatus = () => {
  const [stat, setStat] = useState({
    physical: 10,
    artistic: 20,
    intelligent: 30,
    inactive: 20,
    active: 10,
    etc: 0,
  });

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
<<<<<<< HEAD
        data: [
          stat.physical,
          stat.artistic,
          stat.intelligent,
          stat.inactive,
          stat.active,
          stat.etc,
        ],
=======
        data: [stat.physical, stat.artistic, stat.intelligent, stat.inactive, stat.active, stat.etc],
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352
      },
    ],
  };

  const qwe = () => {
    console.log(123);
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
          onClick={qwe}
        />
        <button className="StatButton">
          <IoStatsChart size={'100%'} color={'green'}/>
        </button>
      </div>
    </div>
  );
};

export default HamStatus;
