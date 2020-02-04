package com.example.justjava;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=0;
    int price =0;

    /**

     This method is called when the order button is clicked.
     */
    private int calculatePrice(int quantit,boolean b) {

        if(b==true)
            return quantit*10;
        else
            return quantit*5;
    }

    public void submitOrder(View view) {
       int n=0;
        EditText inpt=(EditText) findViewById(R.id.input);
        String s=inpt.getText().toString();
        CheckBox whippedcrea=(CheckBox) findViewById(R.id.whippedcream);
        boolean b=whippedcrea.isChecked();
        n= calculatePrice(quantity,b);



            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_TEXT, "name:"+s+"\nwhipped cream="+b+"\nPrice="+n+"\nThankyou!" );
            intent.putExtra(Intent.EXTRA_SUBJECT, "coffee order for = " +s);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }


        String new1="name:"+s+"\nwhipped cream="+b+"\nPrice="+n+"\nThankyou!";
        displayMessage(new1);
    }
        public void increment(View view){
            if(quantity==100)
            {
                Toast.makeText(this,"coffee cannot be more than 100",2).show();
                return;
            }
            quantity=quantity+1;
            display(quantity);
    }
    public void decrement(View view) {
        if(quantity==1)
        {
            Toast.makeText(this,"coffee cannot be less than 1",2).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
