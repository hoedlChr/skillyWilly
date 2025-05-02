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
import UserSettings from './pages/UserSettings';
import MySkilly from './pages/MySkilly';

function App() {
  const [user, setUser] = useState(localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null);
  const [session, setSession] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
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
  }, [user, session]);

  if(isLoading){
    return (<LoadingBar />)
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
              <Route path="UserSettings" element={<UserSettings user={user} />} />
              <Route path="MySkilly" element={<MySkilly user={user} />} />
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
