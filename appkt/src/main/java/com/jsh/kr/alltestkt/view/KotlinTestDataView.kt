package com.jsh.kr.alltestkt.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jsh.kr.alltestkt.R
import com.jsh.kr.alltestkt.model.base.KotlinTestData

class KotlinTestDataView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
    private var rvTestData : RecyclerView? = null
    private var adapter : KotlinTestDataAdapter? = null
    private var tvTestDataMax : TextView? = null

    constructor(context: Context) :this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    init {
        LayoutInflater.from(context).inflate(R.layout.ui_kotlin_test_data, this)
        rvTestData = findViewById(R.id.rv_kotlin_test_data)
        tvTestDataMax = findViewById(R.id.tv_kotlin_test_data_max)

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvTestData!!.layoutManager = layoutManager

        adapter = KotlinTestDataAdapter()
        rvTestData!!.adapter = adapter
    }

    fun refresh(dataList : ArrayList<KotlinTestData>) {
        showMaxData(dataList)

        adapter!!.refresh(dataList)
    }

    private fun showMaxData(dataList : ArrayList<KotlinTestData>) {
        // Kotlin collect extend function maxBy()
//        val maxData = dataList.maxBy({data : KotlinTestData -> data.dataVar}) // => last param is lamda
//        val maxData = dataList.maxBy() {data : KotlinTestData -> data.dataVar} // => param is empty in ()
//        val maxData = dataList.maxBy { data : KotlinTestData -> data.dataVar } // => all param is one, param is it
//        val maxData = dataList.maxBy { it.dataVar } // deprecated
        val maxData = dataList.maxByOrNull { it.dataVal }

        tvTestDataMax!!.text = maxData.toString()
    }

    class KotlinTestDataAdapter : RecyclerView.Adapter<KotlinTestDataAdapter.ViewHolder>() {
        private val dataList = ArrayList<KotlinTestData>()

        fun refresh(dataList : ArrayList<KotlinTestData>) {
            this.dataList.clear()
            this.dataList.addAll(dataList)
            notifyDataSetChanged()
        }

        private fun getItemData(pos : Int) : KotlinTestData {
            return dataList[pos]
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_kotlin_test_data, parent, false))
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = getItemData(position)
            holder.tvTestData!!.text = data.toString()
            holder.tvTestDataVal!!.text = data.dataVal
            holder.tvTestDataVar!!.text = data.dataVar
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tvTestData : TextView? = null
            var tvTestDataVal : TextView? = null
            var tvTestDataVar : TextView? = null
            init {
                tvTestData = itemView.findViewById(R.id.tv_kotlin_test_data);
                tvTestDataVal = itemView.findViewById(R.id.tv_kotlin_test_data_val)
                tvTestDataVar = itemView.findViewById(R.id.tv_kotlin_test_data_var)
            }
        }
    }

}