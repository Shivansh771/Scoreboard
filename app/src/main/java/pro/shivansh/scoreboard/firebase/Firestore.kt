package pro.shivansh.scoreboard.firebase

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import pro.shivansh.scoreboard.data.testData
import pro.shivansh.scoreboard.ui.Tests.TestFragment

class Firestore {
    private val mFirestore=FirebaseFirestore.getInstance()
    fun getTestList(fragment:TestFragment){
        mFirestore.collection("test")
            .get().addOnSuccessListener { result ->
                val testList:ArrayList<testData> = ArrayList()
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    val temp= document.toObject(testData::class.java)
                    temp.testDate=document.id.toString()
                    temp.testName=document.data.toString()
                    testList.add(temp)
                }
                fragment.populateTestListToUI(testList)
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
    }

}