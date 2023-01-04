package ru.sample.store.binscaner.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import ru.sample.store.binscaner.Bin
import ru.sample.store.binscaner.R
import ru.sample.store.binscaner.databinding.FragmentMainBinding
import ru.sample.store.binscaner.model.DataCardBin
import ru.sample.store.binscaner.model.RepositoryRoomImpl
import ru.sample.store.binscaner.viewmodel.InfoViewModel
import ru.sample.store.binscaner.viewmodel.MainFragmentAppState
import ru.sample.store.binscaner.viewmodel.MainViewModel

@Suppress("CAST_NEVER_SUCCEEDS")
class MainFragment : Fragment(), OnItemClick {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner) { t ->
            renderData(t)
        }
        viewModel.getAllHistory()

        binding.textField.setEndIconOnClickListener {

            val newA = binding.inputEditText.text.toString()
            val newBin = Bin(newA)
            RepositoryRoomImpl().addBin(newBin)

            val activity = context as AppCompatActivity
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerMain, BinInfoFragment.newInstance(newA))
                .addToBackStack(null)
                .commit()
        }
    }

    private fun renderData(mainFragmentAppState: MainFragmentAppState) {
        when (mainFragmentAppState) {
            is MainFragmentAppState.Error -> {
            }
            MainFragmentAppState.Loading -> {
            }
            is MainFragmentAppState.SuccessMulti -> {
                binding.recyclerBinHistoryList.adapter =
                    RecyclerBinList(mainFragmentAppState.binList, this)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(bin: Bin) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .hide(this)
            .add(R.id.containerMain, BinInfoFragment.newInstance(bin.number))
            .addToBackStack("")
            .commit()
    }
}