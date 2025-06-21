import React, { useEffect, useState } from 'react';
import StandardInputField from '../../components/StandardInputField';
import Button from '../../components/Button';
import LoadingBar from '../../components/LoadingBar';

function ElementEditor({id, data, setShowElement, user}) {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [created, setCreated] = useState("");
    const [loading, setLoading] = useState(false);
    useEffect(()=>{
        let element = data.find((element) => element.id === id);
        if (!element) {
            return;
        }
        setTitle(element.subject);
        let date = new Date(element.created);
        setCreated(date.toLocaleDateString() + " " + date.toLocaleTimeString());
        setDescription(element.body);
    },[id])

    const saveChanges = () => {
		let data = {
			subject: title,
			body: description,
			created: new Date().toISOString(),
			userId: user.id,
		};
        setLoading(true);
		fetch(`/api/skills/${id}`, {
			method: "PUT",
            credentials: "include",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		})
        .then((res) => res.json())
        .then((data) => {
        })
        .catch((err) => {
            console.log(err);
        })
        .finally(() => {
            setLoading(false);
        });
	}

    const cancel = () => {
        setShowElement(false);
    }


    return (
        <div className='mt-4'>
            <StandardInputField
				id={"title"}
				label={"Title"}
				className={""}
				readOnly={false}
				value={title}
				changeHandler={setTitle}
			/>
            <div className='text-end'>
                <small >{created}</small>
            </div>
            <hr />
            <StandardInputField
                id={"description"}
                label={"Description"}
                className={""}
                readOnly={false}
                value={description}
                type="html"
                changeHandler={setDescription}
            />
            {
                loading ?
                <LoadingBar />
                : <>
                    <Button className="btn-success btn-block mr-2" onClick={saveChanges}>
                        save changes
                    </Button>
                    <Button className="btn-danger btn-block" onClick={cancel}>
                        cancel
                    </Button>
                </>
            }
        </div>
    );
}

export default ElementEditor;