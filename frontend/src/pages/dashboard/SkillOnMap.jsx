import React, { use, useEffect, useState } from "react";
import Button from "../../components/Button";
import { Modal, ModalBody, ModalFooter, ModalHeader } from "reactstrap";
import InputField from "../../components/InputField";
import StandardInputField from "../../components/StandardInputField";
import Map from "../../components/Map";

function SkillOnMap({ show, setShow }) {
	const [location, setLocation] = useState(null);
	const cancel = () => {
		setShow(false);
	};

	useEffect(() => {
		if (show) {
			navigator.geolocation.getCurrentPosition(
				(position) => {
					setLocation([position.coords.latitude, position.coords.longitude]);
				},
				(error) => {
					if (error.code === 1) {
						console.error("Permission denied. Using fallback location.");
					} else {
						console.error("Error getting location:", error);
					}
					setLocation([47.076, 15.421]); //fallback
				}
			);
		}
	}, [show]);

	return (
		<Modal isOpen={show} toggle={cancel} className="modal-xl">
		<ModalHeader toggle={cancel}>
			<h1>
			ShowSkilly
			</h1>
			</ModalHeader>
		<ModalBody>
			{
				location === null ? null :
				<Map markers={[{position: [47.06364187205705, 15.448151871947529], content: "Hello"}]} center={location} style={{height: "60vh"}}/>
			}
		</ModalBody>
		<ModalFooter>
			<Button className="btn-danger" onClick={cancel}>
				cancel
			</Button>
		</ModalFooter>
		</Modal>
	);
	}

export default SkillOnMap;
