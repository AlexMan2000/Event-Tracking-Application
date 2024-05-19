import './App.css'
import HomePage from './components/HomePageComponents';
import LoginPage from './components/LoginPageComponents';
import React, { useState } from 'react';
// Router
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';



function App () {
  
  return (
    <Router>
      <Routes>
        <Route Component={HomePage} path=""/>
        <Route Component={LoginPage} path=""/>
      </Routes>

    </Router>
    // <HomePage/>
  );
};
export default App;
