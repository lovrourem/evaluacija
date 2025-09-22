import { useState } from 'react'
import React from 'react'
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom'

import './App.css'

import Dashboard from "./components/Dashboard"
import Homepage from "./components/Homepage"
import RegisterPage from "./components/RegisterPage"


function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Homepage />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/register" element={<RegisterPage/>} />
      </Routes>
    </Router>
  )

}

export default App
