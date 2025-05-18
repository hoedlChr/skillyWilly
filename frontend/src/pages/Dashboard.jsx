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

    const [smallViewChats, setSmallViewChats] = useState(false);
    const [smallViewSkills, setSmallViewSkills] = useState(false);

    const [data, setData] = useState([]);
    const [chats, setChats] = useState([]);
    const [users, setUsers] = useState({});
    const [allUserData, setAllUserData] = useState({});

    const height = 90;
    useEffect(() => {
// Fetch all skills
        fetch(`/api/skills`, {
            method: "GET",
            credentials: "include",
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
                findUser(element.userId);
            });

        })
        .catch((err) => {
            console.error("Error fetching skills:", err);
        });

//get all User data 
        fetch(`/api/users/${user.id}`, {
            method: "GET",
            credentials: "include",
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
            setData(data.skills);
            setAllUserData(data);
        })
        .catch((error) => {
            console.error("Error fetching user data:", error);
        });

// get messages from/to user
        fetch(`/api/messages/all/${user.id}`, {
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
            let sortedData = data.sort((a, b) => new Date(b.created) + new Date(a.created));
            console.log(sortedData);
            setChats(sortedData);
            // Fetch users
            data.forEach(element => {
                findUser(element.userFromId);
                findUser(element.userToId);
            });
        })
        .catch((error) => {
            console.error("Error fetching user data:", error);
        });

    }, []);

    useEffect(() => {
        setSmallViewChats(false);
        setSmallViewSkills(false);
    }, [showChat, showElement]);

    const findUser = (userId) => {
        if (users[userId]) {
            return;
        }
        fetch(`/api/users/${userId}`, {
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
                [userId]: data,
            }));
        })
        .catch((err) => {
            console.error("Error fetching skills:", err);
        });
    };

    return (<>
        <CreateSkill show={showCreateSkill} user={user} setShow={setShowCreateSkill}/>
        <SkillOnMap data={data} users={users} show={showElementsOnMap} setShow={setShowElementsOnMap}/>
        <div className='container'>
            <Navbar text="Dashboard" setShowCreateSkill={setShowCreateSkill} setShowElementsOnMap={setShowElementsOnMap}/>
            <div className='d-block d-xl-none'>
                {
                    chats.length > 0 ?
                    <div className="card border-primary mb-3">
                        <div className='card-header border-primary bg-primary text-white clickable' onClick={()=>setSmallViewChats(!smallViewChats)}>
                            <strong>Chats</strong>
                            {
                                smallViewChats ?
                                <i className="bi bi-caret-down-fill float-end"></i>:
                                <i className="bi bi-caret-left-fill float-end"></i>
                            }
                        </div>

                    {
                        smallViewChats ? 
                        <div className="card-body border-primary">
                            <div className={`col overflow-auto`}>
                                    <ChatList setShowChat={setShowChat} currentUser={allUserData} chats={chats} users={users} setShowElement={setShowElement}/>
                            </div>
                        </div>:null
                    }
                    </div>:null
                }

                <div className="card border-primary mb-3">
                    <div className='card-header border-primary bg-primary text-white clickable' onClick={()=>setSmallViewSkills(!smallViewSkills)}>
                        <strong>Skills</strong>
                        {
                                smallViewSkills ?
                                <i className="bi bi-caret-down-fill float-end"></i>:
                                <i className="bi bi-caret-left-fill float-end"></i>
                            }
                    </div>
                {
                    smallViewSkills ? 
                    <div className="card-body border-primary">
                        <div className={`col overflow-auto`}>
                            <ElementList data={data} users={users} setShowChat={setShowChat} showElement={showElement} setShowElement={setShowElement}/>
                        </div>
                    </div>:null
                }
                </div>
                {
                    showChat === false && showElement === false ? null:
                    <div className="card">
                        {
                            showChat ?
                            <Chat currentUser={allUserData} userId={showChat} chats={chats} users={users} style={{height: `${height}vh`}} />:null
                        }
                        {
                            showElement ?
                            <ElementView id={showElement} data={data} users={users}/> :null
                        }
                    </div>
                }

            </div>
            <div className='d-none d-xl-block'>
                <div className='row'>
                    {
                        chats.length > 0 ?
                        <div className='col-3 overflow-auto' style={{height: `${height}vh`}}>
                            <div className="row">
                                <ChatList setShowChat={setShowChat} currentUser={allUserData} chats={chats} users={users} setShowElement={setShowElement}/>
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
                                <Chat currentUser={allUserData} userId={showChat} chats={chats} users={users} style={{height: `${height}vh`}} />:null
                            }
                            {
                                showElement ?
                                <ElementView id={showElement} data={data} users={users}/> :null
                            }
                        </div>
                    }
                </div>
            </div>
        </div>
    </>);
}

export default Dashboard;