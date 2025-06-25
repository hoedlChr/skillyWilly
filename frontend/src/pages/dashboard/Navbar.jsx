import React, { useEffect, useState } from 'react';
import Button from '../../components/Button';
import { Dropdown, DropdownItem, DropdownMenu, DropdownToggle } from 'reactstrap';
import InputField from '../../components/InputField';

function Navbar({text, search, setSearch, setShowCreateSkill, setShowElementsOnMap}) {
	const [showUserContextMenu, setShowUserContextMenu] = useState(false);

    const logout = () => {
        localStorage.removeItem("user");
        window.location.href = "/login";
        // delete httpOnly cookie
        fetch("/api/users/logout", {
            method: "POST",
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
            },
        })
        .then((res) => {
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
            return res.json();
        })
        .then((data) => {
        })
        .catch((err) => {
            console.log(err);
        });
    }
    return (
        <div className='border-bottom my-2'>

        <div className='row mx-2'>
            <div className='row  container'>
                <div className="col">
                <div className="col-auto d-flex align-items-center">

                    <a style={{ marginRight: "16px"}} href="/Dashboard">
                        <img
                            src="/logo.png"
                            alt="Logo"
                            style={{ height: "40px", verticalAlign: "middle" }}
                        />
                    </a>
                    <h2><a style={{color: "black", textDecoration: "none"}} href="/Dashboard">{text}</a></h2>
                </div>
                </div>
                { search === undefined ? null:
                <div className="col-4">
                    <div className="d-flex justify-content-center">
                        <InputField 
                            type="text" 
                            className="form-control" 
                            placeholder="Search..." 
                            value={search} 
                            changeHandler={setSearch}
                        />
                    </div>
                    </div>
                }

                <div className="col-4 text-end">
                    <div className="d-flex justify-content-end gap-2">
                        {
                            setShowElementsOnMap === undefined ? null:
                            <Button className='btn-primary' onClick={() => {
                                setShowElementsOnMap(true);
                            }}>show Skilly on Map</Button>
                        }
                        {
                            setShowCreateSkill === undefined ? null:
                            <Button className='btn-primary' onClick={() => {
                                setShowCreateSkill(true);
                            }}>New Skilly</Button>
                        }
                        
                        <Dropdown isOpen={showUserContextMenu} toggle={() => {setShowUserContextMenu(!showUserContextMenu)}}>
                            <DropdownToggle tag="span" data-toggle="dropdown" aria-expanded={showUserContextMenu} style={{cursor: "pointer"}}>
                            <h2 className='mx-2 d-inline' style={{cursor: "pointer"}} onClick={() => {setShowUserContextMenu(!showUserContextMenu)}}>
                                <i className="bi bi-person-circle"></i>
                            </h2>
                            </DropdownToggle>
                            <DropdownMenu aria-labelledby="navbarDropdown">
                                <DropdownItem href="/Dashboard">Dashboard</DropdownItem>
                                <DropdownItem href="/UserSettings">User settings</DropdownItem>
                                <DropdownItem href="/MySkilly">See My Skilly</DropdownItem>
                                <DropdownItem onClick={logout}>Log out</DropdownItem>
                            </DropdownMenu>
                        </Dropdown>
                    </div>

                </div>
            </div>
            </div>
        </div>
    );
}

export default Navbar;