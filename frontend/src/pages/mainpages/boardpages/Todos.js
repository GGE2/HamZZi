import React, { useRef, useState } from "react";
import TodoList from "../../../components/TodoList";
import TodoInput from "../../../components/TodoInput";
import "../../../styles/Todos.css";
import { FcCalendar } from "react-icons/fc";
import DatePicker from "react-datepicker";
<<<<<<< HEAD
import Header from "./../../../components/Header";
=======
import Header from './../../../components/Header';
>>>>>>> 862d795ade2ac4e38d20670b3253c2f34ecca8e8

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
<<<<<<< HEAD
    console.log(e);
=======
    console.log(e)
>>>>>>> 862d795ade2ac4e38d20670b3253c2f34ecca8e8
    setStartDate(e);
  };
  const handleClick = (e) => {
    e.preventDefault();
    setIsOpen(!isOpen);
<<<<<<< HEAD
  };

  return (
    <>
     <Header data={"Todo"} type={"Todo"} />
          
      <div className="MyBody">
      <button onClick={handleClick} className="Todobutton">
            <FcCalendar />
          </button>
        
          {/* <h1> list</h1> */}
         
          {isOpen && (
            <DatePicker selected={startDate} onChange={handleChange} inline />
          )}
          <TodoInput onAdd={onAdd} />
          <TodoList todos={todos} onDel={onDel} onToggle={onToggle} />
        
      </div>
    </>
=======
    
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
>>>>>>> 862d795ade2ac4e38d20670b3253c2f34ecca8e8
  );
};

export default Todos;
