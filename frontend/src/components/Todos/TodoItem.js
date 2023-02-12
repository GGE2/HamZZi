import React, { useState, useRef } from "react";
import { FaRegTrashAlt } from "react-icons/fa";
import {
  MdOutlineCheckBox,
  MdOutlineCheckBoxOutlineBlank,
} from "react-icons/md";
import { BiDotsVerticalRounded } from "react-icons/bi";
import DropdownTodoMenu from "./DropdownTodoMenu";
import { Reorder, useMotionValue } from "framer-motion";
import { useRaisedShadow } from "./useRaisedShadow";
import $ from "jquery";
// import { OpenVidu } from "openvidu-browser";

const TodoItem = ({ todos, onDel, onToggle, onEdit }) => {
  const { todo_id, content, ischeck, datetime } = todos;
  // const [ OV, setOV ] = useState(<OpenVidu>);
  const outside = useRef();
  // session.subscribe
  const [isOpen, setIsOpen] = useState(false);
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
  const showDetail = () => {
    setIsOpen(true);
  };

  const alreadyChecked = (id) => {
    const target = "#TodoItem" + id;
    $(document).ready(function () {
      $(target).addClass("disable-div");
    });
  };

  const y = useMotionValue(0);
  const boxShadow = useRaisedShadow(y);

  return (
    <>
      <Reorder.Item id={todos} value={todos} style={{ boxShadow, y }}>
        <div
          className="TodoItem"
          id={"TodoItem" + todo_id}
          onClick={showDetail}
        >
          <>
            <div onClick={() => onToggle(todo_id)} className="CheckTodo">
              {ischeck ? (
                (alreadyChecked(todo_id), (<button className="CheckedTodo" />))
              ) : (
                <button className="UnCheckedTodo" />
              )}
            </div>

            {/* {datetime} */}

            {/* <em onClick={() => onToggle(todo_id)}>{content}</em> */}

            <div className="content">
              {isEdit ? (
                <textarea
                  ref={localContentInput}
                  value={localContent}
                  onChange={(e) => setLocalContent(e.target.value)}
                  disabled={ischeck}
                />
              ) : (
                <p disabled={ischeck}>{content}</p>
              )}
            </div>

            <div className="TodoSetting">
              <DropdownTodoMenu
                todo_id={todo_id}
                onEdit={onEdit}
                onDel={onDel}
                toggleIsEdit={toggleIsEdit}
              />
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
        </div>{" "}
      </Reorder.Item>
    </>
  );
};

export default TodoItem;
