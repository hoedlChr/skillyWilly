import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';
import Chat from './dashboard/Chat';
import ChatList from './dashboard/ChatList';
import ElementList from './dashboard/ElementList';
import Filter from './dashboard/Filter';
import ElementView from './dashboard/ElementView';

function Dashboard() {

    useEffect(()=>{

    },[])


    return (
        <div className='container'>
            <h1>Dashboard</h1>
            <div className='row'>
                <div className='col-2'>
                    <div className="row">
                        <Filter />
                    </div>
                    <div className="row">
                        <ChatList />
                    </div>
                </div>
                <div className='col-6' style={{height: "100vh", overflowY: "scroll"}}>
                    <ElementList/>
                </div>
                <div className="col-3">
                    <Chat />  
                    <ElementView/>  
                </div>
            </div>
        </div>
    );
}

export default Dashboard;