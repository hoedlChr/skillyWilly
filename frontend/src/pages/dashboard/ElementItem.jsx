import React, { useEffect, useState } from 'react';

import './ElementItem.css';
import Button from '../../components/Button';

function ElementItem({title, name, ort, id}) {

    useEffect(()=>{

    },[])


    return (
        <div className='elementItem clickable mb-2 d-flex align-items-center justify-content-between'>
            <div className='name mx-2'>
                {name}
            </div>
            <div className='ort mx-2'>
                {ort}
            </div>
            <div className='title mx-2'>
                {title}
            </div>
            <div className='button mx-2'>
                <Button className='btn-primary' onClick={() => {console.log("Edit", id)}}>Contact</Button>
            </div>
        </div>
    );
}

export default ElementItem;