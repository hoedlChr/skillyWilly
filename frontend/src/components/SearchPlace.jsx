import React, { useEffect, useState } from 'react';
import Button from './Button';
import InputField from './InputField';

function SearchPlace({value, changeHandler}) {
    const [searchValue, setSearchValue] = useState(value.display_name);
    const [geoData, setGeoData] = useState([]);
    const [loading, setLoading] = useState(false);

    const findLocation = () => {
        setLoading(true);
        const location = encodeURIComponent(searchValue);
        fetch(`https://nominatim.openstreetmap.org/search.php?q=${location}&polygon_geojson=1&format=jsonv2`)
            .then(response => response.json())
            .then(data =>{ 
                setGeoData(data);
                if(data.length === 1){
                    changeHandler(data[0]);
                    setSearchValue(data[0].display_name);
                    setGeoData([]);
                }

            })
            .catch(error => console.error('Error fetching data:', error))
            .finally(() => {
                setLoading(false);
            });
        };
    

    return (<>
        <div className='input-group'>
                <InputField
                    id={"location"}
                    className={""}
                    readOnly={false}
                    placeholder={""}
                    value={searchValue}
                    changeHandler={setSearchValue}
                />
                {
                    loading ? <div className='loading'>Loading...</div> : 
                    <Button onClick={findLocation} className='btn-primary'>Search</Button>
                }
        </div>
        {JSON.stringify(geoData)}
    </>
    );
};

export default SearchPlace;