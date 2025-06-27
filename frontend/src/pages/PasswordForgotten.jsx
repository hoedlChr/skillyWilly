import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';

function PasswordForgotten() {
    const [email, setEmail] = useState("");
    

    useEffect(()=>{

    },[])


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
                <Button onClick={() => {console.log("Login clicked")}} className="btn-primary my-2">Reset Password</Button>
                <Button onClick={() => {window.history.back()}} className="btn btn-danger my-2">Cancel</Button>
            </div>
        </div>
    );
}

export default PasswordForgotten;