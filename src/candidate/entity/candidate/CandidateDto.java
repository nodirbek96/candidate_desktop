package candidate.entity.candidate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CandidateDto {
    private Integer id;
    private Integer userId;
    private String firstname;
    private String lastname;
    private String middleName;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String jobPlace;
    private String occupation;
    private String educationPlace;
    private String education;
    private String relative;
    private String position;
    private String passport;
    private LocalDate endDate;
    private String result;
    private LocalDateTime createdDate;

    public CandidateDto() {
    }

    public CandidateDto(Integer id, Integer userId, String firstname, String lastname, String middleName, LocalDate birthDate, String address, String phone, String jobPlace, String occupation, String educationPlace, String education, String relative, String position, String passport, LocalDate endDate, String result, LocalDateTime createdDate) {
        this.id = id;
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.jobPlace = jobPlace;
        this.occupation = occupation;
        this.educationPlace = educationPlace;
        this.education = education;
        this.relative = relative;
        this.position = position;
        this.passport = passport;
        this.endDate = endDate;
        this.result = result;
        this.createdDate = createdDate;
    }

    public CandidateDto(Integer userId, String firstname, String lastname, String middleName, LocalDate birthDate, String address, String phone, String jobPlace, String occupation, String educationPlace, String education, String relative, String position, String passport, LocalDate endDate, String result) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.jobPlace = jobPlace;
        this.occupation = occupation;
        this.educationPlace = educationPlace;
        this.education = education;
        this.relative = relative;
        this.position = position;
        this.passport = passport;
        this.endDate = endDate;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobPlace() {
        return jobPlace;
    }

    public void setJobPlace(String jobPlace) {
        this.jobPlace = jobPlace;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEducationPlace() {
        return educationPlace;
    }

    public void setEducationPlace(String educationPlace) {
        this.educationPlace = educationPlace;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", userId=" + userId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", jobPlace='" + jobPlace + '\'' +
                ", occupation='" + occupation + '\'' +
                ", educationPlace='" + educationPlace + '\'' +
                ", education='" + education + '\'' +
                ", relative='" + relative + '\'' +
                ", position='" + position + '\'' +
                ", passport='" + passport + '\'' +
                ", endDate=" + endDate +
                ", result='" + result + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
