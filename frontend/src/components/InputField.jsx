import React, { lazy } from 'react';
const FCKEditor = lazy(()=>import('./FCKEditor'));
const SearchPlace = lazy(()=>import('./SearchPlace'));

function InputField({
    id,
    className,
    readOnly = false,
    placeholder,
    value,
    type="text",
    required=false,
    changeHandler = ()=>{},
    onKeyDown = ()=>{},
}){
    const [passwordType, setPasswordType] = React.useState(type);
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
        case "password":
            return(<div className="input-group">
                <input id={id} type={passwordType} className={"form-control "+className} onChange={myChangeHandler} onKeyDown={onKeyDown} readOnly={readOnly} placeholder={placeholder} value={value}/>
                <button className="btn btn-primary" disabled={readOnly}  onClick={() => {setPasswordType(passwordType === "password" ? "text" : "password")}}>{passwordType === "password" ? "Show" : "Hide"}</button>
            </div>
            );
        default:
            return (
                <input id={id} type={type} className={"form-control "+className} onChange={myChangeHandler} onKeyDown={onKeyDown} readOnly={readOnly} placeholder={placeholder} value={value}/>
            );
        break;
    }
};

export default InputField;