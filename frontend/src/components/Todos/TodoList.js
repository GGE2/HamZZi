import React, { useState } from "react";
import TodoItem from "./TodoItem";
// import './TodoList.css';
// import PetCarousel from './../PetCarousel';
import { Reorder } from "framer-motion";

const TodoList = ({ Caldate, todos, onDel, onEdit, onToggle, setTodos }) => {
  // console.log(todos)

  return (
    <>
      {/* {Caldate} */}
      <div className="TodoList">
        {/* <PetCarousel /> */}
        <Reorder.Group axis="y" values={todos} onReorder={setTodos}>
          {todos
            .filter((todos) => todos.datetime === Caldate)
            .map((todos) => (
              <TodoItem
                key={todos.todo_id}
                todos={todos}
                onDel={onDel}
                onToggle={onToggle}
                onEdit={onEdit}
              />
            ))}
        </Reorder.Group>
        {/* {todos.map((todos) => (
          <TodoItem
            key={todos.todo_id}
            todos={todos}
            onDel={onDel}
            onToggle={onToggle}
          />))} */}
      </div>
    </>
  );
};

export default TodoList;
