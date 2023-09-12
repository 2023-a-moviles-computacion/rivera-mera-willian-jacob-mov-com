package com.example.onedrive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaCarpetas = arrayListOf<Carpeta>()
        listaCarpetas.add(Carpeta(R.drawable.documento,"Documentos","1 GB"))
        listaCarpetas.add(Carpeta(R.drawable.hojacalculo,"Hojas de Cálculo","1 GB"))
        listaCarpetas.add(Carpeta(R.drawable.power,"Presentaciones","3 GB",))
        listaCarpetas.add(Carpeta(R.drawable.imagen,"Imágenes","10 GB",))
        listaCarpetas.add(Carpeta(R.drawable.video,"Videos","70 GB",))
        listaCarpetas.add(Carpeta(R.drawable.audios,"Audios","0.7 GB",))
        listaCarpetas.add(Carpeta(R.drawable.formulario,"Formularios","0 GB",))
        listaCarpetas.add(Carpeta(R.drawable.sitios,"Sitios","0,1 GB"))

        val recyclerView = findViewById<RecyclerView>(R.id.rvDirectorio)
        initRecyclerView(listaCarpetas, recyclerView)
    }

    private fun initRecyclerView(listaCarpetas: ArrayList<Carpeta>, recyclerView: RecyclerView) {
        val adapter = AdapterCarpeta(listaCarpetas,this,recyclerView)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
        adapter.onItemClick = {
            val intent = Intent(this, DocumentoActivity::class.java)
            startActivity(intent)
        }
    }
}