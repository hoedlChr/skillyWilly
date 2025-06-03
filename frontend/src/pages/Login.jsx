import React, { lazy, useEffect, useState } from 'react';
import LoadingBar from '../components/LoadingBar';

const StandardInputField = lazy(() => import('../components/StandardInputField'));
const Button = lazy(() => import('../components/Button'));

function Login({setUser}) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);

    const handleLogin = () => {
        const formdata = new FormData();
        formdata.append("username", username);
        formdata.append("password", password);

        const requestOptions = {
        method: "POST",
        body: formdata,
        credentials: "include",
        redirect: "follow"
        };
        setLoading(true);
        fetch("/api/users/login", requestOptions)
        .then((response) => {
            console.log(response);
            if (response.status === 401) {
                throw new Error("Unauthorized: Invalid username or password");
            }
            return response.json()
        })
        .then((result) => {
            setUser(result.user);
        })
        .catch((error) => {
            console.error("Login failed:", error);
            window.alert("Login failed. Please check your username and password.");
        }).finally(() => {
            setLoading(false);
        })
    }


    return (
        <div className='w-25 m-auto mt-5'>
            <h1>Login</h1>
            <StandardInputField 
                label="Username"
                id={"username"}
                className={""}
                placeholder={""}
                value={username}
                helptext={""}
                readOnly={loading}
                changeHandler={setUsername}
            />
            <StandardInputField 
                label="Password"
                id={"password"}
                type={"password"}
                className={""}
                placeholder={""}
                value={password}
                helptext={""}
                readOnly={loading}
                changeHandler={setPassword}
            />
            {
                loading === true ?
                <LoadingBar/>:
                <div className='d-grid'>
                    <Button onClick={() => {handleLogin()}}className="btn-primary my-2">Login</Button>
                    <a href={'/passwordForgotten'} className="btn btn-danger my-2">Forgot Password</a>
                    <a href={'/createAccount'} className="btn btn-success my-2">Create Account</a>
                </div>
            }
        </div>
    );
}

export default Login;