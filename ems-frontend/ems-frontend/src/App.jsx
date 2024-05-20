import './App.css'
import HomePage from './components/HomePageComponents';
import LoginPage from './components/LoginPageComponents';
import React, { useState } from 'react';
// Router
import {BrowserRouter as Router, Route, Routes, Navigate} from 'react-router-dom';


function App () {
  
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate replace to="/login" />} />
        <Route Component={HomePage} path="/home"/>
        <Route Component={LoginPage} path="/login"/>
      </Routes>
    </Router>  
  );
};
export default App;
