import React from "react";
import TodoItem from "./TodoItem";
// import './TodoList.css';

const TodoList = ({ Caldate, todos, onDel, onToggle }) => {

  return (
    <div>
      {Caldate}
      <ul className="TodoList">
        {todos.filter((todos)=> todos.created_date === Caldate).map((todos) => (
          <TodoItem
            key={todos.id}
            todos={todos}
            onDel={onDel}
            onToggle={onToggle}
          />
        ))}
        {/* {todos.map} */}
      </ul>
    </div>
  );
};

export default TodoList;
