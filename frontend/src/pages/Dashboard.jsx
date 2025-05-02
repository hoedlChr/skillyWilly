import React, { useEffect, useState } from 'react';
import StandardInputField from '../components/StandardInputField';
import Button from '../components/Button';
import Chat from './dashboard/Chat';
import ChatList from './dashboard/ChatList';
import ElementList from './dashboard/ElementList';
import Filter from './dashboard/Filter';
import ElementView from './dashboard/ElementView';
import CreateSkill from './dashboard/CreateSkill';
import SkillOnMap from './dashboard/SkillOnMap';
import Navbar from './dashboard/Navbar';

function Dashboard({user, setUser}) {
    const [showChat, setShowChat] = useState(false);
    const [showElement, setShowElement] = useState(false);
    const [showCreateSkill, setShowCreateSkill] = useState(false);
    const [showElementsOnMap, setShowElementsOnMap] = useState(false);
    const [showUserContextMenu, setShowUserContextMenu] = useState(false);

    const height = 90;
    useEffect(()=>{

    },[])


    return (<>
        <CreateSkill show={showCreateSkill} setShow={setShowCreateSkill}/>
        <SkillOnMap show={showElementsOnMap} setShow={setShowElementsOnMap}/>
        <div className='container overflow-hidden'>
            <Navbar text="Dashboard" setShowCreateSkill={setShowCreateSkill} setShowElementsOnMap={setShowElementsOnMap}/>
            <div className='row'>
                <div className='col-3 overflow-auto' style={{height: `${height}vh`}}>
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
                            <Chat style={{height: `${height}vh`}}/>:null
                        }
                        {
                            showElement ?
                            <ElementView/> :null
                        }
                    </div>
                }
            </div>
        </div>
    </>);
}

export default Dashboard;