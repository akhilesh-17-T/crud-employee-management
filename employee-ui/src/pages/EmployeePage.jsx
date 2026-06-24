import { useEffect, useState } from "react";

import EmployeeForm from "../components/EmployeeForm";
import EmployeeList from "../components/EmployeeList";

import {
    getEmployees,
    createEmployee,
    updateEmployee,
    deleteEmployee
}
from "../api/EmployeeService";

function EmployeePage() {

    const [employees, setEmployees] = useState([]);

    const [editing, setEditing] = useState(null);

    function loadEmployees() {

        getEmployees()
                .then(res => setEmployees(res.data));
    }

    useEffect(() => {

        loadEmployees();

    }, []);

    const saveEmployee = (employee) => {

        if(editing){

            updateEmployee(editing.id, employee)
                    .then(() => {

                        loadEmployees();
                        setEditing(null);
                    });

        } else {

            createEmployee(employee)
        .then(() => loadEmployees());
        }
    };

    const removeEmployee = (id) => {

        deleteEmployee(id)
        .then(() => loadEmployees());
    };

    return (
        <div>

            <h1>Employee Management</h1>

            <EmployeeForm
                onSave={saveEmployee}
                editingEmployee={editing}
            />

            <hr />

            <EmployeeList
                employees={employees}
                onDelete={removeEmployee}
                onEdit={setEditing}
            />

        </div>
    );
}

export default EmployeePage;