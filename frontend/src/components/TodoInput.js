import React, { useRef, useState } from "react";
// import "./TodoInput.css";
import { FcAddRow } from "react-icons/fc";


const TodoInput = ({ onAdd }) => {
  const textRef = useRef();
  const [text, setText] = useState();

  const changeInput = (evt) => {
    const { value } = evt.target;
    setText(value);
  };

  const onSubmit = (e) => {
    e.preventDefault(); // 새로고침 방지

    if (!text) return;

    onAdd(text);

    setText("");
    textRef.current.focus();
  };
  return <div>
    <form className="TodoInput" onClick={onSubmit}>
        <input type="text" value={text} onChange={changeInput} ref={textRef} />
        <button>
                <FcAddRow className='icon' size='50'>
                </FcAddRow>
            </button>
    </form>
  </div>;
};

export default TodoInput;
