import React from 'react'
import styles from './Dashboard.module.css'
import TaskComponent from './TaskComponent'

function Dashboard() {

    const dummyData = [
        {
            "id": 1,
            "title": "domaci",
            "description": "opis",
            "completed": false,
            "createdAt": "2025-09-21T18:45:00"
        },
        {
            "id": 2,
            "title": "dz",
            "description": "opis",
            "completed": false,
            "createdAt": "2025-10-21T18:45:00"
        },
        {
            "id": 2,
            "title": "dz",
            "description": "opis",
            "completed": false,
            "createdAt": "2025-10-21T18:45:00"
        },
        {
            "id": 2,
            "title": "dz",
            "description": "opis",
            "completed": false,
            "createdAt": "2025-10-21T18:45:00"
        },
        {
            "id": 5,
            "title": "dz",
            "description": "opis",
            "completed": true,
            "createdAt": "2025-10-21T18:45:00"
        }
    ]

    return (
    <div className={styles.outer}>
      <div className={styles.container}>
        <div className={styles.tasks}>
          {dummyData.map((task) => (
            <TaskComponent key={task.id} task={task} />
          ))}
        </div>
      </div>
    </div>
  )
}

export default Dashboard