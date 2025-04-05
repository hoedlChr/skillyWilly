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
        case "place":
            return (<div className='form-group my-2'>
                <label htmlFor={id}>{label}</label>
                <InputField id={id} type="place" className={"form-control "+className} changeHandler={changeHandler} readOnly={readOnly} placeholder={placeholder} value={value}/>
                {
                    helptext != "" ? <small>{helptext}</small> : null
                }
                </div>);
        case "html":
            return (<div className='form-group my-2'>
                <label htmlFor={id}>{label}</label>
                <InputField id={id} type="html" className={"form-control "+className} changeHandler={changeHandler} readOnly={readOnly} placeholder={placeholder} value={value}/>
                </div>);
        break;
        case "teaxtarea":
            return (<div className='form-group my-2'>
                <label htmlFor={id}>{label}</label>
                <InputField id={id} type="teaxtarea" className={"form-control "+className} changeHandler={changeHandler} readOnly={readOnly} placeholder={placeholder} value={value}/>
                {
                    helptext != "" ? <small>{helptext}</small> : null
                }
                </div>);
        break;
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