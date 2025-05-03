import React, { useEffect, useState } from 'react';

import './ElementItem.css';
import Button from '../../components/Button';

function ElementItem({mySkilly=false,setShowChat, setShowElement, showElement,user, creatorId, title, name, ort, id}) {

    const clickChat = (e) => {
        e.stopPropagation();
        setShowChat(true);
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
        fetch(`/api/skills/${id}`, {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json",
			},
		})
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((err) => {
            console.log(err);
        });

    }
    
    return (
        <table style={{width: "100%"}} onClick={clickItem}>
            <tbody>
                <tr className={'elementItem clickable mb-2 d-flex align-items-center justify-content-between ' + (showElement === id ? "selectedItem" : "")}>
                    <td style={{height: "3em"}} className='name overflow-auto'>
                        {name}<br/>
                        <small>
                            {ort}
                            </small>
                    </td>
                    <td className='title'>
                        {title}
                    </td>
                    <td className='button'>
                        {
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