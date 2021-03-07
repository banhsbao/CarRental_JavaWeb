/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dtos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class CartObjectDetail implements Serializable {

    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

    public ArrayList<CartDTO> addCart(CartDTO cart, String renDate, String retDate, ArrayList<CartDTO> listCart) throws ParseException {
        if (listCart.size() == 0) {
            listCart.add(cart);
        } else {
            for (int i = 0; i < listCart.size(); i++) {
                if (cart.getId().equals(listCart.get(i).getId())) {
                    Date rentalDate = new java.util.Date(listCart.get(i).getRentalDate().getTime());
                    Date returnDate = new java.util.Date(listCart.get(i).getReturnDate().getTime());
                    if (rentalDate.compareTo((java.util.Date) sd.parse(renDate)) == 0 && returnDate.compareTo((java.util.Date) sd.parse(retDate)) == 0) {
                        listCart.get(i).setAmount(listCart.get(i).getAmount() + 1);
                    }
                }
            }
            boolean notInclude = true;
            for (CartDTO cartDTO : listCart) {
                if (cartDTO.getId().equals(cart.getId())) {
                    notInclude = false;
                }
            }
            if (notInclude) {
                listCart.add(cart);
            }
        }
        return listCart;
    }

    public ArrayList<CartDTO> deleteItemtoCart(CartDTO cart, ArrayList<CartDTO> listCart) {
        if (listCart != null) {
            for (CartDTO cartDTO : listCart) {
                if (cartDTO.getId().equals(cart.getId())) {
                    listCart.remove(cart);
                }
            }
        }
        return listCart;
    }

    public ArrayList<CartDTO> updateItemtoCart(CartDTO cart, ArrayList<CartDTO> listCart, int quantity) {
        for (CartDTO cartDTO : listCart) {
            if (cartDTO.getId().equals(cart.getId())) {
                cartDTO.setAmount(quantity);
            }
        }
        return listCart;
    }
    public String getID() {
        String RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) {
            int index = (int) (rnd.nextFloat() * RANDOM.length());
            salt.append(RANDOM.charAt(index));
        }
        String randomString = salt.toString();
        return "[" + "MA" + "]" + randomString;
    }
    public String generateID() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy-HHmmss");
        Date date = new Date();
        return getID() + formatter.format(date);
    }
}
