package com.example.administrator.mykotlin.Utils

import android.content.Context
import android.widget.Toast

/**
 * 多个扩展函数名字也不能一样，因为是以静态代码的形式注入的
 * Created by Administrator on 2017/6/26 0026.
 */
fun Context.toask(mes : CharSequence = "默认值", time : Int = Toast.LENGTH_SHORT) {
   Toast.makeText(this,mes,time).show();
}