package me.cal1br.webserverprogramming.api.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cal1br.webserverprogramming.api.base.model.BaseDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {
    @NotEmpty
    @Size(min = 5, max = 15)
    private String userName;
    /**
     * Password must contain <b>at least one</b>
     * <br/>- one lower case latin letter
     * <br/>- one upper case latin letter
     * <br/>- digit
     * <br/>- special symbol, meaning not a regular letter, e.g. [^A-Za-z\d]
     */
    @NotEmpty
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z\\d]).+")
    @Size(min = 6, max = 20)
    private String password;
    @NotEmpty
    @Email
    private String email;

    @Size(min = 12)
    @Pattern(regexp = "\\d*")
    private String phoneNumber;
}
