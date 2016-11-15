package com.media.cluster.cluster.Login;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.media.cluster.cluster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterNumberFragment extends Fragment {
    private  static EditText CountryInput;
    private static TextView NumberError;
    private static EditText NumberInput;
    private static CheckBox VisibleCheck;
    private Button SkipButton;

     private View layout;


    public RegisterNumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_register_number, container, false);

        //Grab references
        CountryInput = (EditText) layout.findViewById(R.id.register_number_country);
        NumberInput = (EditText) layout.findViewById(R.id.register_number_input);
        VisibleCheck = (CheckBox) layout.findViewById(R.id.register_number_check);
        SkipButton = (Button) layout.findViewById(R.id.register_number_skip);
        NumberError = (TextView)layout.findViewById(R.id.register_number_error);

        CountryInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RegisterSelectCountryActivity.class));
            }
        });

        VisibleCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Dialog dialog ;
                if (VisibleCheck.isChecked()) {
                    AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getContext());
                    confirmBuilder.setTitle(getResources().getString(R.string.numberDialogTitle));
                    confirmBuilder.setMessage(getResources().getString(R.string.numberVisibleHeader));

                    confirmBuilder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            VisibleCheck.setChecked(true);
                        }
                    });

                    confirmBuilder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            VisibleCheck.setChecked(false);
                        }
                    });

                    dialog = confirmBuilder.create();
                    dialog.show();

                }
            }
        });


        SkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.skipPage();
            }
        });
        return layout;
    }

    public static void setCountryInput(String countryInput){
        switch (countryInput){
            case"AD (Andorra)":CountryInput.setText("+375"); break;
            case"AE (United Arab Emirates)":CountryInput.setText("+971"); break;
            case"AF (Afghanistan)":CountryInput.setText("+93"); break;
            case"AG (Antigua and Barbuda)":CountryInput.setText("+1-268"); break;
            case"AI (Anguilla)":CountryInput.setText("+1-264"); break;
            case"AL (Albania)":CountryInput.setText("+355"); break;
            case"AM (Armenia)":CountryInput.setText("+374"); break;
            case"An (Netherlands Antilles)":CountryInput.setText("+599"); break;
            case"AO (Angola)":CountryInput.setText("+244"); break;
            case"AQ (Antarctica)":CountryInput.setText("+672"); break;
            case"AR (Argentina)":CountryInput.setText("+54"); break;
            case"AS (American Samoa)":CountryInput.setText("+1-684"); break;
            case"AT (Austria)":CountryInput.setText("+43"); break;
            case"AW (Aruba)":CountryInput.setText("+61"); break;
            case"AZ (Azerbaijan)":CountryInput.setText("+297"); break;
            case"BA (Bosnia and Herzegovina)":CountryInput.setText("+387"); break;
            case"BB (Barbados)":CountryInput.setText("+1-246"); break;
            case"BD (Bangladesh)":CountryInput.setText("+880"); break;
            case"BE (Belgium)":CountryInput.setText("+32"); break;
            case"BF (Burkina Faso)":CountryInput.setText("+226"); break;
            case"BG (Bulgaria)":CountryInput.setText("+359"); break;
            case"BH (Bahrain)":CountryInput.setText("+973"); break;
            case"BI (Burundi)":CountryInput.setText("+257"); break;
            case"BJ (Benin)":CountryInput.setText("+229"); break;
            case"BL (Saint Barthelemy)":CountryInput.setText("+590"); break;
            case"BM (Bermuda)":CountryInput.setText("+1-441"); break;
            case"BN (Brunei)":CountryInput.setText("+673"); break;
            case"BO (Bolivia)":CountryInput.setText("+591"); break;
            case"BR (Brazil)":CountryInput.setText("+55"); break;
            case"BS (Bahamas)":CountryInput.setText("+1-242"); break;
            case"BT (Bhutan)":CountryInput.setText("+975"); break;
            case"BW (Botswana)":CountryInput.setText("+267"); break;
            case"BY (Belarus)":CountryInput.setText("+375"); break;
            case"BZ (Belize)":CountryInput.setText("+501"); break;
            case"CA (Canada)":CountryInput.setText("+1"); break;
            case"CC (Cocos Islands)":CountryInput.setText("+61"); break;
            case"CD (Democratic Republic of the Congo)":CountryInput.setText("+243"); break;
            case"CF (Central African Republic)":CountryInput.setText("+236"); break;
            case"CG (Republic of the Congo)":CountryInput.setText("+242"); break;
            case"CH (Switzerland)":CountryInput.setText("+41"); break;
            case"CI (Ivory Coast)":CountryInput.setText("+225"); break;
            case"CK (Cook Island)":CountryInput.setText("+682"); break;
            case"CL (Chile)":CountryInput.setText("+56"); break;
            case"CM (Cameroon)":CountryInput.setText("+237"); break;
            case"CN (China)":CountryInput.setText("+86"); break;
            case"CO (Colombia)":CountryInput.setText("+57"); break;
            case"CR (Costa Rica)":CountryInput.setText("+506"); break;
            case"Cu (Cuba)":CountryInput.setText("+53"); break;
            case"CV (Cape Verde)":CountryInput.setText("+238"); break;
            case"CW (Curacao)":CountryInput.setText("+599"); break;
            case"CX (Christmas Island)":CountryInput.setText("+61"); break;
            case"CY (Cyprus)":CountryInput.setText("+357"); break;
            case"CZ (Czech Republic)":CountryInput.setText("+420"); break;
            case"DE (Germany)":CountryInput.setText("+49"); break;
            case"DJ (Djibouti)":CountryInput.setText("+253"); break;
            case"DK (Denmark)":CountryInput.setText("+45"); break;
            case"DM (Dominica)":CountryInput.setText("+1-767"); break;
            case"DO (Dominican Republic)":CountryInput.setText("+"); break;
            case"DZ (Algeria)":CountryInput.setText("+213"); break;
            case"EC (Ecuador)":CountryInput.setText("+593"); break;
            case"EE (Estonia)":CountryInput.setText("+372"); break;
            case"EG (Egypt)":CountryInput.setText("+20"); break;
            case"EH (Western Sahara)":CountryInput.setText("+212"); break;
            case"ER (Eritrea)":CountryInput.setText("+291"); break;
            case"ES (Spain)":CountryInput.setText("+34"); break;
            case"ET (Ethiopia)":CountryInput.setText("+251"); break;
            case"FI (Finland)":CountryInput.setText("+358"); break;
            case"FJ (Fiji)":CountryInput.setText("+679"); break;
            case"FK (Falkland Islands)":CountryInput.setText("+500"); break;
            case"FM (Micronesia)":CountryInput.setText("+291"); break;
            case"FO (Faroe Islands)":CountryInput.setText("+298"); break;
            case"FR (France)":CountryInput.setText("+33"); break;
            case"GA (Gabon)":CountryInput.setText("+241"); break;
            case"GB (United Kingdom)":CountryInput.setText("+44"); break;
            case"GD (Grenada)":CountryInput.setText("+1-473"); break;
            case"GE (Georgia)":CountryInput.setText("+995"); break;
            case"GG (Guernsey)":CountryInput.setText("+44-1481"); break;
            case"GH (Ghana)":CountryInput.setText("+233"); break;
            case"GI (Gibraltar)":CountryInput.setText("+350"); break;
            case"GL (Greenland)":CountryInput.setText("+299"); break;
            case"GM (Gambia)":CountryInput.setText("+220"); break;
            case"GN (Guinea)":CountryInput.setText("+224"); break;
            case"GQ (Equatorial Guinea)":CountryInput.setText("+240"); break;
            case"GR (Greece)":CountryInput.setText("+30"); break;
            case"GT (Guatemala)":CountryInput.setText("+502"); break;
            case"GU (Guam)":CountryInput.setText("+1-671"); break;
            case"GW (Guinea-Bissau)":CountryInput.setText("+245"); break;
            case"GY (Guyana)":CountryInput.setText("+592"); break;
            case"HK (Hong Kong)":CountryInput.setText("+852"); break;
            case"HN (Honduras)":CountryInput.setText("+504"); break;
            case"HR (Croatia)":CountryInput.setText("+380"); break;
            case"HT (Haiti)":CountryInput.setText("+509"); break;
            case"HU (Hungary)":CountryInput.setText("+36"); break;
            case"ID (Indonesia)":CountryInput.setText("+62"); break;
            case"IE (Ireland)":CountryInput.setText("+353"); break;
            case"IL (Israel)":CountryInput.setText("+972"); break;
            case"IM (Isle of Man)":CountryInput.setText("+"); break;
            case"IN (India)":CountryInput.setText("+91"); break;
            case"IO (British Indian Ocean Territory)":CountryInput.setText("+246"); break;
            case"IQ (Iraq)":CountryInput.setText("+964"); break;
            case"IR (Iran)":CountryInput.setText("+98"); break;
            case"IS (Iceland)":CountryInput.setText("+354"); break;
            case"IT (Italy)":CountryInput.setText("+39"); break;
            case"JE (Jersey)":CountryInput.setText("+44-1534"); break;
            case"JM (Jamaica)":CountryInput.setText("+1-876"); break;
            case"JO (Jordan)":CountryInput.setText("+962"); break;
            case"JP (Japan)":CountryInput.setText("+81"); break;
            case"KE (Kenya)":CountryInput.setText("+254"); break;
            case"KG (Kyrgyzstan)":CountryInput.setText("+996"); break;
            case"KH (Cambodia)":CountryInput.setText("+855"); break;
            case"KI (Kiribati)":CountryInput.setText("+686"); break;
            case"KM (Comoros)":CountryInput.setText("+269"); break;
            case"KN (Saint Kitts and Nevis)":CountryInput.setText("+1-869"); break;
            case"KP (North Korea)":CountryInput.setText("+580"); break;
            case"KR (South Korea)":CountryInput.setText("+82"); break;
            case"KW (Kuwait)":CountryInput.setText("+965"); break;
            case"KY (Cayman Islands)":CountryInput.setText("+1-345"); break;
            case"KZ (Kazakhstan)":CountryInput.setText("+7"); break;
            case"LA (Laos)":CountryInput.setText("+856"); break;
            case"LB (Lebanon)":CountryInput.setText("+961"); break;
            case"LC (Saint Lucia)":CountryInput.setText("+1-758"); break;
            case"LI (Lichtenstein)":CountryInput.setText("+423"); break;
            case"LK (Sri Lanka)":CountryInput.setText("+94"); break;
            case"LR (Liberia)":CountryInput.setText("+231"); break;
            case"LS (Lesotho)":CountryInput.setText("+266"); break;
            case"LT (Lithuania)":CountryInput.setText("+370"); break;
            case"LU (Luxembourg)":CountryInput.setText("+352"); break;
            case"LV (Latvia)":CountryInput.setText("+371"); break;
            case"LY (Libya)":CountryInput.setText("+218"); break;
            case"MA (Morocco)":CountryInput.setText("+212"); break;
            case"MC (Monaco)":CountryInput.setText("+377"); break;
            case"MD (Moldova)":CountryInput.setText("+373"); break;
            case"ME (Montenegro)":CountryInput.setText("+382"); break;
            case"MF (Saint Martin)":CountryInput.setText("+95"); break;
            case"MG (Madagascar)":CountryInput.setText("+261"); break;
            case"MH (Marshall Islands)":CountryInput.setText("+692"); break;
            case"MK (Macedonia)":CountryInput.setText("+389"); break;
            case"ML (Mali)":CountryInput.setText("+223"); break;
            case"MM (Myanmar)":CountryInput.setText("+95"); break;
            case"MN (Mongolia)":CountryInput.setText("+976"); break;
            case"MO (Macau)":CountryInput.setText("+853"); break;
            case"MP (Northern Mariana Islands)":CountryInput.setText("+1-670"); break;
            case"MR (Mauritania)":CountryInput.setText("+222"); break;
            case"MS (Montserrat)":CountryInput.setText("+1-664"); break;
            case"MT (Malta)":CountryInput.setText("+356"); break;
            case"MU (Mauritius)":CountryInput.setText("+230"); break;
            case"MV (Maldives)":CountryInput.setText("+960"); break;
            case"MW (Malawi)":CountryInput.setText("+265"); break;
            case"MX (Mexico)":CountryInput.setText("+52"); break;
            case"MY (Malaysia)":CountryInput.setText("+60"); break;
            case"MZ (Mozambique)":CountryInput.setText("+258"); break;
            case"NA (Namibia)":CountryInput.setText("+264"); break;
            case"NC (New Caledonia)":CountryInput.setText("+687"); break;
            case"NE (Niger)":CountryInput.setText("+227"); break;
            case"NG (Nigeria)":CountryInput.setText("+234"); break;
            case"NI (Nicaragua)":CountryInput.setText("+505"); break;
            case"NL (Netherlands)":CountryInput.setText("+31"); break;
            case"NO (Norway)":CountryInput.setText("+47"); break;
            case"NP (Nepal)":CountryInput.setText("+977"); break;
            case"NR (Nauru)":CountryInput.setText("+674"); break;
            case"NU (Niue)":CountryInput.setText("+683"); break;
            case"NZ (New Zealand)":CountryInput.setText("+64"); break;
            case"OM (Oman)":CountryInput.setText("+968"); break;
            case"PA (Panama)":CountryInput.setText("+507"); break;
            case"PE (Peru)":CountryInput.setText("+51"); break;
            case"PF (French Polynesia)":CountryInput.setText("+680"); break;
            case"PG (Papua New Guinea)":CountryInput.setText("+675"); break;
            case"PH (Philippines)":CountryInput.setText("+63"); break;
            case"PK (Pakistan)":CountryInput.setText("+92"); break;
            case"PL (Poland)":CountryInput.setText("+48"); break;
            case"PM (Saint Pierre and Miquelon)":CountryInput.setText("+508"); break;
            case"PN (Pitcairn)":CountryInput.setText("+64"); break;
            case"PR (Puerto Rico)":CountryInput.setText("+"); break;
            case"PS (Palestine)":CountryInput.setText("+970"); break;
            case"PT (Portugal)":CountryInput.setText("+351"); break;
            case"PW (Palau)":CountryInput.setText("+680"); break;
            case"PY (Paraguay)":CountryInput.setText("+595"); break;
            case"QA (Qatar)":CountryInput.setText("+974"); break;
            case"RE (Reunion)":CountryInput.setText("+262"); break;
            case"RO (Romania)":CountryInput.setText("+40"); break;
            case"RS (Serbia)":CountryInput.setText("+381"); break;
            case"RU (Russia)":CountryInput.setText("+7"); break;
            case"RW (Rwanda)":CountryInput.setText("+250"); break;
            case"SA (Saudi Arabia)":CountryInput.setText("+966"); break;
            case"SB (Solomon Islands)":CountryInput.setText("+677"); break;
            case"SC (Seychelles)":CountryInput.setText("+248"); break;
            case"SD (Sudan)":CountryInput.setText("+249"); break;
            case"SE (Sweden)":CountryInput.setText("+46"); break;
            case"SG (Singapore)":CountryInput.setText("+65"); break;
            case"SH (Saint Helena)":CountryInput.setText("+92"); break;
            case"SI (Slovenia)":CountryInput.setText("+386"); break;
            case"SJ (Svalbard and Jan Mayen)":CountryInput.setText("+47"); break;
            case"SK (Slovakia)":CountryInput.setText("+421"); break;
            case"SL (Sierra Leone)":CountryInput.setText("+232"); break;
            case"SM (San Marino)":CountryInput.setText("+378"); break;
            case"SN (Senegal)":CountryInput.setText("+221"); break;
            case"SO (Somalia)":CountryInput.setText("+252"); break;
            case"SR (Suriname)":CountryInput.setText("+597"); break;
            case"SS (South Sudan)":CountryInput.setText("+211"); break;
            case"ST (Sao Tome and Principe)":CountryInput.setText("+239"); break;
            case"SV (El Salvador)":CountryInput.setText("+503"); break;
            case"SX (Sint Maarten)":CountryInput.setText("+1-721"); break;
            case"SY (Syria)":CountryInput.setText("+963"); break;
            case"SZ (Swaziland)":CountryInput.setText("+268"); break;
            case"TC (Turks and Caicos Islands)":CountryInput.setText("+1-649"); break;
            case"TD (Chad)":CountryInput.setText("+235"); break;
            case"TG (Togo)":CountryInput.setText("+228"); break;
            case"TH (Thailand)":CountryInput.setText("+66"); break;
            case"TJ (Tajikistan)":CountryInput.setText("+992"); break;
            case"TK (Tokelau)":CountryInput.setText("+290"); break;
            case"TL (East Timor)":CountryInput.setText("+670"); break;
            case"TM (Turkmenistan)":CountryInput.setText("+993"); break;
            case"TN (Tunisia)":CountryInput.setText("+216"); break;
            case"TO (Tonga)":CountryInput.setText("+676"); break;
            case"TR (Turkey)":CountryInput.setText("+90"); break;
            case"TT (Trinidad and Tobago)":CountryInput.setText("+1-868"); break;
            case"TV (Tuvalu)":CountryInput.setText("+688"); break;
            case"TW (Taiwan)":CountryInput.setText("+886"); break;
            case"TZ (Tanzania)":CountryInput.setText("+255"); break;
            case"UA (Ukraine)":CountryInput.setText("+380"); break;
            case"UG (Uganda)":CountryInput.setText("+256"); break;
            case"US (United States)":CountryInput.setText("+1"); break;
            case"UY (Uruguay)":CountryInput.setText("+598"); break;
            case"UZ (Uzbekistan)":CountryInput.setText("+998"); break;
            case"VA (Vatican)":CountryInput.setText("+379"); break;
            case"VC (Saint Vincent and the Grenadines)":CountryInput.setText("+1-784"); break;
            case"VE (Venezuela)":CountryInput.setText("+58"); break;
            case"VG (British Virgin Islands)":CountryInput.setText("+1-284"); break;
            case"VI (US Virgin Islands)":CountryInput.setText("+1-340"); break;
            case"VN (Vietnam)":CountryInput.setText("+84"); break;
            case"VU (Vanuatu)":CountryInput.setText("+678"); break;
            case"WF (Wallis and Futuna)":CountryInput.setText("+681"); break;
            case"WS (Samoa)":CountryInput.setText("+685"); break;
            case"XK (Kosovo)":CountryInput.setText("+383"); break;
            case"YE (Yemen)":CountryInput.setText("+967"); break;
            case"YT (Mayotte)":CountryInput.setText("+262"); break;
            case"ZA (South Africa)":CountryInput.setText("+27"); break;
            case"ZM (Zambia)":CountryInput.setText("+260"); break;
            case"ZW (Zimbabwe)":CountryInput.setText("+263"); break;
        }


    }

    public static void setErrorMessage(boolean visible){
        if(!visible){
            NumberError.setVisibility(View.VISIBLE);
        }else{
            NumberError.setVisibility(View.INVISIBLE);
        }
    }

    public static boolean getNumberVisibilty(){
        return VisibleCheck.isChecked();
    }

    public static String getNumber(){
        return NumberInput.getText().toString().trim();
    }

    public static String getCountry(){
        return CountryInput.getText().toString().trim();
    }



}
