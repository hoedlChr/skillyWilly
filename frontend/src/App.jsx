import { useState } from 'react'
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Test from "./pages/Test";
import Welcome from './pages/Welcome';
import ErrorBoundary from "./ErrorBoundary";
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {

  return (
    <>
    <ErrorBoundary>
      <BrowserRouter>
        <Routes path='/'>
          <Route index element={<Welcome/>}/>
          <Route path="test" element={<Test/>}/>
        </Routes>
      </BrowserRouter>
    </ErrorBoundary>
    </>
  )
}


export default App
