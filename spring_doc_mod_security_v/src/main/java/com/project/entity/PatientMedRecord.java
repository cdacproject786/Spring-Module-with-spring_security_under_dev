package com.project.entity;
// Generated 03-Mar-2023, 8:40:48 pm by Hibernate Tools 4.3.6.Final

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * PatientMedRecord generated by hbm2java
 */
@Entity
@Table(name = "patient_med_record", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ABHA_NUMBER"), @UniqueConstraint(columnNames = "INSURANCE_ID") })
public class PatientMedRecord implements java.io.Serializable {

	private Integer medRecordId;
	private String bloodPressure;
	private Integer weight;
	private String sugarLevel;
	private String bloodGroup;
	private char disability;
	//private Date lastUpdatedDate;
	private String insuranceId;
	private String insuranceName;
	private Date insuranceExpireDate;
	private String abhaNumber;
	//private String extCol1;
	//private String extCol2;
	//private String extCol3;
	//private Set patientPrimaries = new HashSet(0);

	public PatientMedRecord() {
	}

	public PatientMedRecord(String bloodPressure, Integer weight, String sugarLevel, String bloodGroup,
			char disability) {
		this.bloodPressure = bloodPressure;
		this.weight = weight;
		this.sugarLevel = sugarLevel;
		this.bloodGroup = bloodGroup;
		this.disability = disability;
	}

	public PatientMedRecord(String bloodPressure, Integer weight, String sugarLevel, String bloodGroup,
			char disability, Date lastUpdatedDate, String insuranceId, String insuranceName, Date insuranceExpireDate,
			String abhaNumber, String extCol1, String extCol2, String extCol3, Set patientPrimaries) {
		this.bloodPressure = bloodPressure;
		this.weight = weight;
		this.sugarLevel = sugarLevel;
		this.bloodGroup = bloodGroup;
		this.disability = disability;
		//this.lastUpdatedDate = lastUpdatedDate;
		this.insuranceId = insuranceId;
		this.insuranceName = insuranceName;
		this.insuranceExpireDate = insuranceExpireDate;
		this.abhaNumber = abhaNumber;
		//this.extCol1 = extCol1;
		//this.extCol2 = extCol2;
		//this.extCol3 = extCol3;
		//this.patientPrimaries = patientPrimaries;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "MED_RECORD_ID", unique = true, nullable = false)
	public Integer getMedRecordId() {
		return this.medRecordId;
	}

	public void setMedRecordId(Integer medRecordId) {
		this.medRecordId = medRecordId;
	}

	@Column(name = "BLOOD_PRESSURE", nullable = false, length = 7)
	public String getBloodPressure() {
		return this.bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	@Column(name = "WEIGHT", nullable = false, precision = 7)
	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Column(name = "SUGAR_LEVEL", nullable = false, length = 3)
	public String getSugarLevel() {
		return this.sugarLevel;
	}

	public void setSugarLevel(String sugarLevel) {
		this.sugarLevel = sugarLevel;
	}

	@Column(name = "BLOOD_GROUP", nullable = false, length = 3)
	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Column(name = "DISABILITY", nullable = false, length = 1)
	public char getDisability() {
		return this.disability;
	}

	public void setDisability(char disability) {
		this.disability = disability;
	}

	/*
	 * @Temporal(TemporalType.TIMESTAMP)
	 * 
	 * @Column(name = "LAST_UPDATED_DATE", length = 19) public Date
	 * getLastUpdatedDate() { return this.lastUpdatedDate; }
	 * 
	 * public void setLastUpdatedDate(Date lastUpdatedDate) { this.lastUpdatedDate =
	 * lastUpdatedDate; }
	 */

	@Column(name = "INSURANCE_ID", unique = true, length = 20)
	public String getInsuranceId() {
		return this.insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	@Column(name = "INSURANCE_NAME", length = 50)
	public String getInsuranceName() {
		return this.insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INSURANCE_EXPIRE_DATE", length = 10)
	public Date getInsuranceExpireDate() {
		return this.insuranceExpireDate;
	}

	public void setInsuranceExpireDate(Date insuranceExpireDate) {
		this.insuranceExpireDate = insuranceExpireDate;
	}

	@Column(name = "ABHA_NUMBER", unique = true, length = 17)
	public String getAbhaNumber() {
		return this.abhaNumber;
	}

	public void setAbhaNumber(String abhaNumber) {
		this.abhaNumber = abhaNumber;
	}

	/*
	 * @Column(name = "EXT_COL_1", length = 100) public String getExtCol1() { return
	 * this.extCol1; }
	 * 
	 * public void setExtCol1(String extCol1) { this.extCol1 = extCol1; }
	 * 
	 * @Column(name = "EXT_COL_2", length = 100) public String getExtCol2() { return
	 * this.extCol2; }
	 * 
	 * public void setExtCol2(String extCol2) { this.extCol2 = extCol2; }
	 * 
	 * @Column(name = "EXT_COL_3", length = 100) public String getExtCol3() { return
	 * this.extCol3; }
	 * 
	 * public void setExtCol3(String extCol3) { this.extCol3 = extCol3; }
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "patientMedRecord") public Set
	 * getPatientPrimaries() { return this.patientPrimaries; }
	 * 
	 * public void setPatientPrimaries(Set patientPrimaries) { this.patientPrimaries
	 * = patientPrimaries; }
	 */
}