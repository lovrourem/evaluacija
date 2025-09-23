import { useState } from 'react'
import React from 'react'
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom'

import './App.css'

import Dashboard from "./components/Dashboard"
import Homepage from "./components/Homepage"
import RegisterPage from "./components/RegisterPage"
import AddTask from "./components/AddTaskPage"
import EditTask from "./components/EditTaskPage"

function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Homepage />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/dashboard/add" element={<AddTask/>} />
        <Route path="/dashboard/edit" element={<EditTask/>} />
      </Routes>
    </Router>
  )

}

export default App
