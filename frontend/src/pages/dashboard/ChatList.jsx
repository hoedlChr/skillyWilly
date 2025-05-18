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
    if(chats === undefined || chats.length === 0){
        return (
            <LoadingBar />
        )
    }
    let chatList = [];
    console.log("ChatList", chats, users, currentUser);
    chats.forEach(chat => {
        if(chatList[chat.userToId] === undefined && currentUser.id !== chat.userToId){
            chatList[chat.userToId] = 
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={users[chat.userToId].username} chatname={chat.message} lastmessage={"test"} time={"12:30"}/>
        }
        if(chatList[chat.userFromId] === undefined && currentUser.id !== chat.userFromId){
            chatList[chat.userFromId] = 
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={users[chat.userFromId].username} chatname={chat.message} lastmessage={"test"} time={"12:30"}/>
        }
    })

    return (
        <div>
            Chats
            {chatList}
            </div>
    );
}

function ChatItem({setShowChat, setShowElement, name, chatname, lastmessage, time}) {
    return (
        <div className="chatListItem row clickable mb-2" onClick={() => {setShowChat(true); setShowElement(false)}}>
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