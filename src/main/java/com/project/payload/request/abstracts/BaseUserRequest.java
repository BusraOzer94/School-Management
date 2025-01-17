package com.project.payload.request.abstracts;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@SuperBuilder // Iki classta @SuperBuilder kullanirsak hem buldugu classtaki hem extends edildigi classtaki  param larida gorur.
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseUserRequest extends AbstractUserRequest {


    @NotNull(message = "Please enter your password")
    @Size(min = 8, max = 60,message = "Your password should be at least 8 chars or maximum 60 characters")
    private String password;

    private Boolean builtIn = false;


}
