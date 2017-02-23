package io.chuumong.cleancodekotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import io.chuumong.cleancodekotlin.App
import io.chuumong.cleancodekotlin.R
import io.chuumong.cleancodekotlin.data.model.CityList
import io.chuumong.cleancodekotlin.di.main.MainModule
import io.chuumong.cleancodekotlin.presenter.MainPresenter
import io.chuumong.cleancodekotlin.presenter.MainView
import javax.inject.Inject
import  android.view.View
import android.widget.Toast
import io.chuumong.cleancodekotlin.data.model.CityData
import io.chuumong.cleancodekotlin.ui.adapter.CityAdapter

class MainActivity : AppCompatActivity(), MainView, CityAdapter.OnItemClickListener {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var rvCitys: RecyclerView

    private lateinit var progress: ProgressBar

    private lateinit var adapter: CityAdapter

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCitys = findViewById(R.id.list) as RecyclerView
        progress = findViewById(R.id.progress) as ProgressBar

        rvCitys.layoutManager = LinearLayoutManager(this)

        adapter = CityAdapter(this, this)
        rvCitys.adapter = adapter

        App.get(this).getComponent().plus(MainModule(this)).inject(this)
        presenter.getCityList()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hiedProgress() {
        progress.visibility = View.GONE
    }

    override fun onError(throwable: Throwable) {
        Log.e(TAG, "onError#message : " + throwable.message, throwable)
    }

    override fun resultCityList(citys: CityList) {
        adapter.add(citys.data)
    }

    override fun onClick(data: CityData) {
        Toast.makeText(this, data.name, Toast.LENGTH_LONG).show()
    }
}
