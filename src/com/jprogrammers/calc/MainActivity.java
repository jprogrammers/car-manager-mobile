package com.jprogrammers.calc;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.DecimalFormat;
import java.text.Format;


public class MainActivity extends Activity {

    double fobPrice;
    double numberOfCar = 1;
    double shipmentPrice;
    double stuffPriceInDollar;
    double dollarPrice;
    double stuffPriceInRial;

    double insurance;
    double insurancePercent = 0.5;

    double customs;

    double entrance;
    double entrancePercent;

    double helalAhmar;
    double helalAhmarPercent = 0.5;

    double tax1;
    double tax2;
    double tax;
    double taxPercent1;
    double taxPercent2;

    double importDuties;
    double importDutiesPercent = 5.0;

    double customsPay;
    double entranceDuties = 2500000;

    double note;
    double assetSide;
    double taxOther;
    double markingInsurance;
    double municipal;
    double plaque;
    double vehicleCarrier;
    double licence;
    double other;

    double totalPrice;

    private EditText foobCostText;
    private EditText countText;
    private EditText transportText;
    private EditText currencyText;
    private EditText incomePercentText;
    private EditText redMoonPercentText;
    private EditText tax1PercentText;
    private EditText tax2PercentText;
    private EditText inputTollText;
    private EditText noteText;
    private EditText taxText;
    private EditText municipalText;
    private EditText vehicleText;
    private EditText otherText;
    private EditText financeTollText;
    private EditText insuranceCountText;
    private EditText plateText;
    private EditText licenceText;

    private Button calculateButton;
    private TextView stuffPriceInDollarLabel;
    private TextView stuffPriceInRialLabel;
    private TextView insuranceLabel;
    private TextView customsLabel;
    private TextView entranceLabel;
    private TextView helalAhmarLabel;
    private TextView tax1Label;
    private TextView tax2Label;
    private TextView totalTaxLabel;
    private TextView importDutiesLabel;
    private TextView customsPayLabel;
    private TextView totalPriceLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setUpViews();
    }

    private void setUpViews() {

        foobCostText = (EditText) findViewById(R.id.foob_cost);
        countText = (EditText) findViewById(R.id.count);
        transportText = (EditText) findViewById(R.id.transport);
        currencyText = (EditText) findViewById(R.id.currency);
        incomePercentText = (EditText) findViewById(R.id.income_percent);
        redMoonPercentText = (EditText) findViewById(R.id.red_moon_percent);
        tax1PercentText = (EditText) findViewById(R.id.tax_1_percent);
        tax2PercentText = (EditText) findViewById(R.id.tax_2_percent);
        inputTollText = (EditText) findViewById(R.id.input_toll);
        noteText = (EditText) findViewById(R.id.note);
        taxText = (EditText) findViewById(R.id.tax);
        municipalText = (EditText) findViewById(R.id.municipal);
        vehicleText = (EditText) findViewById(R.id.vehicle);
        otherText = (EditText) findViewById(R.id.other);
        financeTollText = (EditText) findViewById(R.id.finance_toll);
        insuranceCountText = (EditText) findViewById(R.id.insurance_count);
        plateText = (EditText) findViewById(R.id.plate);
        licenceText = (EditText) findViewById(R.id.licence);





        calculateButton = (Button) findViewById(R.id.calculate);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fillVariables();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this , "لطفا تمامی فیلدها را پر کنید" , Toast.LENGTH_SHORT).show();
                    return;
                }


                stuffPriceInDollar = (fobPrice * numberOfCar) + shipmentPrice;
                stuffPriceInRial = stuffPriceInDollar * dollarPrice;

                insurance = stuffPriceInRial * (insurancePercent / 100.0);

                customs = stuffPriceInRial + insurance;

                entrance = customs * (entrancePercent / 100.0);

                helalAhmar = entrance * (helalAhmarPercent / 100.0);

                tax1 = (entrance + customs) * (taxPercent1 / 100.0);
                tax2 = (entrance + customs) * (taxPercent2 / 100.0);
                tax = tax1 + tax2;

                importDuties = fobPrice * dollarPrice * (importDutiesPercent / 100.0);

                customsPay = entrance + helalAhmar + tax + importDuties + entranceDuties;

                totalPrice = customsPay + note + assetSide + taxOther + markingInsurance +
                        municipal + plaque + vehicleCarrier + licence + other;

                final Dialog dialog = new Dialog(MainActivity.this);


                dialog.setContentView(R.layout.result_dialog);
                dialog.setTitle("نتیجه");

                Button closeButton = (Button) dialog.findViewById(R.id.close_btn);
                closeButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                stuffPriceInDollarLabel = (TextView) dialog.findViewById(R.id.stuff_price_in_dollar);
                stuffPriceInRialLabel = (TextView) dialog.findViewById(R.id.stuff_price_in_rial);
                insuranceLabel = (TextView) dialog.findViewById(R.id.insurance);
                customsLabel = (TextView) dialog.findViewById(R.id.customs);
                entranceLabel = (TextView) dialog.findViewById(R.id.entrance);
                helalAhmarLabel = (TextView) dialog.findViewById(R.id.helalAhmar);
                tax1Label = (TextView) dialog.findViewById(R.id.tax_1);
                tax2Label = (TextView) dialog.findViewById(R.id.tax_2);
                totalTaxLabel = (TextView) dialog.findViewById(R.id.total_tax);
                importDutiesLabel = (TextView) dialog.findViewById(R.id.import_duties);
                customsPayLabel = (TextView) dialog.findViewById(R.id.custom_pay);
                totalPriceLabel = (TextView) dialog.findViewById(R.id.total_price);

                stuffPriceInDollarLabel.setText(stuffPriceInDollarLabel.getText() + showInIRCurrency(stuffPriceInDollar));
                stuffPriceInRialLabel.setText(stuffPriceInRialLabel.getText() + showInIRCurrency(stuffPriceInRial));
                insuranceLabel.setText(insuranceLabel.getText() + showInIRCurrency(insurance));
                customsLabel.setText(customsLabel.getText() + showInIRCurrency(customs));
                entranceLabel.setText(entranceLabel.getText() + showInIRCurrency(entrance));
                helalAhmarLabel.setText(helalAhmarLabel.getText() + showInIRCurrency(helalAhmar));
                tax1Label.setText(tax1Label.getText() + showInIRCurrency(tax1));
                tax2Label.setText(tax2Label.getText() + showInIRCurrency(tax2));
                totalTaxLabel.setText(totalTaxLabel.getText() + showInIRCurrency(tax));
                importDutiesLabel.setText(importDutiesLabel.getText() + showInIRCurrency(importDuties));
                customsPayLabel.setText(customsPayLabel.getText() + showInIRCurrency(customsPay));
                totalPriceLabel.setText(totalPriceLabel.getText() + showInIRCurrency(totalPrice));

                dialog.show();
            }
        });
    }

    private void fillVariables() {
        fobPrice = getDoubleValue(foobCostText.getText().toString());
        numberOfCar = getDoubleValue(countText.getText().toString());
        shipmentPrice = getDoubleValue(transportText.getText().toString());
        dollarPrice = getDoubleValue(currencyText.getText().toString());
        entrancePercent = getDoubleValue(incomePercentText.getText().toString());
        fobPrice = getDoubleValue(redMoonPercentText.getText().toString());
        taxPercent1 = getDoubleValue(tax1PercentText.getText().toString());
        taxPercent2 = getDoubleValue(tax2PercentText.getText().toString());
        entranceDuties = getDoubleValue(inputTollText.getText().toString());
        note = getDoubleValue(noteText.getText().toString());
        taxOther = getDoubleValue(taxText.getText().toString());
        municipal = getDoubleValue(municipalText.getText().toString());
        vehicleCarrier = getDoubleValue(vehicleText.getText().toString());
        other = getDoubleValue(otherText.getText().toString());
        assetSide = getDoubleValue(financeTollText.getText().toString());
        markingInsurance = getDoubleValue(insuranceCountText.getText().toString());
        plaque = getDoubleValue(plateText.getText().toString());
        licence = getDoubleValue(licenceText.getText().toString());
    }

    public double getDoubleValue(String text){
        try{
            return new Double(text);
        } catch(Exception e){
            return 0d;
        }
    }

    public String showInIRCurrency(double number){
        Format fmt = new DecimalFormat("###,###,###,###,###,###");

        return fmt.format(number);
    }
}
