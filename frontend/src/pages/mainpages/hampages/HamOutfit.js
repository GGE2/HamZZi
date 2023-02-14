import React, { useState, useRef, useEffect } from "react";
import { GrAdd } from "react-icons/gr";
import HamModal from "./HamModal";
// import SetNickName from './../../loginpages/SetNickName';
import "../../../styles/Modal.css";
import html2canvas from "html2canvas";
import api from "./../../../components/api";
import { useSelector } from "react-redux";
import { selectCurrentHamLevel, selectCurrentPetType } from "../../../hamStatSlice";

// GrAdd
const HamOutfit = ({ Wear }) => {
  const type = useSelector(selectCurrentPetType);
  const nickname = localStorage.getItem("nickname");
  console.log(useSelector(selectCurrentHamLevel));
  const level = useSelector(selectCurrentHamLevel);
  const [isCreate, setIsCreate] = useState(false);
  const [isModal, setIsModal] = useState(false);
  console.log(Wear)
  console.log('타입')
  console.log(type)

  const outside = useRef();
  // 캐릭터 생성 창을 누르면 모달창을 띄워서 펫 이름을 받는다.
  // 모달창 노출
  const onclickCreatePet = () => {
    // setIsCreate();
    setIsModal(true);
  };
  useEffect(() => {
    api.get(`/api/pet/${nickname}`).then((res) => {
      setIsCreate(res.data.pet);
    });
    console.log(isCreate);
  }, []);

  useEffect(() => {
    // window.location.replace("/main")
  }, [level]);

  const onCapture = () => {
    console.log("onCapture");
    html2canvas(document.getElementById("div")).then((canvas) => {
      onSaveAs(canvas.toDataURL("image/png"), "image-download.png", 0.1);
    });
  };

  const onSaveAs = (uri, filename) => {
    console.log("onSaveAs");
    var link = document.createElement("a");
    document.body.appendChild(link);
    link.href = uri;
    link.download = filename;
    link.click();
    document.body.removeChild(link);
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
          <HamModal setIsModal={setIsModal} setIsCreate={setIsCreate} />
        </div>
      )}
      {/* 펫이 없으면 만드는 추가 */}
      {!isCreate && (
        <div className="HamOutfit">
          <div className="PetCreate" onClick={onclickCreatePet}>
            <GrAdd size={"50%"} />
          </div>
        </div>
      )}
      {/* 펫이 있으면 펫 출력 */}
      {isCreate && (
        <div className="HamOutfit" id="div">
          <div className="HamBody">
            <img
              src={`wearlist/${
                JSON.stringify(level) + Wear.hat + Wear.dress + JSON.stringify(type)
              }.gif`}
              alt=""
            />
            {/* </Draggable> */}
          </div>
    

          {/* 사진 찍기 */}
          {/* <button className="test" onClick={onCapture}>
            click
          </button> */}
        </div>
      )}
    </>
  );
};

export default HamOutfit;
