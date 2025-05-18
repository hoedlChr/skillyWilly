import React, { useEffect, useState } from 'react';

import './ChatList.css';
import LoadingBar from '../../components/LoadingBar';

function ChatList({setShowChat,currentUser, chats, users, setShowElement}) {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [search, setSearch] = useState("");

    useEffect(()=>{

    },[])

    if(loading){
        return (<LoadingBar />)
    }
    if(chats === undefined || chats.length === 0 || users === undefined || users.length === 0){
        return (
            <LoadingBar />
        )
    }
    let chatList = [];
    chats.forEach((chat, index) => {
        const t = new Date(chat.created);
        let time = t.toLocaleTimeString([], {
            hour: "2-digit",
            minute: "2-digit",
        });
        let date = t.toLocaleDateString("de-AT")
        
        if (t.toLocaleDateString("de-AT") === new Date().toLocaleDateString("de-AT")) {
            date = "";
        }
        if(chatList[chat.userToId] === undefined && currentUser.id !== chat.userToId){
            if(users[chat.userToId] === undefined){
                return;
            }
            chatList[chat.userToId] = <ChatItem key={"chatItem" + index}
            id={chat.userToId} 
            setShowChat={setShowChat} 
            setShowElement={setShowElement} 
            name={users[chat.userToId].username} 
            chatname={chat.message} 
            lastmessage={"test"} 
            time={date + " " + time}/>
        }
        if(chatList[chat.userFromId] === undefined && currentUser.id !== chat.userFromId){
            if(users[chat.userFromId] === undefined){
                return;
            }
            chatList[chat.userFromId] = 
            <ChatItem  key={"chatItem" + index}
            id={chat.userFromId} 
            setShowChat={setShowChat} 
            setShowElement={setShowElement} 
            name={users[chat.userFromId].username} 
            chatname={chat.message} 
            lastmessage={"test"} 
            time={date + " " + time}/>
        }
    })

    return (
        <div>
            Chats
            {chatList}
            </div>
    );
}

function ChatItem({id, setShowChat, setShowElement, name, chatname, lastmessage, time}) {
    return (
        <div className="chatListItem row clickable mb-2" onClick={() => {setShowChat(id); setShowElement(false)}}>
            <div className='col'>
                <div>
                    <strong>
                        {name}
                    </strong>
                </div>
                <div>
                    <em>
                        {chatname}
                    </em>
                </div>
            </div>
            <div className="col" style={{textAlign: "right"}}>
                <div>
                    {time}
                </div>
            </div>
        </div>
    )
}

export default ChatList;