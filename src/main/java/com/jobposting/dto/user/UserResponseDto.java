package com.jobposting.dto.user;

import com.jobposting.entity.Role;
import com.jobposting.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private int id;
    private String firstname;
    private String lastname;
    private String mobileNo;
    private String email;
    private boolean whatsAppConcent;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.mobileNo = user.getMobileNo();
        this.whatsAppConcent = user.isWhatsAppConcent();
        this.role = user.getRole();
    }
}
