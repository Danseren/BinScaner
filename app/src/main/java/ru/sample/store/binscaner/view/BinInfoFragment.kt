package ru.sample.store.binscaner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.sample.store.binscaner.USER_KEY
import ru.sample.store.binscaner.databinding.FragmentBinInfoBinding
import ru.sample.store.binscaner.viewmodel.AppState
import ru.sample.store.binscaner.viewmodel.InfoViewModel

class BinInfoFragment : Fragment() {

    private var _binding: FragmentBinInfoBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(number: String): BinInfoFragment {
            val fragment = BinInfoFragment()
            val bundle = Bundle()
            bundle.putString(USER_KEY, number)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(InfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBinInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val num = this.arguments?.getString(USER_KEY)?.toInt()
        if (num != null) {
            viewModel.getInfo(num)
        }
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {}
            AppState.Loading -> {}
            is AppState.Success -> {
                val binDTO = appState.binData
                binding.apply {
                    tvNumberValue.text = arguments?.getString(USER_KEY)
                    tvSchemeValue.text = binDTO.scheme
                    tvTypeValue.text = binDTO.type
                    tvBrandValue.text = binDTO.brand
                    tvPrepaidValue.text = binDTO.prepaid.toString()

                    binDTO.number?.apply {
                        tvLengthValue.text = length.toString()
                        tvLuhnValue.text = luhn.toString()
                    }
                    binDTO.country?.apply {
                        tvNumericValue.text = numeric
                        tvAlphaValue.text = alpha2
                        tvNameValue.text = name
                        tvEmojiValue.text = emoji
                        tvCurrencyValue.text = currency
                        tvLatitudeValue.text = longitude.toString()
                        tvLongitudeValue.text = latitude.toString()
                    }
                    binDTO.bank?.apply {
                        tvBankNameValue.text = name
                        tvURLValue.text = url
                        tvPhoneValue.text = phone
                        tvCityValue.text = city
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}