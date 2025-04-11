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
import PasswordForgotten from './pages/PasswordForgotten';

function App() {
  const [user, setUser] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  return (
    <>
    <ErrorBoundary>
      <BrowserRouter>
        <Routes>
          {user ? (
            <>
              <Route path="test" element={<Test />} />
              <Route path="Dashboard" element={<Dashboard user={user} setUser={setUser} />} />
              <Route path="*" element={<div className='text-center mt-5'><img style={{width: "250px"}} src="./404.png" /></div>} />
            </>
          ) : (
            <>
              <Route path="*" element={<Login user={user} setUser={setUser} />} />
              <Route path="createAccount" element={<CreateAccount />} />
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
