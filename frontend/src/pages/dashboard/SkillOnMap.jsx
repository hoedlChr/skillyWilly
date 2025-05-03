import React, { use, useEffect, useState } from "react";
import Button from "../../components/Button";
import { Modal, ModalBody, ModalFooter, ModalHeader } from "reactstrap";
import InputField from "../../components/InputField";
import StandardInputField from "../../components/StandardInputField";
import Map from "../../components/Map";

function SkillOnMap({ data, users, show, setShow }) {
	const [location, setLocation] = useState(null);
	const [markers, setMarkers] = useState([]);
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
		Object.keys(users).forEach((user_id) => {
			const user = users[user_id];
			let marker = {
				position: [parseFloat(user.location.lat), parseFloat(user.location.lon)],
				content: `${user.username}`,
			};
			let skillsbyUser = data.filter((skill) => {return skill.userId === parseInt(user_id)});
			
			if (skillsbyUser.length > 0) {
				marker.content = `Skillys: ${skillsbyUser.map((skill) => skill.subject).join(", ")}`;
			}
			setMarkers((prevMarkers) => [...prevMarkers, marker]);
		});
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
				<Map markers={markers} center={location} style={{height: "60vh"}}/>
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
