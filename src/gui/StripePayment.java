/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class StripePayment {
    
    
    public static void initStripe() {
        Stripe.apiKey = "sk_test_51QPrdWL4zHw9zO9WM929q6JxJLYIcPS2LOXyXXluD9ujw6FOniGd2Oa2zYKolPfOUtTy848Uh6VpNA3AKEt767Z000PDJ2eq0s";
    }
    
    
    public static String createPaymentIntent(double amount)throws StripeException{
    
        Map<String, Object> parms = new HashMap<>();
        parms.put("amount",(int) (amount*100));
        parms.put("currency", "LKR");
        parms.put("payment_method_types", new String[]{"card"});
        
        PaymentIntent intent = PaymentIntent.create(parms);
        return intent.getClientSecret();
        
    
    
    
    }
    
        
        
    }
    
    
    
    
    
    
    
    
    

