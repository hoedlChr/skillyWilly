import React, { useEffect, useState } from 'react';

import './ElementItem.css';
import Button from '../../components/Button';

function ElementItem({mySkilly=false,setShowChat, setShowElement, title, name, ort, id}) {

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
        e.stopPropagation();
        console.log("delete item");
    }

    return (
        <table style={{width: "100%"}} onClick={clickItem}>
            <tbody>
                <tr className='elementItem clickable mb-2 d-flex align-items-center justify-content-between'>
                    <td className='name'>
                        {name}<br/>
                        {ort}
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