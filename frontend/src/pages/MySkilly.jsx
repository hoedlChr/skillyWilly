import React, { useEffect, useState } from 'react';
import ElementList from './dashboard/ElementList';
import CreateSkill from './dashboard/CreateSkill';
import Navbar from './dashboard/Navbar';
import ElementEditor from './dashboard/ElementEditor';

function MySkilly({user}) {
    const [showElement, setShowElement] = useState(false);
    const [showCreateSkill, setShowCreateSkill] = useState(false);
    const [data, setData] = useState([]);
    const [allUserData, setAllUserData] = useState({});
    const height = 90;
    useEffect(()=>{
        //fetch user data
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
    },[])

    const users = {[user.id]: allUserData};
    return (<>
        <CreateSkill show={showCreateSkill} user={user} setShow={setShowCreateSkill}/>
        <div className='container'>
            <Navbar text="MySkilly" setShowCreateSkill={setShowCreateSkill}/>
            <div className='row'>
                <div className='col-5 overflow-auto' style={{height: `${height}vh`}}>
                        <ElementList data={data} user={user} users={users} mySkilly={true} setShowElement={setShowElement}/>
                </div>
                {
                    showElement === false ? null:
                    <div className="col-4" style={{height: `${height}vh`}}>
                        {
                            showElement ?
                            <ElementEditor id={showElement} data={data} setShowElement={setShowElement} user={user}/> :null
                        }
                    </div>
                }
            </div>
        </div>
    </>);
}

export default MySkilly;