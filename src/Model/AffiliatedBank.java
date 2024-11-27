package Model;

public class AffiliatedBank {
    private int bankID;
    private String bankName;
    private float charge;

    public AffiliatedBank(){}

    public AffiliatedBank(int bankID, String bankName, float charge) {
        this.bankID = bankID;
        this.bankName = bankName;
        this.charge = charge;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }
}
