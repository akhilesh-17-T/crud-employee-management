function EmployeeList({ employees, onDelete, onEdit }) {

    return (
        <table border="1">

            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Salary</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>

                {employees.map(emp => (

                    <tr key={emp.id}>

                        <td>{emp.name}</td>
                        <td>{emp.email}</td>
                        <td>{emp.salary}</td>

                        <td>

                            <button
                                className="editBtn"
                                onClick={() => onEdit(emp)}
                            >
                                Edit
                            </button>

                            <button
                                className="deleteBtn"
                                onClick={() => onDelete(emp.id)}
                            >
                                Delete
                            </button>

                        </td>

                    </tr>

                ))}

            </tbody>

        </table>
    );
}

export default EmployeeList;