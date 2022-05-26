package com.developr.me_commars.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.developr.me_commars.R;
import com.developr.me_commars.databinding.ActivityProudctDetailsBinding;

public class ProudctDetails extends AppCompatActivity {

    ActivityProudctDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProudctDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}