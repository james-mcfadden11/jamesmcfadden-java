package com.techelevator.projects.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.projects.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProject(Long projectId) {
		String sql = "select * from project where project_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		if (results.next()) {
			return this.mapToProject(results);
		}
		return null;
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> projectsList = new ArrayList<>();
		String sql = "select * from project;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {
			projectsList.add(mapToProject(results));
		}
		return projectsList;
	}

	@Override
	public Project createProject(Project newProject) {
		String sql = "INSERT INTO project (name, from_date, to_date) "
				+ "VALUES (?, ?, ?) RETURNING project_id;";
		Long project_id = jdbcTemplate.queryForObject(sql, Long.class,
				newProject.getName(), newProject.getFromDate(), newProject.getToDate());

		if(project_id == null) return null;

		return getProject(project_id);
	}

	@Override
	public void deleteProject(Long projectId) {
		String sqlDeleteTimesheet = "DELETE FROM timesheet WHERE project_id = ?;";
		jdbcTemplate.update(sqlDeleteTimesheet, projectId);

		String sqlDeleteProjectEmployee = "DELETE FROM project_employee WHERE project_id = ?;";
		jdbcTemplate.update(sqlDeleteProjectEmployee, projectId);

		String sqlDeleteProject = "DELETE FROM project where project_id = ?;";
		jdbcTemplate.update(sqlDeleteProject, projectId);

	}

	private Project mapToProject(SqlRowSet results) {
		Project project = new Project();
		project.setId(results.getLong("project_id"));
		project.setName(results.getString("name"));

		Date date = results.getDate("from_date");
		if (date != null) {
			project.setFromDate(date.toLocalDate());
		}

		Date date2 = results.getDate("to_date");
		if (date2 != null) {
			project.setToDate(date2.toLocalDate());
		}

		return project;
	}

}
