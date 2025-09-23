import React from 'react'
import styles from "./Homepage.module.css"
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Homepage() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleRegister = async(e) => {
        e.preventDefault();
        if (!username || !password) {
            alert("Username and password are required!");
            return;
        }

        try{
            const response = await fetch("http://localhost:8080/api/users/register",{
                method: "POST",
                headers:{
                    'Content-Type': "application/json",
                },
                body: JSON.stringify({username,password}),
            });

            if (!response.ok) {
                const errorData = await response.json();
                alert(errorData.message);
                return;
            }

            if(response.status === 200){
                const data = await response.json();
                navigate("/");
            }

        }catch(error){
            alert ("Something went wrong.")
        }
    };

  return (
    <div className={styles.outer}>
        <div className={styles.container}>
            <div className={styles.formDiv}>
                <div className={styles.form}>
                    <form onSubmit={handleRegister}>
                        <p className={styles.header}>Register</p>
                        <div className={styles.inputDiv}>
                            <label>Username:</label>
                            <input
                                className={styles.input}
                                type='text'
                                required
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                            >
                            </input>
                        </div>
                        <div className={styles.inputDiv}>
                            <label>Password:</label>
                            <input
                                className={styles.input}
                                type='password'
                                required
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            >
                            </input>
                        </div>
                        <div className={styles.inputDiv}>
                            <button type="submit" className={styles.btn}>Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default Homepage