import React, { useEffect, useState } from 'react';
import InputField from '../../components/InputField';
import Button from '../../components/Button';

function Chat({name="name", title="title", style}) {

    useEffect(()=>{

    },[])


    return (<div style={style}>
        <div className="row">
            <h2>
                {name}
            </h2>
            <h4>
                {title}
            </h4>
            <hr/>
        </div>
        <div className="overflow-auto" style={{height: "75vh"}}>
            <InComingMessage time="02.05.2025 12:31" message="Hello, how are you?" />
            <OutGoingMessage time="02.05.2025 12:21" message="I am fine, thank you!" />
        </div>
        <div>
            <div className="row">
                <hr/>
                <div className="col-10">
                    <InputField placeholder="Type a message..." />
                </div>
                <div className="col-2">
                    <Button className="btn btn-primary">Send</Button>
                </div>
            </div>
        </div>
        </div>
    );
}

function InComingMessage({message="", time=""}) {
    return (
        <div className="row">
            <div className="col-12">
                <small className="text-muted">{time}</small>
                <div className="alert alert-primary" role="alert">
                    {message}
                </div>
            </div>
        </div>
    );
}
function OutGoingMessage({message="", time=""}) {
    return (
        <div className="row">
            <div className="col-12 text-end">
                <small className="text-muted">{time}</small>
                <div className="alert alert-secondary" role="alert">
                    {message}
                </div>
            </div>
        </div>
    );
}

export default Chat;