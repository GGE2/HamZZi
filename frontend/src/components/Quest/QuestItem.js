import React, { useState } from "react";
import {
  RiCheckboxBlankCircleLine,
  RiCheckboxCircleLine,
} from "react-icons/ri";

const QuestItem = ({ content }) => {
  const [isCheck, setCheck] = useState(false);

  const onclickQcheck = () => {
    setCheck(!isCheck);
  };

  return (
    <div className="QuestItem">
      <div className="QuestItemButton">
        {isCheck ? (
          <RiCheckboxCircleLine  color="pink"  size={"100%"} onClick={onclickQcheck} />
        ) : (
          <RiCheckboxBlankCircleLine  size={"100%"} onClick={onclickQcheck} />
        )}
      </div>
      <textarea readonly defaultValue={content} disabled={isCheck}></textarea> 
    </div>
  );
};


export default QuestItem;
