package com.trusindo.april.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.trusindo.april.R;
import com.trusindo.april.utils.AppUtils;

/**
 * Created by jakalesmana on 10/10/17.
 */

public class SurveyComparationDataFragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_survey_comparation_data, container, false);

        EditText txtAlamat1 = v.findViewById(R.id.txtAlamat1);
        EditText txtSubDistrict1 = v.findViewById(R.id.txtSubDistrict1);
        EditText txtDistrict1 = v.findViewById(R.id.txtDistrict1);
        EditText txtCity1 = v.findViewById(R.id.txtCity1);
        EditText txtCp1 = v.findViewById(R.id.txtCp1);
        EditText txtPhone1 = v.findViewById(R.id.txtPhone1);
        EditText txtStatusData1 = v.findViewById(R.id.txtStatusData1);
        EditText txtPrice1 = v.findViewById(R.id.txtPrice1);
        EditText txtPropType1 = v.findViewById(R.id.txtPropType1);
        EditText txtLuasTanah1 = v.findViewById(R.id.txtLuasTanah1);
        EditText txtPeruntukan1 = v.findViewById(R.id.txtPeruntukan1);
        EditText txtLicense1 = v.findViewById(R.id.txtLicense1);
        EditText txtLebarJalan1 = v.findViewById(R.id.txtLebarJalan1);
        EditText txtPosisiTanah1 = v.findViewById(R.id.txtPosisiTanah1);
        EditText txtKonturTanah1 = v.findViewById(R.id.txtKonturTanah1);
        EditText txtBentukTanah1 = v.findViewById(R.id.txtBentukTanah1);
        EditText txtKondisiBangunan1 = v.findViewById(R.id.txtKondisiBangunan1);
        EditText txtLuasBangunan1 = v.findViewById(R.id.txtLuasBangunan1);
        EditText txtKelasBangunan1 = v.findViewById(R.id.txtKelasBangunan1);
        EditText txtJumlahLantai1 = v.findViewById(R.id.txtJumlahLantai1);

        EditText txtAlamat2 = v.findViewById(R.id.txtAlamat2);
        EditText txtSubDistrict2 = v.findViewById(R.id.txtSubDistrict2);
        EditText txtDistrict2 = v.findViewById(R.id.txtDistrict2);
        EditText txtCity2 = v.findViewById(R.id.txtCity2);
        EditText txtCp2 = v.findViewById(R.id.txtCp2);
        EditText txtPhone2 = v.findViewById(R.id.txtPhone2);
        EditText txtStatusData2 = v.findViewById(R.id.txtStatusData2);
        EditText txtPrice2 = v.findViewById(R.id.txtPrice2);
        EditText txtPropType2 = v.findViewById(R.id.txtPropType2);
        EditText txtLuasTanah2 = v.findViewById(R.id.txtLuasTanah2);
        EditText txtPeruntukan2 = v.findViewById(R.id.txtPeruntukan2);
        EditText txtLicense2 = v.findViewById(R.id.txtLicense2);
        EditText txtLebarJalan2 = v.findViewById(R.id.txtLebarJalan2);
        EditText txtPosisiTanah2 = v.findViewById(R.id.txtPosisiTanah2);
        EditText txtKonturTanah2 = v.findViewById(R.id.txtKonturTanah2);
        EditText txtBentukTanah2 = v.findViewById(R.id.txtBentukTanah2);
        EditText txtKondisiBangunan2 = v.findViewById(R.id.txtKondisiBangunan2);
        EditText txtLuasBangunan2 = v.findViewById(R.id.txtLuasBangunan2);
        EditText txtKelasBangunan2 = v.findViewById(R.id.txtKelasBangunan2);
        EditText txtJumlahLantai2 = v.findViewById(R.id.txtJumlahLantai2);

        EditText txtAlamat3 = v.findViewById(R.id.txtAlamat3);
        EditText txtSubDistrict3 = v.findViewById(R.id.txtSubDistrict3);
        EditText txtDistrict3 = v.findViewById(R.id.txtDistrict3);
        EditText txtCity3 = v.findViewById(R.id.txtCity3);
        EditText txtCp3 = v.findViewById(R.id.txtCp3);
        EditText txtPhone3 = v.findViewById(R.id.txtPhone3);
        EditText txtStatusData3 = v.findViewById(R.id.txtStatusData3);
        EditText txtPrice3 = v.findViewById(R.id.txtPrice3);
        EditText txtPropType3 = v.findViewById(R.id.txtPropType3);
        EditText txtLuasTanah3 = v.findViewById(R.id.txtLuasTanah3);
        EditText txtPeruntukan3 = v.findViewById(R.id.txtPeruntukan3);
        EditText txtLicense3 = v.findViewById(R.id.txtLicense3);
        EditText txtLebarJalan3 = v.findViewById(R.id.txtLebarJalan3);
        EditText txtPosisiTanah3 = v.findViewById(R.id.txtPosisiTanah3);
        EditText txtKonturTanah3 = v.findViewById(R.id.txtKonturTanah3);
        EditText txtBentukTanah3 = v.findViewById(R.id.txtBentukTanah3);
        EditText txtKondisiBangunan3 = v.findViewById(R.id.txtKondisiBangunan3);
        EditText txtLuasBangunan3 = v.findViewById(R.id.txtLuasBangunan3);
        EditText txtKelasBangunan3 = v.findViewById(R.id.txtKelasBangunan3);
        EditText txtJumlahLantai3 = v.findViewById(R.id.txtJumlahLantai3);

        txtStatusData1.setOnClickListener(this);
        txtPropType1.setOnClickListener(this);
        txtPeruntukan1.setOnClickListener(this);
        txtLicense1.setOnClickListener(this);
        txtPosisiTanah1.setOnClickListener(this);
        txtKonturTanah1.setOnClickListener(this);
        txtBentukTanah1.setOnClickListener(this);
        txtKondisiBangunan1.setOnClickListener(this);
        txtKelasBangunan1.setOnClickListener(this);

        txtStatusData2.setOnClickListener(this);
        txtPropType2.setOnClickListener(this);
        txtPeruntukan2.setOnClickListener(this);
        txtLicense2.setOnClickListener(this);
        txtPosisiTanah2.setOnClickListener(this);
        txtKonturTanah2.setOnClickListener(this);
        txtBentukTanah2.setOnClickListener(this);
        txtKondisiBangunan2.setOnClickListener(this);
        txtKelasBangunan2.setOnClickListener(this);

        txtStatusData3.setOnClickListener(this);
        txtPropType3.setOnClickListener(this);
        txtPeruntukan3.setOnClickListener(this);
        txtLicense3.setOnClickListener(this);
        txtPosisiTanah3.setOnClickListener(this);
        txtKonturTanah3.setOnClickListener(this);
        txtBentukTanah3.setOnClickListener(this);
        txtKondisiBangunan3.setOnClickListener(this);
        txtKelasBangunan3.setOnClickListener(this);



        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.txtStatusData1 || view.getId() == R.id.txtStatusData2 || view.getId() == R.id.txtStatusData3) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_prop_status);
        } else if(view.getId() == R.id.txtPropType1 || view.getId() == R.id.txtPropType2 || view.getId() == R.id.txtPropType3) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_use_existing);
        } else if(view.getId() == R.id.txtPeruntukan1 || view.getId() == R.id.txtPeruntukan2 || view.getId() == R.id.txtPeruntukan3) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_peruntukan_wilayah);
        } else if(view.getId() == R.id.txtLicense1 || view.getId() == R.id.txtLicense2 || view.getId() == R.id.txtLicense3) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_bukti_hak);
        } else if(view.getId() == R.id.txtPosisiTanah1 || view.getId() == R.id.txtPosisiTanah2 || view.getId() == R.id.txtPosisiTanah3) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_ground_position);
        } else if(view.getId() == R.id.txtKonturTanah1 || view.getId() == R.id.txtKonturTanah2 || view.getId() == R.id.txtKonturTanah3) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_ground_contur);
        } else if(view.getId() == R.id.txtBentukTanah1 || view.getId() == R.id.txtBentukTanah2 || view.getId() == R.id.txtBentukTanah3) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_ground_shape);
        } else if(view.getId() == R.id.txtKondisiBangunan1 || view.getId() == R.id.txtKondisiBangunan2 || view.getId() == R.id.txtKondisiBangunan3) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_condition);
        } else if(view.getId() == R.id.txtKelasBangunan1 || view.getId() == R.id.txtKelasBangunan2 || view.getId() == R.id.txtKelasBangunan3) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_class);
        }
    }
}
