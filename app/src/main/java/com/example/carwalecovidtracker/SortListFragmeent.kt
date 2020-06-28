package com.example.carwalecovidtracker


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.carwalecovidtracker.pojo.SortData
import io.reactivex.subjects.PublishSubject

/**
 * A simple [Fragment] subclass.
 */
class SortListFragmeent : androidx.fragment.app.DialogFragment() {

    @BindView(R.id.sortListButton) lateinit var sortListButton: Button
    @BindView(R.id.closeDialog) lateinit var closeDialog: ImageView
    @BindView(R.id.filterListField) lateinit var sortLisField: Spinner
    @BindView(R.id.filterRangeType) lateinit var sortListype: Spinner
    private var sortDataPublishSubject:PublishSubject<SortData>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sort_list_fragmeent, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is CommunicationProvider){
            sortDataPublishSubject = context.getSortingPubSub()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.isCancelable = false
        ButterKnife.bind(this, view);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        closeDialog.setOnClickListener{
            dismiss()
        }
        sortListButton.setOnClickListener{
            var sortingType = sortListype.selectedItem.toString()
            var sortingFields = sortLisField.selectedItem.toString()
            if(validateFields(sortingType, sortingFields)) {
                dismiss()
                sortDataPublishSubject!!.onNext(SortData(sortingType, sortingFields))
            }

        }
    }

    fun validateFields(listType:String,listField:String):Boolean{
        if(!listType.equals("") && !listField.equals("")){
            return true
        }else{
            Toast.makeText(activity,"Iinvalid Fields", Toast.LENGTH_LONG)
            return false
        }
    }


    override fun onStart() {
        super.onStart()
        // Less dimmed background; see https://stackoverflow.com/q/13822842/56285
        val window = dialog.window
        window!!.setGravity(Gravity.CENTER)
        val params = window!!.attributes
        params.x = 10;
        params.y = 10;
        params.dimAmount = 0.2f // dim only a little bit
        window.attributes = params
        window.setBackgroundDrawableResource(android.R.color.transparent)

    }


}
