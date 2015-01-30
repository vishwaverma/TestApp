package com.quytech.dao;


public class Employee 
{
	private int empId;
    private String empName;
    private String dob;
    public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

	private double salary;
    private int deptId;
     
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
   
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public int getDeptId() {
        return deptId;
    }
     
    //toString()
    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", empName=" + empName + ", dob="
                + dob + ", salary=" + salary + ", deptId=" + deptId + "]";
    } 
}
