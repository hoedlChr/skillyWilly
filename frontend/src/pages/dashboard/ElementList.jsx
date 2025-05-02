import React, { useEffect, useState } from 'react';
import ElementItem from './ElementItem';
import LoadingBar from '../../components/LoadingBar';

function ElementList({setShowChat, setShowElement}) {
    const [elements, setElements] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [search, setSearch] = useState("");

    useEffect(()=>{

    },[])
    
    if(loading){
        return (<LoadingBar />)
    }

    return (
        <div>
            Elements
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christophasdfasdfsafdasfsafd"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeldasdfsdfasdfsadf"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmierenasdfasdfsdafsdfsadfsad"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
            <ElementItem setShowChat={setShowChat} setShowElement={setShowElement} title={"Programmieren"} ort={"Eichfeld"} name={"Christoph"} />
        </div>
    );
}

export default ElementList;