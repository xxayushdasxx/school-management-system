package com.example.hellofx.data;

public class ProfileData {
    private static ProfileData instance = null;

    private static String enrollmentNumber;
    private String applicationNumber;
    private static String name;
    private String academicYear;
    private static String program;
    private String doj;
    private String dob;
    private String gender;
    private String mobileNumber;
    private String email;
    private String bloodGroup;
    private String category;
    private String nationality;
    private String religion;
    private String socialCategory;
    private String motherTongue;
    private String maritalStatus;
    private String domicileState;

    private ProfileData() {}

    public static ProfileData getInstance() {
        if (instance == null) {
            instance = new ProfileData();
        }
        return instance;
    }

    // Getters and setters for each field
    public static String getEnrollmentNumber() { return enrollmentNumber; }
    public void setEnrollmentNumber(String enrollmentNumber) { this.enrollmentNumber = enrollmentNumber; }

    public String getApplicationNumber() { return applicationNumber; }
    public void setApplicationNumber(String applicationNumber) { this.applicationNumber = applicationNumber; }

    public static String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public static String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public String getDoj() { return doj; }
    public void setDoj(String doj) { this.doj = doj; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getReligion() { return religion; }
    public void setReligion(String religion) { this.religion = religion; }

    public String getSocialCategory() { return socialCategory; }
    public void setSocialCategory(String socialCategory) { this.socialCategory = socialCategory; }

    public String getMotherTongue() { return motherTongue; }
    public void setMotherTongue(String motherTongue) { this.motherTongue = motherTongue; }

    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }

    public String getDomicileState() { return domicileState; }
    public void setDomicileState(String domicileState) { this.domicileState = domicileState; }
}
