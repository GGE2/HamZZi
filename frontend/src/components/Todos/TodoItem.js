import React, { useState, useRef } from "react";
import { FaRegTrashAlt } from "react-icons/fa";
import {
  MdOutlineCheckBox,
  MdOutlineCheckBoxOutlineBlank,
} from "react-icons/md";
import { BiDotsVerticalRounded } from "react-icons/bi";
import DropdownTodoMenu from './DropdownTodoMenu';
// import { OpenVidu } from "openvidu-browser";


const TodoItem = ({ todos, onDel, onToggle, onEdit }) => {
  const { todo_id, content, ischeck, datetime } = todos;
  // const [ OV, setOV ] = useState(<OpenVidu>);
 

  // session.subscribe


  const localContentInput = useRef();
  const [localContent, setLocalContent] = useState(content);
  const [isEdit, setIsEdit] = useState(false);
  const toggleIsEdit = () => {
    setIsEdit(!isEdit);
  };

  const handleQuitEdit = () => {
    setIsEdit(false);
    setLocalContent(content);
  };

  const handleEdit = () => {
    if (localContent.length < 1) {
      localContentInput.current.focus();
      return;
    }
    onEdit(todo_id, localContent, ischeck, datetime);
    toggleIsEdit();

    // if (window.confirm(`${todo_id}번 째 일기를 수정하시겠습니까?`)) {
    //   onEdit(todo_id, localContent);
    //   toggleIsEdit();
    // }
  };

  return (
    <div className="TodoItem">
      <>
        <div onClick={() => onToggle(todo_id)} className="CheckTodo">
          {ischeck ? (
       
            <MdOutlineCheckBox size={"100%"} />
            
         
          ) : (
        
            <MdOutlineCheckBoxOutlineBlank size={"100%"} />
         
         
          )}
        </div>
        {/* {datetime} */}

        {/* <em onClick={() => onToggle(todo_id)}>{content}</em> */}
        <div className="TodoSetting"><DropdownTodoMenu todo_id={todo_id}  onEdit={onEdit} onDel={onDel} toggleIsEdit={toggleIsEdit}/></div>
        

        <div className="content" >
          {isEdit ? (
            <textarea
              ref={localContentInput}
              value={localContent}
              onChange={(e) => setLocalContent(e.target.value)}
              disabled={ischeck}
            />
          ) : (
            <p
        
              disabled={ischeck}
            >{content}</p>
            
            
          )}
        </div>

        {isEdit ? (
          <>
            <button onClick={handleQuitEdit}>수정 취소</button>
            <button onClick={handleEdit}>수정 완료</button>
          </>
        ) : (
          <>
            {/* <button onClick={() => onDel(todo_id)}>
              <FaRegTrashAlt color="rgb(175,169,169)" size="20" />
            </button>
            <button onClick={toggleIsEdit}>수정하기</button> */}
          </>
        )}
      </>
    </div>
  );
};

export default TodoItem;
