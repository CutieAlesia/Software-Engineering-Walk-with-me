package com.walkwithme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.walkwithme.databinding.FragmentTopUsersBinding;

public class TopUsers extends Fragment {

    private FragmentTopUsersBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTopUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TableLayout tableLayout = (TableLayout) getView().findViewById(R.id.topUserTable);

        TextView textView;
        ImageView imageView;
        TableRow tRow;
        final TableLayout.LayoutParams params =
                new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
        final TableRow.LayoutParams rowParams =
                new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        for (int c = 1; c < 100; c++) {
            tRow = new TableRow(getContext());
            imageView = new ImageView(getContext());
            imageView.setId(c);
            imageView.setImageResource(R.drawable.testi);
            tRow.addView(imageView, 250, 250);

            textView = new TextView(getContext());
            textView.setText("Text" + c);
            textView.setId(c);
            tRow.addView(textView, rowParams);

            tableLayout.addView(tRow);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
