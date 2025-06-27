import React, { useEffect, useState } from 'react';
import { Button } from 'reactstrap';

function ElementView({id, data, users, currentUser, setShowElement, setShowChat}) {
    const [title, setTitle] = useState("");
    const [name, setName] = useState("");
    const [userID, setUserID] = useState("");
    const [ort, setOrt] = useState("");
    const [description, setDescription] = useState("");
    const [created, setCreated] = useState("");

    const [likeCount, setLikeCount] = useState("");
    const [liked, setLiked] = useState(false);

    useEffect(()=>{
        let element = data.find((element) => element.id === id);
        if (!element) {
            return;
        }
        setName(users[element.userId].username);
        setUserID(element.userId);
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
            if(data.userIds !== undefined && data.userIds !== null && data.userIds.includes(currentUser.id)){
                setLiked(true);
            } else {
                setLiked(false);
            }
        })
        .catch((err) => {
            console.log(err);
        });
    },[id, likeCount])

    const like = () => {
        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const raw = JSON.stringify({
        "userId": currentUser.id,
        "skillId": id
        });

        const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
        };

        fetch("/api/like-skills/like", requestOptions)
        .then((response) => response.text())
        .then((result) => {setLikeCount(likeCount+1);})
        .catch((error) => console.error(error));
    }

    const unLike = () => {
        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const raw = JSON.stringify({
        "userId": currentUser.id,
        "skillId": id
        });

        const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
        };

        fetch("/api/like-skills/unlike", requestOptions)
        .then((response) => response.text())
        .then((result) => {setLikeCount(likeCount-1);})
        .catch((error) => console.error(error));
    }
    return (
        <div className='mt-1'>
            <div className="d-flex justify-content-between align-items-center">
                <h2 className="mb-0">{title}</h2>
                {
                    !liked ?
                    <Button className='btn btn-primary text-success' style={{ background: 'initial', border: "none", padding: "0px", color: 'black' }} onClick={() => {like()}}>
                        <i className="bi bi-hand-thumbs-up"></i>
                        <small className='ms-1'>{likeCount > 0 ? likeCount : "Like"}</small>
                    </Button>
                    :
                    <Button className='btn btn-primary text-danger' style={{ background: 'initial', border: "none", padding: "0px", color: 'black' }} onClick={() => {unLike()}}>
                        <i className="bi bi-hand-thumbs-down"></i>
                        <small className='ms-1'>{likeCount > 0 ? likeCount : "Like"}</small>
                    </Button>
                }
            </div>
            <Button className='btn clickable' style={{ background: 'initial', border: "none", padding: "0px", color: 'black' }} onClick={() => {
                setShowElement(false);
                setShowChat(userID);
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