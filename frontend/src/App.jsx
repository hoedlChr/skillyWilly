import { useState, useEffect, lazy } from 'react'
import {BrowserRouter, Routes, Route} from "react-router-dom";
import ErrorBoundary from "./ErrorBoundary";
import 'bootstrap/dist/css/bootstrap.min.css';

import CreateAccount from './pages/CreateAccount';
import './App.css';
import PasswordForgotten from './pages/PasswordForgotten';
import LoadingBar from './components/LoadingBar';

const Dashboard = lazy(() =>  import('./pages/Dashboard'))
const UserSettings = lazy(() =>  import('./pages/UserSettings'))
const MySkilly = lazy(() =>  import('./pages/MySkilly'))
const Login = lazy(() =>  import('./pages/Login'))

function App() {
  const [user, setUser] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    //check Session
      setIsLoading(true);
      const formdata = new FormData();

      const requestOptions = {
        method: "POST",
        body: formdata,
        credentials: "include",
        redirect: "follow"
      };

      fetch("/api/users/verify", requestOptions)
        .then((response) => response.json())
        .then((result) => {
          if(result.hasOwnProperty("error")) {
            throw new Error(result.error);
          }
          if(result.hasOwnProperty("user")){ 
            setUser(result.user);
          }
          setIsLoading(false)
        })
        .catch((error) => {
          console.error(error);
        });
  }, []);

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
              <Route path="*" element={<Dashboard user={user} />} />
              <Route path="UserSettings" element={<UserSettings user={user} />} />
              <Route path="MySkilly" element={<MySkilly user={user} />} />
              {/* <Route path="*" element={<div className='text-center mt-5'><img style={{width: "250px"}} src="./404.png" /></div>} /> */}
            </>
          ) : (
            <>
              <Route path="*" element={<Login setUser={setUser} />} />
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
