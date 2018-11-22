package com.corso.activities.core.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Lob
	private String description;
	@ManyToOne
	private Customer customer;
	@OneToMany(mappedBy="activity", cascade=CascadeType.ALL)
	private Set<TimeSpent> timeSpents;

	/**
	 * 
	 */
	public Activity() {
	}

	/**
	 * @param description
	 */
	public Activity(String description) {
		this.description = description;
	}

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
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the timeSpents
	 */
	public Set<TimeSpent> getTimeSpents() {
		return timeSpents;
	}

	/**
	 * @param timeSpents the timeSpents to set
	 */
	public void setTimeSpents(Set<TimeSpent> timeSpents) {
		this.timeSpents = timeSpents;
	}

	public void addTimeSpent(TimeSpent timeSpent) {
		if (this.timeSpents == null) {
			this.timeSpents = new HashSet<>();
		}
		this.timeSpents.add(timeSpent);
		timeSpent.setActivity(this);
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
		if (!(obj instanceof Activity))
			return false;
		Activity other = (Activity) obj;
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
		return "Activity [" + (id != null ? "id=" + id + ", " : "")
				+ (description != null ? "description=" + description : "") + "]";
	}

}
