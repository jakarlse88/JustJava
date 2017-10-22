/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 *
 */
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int price = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Creates a summmary of a customer's order.
     * @param price         total price of the order
     * @param hasCream      whether the customer wants whipped cream
     * @param hasChocolate  whether the customer wants hot chocolate
     * @return              the complete summary
     */
    private String createOrderSummary(int price, boolean hasCream, boolean hasChocolate) {
        String summary = "Name: Jon Karlsen";
        summary += "\nWhipped cream? " + hasCream;
        summary += "\nHot chocolate? " + hasChocolate;
        summary += "\nTotal: $" + (price / 3);
        summary += "\nThank you!";

        return summary;
    }

    /**
     * Calculates the total cost
     * @param quantity is the amount of cups
     * @param price is the price of one cup
     * @return
     */
    private int calculatePrice(int quantity, int price) {
        return quantity * price;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox checkCream = (CheckBox) findViewById(R.id.check_whipped_cream);
        boolean hasCream = checkCream.isChecked();

        CheckBox checkChocolate = (CheckBox) findViewById(R.id.check_hot_chocolate);
        boolean hasChocolate = checkChocolate.isChecked();

        String priceMessage = "Total: $" + (quantity * price) + "\nThank you!";
        displayMessage(priceMessage);
        displayMessage(createOrderSummary(calculatePrice(quantity, price), hasCream, hasChocolate));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Increments quantity of cups.
     */
    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * Decrements quantity of cups.
     */
    public void decrement(View view) {
        if (quantity >= 1) {
            quantity--;
            displayQuantity(quantity);
        } else {
            displayQuantity(quantity);
        }
    }
}