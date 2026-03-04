package com.example.leitorqrcodetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.fragment.app.Fragment
import com.example.leitorqrcodetest.databinding.FragmentFirstBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // .\AppData\Local\Android\Sdk\emulator\emulator.exe -avd Pixel_8 -feature -Vulkan
        binding.lerQRCodeButton.setOnClickListener {
            onButtonClick()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Register the launcher and result handler
    private val barcodeLauncher = registerForActivityResult<ScanOptions?, ScanIntentResult?>(
        ScanContract(),
        ActivityResultCallback { result: ScanIntentResult? ->
            if (result!!.getContents() == null) {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Scanned: " + result.getContents(),
                    Toast.LENGTH_LONG
                ).show()
                binding.textoLidoTextView.text = result.contents
            }
        })

    // Launch
    fun onButtonClick() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Ler qrcode...")
        options.setCameraId(1) // Use a specific camera of the device
        options.setBeepEnabled(true)
        //options.setBarcodeImageEnabled(true)
        barcodeLauncher.launch(options)
    }
}


//.\AppData\Local\Android\Sdk\emulator\emulator.exe -avd Pixel_8 -feature -Vulkan -gpu host -no-snapshot -no-boot-anim -memory 2048 -scale 0.7