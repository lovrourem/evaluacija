import React from 'react'
import styles from "./Homepage.module.css"
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Homepage() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async() => {
        if (!username || !password) {
            alert("Username and password are required!");
            return;
        }

        try{
            const response = await fetch("http://localhost:8080/api/users/login",{
                method: "POST",
                headers:{
                    'Content-Type': "application/json",
                },
                body: JSON.stringify({username,password}),
            });

            if (!response.ok) {
                const text = await response.text();
                alert("Login failed");
                return;
            }

            if(response.status === 200){
                const data = await response.json();
                localStorage.setItem("token", data.token);
                localStorage.setItem("username", data.username);
                navigate("/dashboard");
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
                    <p className={styles.header}>Login</p>
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
                            type='text'
                            required
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        >
                        </input>
                    </div>
                    <div className={styles.inputDiv}>
                        <button type="button" onClick={handleLogin} className={styles.btn}>Login</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default Homepage