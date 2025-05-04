import React, { lazy, useEffect, useState } from 'react';

const StandardInputField = lazy(() => import('../components/StandardInputField'));
const Button = lazy(() => import('../components/Button'));

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

        fetch("/api/users/verify", requestOptions)
        .then((response) => {console.log(response);return response.json()})
        .then((result) => {
            let data = {
                username: username,
                password: password,
                id: result.id
            };
            setUser(data);
            localStorage.setItem("user", JSON.stringify(data));
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