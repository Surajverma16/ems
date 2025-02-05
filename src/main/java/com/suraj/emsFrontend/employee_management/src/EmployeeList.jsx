import React, { useEffect, useState } from 'react'
import axios from 'axios'
import UpdateEmployee from './UpdateEmployee';

export const EmployeeList = () => {

  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showModel, setShowModel] = useState(false);
  const [selectedEmployeeId, setSelectedEmployeeId] = useState(false);


  const fetchEmployees = async () => {
    setLoading(true);
    try {
      const response = await axios.get("/api/employee");
      setEmployees(response.data)
    } catch {
      setError("Error while fetching employees details!!")
    } finally {
      setLoading(false)
    }

  }
  useEffect(() => {
    fetchEmployees()

  }, []);

  //Update Employee
  const updateEmployee = (employeeId) =>{
    setSelectedEmployeeId(employeeId);
    setShowModel(true);
  }
  
  const handleModelClose = () =>{
    setShowModel(false);
    setSelectedEmployeeId(null);
  }

  //Delete Employee

  const deleteEmployee = async (employeeId) =>{
    const confirm = window.confirm("Are you sure? You want to delete this employee");
    if(confirm){
      try{
          await axios.delete(`/api/employee/${employeeId}`);
          alert("Employee Deleted Successfully");
          fetchEmployees();
      }catch(error){
        console.error("Error while deleting employee:", error);
        alert("Failed to delete employee");
      }
    }
  }

  if (loading) return <div className='text-center mt-5'>Loading...</div>
  if (error) return <div className='alert alert-danger text-center'>{error}</div>

  return (
    <div className='container mt-5'>
      <h1 className='text-center mb-4'>Employee List</h1>
      <button className='btn btn-primary mb-4' onClick={fetchEmployees}>Refresh Employee List</button>
      {employees.length === 0 ? <p> No Employees Found</p> : (
      <ul className='list-group'>{employees.map((employee) => (
        <div key={employee.id} className='card mt-4'>

          <div className='card-body'>
            <h5 className='card-title'>{employee.firstName} {employee.lastName}</h5>
            <p className='mb-2 text-muted'>{employee.email}</p>
            <div className='button-container' style={{display: 'flex', gap:'10px'}}>
              <button className='btn btn-primary' onClick={() => updateEmployee(employee.id)}>Update</button>
              <button className='btn btn-danger' onClick={() => deleteEmployee(employee.id)}>Delete</button>
            </div>
          </div>
        </div>
      ))}</ul>
      )}
      {/* Render the update employee model */}
      {showModel && (
        <UpdateEmployee employeeId = {selectedEmployeeId}
        onClose = {handleModelClose}
        onUpdate = {fetchEmployees}/>
      )}
    </div>
  )
}
