import React, { useEffect, useState } from 'react';
import InputField from '../../components/InputField';

function Chat() {

    useEffect(()=>{

    },[])


    return (
        <div>
            <div>
                <h1>Oliver</h1>
                <h2>SkillyWilly</h2>
            </div>
            <div className='chat' style={{height: "70vh", width: "100%", overflowY: "scroll"}}>
                
            </div>
            <div>
                <div className='row'>
                    <div className='col-8'>
                        <InputField type="text" id="chatInput" label="Nachricht" className="mb-2" readOnly={false} value={""} changeHandler={() => {}}/>
                    </div>
                    <div className="col">
                            <button className="btn btn-primary">Senden</button>
                    </div>
                </div>
            </div>
            
        </div>
    );
}

export default Chat;