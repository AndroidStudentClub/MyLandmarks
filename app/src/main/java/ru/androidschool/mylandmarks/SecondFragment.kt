package ru.androidschool.mylandmarks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.androidschool.mylandmarks.network.LandmarkInfo
import ru.androidschool.mylandmarks.network.WikiClientRetrofit

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val landmarkText = view.findViewById<TextView>(R.id.textview_second)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val query = arguments?.getString("landmark")

        // Вызываем метод getLandmarkInfo()
        val call = WikiClientRetrofit.apiClient.getLandmarkInfo(query!!)
        call.enqueue(object : Callback<LandmarkInfo> {
            override fun onResponse(
                call: Call<LandmarkInfo>,
                response: Response<LandmarkInfo>
            ) {
                // Получаем результат
                val title = response.body()!!.extract
                landmarkText.text = title
                Log.d("SecondFragment", title)
            }

            override fun onFailure(call: Call<LandmarkInfo>, t: Throwable) {
                // Log error here since request failed
                Log.e("SecondFragment", t.toString())
            }
        })
    }
}