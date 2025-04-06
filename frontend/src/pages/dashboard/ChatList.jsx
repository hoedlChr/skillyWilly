import React, { useEffect, useState } from 'react';

import './ChatList.css';

function ChatList() {

    useEffect(()=>{

    },[])


    return (
        <div>
            Chats
            <ChatItem name={"Oliver"} chatname={"SkillyWilly"} lastmessage={"test"} time={"12:30"}/>
            <ChatItem name={"Selina"} chatname={"SkillyWilly"} lastmessage={"test"} time={"05.04.2025"}/>
            <ChatItem name={"Nicole"} chatname={"SkillyWilly"} lastmessage={"test"} time={"06.04.2024"}/>
            <ChatItem name={"Markus"} chatname={"SkillyWilly"} lastmessage={"test"} time={"15:30"}/>
        </div>
    );
}

function ChatItem({name, chatname, lastmessage, time}) {
    return (
        <div className="chatListItem row clickable mb-2">
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