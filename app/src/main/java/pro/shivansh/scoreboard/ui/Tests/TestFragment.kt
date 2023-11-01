package pro.shivansh.scoreboard.ui.Tests

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import pro.shivansh.scoreboard.PhoneActivity
import pro.shivansh.scoreboard.databinding.FragmentTestsBinding

class TestFragment : Fragment() {
    private lateinit var auth:FirebaseAuth
    private lateinit var signOutBtn:Button
    private var _binding: FragmentTestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val testViewModel =
            ViewModelProvider(this).get(TestViewModel::class.java)

        _binding = FragmentTestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        testViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth=FirebaseAuth.getInstance()
        signOutBtn=binding.button
        signOutBtn.setOnClickListener{
            auth.signOut()
            startActivity(Intent(requireActivity(),PhoneActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}