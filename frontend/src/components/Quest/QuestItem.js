import React, { useState, useRef } from "react";
import $ from "jquery";

import QuestModal from "./QuestModal";
import "../../styles/QuestModal.css";

const QuestItem = ({ content, completed, questId, point }) => {
  const nickname = localStorage.getItem("nickname");
  const [isCreate, setIsCreate] = useState(false);
  const [isModal, setIsModal] = useState(false);

  const outside = useRef();
  // 캐릭터 생성 창을 누르면 모달창을 띄워서 펫 이름을 받는다.
  // 모달창 노출
  const onOpenModal = () => {
    setIsModal(true);
  };

  console.log(completed);

  const isCompleted = (id) => {
    const target = id;
    $(document).ready(function () {
      $(target).addClass("disable-div");
    });
  };

  return (
    <>
      <div className="QuestItem">
        {completed ? isCompleted(questId) : null}
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
              point={point}
              setIsModal={setIsModal}
              setIsCreate={setIsCreate}
              onClick={onOpenModal}
            />
          </div>
        )}
        <div id={questId} onClick={onOpenModal} defaultValue={content}>
          {content}
        </div>
      </div>
    </>
  );
};

export default QuestItem;
