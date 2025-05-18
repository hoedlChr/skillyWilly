import React, { useEffect, useState } from "react";
import InputField from "../../components/InputField";
import Button from "../../components/Button";

function Chat({ currentUser, userId, users, chats, style }) {
  useEffect(() => {}, []);
let name = users[userId].username;
let title = "";

let messages = [];
let sortedChats = [...chats].sort((a, b) => {
  return new Date(a.created) - new Date(b.created);
});
sortedChats.forEach((chat, index) => {
    const t = new Date(chat.created);
    let time = t.toLocaleTimeString([], {
        hour: "2-digit",
        minute: "2-digit",
    });
    let date = t.toLocaleDateString("de-AT")
    
    if (t.toLocaleDateString("de-AT") === new Date().toLocaleDateString("de-AT")) {
        date = "";
    }
    if (chat.userFromId === userId) {
      messages.push(<InComingMessage key={"chatMessage" + index}
        message={chat.message}
        time={date + " " + time}
        />
      );
    } else if (chat.userToId === userId) {
      messages.push(
        <OutGoingMessage key={"chatMessage" + index}
          message={chat.message}
          time={date + " " + time}
          />);
    }
});

const sendMessage = () => {
  const formdata = new FormData();
  formdata.append("senderId", "29");
  formdata.append("recipientId", "36");
  formdata.append("content", "Guten tag ich hätte gern");

  const requestOptions = {
    method: "POST",
    body: formdata,
    credentials: "include",
    redirect: "follow"
  };

  fetch("/api/messages", requestOptions)
    .then((response) => response.text())
    .then((result) => console.log(result))
    .catch((error) => console.error(error));
}

return (
    <div style={{ ...style, height: "100vh", display: "flex", flexDirection: "column" }}>
        <div className="row">
            <h2>{name}</h2>
            <h4>{title}</h4>
            <hr />
        </div>
        <div className="overflow-auto flex-grow-1" style={{ flex: "1 1 auto", maxHeight: "78vh" }}>
          {messages}
           
        </div>
        <div style={{ flexShrink: 0 }}>
            <div className="row">
                <div className="col-10">
                    <InputField placeholder="Type a message..." />
                </div>
                <div className="col-2">
                    <Button className="btn btn-primary" onClick={() => sendMessage()}>Send</Button>
                </div>
            </div>
        </div>
    </div>
);
}

function InComingMessage({ message = "", time = "" }) {
  return (
    <div className="form-group">
      <small className="text-muted">{time}</small>
      <div className="alert alert-primary" role="alert">
        {message}
      </div>
    </div>
  );
}
function OutGoingMessage({ message = "", time = "" }) {
  return (
    <div className="form-group text-end">
      <small className="text-muted">{time}</small>
      <div className="alert alert-secondary" role="alert">
        {message}
      </div>
    </div>
  );
}

export default Chat;
