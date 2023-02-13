import React, { useState, useRef } from "react";

import QuestModal from "./QuestModal";
import "../../styles/QuestModal.css";

const QuestItem = ({ content, completed }) => {
  const [isCheck, setCheck] = useState(false);

  const onclickQcheck = () => {
    setCheck(!isCheck);
  };

  const nickname = localStorage.getItem("nickname");
  const [isCreate, setIsCreate] = useState(false);
  const [isModal, setIsModal] = useState(false);

  const outside = useRef();
  // 캐릭터 생성 창을 누르면 모달창을 띄워서 펫 이름을 받는다.
  // 모달창 노출
  const onOpenModal = () => {
    setIsModal(true);
  };

  return (
    <div className="QuestItem">
      {isModal && (
        <div
          className="QuestModal"
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) setIsModal(false);
          }}
        >
          <QuestModal
            content={content}
            setIsModal={setIsModal}
            setIsCreate={setIsCreate}
            onClick={onOpenModal}
          />
        </div>
      )}
      {/* {content} */}
      <div
        onClick={onOpenModal}
        readOnly
        defaultValue={content}
        disabled={isCheck}
      >
        {content}
      </div>
    </div>
  );
};

export default QuestItem;
