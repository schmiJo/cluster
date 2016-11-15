package com.media.cluster.cluster.Login;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.media.cluster.cluster.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterSelectCountryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RegisterNumberAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select_country);


        recyclerView = (RecyclerView) findViewById(R.id.register_select_country_recycler_view);
        adapter = new RegisterNumberAdapter(getApplicationContext(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                    adapter.setSelected(view);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        finish();
                    }

                }, 300);
            }
        }));


    }

    public static List<SelectNumberDataModel> getData(){
        List<SelectNumberDataModel> data = new ArrayList<>();
        String[] countries = {"AD (Andorra)","AE (United Arab Emirates)","AF (Afghanistan)","AG (Antigua and Barbuda)","AI (Anguilla)","AL (Albania)","AM (Armenia)","An (Netherlands Antilles)","AO (Angola)","AQ (Antarctica)",
        "AR (Argentina)","AS (American Samoa)","AT (Austria)","AW (Aruba)","AZ (Azerbaijan)","BA (Bosnia and Herzegovina)","BB (Barbados)","BD (Bangladesh)","BE (Belgium)","BF (Burkina Faso)","BG (Bulgaria)","BH (Bahrain)",
        "BI (Burundi)","BJ (Benin)","BL (Saint Barthelemy)","BM (Bermuda)","BN (Brunei)","BO (Bolivia)","BR (Brazil)","BS (Bahamas)","BT (Bhutan)","BW (Botswana)","BY (Belarus)","BZ (Belize)","CA (Canada)","CC (Cocos Islands)","CD (Democratic Republic of the Congo)",
        "CF (Central African Republic)","CG (Republic of the Congo)","CH (Switzerland)","CI (Ivory Coast)","CK (Cook Island)","CL (Chile)","CM (Cameroon)","CN (China)","CO (Colombia)","CR (Costa Rica)","Cu (Cuba)", "CV (Cape Verde)",
        "CW (Curacao)","CX (Christmas Island)","CY (Cyprus)","CZ (Czech Republic)","DE (Germany)","DJ (Djibouti)","DK (Denmark)","DM (Dominica)","DO (Dominican Republic)","DZ (Algeria)","EC (Ecuador)","EE (Estonia)","EG (Egypt)","EH (Western Sahara)",
        "ER (Eritrea)","ES (Spain)","ET (Ethiopia)","FI (Finland)","FJ (Fiji)","FK (Falkland Islands)","FM (Micronesia)","FO (Faroe Islands)","FR (France)","GA (Gabon)","GB (United Kingdom)","GD (Grenada)","GE (Georgia)","GG (Guernsey)","GH (Ghana)",
        "GI (Gibraltar)","GL (Greenland)","GM (Gambia)","GN (Guinea)","GQ (Equatorial Guinea)","GR (Greece)","GT (Guatemala)","GU (Guam)","GW (Guinea-Bissau)","GY (Guyana)","HK (Hong Kong)","HN (Honduras)","HR (Croatia)","HT (Haiti)","HU (Hungary)","ID (Indonesia)","IE (Ireland)",
        "IL (Israel)","IM (Isle of Man)","IN (India)","IO (British Indian Ocean Territory)","IQ (Iraq)","IR (Iran)","IS (Iceland)","IT (Italy)","JE (Jersey)","JM (Jamaica)","JO (Jordan)","JP (Japan)","KE (Kenya)","KG (Kyrgyzstan)",
        "KH (Cambodia)","KI (Kiribati)","KM (Comoros)","KN (Saint Kitts and Nevis)","KP (North Korea)","KR (South Korea)","KW (Kuwait)","KY (Cayman Islands)","KZ (Kazakhstan)","LA (Laos)","LB (Lebanon)","LC (Saint Lucia)","LI (Lichtenstein)",
        "LK (Sri Lanka)","LR (Liberia)","LS (Lesotho)", "LT (Lithuania)","LU (Luxembourg)","LV (Latvia)","LY (Libya)","MA (Morocco)","MC (Monaco)","MD (Moldova)","ME (Montenegro)","MF (Saint Martin)","MG (Madagascar)","MH (Marshall Islands)",
        "MK (Macedonia)","ML (Mali)","MM (Myanmar)","MN (Mongolia)","MO (Macau)","MP (Northern Mariana Islands)","MR (Mauritania)","MS (Montserrat)","MT (Malta)","MU (Mauritius)","MV (Maldives)","MW (Malawi)","MX (Mexico)","MY (Malaysia)","MZ (Mozambique)",
        "NA (Namibia)","NC (New Caledonia)","NE (Niger)","NG (Nigeria)","NI (Nicaragua)","NL (Netherlands)","NO (Norway)","NP (Nepal)","NR (Nauru)","NU (Niue)","NZ (New Zealand)","OM (Oman)","PA (Panama)","PE (Peru)","PF (French Polynesia)","PG (Papua New Guinea)",
        "PH (Philippines)","PK (Pakistan)","PL (Poland)","PM (Saint Pierre and Miquelon)","PN (Pitcairn)","PR (Puerto Rico)","PS (Palestine)","PT (Portugal)","PW (Palau)","PY (Paraguay)","QA (Qatar)","RE (Reunion)","RO (Romania)","RS (Serbia)","RU (Russia)"
        ,"RW (Rwanda)","SA (Saudi Arabia)","SB (Solomon Islands)","SC (Seychelles)","SD (Sudan)","SE (Sweden)","SG (Singapore)","SH (Saint Helena)","SI (Slovenia)","SJ (Svalbard and Jan Mayen)","SK (Slovakia)","SL (Sierra Leone)","SM (San Marino)","SN (Senegal)",
        "SO (Somalia)","SR (Suriname)","SS (South Sudan)","ST (Sao Tome and Principe)","SV (El Salvador)","SX (Sint Maarten)","SY (Syria)","SZ (Swaziland)","TC (Turks and Caicos Islands)","TD (Chad)","TG (Togo)","TH (Thailand)","TJ (Tajikistan)","TK (Tokelau)",
        "TL (East Timor)","TM (Turkmenistan)","TN (Tunisia)","TO (Tonga)","TR (Turkey)","TT (Trinidad and Tobago)","TV (Tuvalu)","TW (Taiwan)","TZ (Tanzania)","UA (Ukraine)","UG (Uganda)","US (United States)","UY (Uruguay)","UZ (Uzbekistan)","VA (Vatican)",
        "VC (Saint Vincent and the Grenadines)","VE (Venezuela)","VG (British Virgin Islands)","VI (US Virgin Islands)","VN (Vietnam)", "VU (Vanuatu)","WF (Wallis and Futuna)", "WS (Samoa)","XK (Kosovo)", "YE (Yemen)", "YT (Mayotte)", "ZA (South Africa)","ZM (Zambia)","ZW (Zimbabwe)"};

    for(int i = 0; i < countries.length; i++){
        SelectNumberDataModel current = new SelectNumberDataModel();
        current.countryName = countries[i];
        data.add(current);
    }
        return data;
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {

            this.clickListener=clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }


            });


        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());

            if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){

                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;


        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

//Interface to indicates the View that was clicked and the Position that was clicked
interface ClickListener {
    void onClick(View view, int position);

} }

