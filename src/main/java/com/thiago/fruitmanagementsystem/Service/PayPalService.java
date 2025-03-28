package com.thiago.fruitmanagementsystem.Service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.thiago.fruitmanagementsystem.Model.Fruta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PayPalService {


    private final APIContext apiContext;


    public PayPalService(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    public Payment cretePayment(Double total,
                                String currency ,
                                String method,
                                String intent,
                                String description,
                                String cancelUrl,
                                String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f", total));
        amount.setCurrency(currency);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

}