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

public class SurveyEnvironmentDataFragment extends BaseFragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_survey_environment_data, container, false);

        EditText txtWilayah = v.findViewById(R.id.txtWilayah);
        EditText txtPeruntukan = v.findViewById(R.id.txtPeruntukan);
        EditText txtUseExisting = v.findViewById(R.id.txtUseExisting);

        EditText txtJaringanListrik = v.findViewById(R.id.txtJaringanListrik);
        EditText txtJaringanTelp = v.findViewById(R.id.txtJaringanTelp);
        EditText txtAirBersih = v.findViewById(R.id.txtAirBersih);
        EditText txtPeneranganJalan = v.findViewById(R.id.txtPeneranganJalan);
        EditText txtTempatIbadah = v.findViewById(R.id.txtTempatIbadah);
        EditText txtPusatPerbelanjaan = v.findViewById(R.id.txtPusatPerbelanjaan);
        EditText txtSaranaPendidikan = v.findViewById(R.id.txtSaranaPendidikan);
        EditText txtFasilitasKeamanan = v.findViewById(R.id.txtFasilitasKeamanan);
        EditText txtSaranaTransportasi = v.findViewById(R.id.txtSaranaTransportasi);
        EditText txtRS = v.findViewById(R.id.txtRS);

        EditText txtKemudahanTransport = v.findViewById(R.id.txtKemudahanTransport);
        EditText txtKemudahanBelanja = v.findViewById(R.id.txtKemudahanBelanja);
        EditText txtKemudahanRekreasi = v.findViewById(R.id.txtKemudahanRekreasi);
        EditText txtResikoKebakaran = v.findViewById(R.id.txtResikoKebakaran);
        EditText txtResikoBencanaAlam = v.findViewById(R.id.txtResikoBencanaAlam);
        EditText txtDASDistance = v.findViewById(R.id.txtDASDistance);
        EditText txtPembuanganSampah = v.findViewById(R.id.txtPembuanganSampah);
        EditText txtMakam = v.findViewById(R.id.txtMakam);
        EditText txtTeganganTinggi = v.findViewById(R.id.txtTeganganTinggi);

        EditText txtJalanDepanProp = v.findViewById(R.id.txtJalanDepanProp);
        EditText txtKonstruksiJalan = v.findViewById(R.id.txtKonstruksiJalan);
        EditText txtLebarJalan = v.findViewById(R.id.txtLebarJalan);
        EditText txtPemeliharaanJalan = v.findViewById(R.id.txtPemeliharaanJalan);
        EditText txtPenghijauan = v.findViewById(R.id.txtPenghijauan);
        EditText txtDrainase = v.findViewById(R.id.txtDrainase);
        EditText txtPenataan = v.findViewById(R.id.txtPenataan);

        txtWilayah.setOnClickListener(this);
        txtPeruntukan.setOnClickListener(this);
        txtUseExisting.setOnClickListener(this);

        txtJaringanListrik.setOnClickListener(this);
        txtJaringanTelp.setOnClickListener(this);
        txtAirBersih.setOnClickListener(this);
        txtPeneranganJalan.setOnClickListener(this);
        txtTempatIbadah.setOnClickListener(this);
        txtPusatPerbelanjaan.setOnClickListener(this);
        txtSaranaPendidikan.setOnClickListener(this);
        txtFasilitasKeamanan.setOnClickListener(this);
        txtSaranaTransportasi.setOnClickListener(this);
        txtRS.setOnClickListener(this);
        txtKemudahanTransport.setOnClickListener(this);
        txtKemudahanBelanja.setOnClickListener(this);
        txtKemudahanRekreasi.setOnClickListener(this);
        txtResikoKebakaran.setOnClickListener(this);
        txtResikoBencanaAlam.setOnClickListener(this);

        txtJalanDepanProp.setOnClickListener(this);
        txtKonstruksiJalan.setOnClickListener(this);
        txtPemeliharaanJalan.setOnClickListener(this);
        txtPenghijauan.setOnClickListener(this);
        txtDrainase.setOnClickListener(this);
        txtPenataan.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txtJaringanListrik || view.getId() == R.id.txtJaringanTelp || view.getId() == R.id.txtAirBersih ||
                view.getId() == R.id.txtPeneranganJalan) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_availability_ext);
        } else if(view.getId() == R.id.txtTempatIbadah || view.getId() == R.id.txtPusatPerbelanjaan || view.getId() == R.id.txtSaranaPendidikan
                || view.getId() == R.id.txtFasilitasKeamanan || view.getId() == R.id.txtSaranaTransportasi || view.getId() == R.id.txtRS) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_availability);
        } else if(view.getId() == R.id.txtWilayah) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_area);
        } else if(view.getId() == R.id.txtPeruntukan) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_peruntukan_wilayah);
        } else if(view.getId() == R.id.txtUseExisting) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_use_existing);
        } else if(view.getId() == R.id.txtKemudahanTransport) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_dificulty);
        } else if(view.getId() == R.id.txtKemudahanBelanja) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_dificulty);
        } else if(view.getId() == R.id.txtKemudahanSekolah) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_dificulty);
        } else if(view.getId() == R.id.txtKemudahanRekreasi) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_dificulty);
        } else if(view.getId() == R.id.txtResikoKebakaran) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_possibility);
        } else if(view.getId() == R.id.txtResikoBencanaAlam) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_possibility);
        } else if(view.getId() == R.id.txtJalanDepanProp) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_jalan_dpn_objek);
        } else if(view.getId() == R.id.txtKonstruksiJalan) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_bahan_jalan);
        } else if(view.getId() == R.id.txtPemeliharaanJalan) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_quality);
        } else if(view.getId() == R.id.txtPenghijauan) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_quality);
        } else if(view.getId() == R.id.txtDrainase) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_quality);
        } else if(view.getId() == R.id.txtPenataan) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_quality);
        }
    }
}
