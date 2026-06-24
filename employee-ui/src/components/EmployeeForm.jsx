import { useState } from "react";

function EmployeeForm({ onSave, editingEmployee }) {

    const [employee, setEmployee] = useState(
        editingEmployee || {
            name: "",
            email: "",
            salary: ""
        }
    );

    const handleChange = (e) => {

        setEmployee({
            ...employee,
            [e.target.name]: e.target.value
        });
    };

    const submit = (e) => {

        e.preventDefault();

        onSave(employee);

        setEmployee({
            name: "",
            email: "",
            salary: ""
        });
    };

    return (
        <form onSubmit={submit}>

            <input
                id="name"
                name="name"
                placeholder="Name"
                value={employee.name}
                onChange={handleChange}
            />

            <input
                id="email"
                name="email"
                placeholder="Email"
                value={employee.email}
                onChange={handleChange}
            />

            <input
                id="salary"
                name="salary"
                placeholder="Salary"
                value={employee.salary}
                onChange={handleChange}
            />

            <button id="saveBtn">
                Save Employee
            </button>

        </form>
    );
}

export default EmployeeForm;