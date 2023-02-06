import React, { useState, useEffect, useRef } from "react";
import HamExp from "./statuspages/HamExp";
import HamLevel from "./statuspages/HamLevel";
import HamName from "./statuspages/HamName";
import "../../../styles/HamStatus.css";
import Chart from "react-apexcharts";
import { IoStatsChart } from "react-icons/io5";
import { useSelector } from "react-redux";
import { selectCurrentHamStat } from "./../../../hamStatSlice";
import StatCtrl from "./statuspages/StatCtrl";

const HamStatus = () => {
  const hamstat = useSelector(selectCurrentHamStat);
  const [isOpen, setIsOpen] = useState(false);
  const outside = useRef();
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
          hamstat.physical,
          hamstat.artistic,
          hamstat.intelligent,
          hamstat.inactive,
          hamstat.active,
          hamstat.etc,
        ],
      },
    ],
  };

  const showStat = () => {
    setIsOpen(true);
  };

  return (
    <>
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
          <button className="StatButton">
            <IoStatsChart onClick={showStat} size={"100%"} color={"green"} />
            {isOpen && (
              <div
                className="Modal"
                ref={outside}
                onClick={(e) => {
                  if (e.target === outside.current) setIsOpen(false);
                }}
              >
                <StatCtrl />
              </div>
            )}
          </button>
        </div>
      </div>
    </>
  );
};

export default HamStatus;
