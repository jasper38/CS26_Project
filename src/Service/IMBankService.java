package Service;

import DTO.LogInRequestDTO;
import DTO.RegistrationRequestDTO;

import java.sql.SQLException;

public interface IMBankService {
    boolean registerPerson(RegistrationRequestDTO registrationRequestDTO) throws SQLException;
    boolean verifyLogIn(LogInRequestDTO logInRequest) throws SQLException, IllegalArgumentException;
    float getBankAccountBalance() throws SQLException;
}
