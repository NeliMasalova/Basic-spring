package pl.pingwit.basic_spring.controller.user;

public class UpdateUserInputDto {
    private String surname;
    private String email;
    private String phone;

    public UpdateUserInputDto(String surname, String email, String phone) {
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
