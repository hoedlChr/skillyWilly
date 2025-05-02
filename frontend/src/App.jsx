import { useState, useEffect } from 'react'
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Test from "./pages/Test";
import Welcome from './pages/Welcome';
import ErrorBoundary from "./ErrorBoundary";
import 'bootstrap/dist/css/bootstrap.min.css';

import Login from './pages/Login';
import CreateAccount from './pages/CreateAccount';
import Dashboard from './pages/Dashboard';
import './App.css';
import PasswordForgotten from './pages/PasswordForgotten';
import LoadingBar from './components/LoadingBar';

function App() {
  const [user, setUser] = useState(localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null);
  const [session, setSession] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    //check Session
    if(user != null && session == null){
      //check if session is still valid
      // if session is valid, set session
      // if session is not valid, set user to null
      setIsLoading(true);
      console.log("Checking session...");
      setIsLoading(false)
    }
  }, []);

  if(isLoading){
    return (
      <div className="text-center mt-5" style={{justifyContent: "center", alignItems: "center", display: "flex", flexDirection: "column" }}>
        <div style={{width: "50%"}}>
          <LoadingBar/>
        </div>
      </div>
    )
  }
  
  return (
    <>
    <ErrorBoundary>
      <BrowserRouter>
        <Routes>
          {user ? (
            <>
              <Route path="test" element={<Test />} />
              <Route path="Dashboard" element={<Dashboard user={user} />} />
              <Route path="*" element={<div className='text-center mt-5'><img style={{width: "250px"}} src="./404.png" /></div>} />
            </>
          ) : (
            <>
              <Route path="*" element={<Login />} />
              <Route path="createAccount" element={<CreateAccount setIsLoading={setIsLoading} />} />
              <Route path="passwordForgotten" element={<PasswordForgotten />} />
            </>
          )}
        </Routes>
      </BrowserRouter>
    </ErrorBoundary>
    </>
  )
}


export default App
