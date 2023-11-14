package pro.shivansh.scoreboard.ui.Tests

import android.content.Intent
import android.media.session.PlaybackState.CustomAction
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.api.Distribution.BucketOptions.Linear
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import pro.shivansh.scoreboard.PhoneActivity
import pro.shivansh.scoreboard.adapter.testAdapter
import pro.shivansh.scoreboard.data.testData
import pro.shivansh.scoreboard.databinding.FragmentTestsBinding
import pro.shivansh.scoreboard.firebase.Firestore

class TestFragment : Fragment() {
    private lateinit var auth:FirebaseAuth
    private lateinit var signOutBtn:Button
    private var _binding: FragmentTestsBinding? = null
    private lateinit var testDB:FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
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

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Firestore().getTestList(this)

        auth=FirebaseAuth.getInstance()
        signOutBtn=binding.button
        signOutBtn.setOnClickListener{
            auth.signOut()
            startActivity(Intent(requireActivity(),PhoneActivity::class.java))
        }
        recyclerView=binding.rvTest
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        val data=ArrayList<testData>()
        //put data from firebase here
        val db=Firebase.firestore
        db.collection("test")
            .get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    val temp=testData(document.data.toString(),document.id)
                    data.add(temp)
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
        val adapter=testAdapter(data)
        recyclerView.adapter=adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun populateTestListToUI(testList:ArrayList<testData>){
        recyclerView=binding.rvTest
        if(testList.size>0){
            recyclerView.layoutManager=LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            val adapter=testAdapter(testList)
            recyclerView.adapter=adapter

        }

    }
}


