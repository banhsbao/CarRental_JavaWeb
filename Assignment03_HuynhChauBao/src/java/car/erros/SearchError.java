/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.erros;

/**
 *
 * @author Admin
 */
public class SearchError {

    private String searchAmount;
    private String searchName;
    private String rentalDateIsEmpty;
    private String returnDateISEmpty;
    private String amountNumberType;
    private String amountNumberLargerThanZero;
    private String rentalDateSmallerthanCurrentDate;
    private String rentalDateLargethanReturnDate;
    private String returnDateSmallerthanCurrentDate;
    private String returnDateSmallerthanRentalDate;

    public String getSearchAmount() {
        return searchAmount;
    }

    public void setSearchAmount(String searchAmount) {
        this.searchAmount = searchAmount;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getRentalDateIsEmpty() {
        return rentalDateIsEmpty;
    }

    public void setRentalDateIsEmpty(String rentalDateIsEmpty) {
        this.rentalDateIsEmpty = rentalDateIsEmpty;
    }

    public String getReturnDateISEmpty() {
        return returnDateISEmpty;
    }

    public void setReturnDateISEmpty(String returnDateISEmpty) {
        this.returnDateISEmpty = returnDateISEmpty;
    }

    public String getAmountNumberType() {
        return amountNumberType;
    }

    public void setAmountNumberType(String amountNumberType) {
        this.amountNumberType = amountNumberType;
    }

    public String getAmountNumberLargerThanZero() {
        return amountNumberLargerThanZero;
    }

    public void setAmountNumberLargerThanZero(String amountNumberLargerThanZero) {
        this.amountNumberLargerThanZero = amountNumberLargerThanZero;
    }

    public String getRentalDateSmallerthanCurrentDate() {
        return rentalDateSmallerthanCurrentDate;
    }

    public void setRentalDateSmallerthanCurrentDate(String rentalDateSmallerthanCurrentDate) {
        this.rentalDateSmallerthanCurrentDate = rentalDateSmallerthanCurrentDate;
    }

    public String getRentalDateLargethanReturnDate() {
        return rentalDateLargethanReturnDate;
    }

    public void setRentalDateLargethanReturnDate(String rentalDateLargethanReturnDate) {
        this.rentalDateLargethanReturnDate = rentalDateLargethanReturnDate;
    }

    public String getReturnDateSmallerthanCurrentDate() {
        return returnDateSmallerthanCurrentDate;
    }

    public void setReturnDateSmallerthanCurrentDate(String returnDateSmallerthanCurrentDate) {
        this.returnDateSmallerthanCurrentDate = returnDateSmallerthanCurrentDate;
    }

    public String getReturnDateSmallerthanRentalDate() {
        return returnDateSmallerthanRentalDate;
    }

    public void setReturnDateSmallerthanRentalDate(String returnDateSmallerthanRentalDate) {
        this.returnDateSmallerthanRentalDate = returnDateSmallerthanRentalDate;
    }

}
