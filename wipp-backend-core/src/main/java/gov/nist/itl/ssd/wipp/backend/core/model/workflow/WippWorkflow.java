package gov.nist.itl.ssd.wipp.backend.core.model.workflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import gov.nist.itl.ssd.wipp.backend.core.model.job.WippJob;
import gov.nist.itl.ssd.wipp.backend.core.rest.annotation.IdExposed;

@IdExposed
@Document(collection = "wippWorkflow")
public abstract class WippWorkflow {

	@Id
    private String id;

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date endTime;
    
//    @Updatable
    protected WippWorkflowStatus status;
	
    protected List<String> jobs = new ArrayList<>();
    
    protected Map<String, Set<String>> dependencies = new HashMap<String, Set<String>>();
	
    protected List<WippNotification> workflowNotifications = new ArrayList<>();
	
	public abstract void addJob(WippJob job, List<String> arguments,
			List<WippNotification> notifications);
	
	public abstract void addDependency(String child, String parent);
	
	public abstract void addWorkflowNotification(WippNotification notification);

    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @return the status
	 */
	public WippWorkflowStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(WippWorkflowStatus status) {
		this.status = status;
	}

	/**
	 * @return the jobs
	 */
	public List<String> getJobs() {
		return jobs;
	}

	/**
	 * @param jobs the jobs to set
	 */
	public void setJobs(List<String> jobs) {
		this.jobs = jobs;
	}

	/**
	 * @return the dependencies
	 */
	public Map<String, Set<String>> getDependencies() {
		return dependencies;
	}

	/**
	 * @param dependencies the dependencies to set
	 */
	public void setDependencies(Map<String, Set<String>> dependencies) {
		this.dependencies = dependencies;
	}

	/**
	 * @return the workflow notifications
	 */
	public List<WippNotification> getWorkflowNotifications() {
		return workflowNotifications;
	}

	/**
	 * @param workflowNotifications the workflow notifications to set
	 */
	public void setWorkflowNotifications(List<WippNotification> workflowNotifications) {
		this.workflowNotifications = workflowNotifications;
	}

	
	

}