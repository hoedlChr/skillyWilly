import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';
import Chat from './dashboard/Chat';
import ChatList from './dashboard/ChatList';
import ElementList from './dashboard/ElementList';
import Filter from './dashboard/Filter';
import ElementView from './dashboard/ElementView';

function Dashboard({user, setUser}) {
    const [showChat, setShowChat] = useState(false);
    const [showElement, setShowElement] = useState(false);
    const height = 95;
    useEffect(()=>{

    },[])


    return (
        <div className='container'>
            <h1>Dashboard</h1>
            <div className='row'>
                <div className='col-3 overflow-auto' style={{height: `${height}vh`}}>
                    {/* <div className="row">
                        <Filter />
                    </div> */}
                    <div className="row">
                        <ChatList setShowChat={setShowChat} setShowElement={setShowElement}/>
                    </div>
                </div>
                <div className='col-5 overflow-auto' style={{height: `${height}vh`}}>
                        <ElementList setShowChat={setShowChat} setShowElement={setShowElement}/>
                </div>
                {
                    showChat === false && showElement === false ? null:
                    <div className="col-4" style={{height: `${height}vh`}}>
                        {
                            showChat ?
                            <Chat/>:null
                        }
                        {
                            showElement ?
                            <ElementView/> :null
                        }
                    </div>
                }
            </div>
        </div>
    );
}

export default Dashboard;