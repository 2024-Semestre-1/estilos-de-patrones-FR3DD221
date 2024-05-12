import React, {useState} from "react";
import '../styles/InputTask.css'
import { v4 as uuidv4 } from 'uuid'

function InputTask (props) {
  const [input, setInput] = useState('');

  const handleChange = e => {
    setInput(e.target.value);
  }

  const handleCommit = e => {
    e.preventDefault();

    const newTask = {
      id: uuidv4(),
      text: input,
      completada: false
    }

    props.onSubmit( newTask );
  }

  return (
    <form 
    onSubmit={handleCommit}
    className="InputTask">
      <input className="Input"
      type="text"
      placeholder="Add a new task"
      name="text"
      onChange={handleChange}
      />
      <button className="TaskButton">
        Add task
      </button>
    </form>
  );
};

export default InputTask;