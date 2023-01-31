import React, { useRef, useState, useEffect } from "react";
import TodoList from "../../../components/Todos/TodoList";
import TodoInput from "../../../components/Todos/TodoInput";
import "../../../styles/Todos.css";
import { FcCalendar } from "react-icons/fc";
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css'
import Header from "./../../../components/Header";
import axios from "axios";

const Todos = () => {
  const no = useRef(1);
  const [todos, setTodos] = useState([]);
  
  const getTodo = async () => {
    try {
      const getTodos = await axios.get("http://3.35.88.23:8080/api/todo"

      );
      console.log(getTodos);
      setTodos([getTodos])
    
    } catch (err) {
      console.log(err.code);

      }
    }
  

  useEffect(() => {
    getTodo()
  }, [])

  function getToday(){
    var date = new Date();
    var year = date.getFullYear();
    var month = ("0" + (1 + date.getMonth())).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);

    return year + "-" + month + "-" + day;
}

  const onAdd = (text) => {
    // setTodos([
    //   ...todos,
    //   {
    //     id: no.current++,
    //     text: text,
    //     done: false,
    //     created_date: getToday(),
    //   },
    // ]);
    const newTodo = {
      content : text,
      datetime: getToday()
    }
    const dummy = axios.post("http://3.35.88.23:8080/api/todo", {
        // userProfile: null,
        // newTodo
        content: text,
        // ischeck: false,
        // datetime: getToday()
      }
      );
      setTodos([
        ...todos,
        newTodo
      ])
      console.log(dummy)
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
    console.log(e);
    setStartDate(e);
  };
  const handleClick = (e) => {
    e.preventDefault();
    setIsOpen(!isOpen);
  };

  const TIME_ZONE = 3240 * 10000;
  // 달력 선택한 날짜.
  const d = new Date(startDate);


  return( 
    <>
      <Header data={"Todo"} type={"Todo"} />

      <div className="MyBody">
        <button onClick={handleClick} className="Todobutton">
          <FcCalendar />
        </button>

        {isOpen && (
          <DatePicker selected={startDate} onChange={handleChange} inline />
        )}
        <TodoList
          Caldate={new Date(+d + TIME_ZONE).toISOString().split("T")[0]}
          todos={todos}
          onDel={onDel}
          onToggle={onToggle}
        />
        <TodoInput onAdd={onAdd} />
      </div>
    </>
  )
};

export default Todos;
