import React, { useEffect } from 'react'
import { useState } from 'react'
import { Navigate, useLocation, useNavigate } from 'react-router-dom'
import styles from "./AddTaskPage.module.css"

function EditTaskPage() {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const navigate = useNavigate();
    const location = useLocation();
    const {taskId} = location.state || {};

    const fetchTask = async () =>{
        try{

            const response = await fetch (`http://localhost:8080/api/tasks/${taskId}` ,{
                method: "GET",
                headers: {
                    "Auth": localStorage.getItem("token")
                }
            })

            if(!response.ok){
                const errorData = await response.json();
                alert(errorData.message)
                return
            }
            
            if(response.status === 200){
                const task = await response.json()
                setTitle(task.title)
                setDescription(task.description)
            }

        }catch(err){
            alert(err)
        }
    }

    const handleEditTask = async (e) =>{
        e.preventDefault();

        const task = {
            title,
            description
        }

        try{

            const response = await fetch(`http://localhost:8080/api/tasks/${taskId}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Auth": localStorage.getItem("token")
                },
                body: JSON.stringify(task)
            })

            if(!response.ok){
                const errorData = await response.json()
                alert(errorData.message)
                return
            }

            const updatedTask = await response.json()
            alert("Task updated")
            navigate("/dashboard")
        }catch(err){
            alert(err)
        }
    }

    useEffect(() => {
        fetchTask()
    }, [taskId])

    return (
        <div className={styles.outer}>
            <div className={styles.container}>
                <form onSubmit={handleEditTask} className={styles.form}> 
                    <div className={styles.inputDiv}>
                        <label>
                            Naziv
                        </label>
                        <input
                            type="text"
                            required
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                        >
                        </input>
                    </div>
                    <div className={styles.inputDiv}>
                        <label>
                            Opis
                        </label>
                        <input
                            type="text"
                            required
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                        >
                        </input>
                    </div>
                    <div className={styles.addBtn}>
                        <button type='submit'>Uredi</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default EditTaskPage