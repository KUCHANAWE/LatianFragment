package com.example.latianfragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Halaman1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Halaman1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_halaman1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnHalaman1 = view.findViewById<Button>(R.id.btnHalaman2)

        var score = 50
        var AngkaBatas = 5
        val tvScore = view.findViewById<TextView>(R.id.tvScore)
        tvScore.text = score.toString()

        btnHalaman1.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString("SCORE", score.toString())

            val mfDua = Halaman2()
            mfDua.arguments = mBundle

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfDua, Halaman2::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        val buttons = arrayOf(
            view.findViewById<Button>(R.id.btn1),
            view.findViewById<Button>(R.id.btn2),
            view.findViewById<Button>(R.id.btn3),
            view.findViewById<Button>(R.id.btn4),
            view.findViewById<Button>(R.id.btn5),
            view.findViewById<Button>(R.id.btn6),
            view.findViewById<Button>(R.id.btn7),
            view.findViewById<Button>(R.id.btn8),
            view.findViewById<Button>(R.id.btn9),
            view.findViewById<Button>(R.id.btn10)
        )

        if (arguments != null) {
            val txtHasil = arguments?.getString("Config")
            AngkaBatas = txtHasil?.toInt() ?: 5
        }

        val angkaAwal = (AngkaBatas - 5) + 1
        val angkaAkhir = AngkaBatas

        val pairedNumbers = mutableListOf<Int>()
        for (i in angkaAwal..angkaAkhir) {
            pairedNumbers.add(i)
            pairedNumbers.add(i)
        }

        pairedNumbers.shuffle()
        buttons.forEach { it.text = "" }

        var lastClickedIndex: Int? = null
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                val currentValue = pairedNumbers[index]
                button.text = currentValue.toString()
                button.isEnabled = false

                if (lastClickedIndex == null) {
                    lastClickedIndex = index
                } else {
                    val firstButton = buttons[lastClickedIndex!!]

                    if (pairedNumbers[lastClickedIndex!!] == currentValue) {
                        score += 10
                        firstButton.isEnabled = false
                    } else {
                        score -= 5
                        Handler(Looper.getMainLooper()).postDelayed({
                            button.text = ""
                            firstButton.text = ""
                            button.isEnabled = true
                            firstButton.isEnabled = true
                        }, 1000)
                    }

                    tvScore.text = score.toString()
                    lastClickedIndex = null

                    if (buttons.all { !it.isEnabled }) {
                        val mBundle = Bundle()
                        mBundle.putString("SCORE", score.toString())

                        val mfDua = Halaman2()
                        mfDua.arguments = mBundle

                        parentFragmentManager.beginTransaction().apply {
                            replace(R.id.frameContainer, mfDua, Halaman2::class.java.simpleName)
                            addToBackStack(null)
                            commit()
                        }
                    }
                }
            }
        }
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Halaman1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Halaman1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}