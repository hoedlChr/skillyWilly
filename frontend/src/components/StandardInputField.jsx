import React from 'react';
import InputField from './InputField';

function StandardInputField({
    id,
    label,
    className,
    readOnly = false,
    placeholder,
    value,
    helptext = "",
    type="text",
    changeHandler = ()=>{}
}){
    switch(type){
        default:
            return (<div className='form-group my-2'>
                <label htmlFor={id}>{label}</label>
                <InputField id={id} type={type} className={"form-control "+className} changeHandler={changeHandler} readOnly={readOnly} placeholder={placeholder} value={value}/>
                {
                    helptext != "" ? <small>{helptext}</small> : null
                }
                </div>);
        break;
    }
};

export default StandardInputField;