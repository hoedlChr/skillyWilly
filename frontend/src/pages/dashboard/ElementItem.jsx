import React, { useEffect, useState } from 'react';

import './ElementItem.css';
import Button from '../../components/Button';

function ElementItem({setShowChat, setShowElement, title, name, ort, id}) {

    const clickButton = (e) => {
        e.stopPropagation();
        setShowChat(true);
        setShowElement(false);
    }

    const clickItem = (e) => {
        e.stopPropagation();
        setShowElement(true);
        setShowChat(false);
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
                        <Button className='btn-primary' onClick={clickButton}>Contact</Button>
                    </td>
                </tr>
            </tbody>
        </table>
    );
}

export default ElementItem;