import axios from "axios";

const BASE_URL = "http://localhost:8080/employees";

export const getEmployees = () => {
    return axios.get(BASE_URL);
};

export const createEmployee = (employee) => {
    return axios.post(BASE_URL, employee);
};

export const updateEmployee = (id, employee) => {
    return axios.put(`${BASE_URL}/${id}`, employee);
};

export const deleteEmployee = (id) => {
    return axios.delete(`${BASE_URL}/${id}`);
};