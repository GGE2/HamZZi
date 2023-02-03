import React, { useRef, useState, useEffect, useLayoutEffect } from "react";
import TodoList from "../../../components/Todos/TodoList";
import TodoInput from "../../../components/Todos/TodoInput";
import { FcCalendar } from "react-icons/fc";
import DatePicker from "react-datepicker";
import Header from "./../../../components/Header";
import axios from "axios";

import "react-datepicker/dist/react-datepicker.css";
import "../../../styles/Todos.css";

const Todos = () => {
  const no = useRef(1);
  const [todos, setTodos] = useState([]);
  const [calDate, setCaldate] = useState();

  // 달력 열기
  const [startDate, setStartDate] = useState(new Date());
  const [isOpen, setIsOpen] = useState(false);

  // 날짜 설정
  const TIME_ZONE = 3240 * 10000;
  const d = new Date(startDate);

  // 달력 선택한 날짜로
  const handleChange = (e) => {
    setIsOpen(!isOpen);
    console.log(e);
    setStartDate(e);
  };
  // 달력 열기
  const handleClick = (e) => {
    e.preventDefault();
    setIsOpen(!isOpen);
  };
  
  // 처음 렌더링
  useEffect(() => {
    getTodo();
    setCaldate(new Date(+d + TIME_ZONE).toISOString().split("T")[0]);
    console.log(todos)
  }, []);

  // useEffect(() => {
  //   getTodo();
  //   console.log(todos)
  // }, [todos]);


  // 달력 날짜가 바뀔 때마다 실행
  useEffect(() => {
    setCaldate(new Date(+d + TIME_ZONE).toISOString().split("T")[0]);
  }, [startDate]);


  // function getToday() {
  //   var date = new Date();
  //   var year = date.getFullYear();
  //   var month = ("0" + (1 + date.getMonth())).slice(-2);
  //   var day = ("0" + date.getDate()).slice(-2);

  //   return year + "-" + month + "-" + day;
  // }


   // db에서 todolist 가져오기
   const getTodo = () => {
      axios.get("http://3.35.88.23:8080/api/todo").then((res) => {
        console.log(res)
        console.log(res.data);
        setTodos(res.data);
      });
  };

  const onAdd = (text) => {
    setCaldate(new Date(+d + TIME_ZONE).toISOString().split("T")[0]);
    axios.post("http://3.35.88.23:8080/api/todo", {
      content: text,
      datetime: calDate,
    }).then((res)=>console.log(res))
    // console.log(dummy)
    getTodo()
  };

  const onDel = (id) => {
    axios.delete(`http://3.35.88.23:8080/api/todo/${id}`);
    getTodo()
  };

  const onToggle = (id) => {
    axios.put(`http://3.35.88.23:8080/api/todo/check/${id}`);
    getTodo()
    setTodos(
      todos.map((todo) =>
        todo.todo_id === id
          ? {
              ...todo,
              ischeck: !todo.ischeck,
            }
          : todo
      )
    );
  };

  // 방명록 수정
  const onEdit = (id, newContent, ischeck, datetime) => {
    axios.put(`http://3.35.88.23:8080/api/todo/${id}`, {
      todo_id: id,
      content: newContent,
      datetime: datetime,
      ischeck: ischeck,
    })
    .then(res=> (res));
    // getTodo()
    setTodos(
      todos.map((todo) =>
      todo.todo_id === id ? { ...todo, content: newContent } : todo
      )
    );
  };


  return (
    <>
      <Header data={"Todo"} type={"Todo"} />
      <div className="MyBody">
      <TodoInput onAdd={onAdd} />
        <button onClick={handleClick} className="Calbutton">
          <FcCalendar />
        </button>
        {/* {startDate} */}
        {isOpen && (
          <DatePicker selected={startDate} onChange={handleChange} inline />
        )}
        <TodoList
          Caldate={calDate}
          todos={todos}
          onDel={onDel}
          onToggle={onToggle}
          onEdit={onEdit}
        />
        
      </div>
    </>
  );
};

export default Todos;
