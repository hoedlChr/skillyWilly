import React from 'react';

const InfoMessage = ({ heading="", body="", children, type="", className="" }) => {
    return (
        <div class={`alert alert-${type} ${className}`} role="alert">
        {
            heading === "" ? null : <h5 class="alert-heading">{heading}</h5>
        }
        {
            body === "" ? null : <p>{body}</p>
        }
        {children}
        </div>
    );
};


export default InfoMessage;