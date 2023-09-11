package com.example.onedrive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class DocumentoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documento)
        var listaArchivos = arrayListOf<Documento>()
        listaArchivos.add(Documento(R.drawable.documento,"Informe_Práctica_01","2 MB","Modif:18/07/2022"))
        listaArchivos.add(Documento(R.drawable.documento,"Informe_Práctica_02","2,3 MB","Modif:06/05/2022"))
        listaArchivos.add(Documento(R.drawable.documento,"Informe_Práctica_03","3 MB","Modif:12/12/2022"))
        listaArchivos.add(Documento(R.drawable.documento,"Informe_Práctica_04","5 MB","Modif:13/08/2022"))
        listaArchivos.add(Documento(R.drawable.documento,"Informe_Práctica_05","1,2 MB","Modif:14/10/2022"))
        listaArchivos.add(Documento(R.drawable.documento,"Informe_Práctica_06","2,4 MB","Modif:20/07/2023"))
        listaArchivos.add(Documento(R.drawable.documento,"Informe_Práctica_07","1 MB","Modif:15/06/2022"))
        listaArchivos.add(Documento(R.drawable.documento,"Informe_Práctica_08","1,5 MB","Modif:13/03/2022"))
        listaArchivos.add(Documento(R.drawable.documento,"Informe_Práctica_09","3 MB","Modif:15/04/2023"))

        val recyclerView = findViewById<RecyclerView>(R.id.rvArchivo)
        initRecyclerView(listaArchivos, recyclerView)
    }

    private fun initRecyclerView(listaArchivos: ArrayList<Documento>, recyclerView: RecyclerView) {
        val adapter = AdapterDocumento(listaArchivos,this,recyclerView)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }
}