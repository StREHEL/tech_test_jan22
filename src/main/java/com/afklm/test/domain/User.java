package com.afklm.test.domain;

import com.afklm.test.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import org.projectlombok.lombok;


/**
 * A user.
 */
@Entity
@Table(name = "jhi_user")
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "reset_date")
    private Instant resetDate = null;
    
    @Column(name = "birth_date")
//    @Getter
//    @Setter
    private LocalDate birthDate = null;
    
    
    /**
     * Country of residence ISO code (2 ou 3 characters)
     */
    @NotNull(message = "ISO code for country residence is mandatory.")
    @Column(name = "resid_country_iso_code")
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "The value of 'countryISO_Code' must be made of 2 or 3 uppercase alphabetic letters.")
    private String countryISO_Code = null;

    /**
     * Allowable values are : 
     * F <-> Female
     * M <-> Male
     * U <-> No gender
     */
//    @Pattern(regexp = "(F|M|U)")
    @Column(name = "gender")
    //TODO : Create a @Gender annotation in order to validate gender code inputs.
//    @Getter
//    @Setter
    private Character gender = null;

    @Column(name = "phone_number")
    @Pattern(regexp = "^(\\+33[1-9]\\.)?(\\d{1,2}\\.){3,4}(\\d{2})$", message = "The value of 'phone number' must be made with digits.")
//    @Getter
//    @Setter
    private String phoneNumber = null;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_authority",
        joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "name") }
    )
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Set<PersistentToken> persistentTokens = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	public void setGender(String gender) {
		if ( StringUtils.isBlank(gender) ) {
			this.gender = 'U';
		} else if (gender.length()>1) {
			this.gender = 'U';
		} else {			
			this.gender = gender.charAt(0);
		}
	}
	
	public String getCountryISO_Code() {
		return countryISO_Code;
	}

	public void setCountryISO_Code(String countryISO_Code) {
		this.countryISO_Code = countryISO_Code;
	}

//	public String getLangKey() {
//        return langKey;
//    }
//
//    public void setLangKey(String langKey) {
//        this.langKey = langKey;
//    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<PersistentToken> getPersistentTokens() {
        return persistentTokens;
    }

    public void setPersistentTokens(Set<PersistentToken> persistentTokens) {
        this.persistentTokens = persistentTokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
//            ", email='" + email + '\'' +
//            ", imageUrl='" + imageUrl + '\'' +
//            ", activated='" + activated + '\'' +
//            ", langKey='" + langKey + '\'' +
//            ", activationKey='" + activationKey + '\'' +
            ", birthDate='" + 
            ( birthDate==null ? "null" : birthDate ) + 
            ", lastName='" + lastName + '\'' +
            ", countryISO_Code='" + countryISO_Code + '\'' +
//            ( birthDate==null ? "null" : birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE) )
            ", phoneNumber='" + 
            ( phoneNumber==null ? "null" : phoneNumber ) +
            ", gender='" + 
            ( gender==null ? "null" : gender ) +
            "}";
    }
}
