import './App.css'
import HomePage from './components/HomePageComponents';
import LoginPage from './components/LoginPageComponents';
import React from 'react';
// Router
import {BrowserRouter as Router, Route, Routes, Navigate,} from 'react-router-dom';
import ProtectedRoute from './protectedRoutes/privateRoutes';

function App () {
  
  return (
    <Router>
      <Routes>
          <Route path="/" element={<Navigate replace to="/login" />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/home" element={<ProtectedRoute element={<HomePage />} />} />
      </Routes>
    </Router>  
  );
};
export default App;
