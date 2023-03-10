import React, { useState, useRef, useEffect } from "react";
import { AiOutlineDownload } from "react-icons/ai";
import "../../../styles/Modal.css";
import html2canvas from "html2canvas";
import api from "./../../../components/api";
import { useSelector } from "react-redux";
import {
  selectCurrentHamLevel,
  selectCurrentPetType,
} from "../../../hamStatSlice";

// GrAdd
const HamOutfit = ({ Wear, getAllProfile }) => {
  // const type = useSelector(selectCurrentPetType);
  const nickname = localStorage.getItem("nickname");
  // console.log(useSelector(selectCurrentHamLevel));
  const level = useSelector(selectCurrentHamLevel);
  const type = useSelector(selectCurrentPetType);
  const [isCreate, setIsCreate] = useState(false);
  const [isModal, setIsModal] = useState(false);
  // console.log(Wear);

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
    // console.log(isCreate);
  }, []);

  useEffect(() => {
    // window.location.replace("/main")
  }, [level]);

  const onCapture = () => {
    // console.log("onCapture");
    html2canvas(document.getElementById("div")).then((canvas) => {
      onSaveAs(canvas.toDataURL("image/png"), "image-download.png", 0.1);
    });
  };

  const onSaveAs = (uri, filename) => {
    // console.log("onSaveAs");
    var link = document.createElement("a");
    document.body.appendChild(link);
    link.href = uri;
    link.download = filename;
    link.click();
    document.body.removeChild(link);
  };

  const ResetItem = () => {
    api.put(`/api/item/clear?nickname=${nickname}`).then((res) => {
      // console.log(res)
      getAllProfile();
    });
  };

  return (
    <>
      <div className="HamOutfit" id="div">
        <div className="HamBody">
          <img
            src={`wearlist/${
              JSON.stringify(level) +
              Wear.hat +
              Wear.dress +
              JSON.stringify(type)
            }.gif`}
            alt=""
          />
        </div>
        <div className="decoImg">
          <img src={`chara/deco/deco${Wear.background}.png`} alt="" />
        </div>
      </div>
      <div className="takephoto">
        <AiOutlineDownload onClick={onCapture} size={"100%"} />
      </div>
      <div className="resetItem">
        <img src="reset.png" alt="" onClick={ResetItem} />
      </div>
      {/* )} */}
    </>
  );
};

export default HamOutfit;
