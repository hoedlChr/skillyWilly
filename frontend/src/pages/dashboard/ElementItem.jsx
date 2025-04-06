import React, { useEffect, useState } from 'react';

import './ElementItem.css';
import Button from '../../components/Button';

function ElementItem({title, name, ort, id}) {

    useEffect(()=>{

    },[])


    return (<div className='elementItem mb-2'>
            <div className='name my-2'>
                {name}
            </div>
            <div className='ort my-2'>
                {ort}
            </div>
            <div className='title my-2'>
                {title}
            </div>
            <div className='button'>
                <Button className='btn-primary' onClick={() => {console.log("Edit", id)}}>Kontaktieren</Button>
            </div>
        </div>
    );
}

export default ElementItem;