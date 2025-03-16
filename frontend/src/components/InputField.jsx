import React from 'react';

function InputField({
    id,
    className,
    readOnly = false,
    placeholder,
    value,
    type="text",
    changeHandler = ()=>{}
}){
    const myChangeHandler = (e) => {
        changeHandler(e.target.value)
    }
    switch(type){
        default:
            return (
                <input id={id} type="text" className={"form-control "+className} onChange={myChangeHandler} readOnly={readOnly} placeholder={placeholder} value={value}/>
            );
        break;
    }
};

export default InputField;