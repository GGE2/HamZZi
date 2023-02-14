import React, { useState, useEffect, useRef } from "react";
import HamExp from "./statuspages/HamExp";
import HamLevel from "./statuspages/HamLevel";

import StatCtrl from "./statuspages/StatCtrl";
import "../../../styles/HamStatus.css";
import Chart from "react-apexcharts";
import { IoStatsChart } from "react-icons/io5";
import { useSelector, useDispatch } from "react-redux";
import { selectCurrentHamStat, clearStat } from "./../../../hamStatSlice";

import api from "./../../../components/api";

const HamStatus = ({ setWear }) => {
  const hamstat = useSelector(selectCurrentHamStat);
  const level = localStorage.getItem("petLevel");
  const petId = localStorage.getItem("petId");
  const petLevel = localStorage.getItem("petLevel");
  const petName = localStorage.getItem("petName");
  const nickname = localStorage.getItem("nickname");
  const [isOpen, setIsOpen] = useState(false);
  const outside = useRef();
  const dispatch = useDispatch(clearStat());

  const handleGraduate = () => {
    api.put(`/api/pet/graduate?pet_id=${petId}`).then(() => {
      setWear({
        hat: 0,
        dress: 0,
        type: 0,
      });
      console.log("graduated");
      dispatch(clearStat());
      localStorage.setItem("petId", null);
      localStorage.setItem("petName", null);
      localStorage.setItem("petLevel", null);
      localStorage.setItem("exp", null);

      window.location.replace("/main");
    });
  };
  const state = {
    options: {
      fill: {
        opacity: 0.5,
        colors: ["#c08457"],
      },
      dataLabels: {
        enabled: true,
        background: {
          enabled: true,
          borderRadius: 2,
        },
      },
      colors: ["rgb(146, 89, 67)"],

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
        // id: 'stat',
        data: [
          hamstat.physical,
          hamstat.artistic,
          hamstat.intelligent,
          hamstat.inactive,
          hamstat.energetic,
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
        <div className="HamName">{petName}</div>

        <HamExp />

        {level === "5" && (
          <>
            <div className="graduatebtn" onClick={handleGraduate}>
              <img src="graduateB.png" alt="" />
            </div>
          </>
        )}
        <div className="HamChart">
          <Chart
            options={state.options}
            series={state.series}
            type="radar"
            height={"100%"}
          />
          <button className="StatButton">
            <IoStatsChart
              onClick={showStat}
              size={"100%"}
              color={"rgb(146, 89, 67)"}
            />
            {isOpen && (
              <div
                className="Modal"
                ref={outside}
                onClick={(e) => {
                  if (e.target === outside.current) {
                    setIsOpen(false);
                  }
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
