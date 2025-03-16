import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';

function Test(){
    const [testField, setTestField] = useState("");
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetch('api/users')
            .then(response => response.json())
            .then(data =>{ 
                setUsers(data)
            })
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    return (
        <div className='container'>
            <h1>Welcome to the Test Page</h1>
            <p>This is a demo page for testing purposes.</p>
            <table className='table table-striped'>
                <thead>
                    <tr><th>id</th><th>name</th></tr>   
                </thead>
                <tbody>
                    {
                        users.map(user => {
                            return  <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.name}</td>
                            </tr>
                        })
                    }
                </tbody>
            </table>
            <StandardInputField
                id={"testField"}
                label={"Label"}
                className={""}
                readOnly={false}
                placeholder={"placeholder..."}
                value={testField}
                helptext={"helptext"}
                changeHandler={setTestField}
            />
        </div>
    );
};

export default Test;