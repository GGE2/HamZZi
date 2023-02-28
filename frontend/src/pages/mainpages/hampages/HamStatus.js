import React, { useState, useRef } from "react";
import HamExp from "./statuspages/HamExp";
import StatCtrl from "./statuspages/StatCtrl";
import "../../../styles/HamStatus.css";
import Chart from "react-apexcharts";
import { IoStatsChart } from "react-icons/io5";
import { useSelector, useDispatch } from "react-redux";
import {
  selectCurrentHamStat,
  clearStat,
  getPetType,
} from "./../../../hamStatSlice";

import api from "./../../../components/api";
import HamModal from "./HamModal";

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

  const [isModal, setIsModal] = useState(false);

  const graduatee = () => {
    ResetItem(); // 장착한 아이템 초기화
    handleGraduate(); // 졸업해서 데이터 비우기
    // 새로 만들기
    setIsModal(true);
  };

  const ResetItem = () => {
    api.put(`/api/item/clear?nickname=${nickname}`).then((res) => {
      // console.log(res)
    });
  };

  const handleGraduate = () => {
    api.put(`/api/pet/graduate?pet_id=${petId}`).then(() => {
      // console.log("graduated");
      dispatch(clearStat());
      localStorage.setItem("petId", null);
      localStorage.setItem("petName", "");
      localStorage.setItem("petLevel", "");
      localStorage.setItem("exp", null);
      dispatch(getPetType(0));
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
      {/* 모달창 띄우기 */}
      {isModal && (
        <div
          className="Modal"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal(false);
          }}
        >
          <HamModal setIsModal={setIsModal} />
        </div>
      )}

      <div className="HamStatus">
        <div className="HamName">{petName}</div>

        <HamExp graduatee={graduatee} />

        {/* {level === "5" && (
          <>
            <div className="graduatebtn" onClick={graduatee}>
              <img src="graduateB.png" alt="" />
            </div>
          </>
        )} */}
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
                className="Modal2"
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
