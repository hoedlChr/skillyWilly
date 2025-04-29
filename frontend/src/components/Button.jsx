import React from 'react';

const Button = ({ onClick, children, disabled=false, className="" }) => {
    return (
        <button disabled={disabled} type="button" onClick={onClick} className={'btn '+className}>
            {children}
        </button>
    );
};


export default Button;