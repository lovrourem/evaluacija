import React from "react";
import styles from "./TaskComponent.module.css";

export default function TaskComponent({ task }) {
    const date = new Date(task.createdAt).toLocaleDateString("hr-HR", {
        dateStyle: "short",
    });

    return (
        <div className={styles.task}>
            <div className={styles.title}>{task.title}</div>
            <div className={styles.description} title={task.description}>
                Opis
            </div>
            <div className={styles.createdAt}>
                <div>Izrađeno</div>
                <div>{date}</div>
            </div>
            <div className={styles.completed}>
                {task.completed ? "Završeno" : "Nije završeno"}
            </div>
            <div className={styles.buttons}>
                {!task.completed && (<button className={styles.button}>Završi</button>)}
                <button className={styles.button}>Uredi</button>
                <button className={styles.button}>Obriši</button>
            </div>
        </div>
    );
}