import React, { useEffect, useState } from 'react';
import ElementItem from './ElementItem';
import LoadingBar from '../../components/LoadingBar';

function ElementList({mySkilly=false, data, users, user, setShowChat, setShowElement, showElement}) {
    const [elements, setElements] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [search, setSearch] = useState("");

    useEffect(()=>{

    },[])
    
    if(loading){
        return (<LoadingBar />)
    }
    if(data === undefined || data === null || users === undefined || users === null){
        return (<>
            <LoadingBar />
        </>
        )
    }
    if(data.length === 0){
        return (
            <div>
                No elements found.
            </div>
        )
    }
    return (
        <div>
            Elements
            {
                data.map((element, index) => {
                    let ort = "";
                    if(users[element.userId] !== undefined && users[element.userId].hasOwnProperty("location") === true && users[element.userId].location !== null){
                        if(users[element.userId].location.hasOwnProperty("display_name") === true){
                            ort = users[element.userId].location.display_name;
                        }
                    }
                    let username = "";
                    if(users[element.userId] !== undefined && users[element.userId].hasOwnProperty("username") === true){
                        username = users[element.userId].username;
                    }
                    return (
                        <ElementItem 
                            key={index} 
                            mySkilly={mySkilly} 
                            setShowChat={setShowChat} 
                            setShowElement={setShowElement} 
                            title={element.subject} 
                            id={element.id}
                            ort={ort} 
                            name={username} 
                            showElement={showElement}
                            user={user}
                            creatorId={element.userId}
                            />
                    )
                })
            }
            </div>
    );
}

export default ElementList;