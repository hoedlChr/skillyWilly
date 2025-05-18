import React, { useEffect, useState } from "react";
import Button from "../../components/Button";
import { Modal, ModalBody, ModalFooter, ModalHeader } from "reactstrap";
import InputField from "../../components/InputField";
import StandardInputField from "../../components/StandardInputField";

function CreateSkill({ show, setShow, user}) {
	const [title, setTitle] = useState("");
	const [description, setDescription] = useState("");
	const [location, setLocation] = useState("");
	const cancel = () => {
		setShow(false);
	};

	const create = () => {
		let data = {
			subject: title,
			body: description,
			created: new Date().toISOString(),
			userId: user.id,
		};

		fetch(`/api/skills`, {
			method: "POST",
			credentials: "include",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		})
			.then((res) => res.json())
			.then((data) => {
				cancel();
			})
			.catch((err) => {
				console.log(err);
			});
	}

	return (
		<Modal isOpen={show} className="modal-xl">
		<ModalHeader toggle={cancel}>
			<h1>
			CreateSkilly
			</h1>
			</ModalHeader>
		<ModalBody>
			<StandardInputField
				id={"title"}
				label={"Title"}
				className={""}
				readOnly={false}
				value={title}
				changeHandler={setTitle}
			/>
			{/* <StandardInputField
				id={"location"}
				label={"Location"}
				className={""}
                type="place"
				readOnly={false}
				value={location}
				changeHandler={setLocation}
			/> */}
			<StandardInputField
				id={"description"}
				label={"Description"}
				className={""}
				readOnly={false}
				value={description}
				type="html"
				changeHandler={setDescription}
			/>
			
		</ModalBody>
		<ModalFooter>
			<Button className="btn-success" onClick={create}>
				create
			</Button>
			<Button className="btn-danger" onClick={cancel}>
			cancel
			</Button>
		</ModalFooter>
		</Modal>
	);
	}

export default CreateSkill;
