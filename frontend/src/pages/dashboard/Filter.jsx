import React, { useEffect, useState } from 'react';
import StandardInputField from '../../components/StandardInputField';

import './Filter.css';

function Filter() {
    const [searchField, setSearchField] = useState("");

    useEffect(()=>{

    },[])


    return (
        <div>
            <StandardInputField
                id={"testField"}
                label={"Suchen"}
                className={""}
                readOnly={false}
                value={searchField}
                changeHandler={setSearchField}
            />
        </div>
    );
}

export default Filter;