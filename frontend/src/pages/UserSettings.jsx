import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';
import InfoMessage from '../components/InfoMessage';
import Navbar from './dashboard/Navbar';

function UserSettings({user}) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [password2, setPassword2] = useState("");
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [location, setLocation] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    const [usedUsername, setUsedUsername] = useState([]);

    useEffect(() => {
        //fetch user data
        fetch(`/api/users/${user.id}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        })
        .then((res) => {
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
            return res.json();
        })
        .then((data) => {
            console.log(data);
            setUsername(data.username);
            setFirstname(data.firstname);
            setLastname(data.lastname);
            setEmail(data.email);
            setLocation(data.location);
            setPassword(data.password);
            setPassword2(data.password);
        })
        .catch((error) => {
            console.error("Error fetching user data:", error);
        });

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
            let temp = data;
            temp.splice(temp.indexOf(user.username), 1);
            setUsedUsername(temp);
        })
        .catch(err => {
            console.log(err);
        });

    },[])

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
        fetch(`/api/users/${user.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(res => res.json())
        .then(data => {
            console.log(data);
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
        <div className='container'>
            <Navbar text="User Settings" />
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
                <Button disabled={disabled} onClick={() => sendData()} className="btn-primary my-2">Save Account</Button>
                <Button onClick={() => {window.history.back()}} className="btn btn-danger my-2">Cancel</Button>
                </div>
        </div>
    );
}

export default UserSettings;