import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';

function Login({setUser}) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = () => {
        const formdata = new FormData();
        formdata.append("username", username);
        formdata.append("password", password);

        const requestOptions = {
        method: "POST",
        body: formdata,
        redirect: "follow"
        };

        fetch("http://localhost:8080/api/users/verify", requestOptions)
        .then((response) => response.text())
        .then((result) => {
            setUser({username: username, password: password, id: result.id});
            localStorage.setItem("user", JSON.stringify({username: username, password: password, id: result.id}));
            // naviagte to dashboard
            window.location.href = "/Dashboard";
        })
        .catch((error) => console.error(error));
    }


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
                type={"password"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={password}
                helptext={""}
                changeHandler={setPassword}
            />
            <div className='d-grid'>
                <Button onClick={() => {handleLogin()}} className="btn-primary my-2">Login</Button>
                <a href={'/passwordForgotten'} className="btn btn-danger my-2">Forgot Password</a>
                <a href={'/createAccount'} className="btn btn-success my-2">Create Account</a>
            </div>
        </div>
    );
}

export default Login;