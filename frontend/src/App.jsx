import { useState } from 'react'
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Test from "./pages/Test";
import Welcome from './pages/Welcome';

function App() {

  return (
    <>
      <BrowserRouter>
        <Routes path='/'>
          <Route index element={<Welcome/>}/>
          <Route path="test" element={<Test/>}/>
        </Routes>
      </BrowserRouter>
    </>
  )
}


export default App
