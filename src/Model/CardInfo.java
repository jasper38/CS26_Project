package Model;

public class CardInfo {
    private Long cardNumber;
    private int cardPIN;
    private int bankAccountNumberID;

    public CardInfo(){}

    public CardInfo(Long cardNumber, int cardPIN, int bankAccountNumberID) {
        this.cardNumber = cardNumber;
        this.cardPIN = cardPIN;
        this.bankAccountNumberID = bankAccountNumberID;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardPIN() {
        return cardPIN;
    }

    public void setCardPIN(int cardPIN) {
        this.cardPIN = cardPIN;
    }

    public int getBankAccountNumberID() {
        return bankAccountNumberID;
    }

    public void setBankAccountNumberID(int bankAccountNumberID) {
        this.bankAccountNumberID = bankAccountNumberID;
    }
}
