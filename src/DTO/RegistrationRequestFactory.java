package DTO;

import Model.BankAccount;
import Model.CardInfo;
import Model.Customer;
import Model.Person;

public class RegistrationRequestFactory {
    public Person savePerson(RegistrationRequestDTO registrationRequestDTO) {
        Person person = new Person();
        person.setFirstName(registrationRequestDTO.getFirstName());
        person.setLastName(registrationRequestDTO.getLastName());
        person.setAge(registrationRequestDTO.getAge());
        person.setBirthdate(registrationRequestDTO.getBirthdate());
        person.setSex(registrationRequestDTO.getSex());
        person.setPhoneNum(registrationRequestDTO.getPhoneNo());
        person.setAddress(registrationRequestDTO.getAddress());
        return person;
    }

    public Customer createCustomer(RegistrationRequestDTO registrationRequestDTO) {
        Customer customer = new Customer();
        customer.setUsername(registrationRequestDTO.getUsername());
        customer.setPassword(registrationRequestDTO.getPassword());
        customer.setEmail(registrationRequestDTO.getEmail());
        return customer;
    }

    public BankAccount createBankAccount(RegistrationRequestDTO registrationRequestDTO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankAccountType(registrationRequestDTO.getBankAccountType());

        return bankAccount;
    }

    public CardInfo createCard() {
        return new CardInfo();
    }
}
