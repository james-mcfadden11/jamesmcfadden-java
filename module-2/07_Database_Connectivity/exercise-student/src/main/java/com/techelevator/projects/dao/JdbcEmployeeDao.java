package com.techelevator.projects.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.projects.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Employee;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcEmployeeDao implements EmployeeDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcEmployeeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		String sql = "select * from employee;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {
			employeeList.add(mapToEmployee(results));
		}
		return employeeList;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> employeeList = new ArrayList<>();
		SqlRowSet results;

		if (firstNameSearch.equals("") && lastNameSearch.equals("")) {
			return getAllEmployees();
		} else if (firstNameSearch.equals("")) {
			String sql = "select * from employee where last_name ILIKE '%' || ? || '%';";
			results = jdbcTemplate.queryForRowSet(sql, lastNameSearch);
		} else if (lastNameSearch.equals("")) {
			String sql = "select * from employee where first_name ILIKE '%' || ? || '%';";
			results = jdbcTemplate.queryForRowSet(sql, firstNameSearch);
		} else {
			String sql = "select * from employee where first_name ILIKE '%' || ? || '%' and last_name ILIKE '%' || ? || '%';";
			results = jdbcTemplate.queryForRowSet(sql, firstNameSearch, lastNameSearch);
		}

		while (results.next()) {
			employeeList.add(mapToEmployee(results));
		}
		return employeeList;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		List<Employee> employeeList = new ArrayList<>();
		String sql = "select * from employee join project_employee using (employee_id) where project_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);

		while (results.next()) {
			employeeList.add(mapToEmployee(results));
		}
		return employeeList;
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		String sql = "insert into project_employee (project_id, employee_id) values (?, ?);";
		jdbcTemplate.update(sql, projectId, employeeId);
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		String sql = "delete from project_employee where employee_id = ? and project_id = ?;";
		jdbcTemplate.update(sql, employeeId, projectId);
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> employeesWithoutProjects = new ArrayList<>();
		List<Employee> allEmployees = getAllEmployees();

		List<Integer> projectIDs = new ArrayList<>();
		String sql = "select * from project_employee where employee_id = ?;";

		for (Employee employee : allEmployees) {
			SqlRowSet employeeProjects = jdbcTemplate.queryForRowSet(sql, employee.getId());
			if (!employeeProjects.next()) {
				employeesWithoutProjects.add(employee);
			}
		}

		return employeesWithoutProjects;
	}

	private Employee mapToEmployee(SqlRowSet results) {
		Employee employee = new Employee();
		employee.setId(results.getLong("employee_id"));
		employee.setDepartmentId(results.getLong("department_id"));
		employee.setFirstName(results.getString("first_name"));
		employee.setLastName(results.getString("last_name"));

		Date date = results.getDate("birth_date");
		if (date != null) {
			employee.setBirthDate(date.toLocalDate());
		}

		Date date2 = results.getDate("hire_date");
		if (date2 != null) {
			employee.setHireDate(date2.toLocalDate());
		}

		return employee;
	}

}
