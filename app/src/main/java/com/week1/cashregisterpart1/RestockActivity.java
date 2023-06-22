package com.week1.cashregisterpart1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RestockActivity extends AppCompatActivity {
    private ListView listView;
    private Button okButton, backButton;
    private TextView txtProductName,txtProductQuantity, txtTotalPrice;
    private EditText editTextProductNumber;
    private ProductAdapter adapter;
    public static List<Product> productList;
    int productQuantity;
    double productPrice;
    private AlertDialog.Builder builder;
    String userInput;
    double totalPrice;
    String productName;
    Product selectedProduct;
    int productNumeber;

    ArrayList<PurchasedProduct> purchasedProducts;
    private ProductList product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restock);


        listView = findViewById(R.id.product_listview);
        txtProductName = findViewById(R.id.textviewProductName);
        txtProductQuantity = findViewById(R.id.textviewQuantity);
        txtTotalPrice = findViewById(R.id.textviewTotalAmount);

        okButton = findViewById(R.id.buttonOk);
        backButton = findViewById(R.id.back_btn);

        builder = new AlertDialog.Builder(this);
        purchasedProducts = new ArrayList<>();

        // Create a list of products

        productList = new ArrayList<>();
        product = ProductList.getInstance();
        productList = product.getProductList();

        // Create and set the custom adapter
        adapter = new ProductAdapter(this, productList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected product from the adapter
                selectedProduct = adapter.getItem(position);

                // Display the product details
                productName = selectedProduct.getName();
                productQuantity = selectedProduct.getQuantity();
                productPrice = selectedProduct.getPrice();
                txtProductName.setText(productName);


            }
        });

        editTextProductNumber = findViewById(R.id.number_picker);
        editTextProductNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Handle the action here
                    userInput = editTextProductNumber.getText().toString();
                    // Do something with the entered text

                    if(Integer.parseInt(userInput)<0)
                        Toast.makeText(getApplicationContext(), "You have entered less stock", Toast.LENGTH_SHORT).show();
                    else
                    {
                        txtProductQuantity.setText(userInput);
                        productNumeber = Integer.parseInt(userInput);
                        totalPrice = productNumeber*productPrice;
                        txtTotalPrice.setText(String.valueOf(totalPrice));
                    }

                    return true;
                }
                return false;
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (editTextProductNumber.getText().toString().equals("") || selectedProduct == null)
                    Toast.makeText(RestockActivity.this, "Please Select Product and Enter Product Number", Toast.LENGTH_SHORT).show();



                else {
                    userInput = editTextProductNumber.getText().toString();
                    //Uncomment the below code to Set the message and title from the strings.xml file
                    builder.setMessage("Are you confirm?").setTitle("Confirm Purchase");

                    //Setting message manually and performing action on button click
                    builder.setMessage("Do you want to update this product?\nAdd  " + userInput + " item(s) to " + productName)
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
//                                    purchasedProducts.add(new PurchasedProduct(productName, productNumeber, totalPrice));
                                    product.updateProductQuantity(productName,(Integer.parseInt(userInput)+productQuantity));
                                    productList = product.getProductList();


                                    Toast.makeText(RestockActivity.this, "Thanks for updating the stock!", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    editTextProductNumber.setText("");
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Confirm Purchase");
                    alert.show();
                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestockActivity.this, ManagerActivity.class);

                // Start the ManageActivity
                startActivity(intent);
            }
        });
    }



}

