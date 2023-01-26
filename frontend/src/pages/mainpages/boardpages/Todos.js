import React, { useRef, useState } from "react";
import TodoList from "../../../components/TodoList";
import TodoInput from "../../../components/TodoInput";
import "../../../styles/Todos.css";
import { FcCalendar } from "react-icons/fc";
import DatePicker from "react-datepicker";
import Header from './../../../components/Header';

const Todos = () => {
  const no = useRef(1);
  const [todos, setTodos] = useState([]);

  const onAdd = (text) => {
    setTodos([
      ...todos,
      {
        id: no.current++,
        text: text,
        done: false,
      },
    ]);
  };

  const onDel = (id) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  const onToggle = (id) => {
    setTodos(
      todos.map((todo) =>
        todo.id === id
          ? {
              ...todo,
              done: !todo.done,
            }
          : todo
      )
    );
  };

  // 달력 열기
  const [startDate, setStartDate] = useState(new Date());
  const [isOpen, setIsOpen] = useState(false);
  const handleChange = (e) => {
    setIsOpen(!isOpen);
    console.log(e)
    setStartDate(e);
  };
  const handleClick = (e) => {
    e.preventDefault();
    setIsOpen(!isOpen);
    
  };

  return (
    <div className="Todos">
      {/* <h1> list</h1> */}
      <Header data={'Todo'} />
      <button onClick={handleClick} className="Todobutton">
        <FcCalendar />
      </button>
      {isOpen && (
        <DatePicker selected={startDate} onChange={handleChange} inline />)}
      {/* {startDate} */}
      <TodoInput onAdd={onAdd} />
      <TodoList todos={todos} onDel={onDel} onToggle={onToggle} />
    </div>
  );
};

export default Todos;
