package com.afklm.test.service.dto;

import com.afklm.test.domain.User;

/**
 * A DTO representing a user, with only the public attributes.
 */
public class UserDTO {

    private Long id;

    private String login;
    
    private java.sql.Date birthDate;
    
    private java.lang.Character gender;
    
    private String residenceCountryCode;
    
    private String phoneNumber;

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User user) {
        this.id = user.getId();
        // Customize it here if you need, or not, firstName/lastName/etc
        this.login = user.getLogin();
        if ( user.getBirthDate() == null ) {
        	this.birthDate = null;
        } else {        	
        	this.birthDate = java.sql.Date.valueOf(user.getBirthDate());
        }
        
        this.residenceCountryCode = user.getCountryISO_Code();
        this.phoneNumber = user.getPhoneNumber();
        this.gender = user.getGender();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public java.sql.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.sql.Date birthDate) {
		this.birthDate = birthDate;
	}

	
	public java.lang.Character getGender() {
		return gender;
	}

	public void setGender(java.lang.Character gender) {
		this.gender = gender;
	}

	public String getResidenceCountryCode() {
		return residenceCountryCode;
	}

	public void setResidenceCountryCode(String countryISO_Code) {
		this.residenceCountryCode = countryISO_Code;
	}

	public String getPhoneNumner() {
		return phoneNumber;
	}

	public void setPhoneNumner(String phoneNumner) {
		this.phoneNumber = phoneNumner;
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "UserDTO{" +
            "id='" + id + '\'' +
            ", login='" + login + '\'' +
            ", birthDate='" + birthDate + '\'' +
            ", coutry_ISO='" + residenceCountryCode + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", gender='" + gender + '\'' +
            "}";
    }
}
