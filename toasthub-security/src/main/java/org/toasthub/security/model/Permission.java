/*
 * Copyright (C) 2016 The ToastHub Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.toasthub.security.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.toasthub.core.general.api.View;
//import org.codehaus.jackson.annotate.JsonIgnore;
//import org.codehaus.jackson.map.annotate.JsonView;
import org.toasthub.security.model.Text;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "permission")
public class Permission extends ToastEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Boolean canRead;
	protected Boolean canWrite;
	protected String code;
	protected Application application;
	
	protected Long applicationId;
	
	// constructors
	public Permission(){
		this.setActive(true);
		this.setArchive(false);
		this.setLocked(false);
		this.setCreated(new Date());
	}
	
	public Permission(String code, Text title, Boolean canRead, Boolean canWrite) {
		this.setActive(true);
		this.setArchive(false);
		this.setLocked(false);
		this.setCreated(new Date());
		this.setCode(code);
		this.setTitle(title);
		this.setCanRead(canRead);
		this.setCanWrite(canWrite);
	}
	

	// Setters and getters
	@JsonView({View.Admin.class})
	@Column(name = "can_read")
	public Boolean getCanRead() {
		return canRead;
	}
	public void setCanRead(Boolean canRead) {
		this.canRead = canRead;
	}

	@JsonView({View.Admin.class})
	@Column(name = "can_write")
	public Boolean getCanWrite() {
		return canWrite;
	}
	public void setCanWrite(Boolean canWrite) {
		this.canWrite = canWrite;
	}

	@JsonView({View.Admin.class})
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonIgnore
	@ManyToOne(targetEntity = Application.class)
	@JoinColumn(name = "application_id")
	public Application getApplication() {
		return this.application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
	@JsonView({View.Admin.class})
	@Transient
	public Long getApplicationId() {
		if (this.application == null){
			return this.applicationId;
		} else {
			return this.application.id;
		}
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	
}
