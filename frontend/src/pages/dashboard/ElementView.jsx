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
        setName(users[element.createdByUserId].username);
        setTitle(element.subject);
        setCreated(element.created);
        setOrt(users[element.createdByUserId].location.display_name);
        setDescription(element.description);
    },[id])

    console.log(id, data, users)
    return (
        <div>
            <h2>{title}</h2>
            <h4>{name}</h4>
            <h4>{ort}</h4>
            <p className='text-end'>{created}</p>
            <div dangerouslySetInnerHTML={{__html: description}}/>
        </div>
    );
}

export default ElementView;