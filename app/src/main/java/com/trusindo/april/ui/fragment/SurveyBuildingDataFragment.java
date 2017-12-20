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

public class SurveyBuildingDataFragment extends BaseFragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_survey_building_data, container, false);

        EditText txtJenisBangunan = v.findViewById(R.id.txtJenisBangunan);
        EditText txtLuasBangunanIMB = v.findViewById(R.id.txtLuasBangunanIMB);
        EditText txtTahunBerdiri = v.findViewById(R.id.txtTahunBerdiri);
        EditText txtTahunRenovasi = v.findViewById(R.id.txtTahunRenovasi);
        EditText txtJumlahLantai = v.findViewById(R.id.txtJumlahLantai);
        EditText txtIMB = v.findViewById(R.id.txtIMB);
        EditText txtLuasBangunanFisik = v.findViewById(R.id.txtLuasBangunanFisik);
        EditText txtStruktur = v.findViewById(R.id.txtStruktur);
        EditText txtLantai = v.findViewById(R.id.txtLantai);
        EditText txtDinding = v.findViewById(R.id.txtDinding);
        EditText txtPlafon = v.findViewById(R.id.txtPlafon);
        EditText txtAtap = v.findViewById(R.id.txtAtap);
        EditText txtPintuJendela = v.findViewById(R.id.txtPintuJendela);
        EditText txtTangga = v.findViewById(R.id.txtTangga);
        EditText txtKelasBangunan = v.findViewById(R.id.txtKelasBangunan);

        EditText txtSambunganListrik = v.findViewById(R.id.txtSambunganListrik);
        EditText txtKapasitasListrik = v.findViewById(R.id.txtKapasitasListrik);
        EditText txtAirBersih = v.findViewById(R.id.txtAirBersih);
        EditText txtTelepon = v.findViewById(R.id.txtTelepon);
        EditText txtLineTelepon = v.findViewById(R.id.txtLineTelepon);
        EditText txtTempatParkir = v.findViewById(R.id.txtTempatParkir);
        EditText txtLuasTempatParkir = v.findViewById(R.id.txtLuasTempatParkir);
        EditText txtPagar = v.findViewById(R.id.txtPagar);
        EditText txtPemadam = v.findViewById(R.id.txtPemadam);
        EditText txtSaranaLainnya = v.findViewById(R.id.txtSaranaLainnya);

        txtJenisBangunan.setOnClickListener(this);
        txtStruktur.setOnClickListener(this);
        txtLantai.setOnClickListener(this);
        txtDinding.setOnClickListener(this);
        txtPlafon.setOnClickListener(this);
        txtAtap.setOnClickListener(this);
        txtPintuJendela.setOnClickListener(this);
        txtTangga.setOnClickListener(this);
        txtKelasBangunan.setOnClickListener(this);

        txtSambunganListrik.setOnClickListener(this);
        txtAirBersih.setOnClickListener(this);
        txtTelepon.setOnClickListener(this);
        txtTempatParkir.setOnClickListener(this);
        txtPagar.setOnClickListener(this);
        txtPemadam.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.txtJenisBangunan) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_type);
        } else if(view.getId() == R.id.txtStruktur) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_structure);
        } else if(view.getId() == R.id.txtLantai) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_floor);
        } else if(view.getId() == R.id.txtDinding) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_wall);
        } else if(view.getId() == R.id.txtPlafon) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_plafond);
        } else if(view.getId() == R.id.txtAtap) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_roof);
        } else if(view.getId() == R.id.txtPintuJendela) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_windowdoor);
        } else if(view.getId() == R.id.txtTangga) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_stair);
        } else if(view.getId() == R.id.txtKelasBangunan) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_building_class);
        } else if(view.getId() == R.id.txtSambunganListrik) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_availability_ext);
        } else if(view.getId() == R.id.txtAirBersih) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_availability_ext);
        } else if(view.getId() == R.id.txtTelepon) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_availability_ext);
        } else if(view.getId() == R.id.txtTempatParkir) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_availability_ext);
        } else if(view.getId() == R.id.txtPagar) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_availability);
        } else if(view.getId() == R.id.txtPemadam) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_availability);
        }
    }
}
