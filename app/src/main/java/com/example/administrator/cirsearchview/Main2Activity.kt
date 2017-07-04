package com.example.administrator.cirsearchview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.administrator.mykotlin.Utils.toask
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //setSupportActionBar(toolbar)
        initToolbar()
        InitRecyData()
        initListener()
    }

    fun InitRecyData() {
        var list = mutableListOf<String>()
        list.add("优酷");
        list.add("土豆");
        list.add("爱奇艺");
        list.add("哔哩哔哩");
        list.add("youtube");
        list.add("斗鱼");
        list.add("熊猫");

        val adapter = SearchRecAdapter(list!!)
        adapter.itemClickUnit { toask(it) }
        recycleview.layoutManager = LinearLayoutManager(this)
        recycleview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                val menuItem = item.itemId
                when (menuItem) {
                    R.id.action_search -> {
                        SearchViewUtils.handleToolBar(getApplicationContext(),cardView_search,et_search);
                    }
                    else -> {
                    }
                }
                return false
            }
        })
    }

    private fun initListener() {
        iv_search_back.setOnClickListener(
                View.OnClickListener {
                    SearchViewUtils.handleToolBar(applicationContext, cardView_search, et_search) })
    }
    class SearchRecAdapter(mList: List<String>) : RecyclerView.Adapter<SearchRecAdapter.MHolder>() {
        var list: List<String> = mList

        var itemClickUnit: (String) -> Unit = { _ -> }
        fun itemClickUnit(itemClickDoing: (String) -> Unit = { _ -> }) {
            itemClickUnit = itemClickDoing;
        }

        override fun onBindViewHolder(holder: MHolder?, position: Int) {
            holder?.tvContent?.text = list.get(position)
            holder?.tvContent?.setOnClickListener { itemClickUnit(list.get(position)) }
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MHolder {
            return MHolder(LayoutInflater.from(parent?.context).inflate(R.layout.serchitem, parent, false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        class MHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
            var tvContent: TextView = itemView!!.findViewById(R.id.content) as TextView;
        }
    }
}
