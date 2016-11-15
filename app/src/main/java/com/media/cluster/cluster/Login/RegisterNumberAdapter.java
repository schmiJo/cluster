package com.media.cluster.cluster.Login;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.media.cluster.cluster.Main.DrawerLayoutManager;
import com.media.cluster.cluster.R;

import java.util.List;


 class RegisterNumberAdapter extends RecyclerView.Adapter<RegisterNumberAdapter.CountryViewHolder> {
    private LayoutInflater inflater;
    private List<SelectNumberDataModel> data;
     View view;


     RegisterNumberAdapter(Context context, List<SelectNumberDataModel> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.register_select_country_row_layout, parent, false);
        return new CountryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {

        SelectNumberDataModel current = data.get(position);

        holder.countryName.setText(current.countryName);

    }
     void setSelected(View item){
        CountryViewHolder viewHolder = new CountryViewHolder(item);
         viewHolder.radioButton.setChecked(true);
         RegisterNumberFragment.setCountryInput(viewHolder.countryName.getText().toString());

    }

     void setDetailSelected(View item){
         CountryViewHolder viewHolder = new CountryViewHolder(item);
         viewHolder.radioButton.setChecked(true);
     }

     String  setProfessionSelected(int position, DrawerLayoutManager drawerLayoutManager){
         CountryViewHolder viewHolder0 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(0));
         CountryViewHolder viewHolder1 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(1));
         CountryViewHolder viewHolder2 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(2));
         CountryViewHolder viewHolder3 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(3));
         CountryViewHolder viewHolder4 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(4));
         CountryViewHolder viewHolder5 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(5));
         CountryViewHolder viewHolder6 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(6));
         CountryViewHolder viewHolder7 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(7));
         CountryViewHolder viewHolder8 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(8));
         CountryViewHolder viewHolder9 = new CountryViewHolder(drawerLayoutManager.findViewByPosition(9));


         if(position == 0){
             viewHolder0.radioButton.setChecked(true);
         }else{
             viewHolder0.radioButton.setChecked(false);
         }

         if(position == 1){
             viewHolder1.radioButton.setChecked(true);
         }else{
             viewHolder1.radioButton.setChecked(false);
         }

         if(position == 2){
             viewHolder2.radioButton.setChecked(true);
         }else{
             viewHolder2.radioButton.setChecked(false);
         }

         if(position == 3){
             viewHolder3.radioButton.setChecked(true);
         }else{
             viewHolder3.radioButton.setChecked(false);
         }

         if(position == 4){
             viewHolder4.radioButton.setChecked(true);
         }else{
             viewHolder4.radioButton.setChecked(false);
         }

         if(position == 5){
             viewHolder5.radioButton.setChecked(true);
         }else{
             viewHolder5.radioButton.setChecked(false);
         }

         if(position == 6){
             viewHolder6.radioButton.setChecked(true);
         }else{
             viewHolder6.radioButton.setChecked(false);
         }

         if(position == 7){
             viewHolder7.radioButton.setChecked(true);
         }else{
             viewHolder7.radioButton.setChecked(false);
         }

         if(position == 8){
             viewHolder8.radioButton.setChecked(true);
         }else{
             viewHolder8.radioButton.setChecked(false);
         }

         if(position == 9){
             viewHolder9.radioButton.setChecked(true);
         }else{
             viewHolder9.radioButton.setChecked(false);
         }

         switch (position){
             case 0:
                 return "";
             case 1:
                 return viewHolder1.countryName.getText().toString();
             case 2:
                 return viewHolder2.countryName.getText().toString();
             case 3:
                 return viewHolder3.countryName.getText().toString();
             case 4:
                 return viewHolder4.countryName.getText().toString();
             case 5:
                 return viewHolder5.countryName.getText().toString();
             case 6:
                 return viewHolder6.countryName.getText().toString();
             case 7:
                 return viewHolder7.countryName.getText().toString();
             case 8:
                 return viewHolder8.countryName.getText().toString();
             case 9:
                 return viewHolder9.countryName.getText().toString();
            default:
                return "No response";
         }
     }




    static class CountryViewHolder extends RecyclerView.ViewHolder{
        RadioButton radioButton;
        TextView countryName;

         CountryViewHolder(View itemView){
            super(itemView);

            countryName = (TextView) itemView.findViewById(R.id.register_number_row_title);
            radioButton = (RadioButton) itemView.findViewById(R.id.register_number_radio_button);
        }
    }
}
