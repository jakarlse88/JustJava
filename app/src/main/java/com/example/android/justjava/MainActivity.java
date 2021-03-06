/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 *
 */
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

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
    private String createOrderSummary(int price, boolean hasCream, boolean hasChocolate,
                                      String name) {
        String summary = name;
        summary += "\nWhipped cream? " + hasCream;
        summary += "\nHot chocolate? " + hasChocolate;
        summary += "\nTotal: $" + price;

        return summary;
    }

    /**
     * Calculates the total cost
     * @param quantity      number of cups
     * @param price         of one cup
     * @param hasCream      whether user wants cream
     * @param hasChocolate  whether user wants chocolate
     * @return              total price
     */
    private int calculatePrice(int quantity, int price,
                               boolean hasCream, boolean hasChocolate) {
        if (hasCream) {
            return (price + 1) * quantity;
        } else if (hasChocolate) {
            return (price + 2) * quantity;
        } else {
            return price * quantity;
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = 3;

        EditText enterName = (EditText) findViewById(R.id.name);
        String name = enterName.getText().toString();
        String subject = "Order for: " + name;

        CheckBox checkCream = (CheckBox) findViewById(R.id.check_whipped_cream);
        boolean hasCream = checkCream.isChecked();

        CheckBox checkChocolate = (CheckBox) findViewById(R.id.check_hot_chocolate);
        boolean hasChocolate = checkChocolate.isChecked();

        int totalPrice = calculatePrice(quantity, price, hasCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(totalPrice, hasCream,
                        hasChocolate, name));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //displayMessage(createOrderSummary(totalPrice, hasCream, hasChocolate, name));//
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
     * Increments quantity of cups,
     * if the current amount is <= 99.
     */
    public void increment(View view) {
        if (quantity <= 99) {
            quantity++;
            displayQuantity(quantity);
        } else {
            displayQuantity(quantity);
        }
    }

    /**
     * Decrements quantity of cups,
     * if the current amount is >= 1.
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