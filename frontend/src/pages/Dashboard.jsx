import React, { use, useEffect, useState } from 'react';
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
import InputField from '../components/InputField';

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

    const [search, setSearch] = useState("");

    const [message, setMessage] = useState("");

    const [followedUsers, setFollowedUsers] = useState([]);
    const [allTasks, setAllTasks] = useState(true);


    const height = 90;

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

        let from = allUserData.id;
        let to = showChat;
        fetch(`/api/messages/${from}/${to}`, requestOptions)
            .then((response) => response.text())
            .then((result) => {setMessage("");})
            .catch((error) => console.error(error));
    }

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
            // setData(data.skills);
            setAllUserData(data);
        })
        .catch((error) => {
            console.error("Error fetching user data:", error);
        });

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

        fetch(`/api/followers/${user.id}/following`, {
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
            console.log("Followed users:", data);
        })
        .catch((error) => {
            console.error("Error fetching user data:", error);
        });

// get messages from/to user
    }, []);

    //call all chats every 5 seconds
    useEffect(() => {
        const interval = setInterval(() => {
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

        }, 5000);
        return () => clearInterval(interval);
    }, []);

    useEffect(() => {
        setSmallViewChats(false);
        setSmallViewSkills(false);
        if(showChat === allUserData.id){
            setShowChat(false);
        }
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
    let filteredData = data
    if(data !== undefined && data.length > 0){
        filteredData = data.filter((element) => {
            if(allTasks === false && element.id !== 19  && element.id !== 20  && element.id !== 32){
                return false;
            }
            if (search === "") {
                return true;
            }
            let ort = "";
            if (users[element.userId] !== undefined && users[element.userId].hasOwnProperty("location") === true && users[element.userId].location !== null && users[element.userId].location.hasOwnProperty("display_name") === true) {
                ort = users[element.userId].location.display_name;
            }
            let username = "";
            if (users[element.userId] !== undefined && users[element.userId].hasOwnProperty("username") === true) {
                username = users[element.userId].username;
            }
            return element.subject.toLowerCase().includes(search.toLowerCase()) || 
                ort.toLowerCase().includes(search.toLowerCase()) || 
                username.toLowerCase().includes(search.toLowerCase());
        })
    }
    return (<>
        <CreateSkill show={showCreateSkill} user={user} setShow={setShowCreateSkill}/>
        <SkillOnMap data={data} users={users} show={showElementsOnMap} setShow={setShowElementsOnMap}/>
            <Navbar text="Dashboard" search={search} setSearch={setSearch} setShowCreateSkill={setShowCreateSkill} setShowElementsOnMap={setShowElementsOnMap}/>
        <div className='container'>
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
                            <ElementList data={filteredData} users={users} setShowChat={setShowChat} showElement={showElement} setShowElement={setShowElement}/>
                        </div>
                    </div>:null
                }
                </div>
                {
                    showChat === false && showElement === false ? null:
                    <div className="card">
                        {
                            showChat ?
                            <Chat currentUser={allUserData} userId={showChat} followedUsers={followedUsers} chats={chats} users={users} style={{height: `${height-5}vh`}} />:null
                        }
                        {
                            showElement ?
                            <ElementView currentUser={allUserData} id={showElement} data={filteredData} users={users}/> :null
                        }
                    </div>
                }

            </div>
            <div className='d-none d-xl-block'>
                <div className='row'>
                    {
                        chats.length > 0 ?
                        <div className='col-3 overflow-auto' style={{height: `${height}vh`}}>
                                <div className='card'>
                                    <h5 className='card-header'>Chats</h5>
                                    <div className='card-body'>
                                        <ChatList setShowChat={setShowChat} currentUser={allUserData} chats={chats} users={users} setShowElement={setShowElement}/>
                                    </div>
                                </div>
                        </div>:null
                    }
                    <div className='col'>

                        <div className='card'>
                            <div className='card-header'>
                                <div className='d-flex justify-content-between align-items-center'>
                                    <h5 className=''>Elements</h5>
                                    <div className='d-flex gap-2'>
                                        <Button className={'btn btn-' + (allTasks ? "primary" : "secondary")} onClick={() => {setAllTasks(!allTasks)}}>All</Button>
                                        <Button className={'btn btn-' + (!allTasks ? "primary" : "secondary")} onClick={() => {setAllTasks(!allTasks)}}>Followed</Button>
                                    </div>
                                </div>
                            </div>
                            <div className='card-body'>
                                <div className={`overflow-auto`} style={{height: `${height}vh`}}>
                                <ElementList data={filteredData} users={users} setShowChat={setShowChat} showElement={showElement} setShowElement={setShowElement}/>
                                </div>
                            </div>
                        </div>
                    </div>
                    {
                        showChat === false && showElement === false ? null:
                        <div className="col-4" style={{height: `${height}vh`}}>
                            {
                                showChat ?
                                <div className='card'>
                                    <h5 className='card-header'>Chat</h5>
                                    <div className='card-body'>
                                        <Chat currentUser={allUserData} userId={showChat} chats={chats} users={users} style={{height: `${height-10}vh`}} />
                                    </div>
                                    <div className='card-footer'>
                                        <div className="col-auto d-flex align-items-center">
                                                <InputField value={message} changeHandler={setMessage} placeholder="Type a message..." />
                                                <Button className="btn btn-primary" onClick={() => sendMessage()}>Send</Button>
                                        </div>
                                    </div>
                                </div>
                                :null
                            }
                            {
                                showElement ?
                                <div className='card'>
                                    <h5 className='card-header'>Element</h5>
                                    <div className='card-body' style={{height: `100%`}}>
                                        <ElementView currentUser={allUserData} id={showElement} data={filteredData} users={users} setShowElement={setShowElement} setShowChat={setShowChat}/> 
                                    </div>
                                </div>
                                :null
                            }
                        </div>
                    }
                </div>
            </div>
        </div>
    </>);
}

export default Dashboard;