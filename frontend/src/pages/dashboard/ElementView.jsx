import React, { useEffect, useState } from 'react';

function ElementView({id, data, users}) {
    const [title, setTitle] = useState("");
    const [name, setName] = useState("");
    const [ort, setOrt] = useState("");
    const [description, setDescription] = useState("");
    const [created, setCreated] = useState("");
    useEffect(()=>{
        let element = data.find((element) => element.id === id);
        if (!element) {
            return;
        }
        setName(users[element.userId].username);
        setTitle(element.subject);
        let date = new Date(element.created);
        setCreated(date.toLocaleDateString() + " " + date.toLocaleTimeString());
        setOrt(users[element.userId].location.display_name);
        setDescription(element.body);
    },[id])

    return (
        <div className='mt-4'>
            <h2>{title}</h2>
            <h4>{name}</h4>
            <p>{ort}</p>
            <div className='text-end'>
                <small >{created}</small>
            </div>
            <hr />
            <div dangerouslySetInnerHTML={{__html: description}}/>
        </div>
    );
}

export default ElementView;