/*
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of
 * their official duties. Pursuant to title 17 Section 105 of the United
 * States Code this software is not subject to copyright protection and is
 * in the public domain. This software is an experimental system. NIST assumes
 * no responsibility whatsoever for its use by other parties, and makes no
 * guarantees, expressed or implied, about its quality, reliability, or
 * any other characteristic. We would appreciate acknowledgement if the
 * software is used.
 */

package gov.nist.itl.ssd.wipp.backend.data.pyramid;

import gov.nist.itl.ssd.wipp.backend.core.rest.annotation.IdExposed;
import gov.nist.itl.ssd.wipp.backend.core.rest.annotation.ManualRef;
import gov.nist.itl.ssd.wipp.backend.core.model.data.Data;
import gov.nist.itl.ssd.wipp.backend.core.model.job.Job;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


/**
*
* @author Antoine Vandecreme <antoine.vandecreme at nist.gov>
*/
@Document
@IdExposed
public class Pyramid {
	
    @Id
    private String id;

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;

    @Indexed(unique = true, sparse = true)
    @ManualRef(Job.class)
    private String job;

    public Pyramid() {
    }

    public Pyramid(Job job) {
        this.name = job.getName();
        this.job = job.getId();
        this.creationDate = new Date();
    }
    
    public Pyramid(Job job, String outputName) {
        this.name = job.getName() + "-" + outputName;
    	this.job = job.getId();
        this.creationDate = new Date();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getJob() {
        return job;
    }

}
