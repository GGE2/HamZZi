import React, { useRef, useState } from "react";
// import "./TodoInput.css";
import { GrReturn } from "react-icons/gr";

const TodoInput = ({ onAdd, onSearchFunc }) => {
  const textRef = useRef();
  const [text, setText] = useState("");

  const changeInput = (evt) => {
    const { value } = evt.target;
    setText(value);
  };

  const onSubmit = (e) => {
    e.preventDefault(); // 새로고침 방지

    // if (!text) return;

    if (text.length < 1) {
      textRef.current.focus();
      return;
    }

    onAdd(text);

    setText("");
    textRef.current.focus();
  };

  const handleSearch = (e) => {
    e.preventDefault();

    if (text.length < 1) {
      textRef.current.focus();
      return;
    }
    onSearchFunc(text);
  };

  return (
    <div>
      <form className="TodoInput">
        {/* <div className="TodoInput_input"> */}
        <input
          type="text"
          value={text}
          placeholder="할 일을 입력하세요"
          onChange={changeInput}
          ref={textRef}
        />
        {/* </div> */}
        {/* Todo 추가 버튼 */}
        <div className="TodoInput_btn" onClick={onSubmit}>
          <img src="assets/buttons/addbtn.png" alt="" />
        </div>

        {/* Todo 검색 버튼 */}
        <div className="TodoSearch_btn" onClick={handleSearch}>
          <img src="assets/buttons/searchbtn.png" alt="" />
        </div>
      </form>
    </div>
  );
};

export default TodoInput;
