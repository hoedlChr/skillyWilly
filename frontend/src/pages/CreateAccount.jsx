import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';
import InfoMessage from '../components/InfoMessage';
import LoadingBar from '../components/LoadingBar';

function CreateAccount() {
    const [isLoading, setIsLoading] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [password2, setPassword2] = useState("");
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [location, setLocation] = useState("");

    const [usedUsername, setUsedUsername] = useState([]);
    useEffect(() => {
        fetch(`/api/users/usernames`,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                },
            }
        )
        .then(res => res.json())
        .then(data => {
            setUsedUsername(data);
        })
        .catch(err => {
            console.log(err);
        });
    }, []);

    function sendData(){
        let data = {
            username: username,
            email: email,
            password: password,
            firstname: firstname,
            lastname: lastname,
            location: location
        }

        //post data to backend
        setIsLoading(true);
        fetch(`/api/users/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(res => res.json())
        .then(data => {
                window.location.href = "/login";
        }).finally(() => {
            setIsLoading(false);
        });
        
    }

    let disabled = false;
    if(username === "" || usedUsername.includes(username)){
        disabled = true;
    } else if (password !== password2){
        disabled = true;
    } else if (email === "" || password === "" || password2 === ""){
        disabled = true;
    } else if (location === ""){
        disabled = true;
    }

    return (
        <div className='w-50 m-auto mt-5'>
            <h1>Create Account</h1>
            <StandardInputField 
                label="Firstname"
                id={"firstname"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={firstname}
                helptext={""}
                changeHandler={setFirstname}
            />
            <StandardInputField
                label="Lastname"
                id={"lastname"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={lastname}
                helptext={""}
                changeHandler={setLastname}
            />
            <StandardInputField
                label="Email"
                id={"email"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={email}
                helptext={""}
                required={true}
                changeHandler={setEmail}
            />
            <StandardInputField 
                label="Username"
                id={"username"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={username}
                helptext={""}
                required={true}
                changeHandler={setUsername}
            />
            {
                usedUsername.includes(username) ? 
                <InfoMessage 
                    heading="Username already taken"
                    body="The username is already taken. Please try again."
                    type="danger"
                    className="my-2"
                />
                : null
            }
            <StandardInputField 
                label="Password"
                id={"password"}
                type="password"
                className={""}
                readOnly={false}
                placeholder={""}
                value={password}
                helptext={""}
                required={true}
                changeHandler={setPassword}
            />
            <StandardInputField 
                label="Password again"
                id={"password2"}
                type="password"
                className={""}
                readOnly={false}
                placeholder={""}
                value={password2}
                helptext={""}
                required={true}
                changeHandler={setPassword2}
            />
            {
                password !== password2 ? 
                <InfoMessage 
                    heading="Password mismatch"
                    body="The passwords do not match. Please try again."
                    type="danger"
                    className="my-2"
                />
                : null
            }
            <StandardInputField
                id={"location"}
                label={"Location"}
                value={location}
                type="place"
                changeHandler={setLocation}/>
            
                <div className='d-grid'>
                {
                    isLoading ? 
                    <LoadingBar /> :
                    <Button disabled={disabled} onClick={() => sendData()} className="btn-primary my-2">Create Account</Button>
                }  
                    <Button onClick={() => {window.history.back()}} className="btn btn-danger my-2">Cancel</Button>
                </div>
        </div>
    );
}

export default CreateAccount;