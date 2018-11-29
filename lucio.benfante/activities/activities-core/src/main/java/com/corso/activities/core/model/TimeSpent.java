package com.corso.activities.core.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TimeSpent {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "startDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	@Column(name = "endDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	@Lob
	private String description;
	@ManyToOne
	private Activity activity;
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<Employee> employees;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	
	public void setStart(int year, int month, int day, int hour, int minute) {
		setStart(java.sql.Timestamp.valueOf(
				LocalDateTime.of(year, month, day, hour, minute)));		
	}

	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	public void setEnd(int year, int month, int day, int hour, int minute) {
		setEnd(java.sql.Timestamp.valueOf(
				LocalDateTime.of(year, month, day, hour, minute)));		
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		if (this.employees == null) {
			this.employees = new LinkedList<>();
		}

		this.employees.add(employee);

		if (employee.getTimeSpents() == null) {
			employee.setTimeSpents(new LinkedList<>());
		}
		
		employee.getTimeSpents().add(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TimeSpent))
			return false;
		TimeSpent other = (TimeSpent) obj;
		if (id == null && other.id == null && this != other) {
			return false;
		}
		return Objects.equals(id, other.id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TimeSpent [" + (id != null ? "id=" + id + ", " : "") + (start != null ? "start=" + start + ", " : "")
				+ (end != null ? "end=" + end + ", " : "") + (description != null ? "description=" + description : "")
				+ "]";
	}
	
	/**
	 * Calculate the time spent in milliseconds.
	 * 
	 * @return The spent milliseconds.
	 * @throws IllegalStateException if end is still null;
	 */
	public long getTimeSpentInMilliseconds() {
		if (this.end == null) {
			throw new IllegalStateException("This time spent is still not completed");
		}
		return this.getEnd().getTime()-this.getStart().getTime();
	}

}
