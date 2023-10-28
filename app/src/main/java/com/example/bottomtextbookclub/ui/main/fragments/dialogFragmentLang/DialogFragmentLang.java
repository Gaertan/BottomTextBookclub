package com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentLang;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.MyApplication;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragmentLang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragmentLang extends DialogFragment {

    public interface OnLanguageSelectedListener {
        void onLanguageSelected(String languageCode);
    }

    private OnLanguageSelectedListener mListener;
    @Override
    public void onAttach( Context context) {
        super.onAttach(context);

        try {
            mListener = (OnLanguageSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " debe implementar OnLanguageSelectedListener");
        }
    }

    public static DialogFragmentLang newInstance(String param1, String param2) {
        DialogFragmentLang fragment = new DialogFragmentLang();
       // Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    public DialogFragmentLang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_fragmentlang, container, false);

        String[] recourseList = getResources().getStringArray(R.array.CountryCodes);

        final ListView listview = view.findViewById(R.id.listView);
        listview.setAdapter(new CountriesListAdapter(requireContext(), recourseList));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountryCode = recourseList[position];
                String[] parts = selectedCountryCode.split(",");
                String selectedLanguageCode = parts[1].toLowerCase();

                MyApplication myApplication = (MyApplication) requireActivity().getApplication();
                myApplication.cambiarLenguage(getContext(),selectedLanguageCode);

            System.out.println("el idioma ha intentado cambiarse con   +  " +selectedLanguageCode);
              //  myApplication.promptRestart(mListener);
                if (mListener != null) {
                    mListener.onLanguageSelected(selectedLanguageCode);
                }

                dismiss();
            }
        });



        return view;
    }

    public static void show(FragmentManager fragmentManager) {
        DialogFragmentLang dialogFragment = new DialogFragmentLang();

        // Iniciar la transacción para mostrar el fragmento
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.addToBackStack(null); // Opcional: agregar la transacción a la pila de retroceso
        transaction.commit();
    }

//----------------------------


//-----------------------------

    public class CountriesListAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public CountriesListAdapter(Context context, String[] values) {
            super(context, R.layout.country_list_item_xml, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.country_list_item_xml, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.txtViewCountryName);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.imgViewFlag);

            String[] g=values[position].split(",");
            textView.setText(GetCountryZipCode(g[1]).trim());


            String pngName = g[1].trim().toLowerCase();
            imageView.setImageResource(context.getResources().getIdentifier("drawable/" + pngName, null, context.getPackageName()));
            return rowView;
        }

        private String GetCountryZipCode(String ssid){
            Locale loc = new Locale("", ssid);

            return loc.getDisplayCountry().trim();
        }
    }






}