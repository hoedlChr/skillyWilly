import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';

function CreateAccount() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [password2, setPassword2] = useState("");
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [zip, setZip] = useState("");

    useEffect(()=>{

    },[])


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
                changeHandler={setUsername}
            />
            <StandardInputField 
                label="Password"
                id={"password"}
                type="password"
                className={""}
                readOnly={false}
                placeholder={""}
                value={password}
                helptext={""}
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
                changeHandler={setPassword2}
            />
            <StandardInputField 
                label="Phone"
                id={"phone"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={phone}
                helptext={""}
                changeHandler={setPhone}
            />
            <StandardInputField 
                label="Address"
                id={"address"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={address}
                helptext={""}
                changeHandler={setAddress}
            />
            <StandardInputField 
                label="City"
                id={"city"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={city}
                helptext={""}
                changeHandler={setCity}
            />
            <StandardInputField 
                label="Zip"
                id={"zip"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={zip}
                helptext={""}
                changeHandler={setZip}
            />
            

            <div className='d-grid'>
                <Button onClick={() => {console.log("Login clicked")}} className="btn-primary my-2">Create Account</Button>
                <Button onClick={() => {window.history.back()}} className="btn btn-danger my-2">Cancel</Button>
                </div>
        </div>
    );
}

export default CreateAccount;