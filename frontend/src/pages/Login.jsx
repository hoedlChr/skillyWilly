import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    useEffect(()=>{

    },[])


    return (
        <div className='w-25 m-auto mt-5'>
            <h1>Login</h1>
            <StandardInputField 
                label="Username"
                id={"username"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={username}
                helptext={""}
                changeHandler={setUsername}
            />
            <StandardInputField 
                label="Password"
                id={"password"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={password}
                helptext={""}
                changeHandler={setPassword}
            />
            <div className='d-grid'>
                <Button onClick={() => {console.log("Login clicked")}} className="btn-primary my-2">Login</Button>
                <Button onClick={() => {console.log("Login clicked")}} className="btn-danger my-2">Forgot Password</Button>
                <Button onClick={() => {console.log("Login clicked")}} className="btn-success my-2">Create Account</Button>
            </div>
        </div>
    );
}

export default Login;