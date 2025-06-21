import React, { useEffect, useState } from 'react';

import './ElementItem.css';
import Button from '../../components/Button';
import LoadingBar from '../../components/LoadingBar';

function ElementItem({mySkilly=false,setShowChat, setShowElement, showElement,user, creatorId, title, name, ort, id}) {
    const [loading, setLoading] = useState(false);
    const [deleted, setDeleted] = useState(false);

    const clickChat = (e) => {
        e.stopPropagation();
        setShowChat(creatorId);
        setShowElement(false);
    }

    const clickItem = (e) => {
        e.stopPropagation();
        setShowElement(id);
        setShowChat(false);
    }

    const deleteItem = (e) => {
        e.preventDefault();
        e.stopPropagation();
        if(!mySkilly){
            return;
        }
        if(user.id !== creatorId){
            return;
        }
        if(!window.confirm("Are you sure you want to delete this item?")){
            return;
        }
        setLoading(true);
        fetch(`/api/skills/${id}`, {
			method: "DELETE",
            credentials: "include",
			headers: {
				"Content-Type": "application/json",
			},
		})
        .then((res) => res.json())
        .then((data) => {
        })
        .catch((err) => {
            console.log(err);
        })
        .finally(() => {
            setLoading(false);
            setDeleted(true);
        });

    }

    let className = 'elementItem clickable mb-2 d-flex align-items-center justify-content-between ' + (showElement === id ? "selectedItem" : "");
    if(deleted){
        className = "elementItem deletedItem mb-2 d-flex align-items-center justify-content-between text-danger bg-light";
    }
    
    return (
        <table style={{width: "100%"}} onClick={deleted ? null : clickItem}>
            <tbody>
                <tr className={
                    className
                    }>
                    <td style={{height: "3em"}} className='name'>
                        {name}<br/>
                        <small className={deleted ? "text-danger" : 'text-muted'}>
                            {ort}
                        </small>
                    </td>
                    <td className='title'>
                        {title}
                    </td>
                    <td className='button'>
                    {
                        deleted ? null :

                            loading ? 
                            <LoadingBar /> :
                            
                                mySkilly ? 
                                <Button className='btn-danger' onClick={deleteItem}>delete</Button>:
                                <Button className='btn-primary' onClick={clickChat}>Contact</Button>
                            
                    }   
                    </td>
                </tr>
            </tbody>
        </table>
    );
}

export default ElementItem;