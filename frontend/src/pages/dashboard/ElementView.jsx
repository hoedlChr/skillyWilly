import React, { useEffect, useState } from 'react';
import { Button } from 'reactstrap';

function ElementView({id, data, users}) {
    const [title, setTitle] = useState("");
    const [name, setName] = useState("");
    const [ort, setOrt] = useState("");
    const [description, setDescription] = useState("");
    const [created, setCreated] = useState("");
    const [likeCount, setLikeCount] = useState("");
    useEffect(()=>{
        let element = data.find((element) => element.id === id);
        if (!element) {
            return;
        }
        setName(users[element.userId].username);
        setTitle(element.subject);
        let date = new Date(element.created);
        setCreated(date.toLocaleDateString() + " " + date.toLocaleTimeString());
        if(users[element.userId] !== undefined && users[element.userId].hasOwnProperty("location") === true && users[element.userId].location !== null && users[element.userId].location.hasOwnProperty("display_name") === true){
            setOrt(users[element.userId].location.display_name);
        }
        setDescription(element.body);
        //get data from backend
        fetch(`/api/like-skills/skill/${id}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        })
        .then((res) => res.json())
        .then((data) => {
            if(data.likeCount !== undefined && data.likeCount !== null && data.likeCount > 0){
                setLikeCount(data.likeCount);
            }
        })
        .catch((err) => {
            console.log(err);
        });
    },[id])

    return (
        <div className='mt-1'>
            <div className="d-flex justify-content-between align-items-center">
                <h2 className="mb-0">{title}</h2>
                <Button className='btn btn-primary' style={{ background: 'initial', border: "none", padding: "0px", color: 'black' }} onClick={() => {
                    window.location.href = "/dashboard";
                }}>
                    <i className="bi bi-hand-thumbs-up"></i>
                    <small className='ms-1'>{likeCount > 0 ? likeCount : "Like"}</small>
                </Button>
            </div>
            <Button className='btn clickable' style={{ background: 'initial', border: "none", padding: "0px", color: 'black' }} onClick={() => {
                window.location.href = `/chat/${users[id].id}`;
            }}><h4>{name}</h4></Button>
            <p>{ort}</p>
            <div className='text-end'>
                <small>{created}</small>
            </div>
            <hr />
            <div>
                {description && (
                    <div
                        dangerouslySetInnerHTML={{
                            __html: description
                                .replace(/&lt;/g, "<")
                                .replace(/&gt;/g, ">")
                                .replace(/&amp;/g, "&"),
                        }}
                    />
                )}
            </div>
        </div>
    );
}

export default ElementView;