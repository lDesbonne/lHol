package lh.test.addresses.data;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains address data
 * 
 * @author Lennon
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address implements DataObject{
	//private static Logger LOG = Logger.getLogger(Address.class);
	@JsonProperty
	private String summaryline;
	@JsonProperty
	private String organisation;
	@JsonProperty
	private String buildingname;
	@JsonProperty
	private String premise;
	@JsonProperty
	private String street;
	@JsonProperty
	private String dependentlocality;
	@JsonProperty
	private String posttown;
	@JsonProperty
	private String county;
	@JsonProperty
	private String postcode;

	public Address() {

	}

	
	public String getSummaryline() {
		return summaryline;
	}


	public void setSummaryline(String summaryline) {
		this.summaryline = summaryline;
	}


	public String getOrganisation() {
		return organisation;
	}


	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}


	public String getBuildingname() {
		return buildingname;
	}


	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}


	public String getPremise() {
		return premise;
	}


	public void setPremise(String premise) {
		this.premise = premise;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getDependentlocality() {
		return dependentlocality;
	}


	public void setDependentlocality(String dependentlocality) {
		this.dependentlocality = dependentlocality;
	}


	public String getPosttown() {
		return posttown;
	}


	public void setPosttown(String posttown) {
		this.posttown = posttown;
	}


	public String getCounty() {
		return county;
	}


	public void setCounty(String county) {
		this.county = county;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Override
	public String toString(){
		return this.summaryline;
	}
	
	@Override
	public Map<String,String> toMap(){
		Map<String,String> values = new HashMap<String,String>();
		values.put("Street", this.street);
		if (this.buildingname != null) values.put("Name", this.buildingname);
		return values;
	}


}
