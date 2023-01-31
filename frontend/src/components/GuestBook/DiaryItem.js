import React, {useState, useRef} from "react";

const DiaryItem = ({ onDelete, onEdit, id, author, content, created_date }) => {
  const localContentInput = useRef();
  const [localContent, setLocalContent] = useState(content);
  const [isEdit, setIsEdit] = useState(false);
  const toggleIsEdit = () => setIsEdit(!isEdit);
  
  const handleClickRemove = () => {
    if (window.confirm(`${id}번째 일기를 정말 삭제함?`)){
      onDelete(id)
    }
  }

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
          <span className="author_info">
            | 작성자 : {author} |
          </span>
          <br />
          <span className="date">{new Date(created_date).toLocaleString()}</span>
        </div>
        <div className="content">
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
      {isEdit ? (
        <>
          <button onClick={handleQuitEdit}>수정 취소</button>
          <button onClick={handleEdit}>수정 완료</button>
        </>
      ) : (
        <>
          <button onClick={handleClickRemove}>삭제하기</button>
          <button onClick={toggleIsEdit}>수정하기</button>
        </>
      )}
    </div>
  );
};
export default DiaryItem;