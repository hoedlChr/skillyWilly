import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';

function PasswordForgotten() {
    const [email, setEmail] = useState("");
    

    useEffect(()=>{

    },[])

    const sendMail = () => {
        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const raw = JSON.stringify({
            "email": email
        });

        const requestOptions = {
            method: "POST",
            headers: myHeaders,
            body: raw,
            redirect: "follow"
        };

        fetch("/api/users/reset-password", requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));
    }

    return (
        <div className='w-50 m-auto mt-5'>
            <h1>Password Forgotten</h1>
            <StandardInputField 
                label="Email Address"
                id={"email"}
                className={""}
                readOnly={false}
                placeholder={""}
                value={email}
                helptext={""}
                changeHandler={setEmail}
            />

            <div className='d-grid'>
                <Button onClick={() => {sendMail()}} className="btn-primary my-2">Reset Password</Button>
                <Button onClick={() => {window.history.back()}} className="btn btn-danger my-2">Cancel</Button>
            </div>
        </div>
    );
}

export default PasswordForgotten;