import React from 'react'
import { useState } from 'react'
import { Navigate, useNavigate } from 'react-router-dom'
import styles from "./AddTaskPage.module.css"

function AddTaskPage() {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const navigate = useNavigate();

    const handleAddTask = async (e) =>{
        e.preventDefault();

        if(!title || !description){
            alert("Insert all the needed values")
            return
        }

        const task = {
            title,
            description
        }

        try{
            const response = await fetch ("http://localhost:8080/api/tasks", {
                method: "POST",
                headers: {
                    "Content-type": "application/json",
                    "Auth": localStorage.getItem("token")
                },
                body: JSON.stringify(task)
            })

            if(!response.ok){
                const errorData = await response.json();
                alert(errorData.message)
                return;
            }

            if(response.status === 201){
                const newTask = await response.json();
                alert("Task created")
                navigate("/dashboard")
            }

        }catch(err){
            alert(err)
        }
    }

    return (
        <div className={styles.outer}>
            <div className={styles.container}>
                <form onSubmit={handleAddTask} className={styles.form}> 
                    <div className={styles.inputDiv}>
                        <label>
                            Naziv
                        </label>
                        <input
                            type="text"
                            required
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
                            onChange={(e) => setDescription(e.target.value)}
                        >
                        </input>
                    </div>
                    <div className={styles.addBtn}>
                        <button type='submit'>Dodaj</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default AddTaskPage