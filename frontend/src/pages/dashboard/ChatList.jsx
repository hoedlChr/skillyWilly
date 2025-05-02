import React, { useEffect, useState } from 'react';

import './ChatList.css';
import LoadingBar from '../../components/LoadingBar';

function ChatList({setShowChat, setShowElement}) {
    const [chats, setChats] = useState([]);
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
            Chats
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Oliver"} chatname={"SkillyWilly"} lastmessage={"test"} time={"12:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Selina"} chatname={"SkillyWilly"} lastmessage={"test"} time={"05.04.2025"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Nicole"} chatname={"SkillyWilly"} lastmessage={"test"} time={"06.04.2024"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
            <ChatItem setShowChat={setShowChat} setShowElement={setShowElement} name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
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