import React, { useEffect, useState } from 'react';
import Button from '../../components/Button';
import { Dropdown, DropdownItem, DropdownMenu, DropdownToggle } from 'reactstrap';

function Navbar({text, setShowCreateSkill, setShowElementsOnMap}) {
	const [showUserContextMenu, setShowUserContextMenu] = useState(false);


    return (
        <div className='row my-2'>
                <div className="col">
                    <h2><a style={{color: "black", textDecoration: "none"}} href="/Dashboard">{text}</a></h2>
                </div>
                <div className="col-4 text-end">
                    <div className="d-flex align-items-center gap-2">
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
                                <DropdownItem onClick={()=>{console.log("logout")}}>Log out</DropdownItem>
                            </DropdownMenu>
                        </Dropdown>
                    </div>

                </div>
            </div>
    );
}

export default Navbar;