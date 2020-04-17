package com.octo.bankoperations;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class CONSTANTS {

    public static final String CORDA_URL = "http://localhost:10050";
    public static final Currency MAD = Currency.getInstance("MAD");
    public static final String CENTRAL_BANK_NAME = "O=CentralBank,L=New York,C=US";
    public static final List<String> SERVICE_NAMES = Arrays.asList("Notary", "Network Map Service");

    public static final String BANK_CODE = "007";
}
