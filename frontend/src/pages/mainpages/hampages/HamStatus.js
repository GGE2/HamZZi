import React, { useState } from "react";
import HamExp from "./statuspages/HamExp";
import HamLevel from "./statuspages/HamLevel";
import HamName from "./statuspages/HamName";
import "../../../styles/HamStatus.css";
import Chart from "react-apexcharts";

import Chart from "react-apexcharts";

const HamStatus = () => {
<<<<<<< HEAD
  const [stat, setStat] = useState({
    physical: 10,
    artistic: 20,
    intelligent: 30,
    inactive: 20,
    active:10,
    etc: 0
  })

=======
>>>>>>> 862d795ade2ac4e38d20670b3253c2f34ecca8e8
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
<<<<<<< HEAD
        id: 'stat',
        data: [stat.physical, stat.artistic, stat.intelligent, stat.inactive, stat.active, stat.etc],
=======
        data: [30, 40, 45, 50, 49, 60],
>>>>>>> 862d795ade2ac4e38d20670b3253c2f34ecca8e8
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
<<<<<<< HEAD
          height={"100%"}
=======
          width={"100%"}
          // height={"100%"}
>>>>>>> 862d795ade2ac4e38d20670b3253c2f34ecca8e8
        />
      </div>
    </div>
  );
};

export default HamStatus;
