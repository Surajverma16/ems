import React, { useState, useEffect } from 'react'
import axios from 'axios'

const UpdateEmployee = ({ employeeId, onClose, onUpdate }) => {

    const [employees, setEmployees] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchEmployee = async () => {
            setLoading(true);
            try {
                const response = await axios.get(`/api/employee/${employeeId}`);
                setEmployees(response.data)
            } catch {
                setError("Error while fetching employees details!!")
            } finally {
                setLoading(false)
            }
        };
        if (employeeId) {
            fetchEmployee();
        }
    }, [employeeId]);

    const handelChange = (e) => {
        const {name, value} = e.target;
        setEmployees((prev) => ({...prev, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.put(`/api/employee/${employeeId}`,employees);
            onUpdate();
            onClose();
        } catch {
            console.error("Error Updating Employee");
            alert("Failed to update Employeee");
        }
    };

    if (loading) return <div className='text-center mt-5'>Loading...</div>
    if (error) return <div className='alert alert-danger text-center'>{error}</div>

    return(
        <div className='modal fade show' style={{ display: 'block', background: 'rgba(0,0,0,0,5)' }} tabIndex="-1" role='dialog'>
            <div className='modal-dialog' role='document'>
                <div className='modal-content' >
                    <div className='modal-header'>
                        <h5 className='modal-title'>Update Employee</h5>
                        <button type='button' className='close' onClick={onClose} aria-label='Close'>
                            <span aria-hidden="true">*</span>
                        </button>
                    </div>
                    <form onSubmit={handleSubmit}>
                        <div className='modal-body'>
                            <div className='form-group'>
                                <label>First Name</label>
                                <input type='text' className='form-control' name='firstName' value={employees.firstName} onChange={handelChange} required />
                            </div>
                            <div className='form-group'>
                                <label>Last Name</label>
                                <input type='text' className='form-control' name='lastName' value={employees.lastName} onChange={handelChange} required />
                            </div>
                            <div className='form-group'>
                                <label>Email</label>
                                <input type='email' className='form-control' name='email' value={employees.email} onChange={handelChange} required />
                            </div>
                        </div>
                        <div className='modal-footer'>
                            <button type='button' className='btn btn-secondary' onClick={onClose}>Close</button>
                            <button type='submit' className='btn btn-primary'>Update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default UpdateEmployee