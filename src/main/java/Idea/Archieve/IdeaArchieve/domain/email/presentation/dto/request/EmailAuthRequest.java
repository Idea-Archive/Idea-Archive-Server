package Idea.Archieve.IdeaArchieve.domain.email.presentation.dto.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class EmailAuthRequest {

    @Email
    @NotNull
    private String email;

    @JsonCreator
    public EmailAuthRequest(@JsonProperty("email") String email) {
        this.email = email;
    }
}
