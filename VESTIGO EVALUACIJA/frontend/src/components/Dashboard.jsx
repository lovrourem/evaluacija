import React, { useEffect } from 'react'
import { useState } from 'react'
import { Navigate, useNavigate } from 'react-router-dom'
import styles from './Dashboard.module.css'
import TaskComponent from './TaskComponent'


function Dashboard() {

    const [tasks, setTasks] = useState([]);
    const navigate = useNavigate();
    const [refresh, setRefresh] = useState(false);
    const [filter, setFilter] = useState("all");

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

    const handleDelete = async(taskId) => {
        try{
            const response = await fetch (`http://localhost:8080/api/tasks/${taskId}`, {
                method: "DELETE",
                headers: {
                    "Auth": localStorage.getItem("token")
                }
            });

            if(!response.ok){
                const errorData = await response.json();
                alert(errorData)
                return
            }

            alert("Task deleted")
            setRefresh((prev) => !prev)

        }catch(err){
            alert(err);
        }
    }

    const handleEdit = async (taskId) => {
      navigate("/dashboard/edit", {state: {taskId}})
    }

    const handleComplete = async (taskId) => {
      try{

        const response = await fetch (`http://localhost:8080/api/tasks/complete/${taskId}`, {
          method: "POST",
          headers: {
            "Auth": localStorage.getItem("token")
          }
        })

        if(!response.ok){
          const errorData = await response.json()
          alert(errorData)
          return
        }

        alert("Task finished")
        setRefresh(prev => (!prev))

      }catch(err){
        alert(err)
      }
    }

    const filteredTasks = tasks.filter((task) =>{
      if(filter === "completed") return task.completed;
      if(filter === "not_completed") return !task.completed;
      return true;
    })

    useEffect(() => {
      fetchTasks();
    }, [refresh]);

    return (
    <div className={styles.outer}>
      <div className={styles.filter}>
        <label>Filter:</label>
        <select value={filter} onChange={(e) => setFilter(e.target.value)}>
          <option value ="all">All</option>
          <option value ="completed">Completed</option>
          <option value ="not_completed">Not Completed</option>
        </select>
      </div>
      <div className={styles.container}>
        <div className={styles.tasks}>
          {filteredTasks.map((task) => (
            <TaskComponent key={task.id} task={task}
                           onDelete={handleDelete} 
                           onEdit={handleEdit}
                           onComplete={handleComplete} />
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