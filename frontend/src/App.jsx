import { useState } from 'react'
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Test from "./pages/Test";
import Welcome from './pages/Welcome';
import ErrorBoundary from "./ErrorBoundary";
import 'bootstrap/dist/css/bootstrap.min.css';

import Login from './pages/Login';
import CreateAccount from './pages/CreateAccount';
import Dashboard from './pages/Dashboard';
import './App.css';

function App() {

  return (
    <>
    <ErrorBoundary>
      <BrowserRouter>
        <Routes path='/'>
          <Route index element={<Welcome/>}/>
          <Route path="test" element={<Test/>}/>
          <Route path="login" element={<Login/>}/>
          <Route path="CreateAccount" element={<CreateAccount/>}/>
          <Route path="Dashboard" element={<Dashboard/>}/>
          <Route path="*" element={<div className='text-center mt-5'><img style={{width: "250px"}} src="./404.png"/></div>}/>
        </Routes>
      </BrowserRouter>
    </ErrorBoundary>
    </>
  )
}


export default App
