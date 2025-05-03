import React, { useEffect, useState } from 'react';
import ElementItem from './ElementItem';
import LoadingBar from '../../components/LoadingBar';

function ElementList({mySkilly=false, data, users, setShowChat, setShowElement}) {
    const [elements, setElements] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [search, setSearch] = useState("");

    useEffect(()=>{

    },[])
    
    if(loading){
        return (<LoadingBar />)
    }
    return (
        <div>
            Elements
            {
                data.map((element, index) => {
                    let ort = "";
                    if(users[element.createdByUserId] !== undefined && users[element.createdByUserId].hasOwnProperty("location") === true){
                        ort = users[element.createdByUserId].location.display_name;
                    }
                    let username = "";
                    if(users[element.createdByUserId] !== undefined && users[element.createdByUserId].hasOwnProperty("username") === true){
                        username = users[element.createdByUserId].username;
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
                            name={username} />
                    )
                })
            }
            </div>
    );
}

export default ElementList;