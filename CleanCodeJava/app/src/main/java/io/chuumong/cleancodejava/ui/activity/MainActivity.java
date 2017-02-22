package io.chuumong.cleancodejava.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import io.chuumong.cleancodejava.App;
import io.chuumong.cleancodejava.R;
import io.chuumong.cleancodejava.data.model.CityData;
import io.chuumong.cleancodejava.data.model.CityList;
import io.chuumong.cleancodejava.di.main.MainModule;
import io.chuumong.cleancodejava.presenter.MainPresenter;
import io.chuumong.cleancodejava.presenter.MainView;
import io.chuumong.cleancodejava.ui.adapter.CityAdapter;

public class MainActivity extends AppCompatActivity implements MainView, CityAdapter.OnItemClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    private ProgressBar progressBar;

    private RecyclerView recyclerView;

    private CityAdapter adapter;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CityAdapter(this, this);

        recyclerView.setAdapter(adapter);

        App.get(this).getAppComponent().plus(new MainModule(this)).inject(this);
        presenter.getCityList();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiedProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e(TAG, throwable.getMessage(), throwable);
    }

    @Override
    public void resultCityList(CityList citys) {
        adapter.add(citys.getData());
    }

    @Override
    public void onClick(CityData data) {
        Toast.makeText(this, data.getName(), Toast.LENGTH_LONG).show();
    }
}
