package com.kulturman.mdd.dtos.requests;

import com.kulturman.mdd.validation.UniqueField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequest {
    @NotEmpty
    @UniqueField(tableName = "users", columnName = "email")
    private String email;

    @NotEmpty
    @UniqueField(tableName = "users", columnName = "username")
    private String username;

    @NotEmpty
    @Size(min = 8)
    private String password;
}
