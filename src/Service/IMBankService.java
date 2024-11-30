package Service;

import DTO.LogInRequestDTO;
import DTO.LogInResult;
import DTO.RegistrationRequestDTO;

import java.sql.SQLException;

public interface IMBankService {
    boolean registerPerson(RegistrationRequestDTO registrationRequestDTO) throws SQLException;
    LogInResult verifyLogIn(LogInRequestDTO logInRequest) throws SQLException;
    float getBankAccountBalance() throws SQLException;
}
