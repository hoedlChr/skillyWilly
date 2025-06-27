import React, { useEffect, useState } from "react";
import InputField from "../../components/InputField";
import Button from "../../components/Button";

function Chat({ currentUser, userId, users, chats, style }) {
  const [message, setMessage] = useState("");
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
        message={chat.message.replace(/^"|"$/g, '')}
        time={date + " " + time}
        />
      );
    } else if (chat.userToId === userId) {
      messages.push(
        <OutGoingMessage key={"chatMessage" + index}
          message={chat.message.replace(/^"|"$/g, '')}
          time={date + " " + time}
          />);
    }
});

const sendMessage = () => {
  const formdata = 
    message.trim()
  ;

  const requestOptions = {
    method: "POST",
    body: JSON.stringify(formdata),
    credentials: "include",
    redirect: "follow"
  };

  let from = currentUser.id;
  let to = userId;
  fetch(`/api/messages/${from}/${to}`, requestOptions)
    .then((response) => response.text())
    .then((result) => {setMessage("");})
    .catch((error) => console.error(error));
}

const followUser = () => {
    let from = currentUser.id;
    let to = userId;

    const formdata = {
      followerId: from,
      followingId: to
    }

    const requestOptions = {
      method: "POST",
      body: JSON.stringify(formdata),
      credentials: "include",
      redirect: "follow"
    };

    fetch(`/api/followers`, requestOptions)
    .then((response) => response.text())
    .then((result) => {console.log(result);})
    .catch((error) => console.error(error));
}

const unFollowUser = () => {
    let from = currentUser.id;
    let to = userId;

    const formdata = {
      followerId: from,
      followingId: to
    }

    const requestOptions = {
      method: "DELETE",
      body: JSON.stringify(formdata),
      credentials: "include",
      redirect: "follow"
    };

    fetch(`/api/followers`, requestOptions)
    .then((response) => response.text())
    .then((result) => {console.log(result);})
    .catch((error) => console.error(error));
}

return (
  <div style={{ ...style, display: "flex", flexDirection: "column" }}>
    <div className="row align-items-center">
      <div className="col">
        <h2>{name}</h2>
      </div>
      <div className="col-auto">
        {
          true ?
          <Button className="btn btn-success" onClick={() => followUser()}>follow</Button>:
          <Button className="btn btn-danger" onClick={() => unFollowUser()}>unfollow</Button>
        }
      </div>
    </div>
    <h4>{title}</h4>
    <div className="mv-2">
      <hr />
    </div>
    <div className="overflow-auto" style={{ flex: 1, height: "100%", maxHeight: "calc(100vh - 200px)" }}>
      {messages}
    </div>
    <div>
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
