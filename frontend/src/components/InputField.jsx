import React from 'react';
import FCKEditor from './FCKEditor';
import SearchPlace from './SearchPlace';

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
        case "place":
            return(
                <SearchPlace value={value} changeHandler={changeHandler}/>
            );
        break;
        case "html":
            return (
                <FCKEditor value={value} changeHandler={changeHandler}/>
            );
        break;
        case "textarea":
            return (
                <textarea id={id} className={"form-control "+className} onChange={myChangeHandler} readOnly={readOnly} placeholder={placeholder} value={value}/>
            );
        default:
            return (
                <input id={id} type={type} className={"form-control "+className} onChange={myChangeHandler} readOnly={readOnly} placeholder={placeholder} value={value}/>
            );
        break;
    }
};

export default InputField;