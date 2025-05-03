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
    const [chatList, setChatList] = useState([1]);

    const [data, setData] = useState([]);
    const [users, setUsers] = useState({});

    const height = 90;
    useEffect(() => {
        fetch(`/api/skills`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        })
        .then((res) => {
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
            return res.json();
        })
        .then((data) => {
            setData(data);

            // Fetch users
            data.forEach(element => {
                if (users[element.userId]) {
                    return;
                }
                fetch(`/api/users/${element.userId}`, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                })
                .then((res) => {
                    if (!res.ok) {
                        throw new Error(`HTTP error! status: ${res.status}`);
                    }
                    return res.json();
                })
                .then((data) => {
                    setUsers((prevUsers) => ({
                        ...prevUsers,
                        [element.userId]: data,
                    }));
                })
                .catch((err) => {
                    console.error("Error fetching skills:", err);
                });
            });

        })
        .catch((err) => {
            console.error("Error fetching skills:", err);
        });

    }, []);

    return (<>
        <CreateSkill show={showCreateSkill} user={user} setShow={setShowCreateSkill}/>
        <SkillOnMap data={data} users={users} show={showElementsOnMap} setShow={setShowElementsOnMap}/>
        <div className='container'>
            <Navbar text="Dashboard" setShowCreateSkill={setShowCreateSkill} setShowElementsOnMap={setShowElementsOnMap}/>
            <div className='row'>
                {
                    chatList.length > 0 ?
                    <div className='col-3 overflow-auto' style={{height: `${height}vh`}}>
                        <div className="row">
                            <ChatList setShowChat={setShowChat} setShowElement={setShowElement}/>
                        </div>
                    </div>:null
                }
                <div className={`col overflow-auto`} style={{height: `${height}vh`}}>
                        <ElementList data={data} users={users} setShowChat={setShowChat} showElement={showElement} setShowElement={setShowElement}/>
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
                            <ElementView id={showElement} data={data} users={users}/> :null
                        }
                    </div>
                }
            </div>
        </div>
    </>);
}

export default Dashboard;