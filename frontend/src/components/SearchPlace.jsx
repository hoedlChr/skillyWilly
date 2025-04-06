import React, { useEffect, useState } from 'react';
import Button from './Button';
import InputField from './InputField';

import './SearchPlace.css';

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

    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            findLocation();
        }
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
                    onKeyDown={handleKeyDown}
                />
                {
                    loading ? <div className='loading'>Loading...</div> : 
                    <Button onClick={findLocation} className='btn-primary'>Search</Button>
                }
        </div>
        { //TODO:
            geoData.length > 0 ? <div>
                    {
                        geoData.map((item, index) => {
                            return <div className='clickable selectElement' key={index} onClick={() => {
                                changeHandler(item);
                                setSearchValue(item.display_name);
                                setGeoData([]);
                            }}>
                                {item.display_name}
                            </div>
                        })
                    }
            </div> : null
        }
    </>
    );
};

export default SearchPlace;