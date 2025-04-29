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
    required=false,
    changeHandler = ()=>{}
}){
    let field = null;
    switch(type){
        default:
            field = <InputField id={id} type={type} required={required} className={"form-control "+className} changeHandler={changeHandler} readOnly={readOnly} placeholder={placeholder} value={value}/>;
        break;
    }
    return (
        <div className='form-group my-2'>
            <label htmlFor={id}>
                <strong>{label}</strong>
                {
                    required === true ? <strong className='text-danger'>*</strong> : null
                }
            </label>
            {field}
            {
                helptext != "" ? <small>{helptext}</small> : null
            }
        </div>
    )
};

export default StandardInputField;