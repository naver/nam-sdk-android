package com.naver.namexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.naver.namexample.databinding.FragmentMainMenuBinding
import com.naver.namexample.sample.ImageBannerFragment
import com.naver.namexample.sample.NativeBannerFragment
import com.naver.namexample.sample.SmartchannelFragment

class MainMenuFragment : ListFragment() {
    private lateinit var binding: FragmentMainMenuBinding
    private val fragmentList = arrayOf(
        SampleAdInfo("이미지형 배너") { ImageBannerFragment() },
        SampleAdInfo("네이티브형 배너") { NativeBannerFragment() },
        SampleAdInfo("스마트채널") { SmartchannelFragment() }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainMenuBinding.inflate(layoutInflater)
        listAdapter = ArrayAdapter(requireContext(), R.layout.content_frgment_list, fragmentList)
        return binding.root
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, (listView.getItemAtPosition(position) as SampleAdInfo).fragmentGetter())
            .addToBackStack(null)
            .commit()
    }

    data class SampleAdInfo(val adName: String, val fragmentGetter: () -> Fragment) {
        override fun toString(): String {
            return adName
        }
    }
}
