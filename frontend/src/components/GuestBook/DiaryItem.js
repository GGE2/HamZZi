import React, { useEffect, useState, useRef } from "react";
import { BiDotsVerticalRounded } from "react-icons/bi";
import DropdownMenu from "./DropdownMenu";

const DiaryItem = ({ onDelete, onEdit, id, author, content, created_date }) => {
  const localContentInput = useRef();
  const [localContent, setLocalContent] = useState(content);
  const [isEdit, setIsEdit] = useState(false);
  const toggleIsEdit = () => setIsEdit(!isEdit);



  const handleClickRemove = () => {
    if (window.confirm(`${id}번째 일기를 정말 삭제함?`)) {
      onDelete(id);
    }
  };

  const handleQuitEdit = () => {
    setIsEdit(false);
    setLocalContent(content);
  };

  const handleEdit = () => {
    if (localContent.length < 5) {
      localContentInput.current.focus();
      return;
    }

    if (window.confirm(`${id}번 째 일기를 수정하시겠습니까?`)) {
      onEdit(id, localContent);
      toggleIsEdit();
    }
  };

  return (
    <div className="DiaryItem">
      <div className="info">
        <div className="author">{author}</div>
        <div>
        <div className="created_date">{created_date}</div>
        <button>삭제</button>
        </div>
        {/* <BiDotsVerticalRounded /> */}
      </div>
      <div className="DiaryItemContent">
        {isEdit ? (
          <textarea
            ref={localContentInput}
            value={localContent}
            onChange={(e) => setLocalContent(e.target.value)}
          />
        ) : (
          content
        )}
      </div>
      
          {/* <DropdownMenu /> */}
      {/* {isEdit ? (
        <>
          <button onClick={handleQuitEdit}>수정 취소</button>
          <button onClick={handleEdit}>수정 완료</button>
        </>
      ) : (
        <>
          <button onClick={handleClickRemove}>삭제하기</button>
          <button onClick={toggleIsEdit}>수정하기</button>
        </>
      )} */}
    </div>
  );
};
export default DiaryItem;
