import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './Login.jsx';
import CreateForm from './createForm.jsx';
import Signup from './Signup.jsx';
import UpdateForm from './UpdateForm.jsx';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login/>} />
        <Route path="/Signup" element={<UpdateForm />} />
      </Routes>
    </Router>
  );
}

export default App
