package com.week1.cashregisterpart1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private Button backButton;

    private ListView listView;
    private PurchasedProductAdapter adapter;
    public static List<Product> productList;
    int productQuantity;
    double productPrice;
    private AlertDialog.Builder builder;
    String userInput;
    double totalPrice;
    String productName;
    Product selectedProduct;

    ArrayList<PurchasedProduct> purchasedProducts;
    private PurchaseList purchaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        backButton = findViewById(R.id.backButton2);

        listView = findViewById(R.id.list_view);

        purchaseList = PurchaseList.getInstance();

        purchasedProducts = purchaseList.getPurchases();

        adapter = new PurchasedProductAdapter(this, purchasedProducts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the clicked PurchasedProduct
                PurchasedProduct clickedProduct = purchasedProducts.get(position);

                // Show the details in a modal or popup
                showProductDetailsPopup(clickedProduct);
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the ManageActivity
                Intent intent = new Intent(HistoryActivity.this, ManagerActivity.class);

                // Start the ManageActivity
                startActivity(intent);
            }
        });

    }

    private void showProductDetailsPopup(PurchasedProduct product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Product Details");

        // Create a string with the product details
        String details = "Product Name: " + product.getName() + "\n"
                + "Quantity: " + product.getQuantity() + "\n"
                + "Price: " + product.getPrice() + "\n"
                + "Timestamp: " + product.getTimestamp();

        // Set the details string as the message of the dialog
        builder.setMessage(details);

        // Add a button to dismiss the dialog
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}