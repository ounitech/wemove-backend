package com.ounitech.wemove.Validator;

import com.ounitech.wemove.models.Member;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MemberValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return Member.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "firstname", "error.firstname", "firstname is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "lastname", "error.lastname", "lastname is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "error.email", "email is required");

        Member member = (Member) obj;

        //FirstName validation
        if (member.getFirstname().length() < 3) {
            e.rejectValue("firstname", "must be greater than or equal to 3");
        } else if (member.getFirstname().length() > 50) {
            e.rejectValue("firstname", "must be less than or equal to 50");
        }

        for (char c : member.getFirstname().toCharArray()) {
            if (!Character.isLetter(c) && c != ' ')
                e.rejectValue("firstname", "must contain only alphabetic characters and spaces");
        }

        //LastName validation
        if (member.getLastname().length() < 3) {
            e.rejectValue("lastname", "must be greater than or equal to 3");
        } else if (member.getLastname().length() > 50) {
            e.rejectValue("lastname", "must be less than or equal to 50");
        }

        for (char c : member.getLastname().toCharArray()) {
            if (!Character.isLetter(c) && c != ' ')
                e.rejectValue("lastname", "must contain only alphabetic characters and spaces");
        }

        //Email validation
        if (member.getEmail().length() < 5) {
            e.rejectValue("email", "must be greater than or equal to 5");
        } else if (member.getLastname().length() > 150) {
            e.rejectValue("email", "must be less than or equal to 150");
        }

        if (!member.getEmail().contains("@") || !member.getEmail().contains(".") || member.getEmail().indexOf("@") > member.getEmail().indexOf("."))
            e.rejectValue("email", "not a well-formed email address");

        //Address validation
        if (!(member.getAddress() == null) && member.getAddress().length() > 150) {
            e.rejectValue("address", "must be less than or equal to 150");
        }

        //PhoneNumber validation
        if (!(member.getPhone() == null)) {
            if (member.getPhone().length() > 25) {
                e.rejectValue("phone", "must be less than or equal to 25");
            }

            for (char c : member.getPhone().toCharArray()) {
                if (!Character.isDigit(c) && c != ' ')
                    e.rejectValue("lastname", "must contain only numeric characters, spaces and dashes");
            }
        }

        //Picture validation
        if (!(member.getPicture() == null) && member.getPicture().length() > 255) {
            e.rejectValue("picture", "must be less than or equal to 255");
        }
    }
}

