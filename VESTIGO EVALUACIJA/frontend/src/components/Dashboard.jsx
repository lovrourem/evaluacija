import React, { useEffect } from 'react'
import { useState } from 'react'
import { Navigate, useNavigate } from 'react-router-dom'
import styles from './Dashboard.module.css'
import TaskComponent from './TaskComponent'


function Dashboard() {

    const [tasks, setTasks] = useState([])
    const navigate = useNavigate();

    const handleAddingTask = async() =>{
      navigate("/dashboard/add");
    }

    const fetchTasks = async () => {
      try{
        const response = await fetch("http://localhost:8080/api/tasks", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Auth": localStorage.getItem("token")
          }
        });

        if(!response.ok){
          alert("Failed to fetch data");
        }

        if(response.status === 200){
          const data = await response.json();
          setTasks(data);
        }

      } catch(err){
        alert ("Something went wrong");
      }
    }

    useEffect(() => {
      fetchTasks();
    }, []);

    return (
    <div className={styles.outer}>
      <div className={styles.container}>
        <div className={styles.tasks}>
          {tasks.map((task) => (
            <TaskComponent key={task.id} task={task} />
          ))}
        </div>
      </div>
      <div className={styles.addBtn}>
        <button onClick={handleAddingTask}>Dodaj</button>
      </div>
    </div>
  )
}

export default Dashboard