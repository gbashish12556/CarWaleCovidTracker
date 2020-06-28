package com.example.carwalecovidtracker


import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.example.carwalecovidtracker.pojo.FilterData
import io.reactivex.subjects.PublishSubject

/**
 * A simple [Fragment] subclass.
 */
class FilterListFragment : androidx.fragment.app.DialogFragment() {

    @BindView(R.id.filterListButton) lateinit var filterListButton: Button
    @BindView(R.id.closeDialog) lateinit var closeDialog: ImageView
    @BindView(R.id.filterListField) lateinit var filterListField: Spinner
    @BindView(R.id.filterRangeType) lateinit var filterRangeType: Spinner
    @BindView(R.id.filterValue) lateinit var filterValue: EditText
    private var filterListPublishSubject: PublishSubject<FilterData>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter_list, container, false)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is CommunicationProvider){
            filterListPublishSubject = context.getFilterPubSub()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.isCancelable = false
        ButterKnife.bind(this, view);
        var sharedPref = App.sharedPref
        var selectedFilterValueInt = sharedPref.getInt(Constant.FILTER_VALUE,0)
        filterValue.setText(selectedFilterValueInt.toString())
        if(selectedFilterValueInt != 0) {
            var selectedFilterField = sharedPref.getString(Constant.FILTER_FIELD,"")
            var selectedFilterRangeValue = sharedPref.getString(Constant.FILTER_FIELD,"")
            filterListField.setSelection(resources.getStringArray(R.array.filter_list_field).indexOf(selectedFilterField))
            filterRangeType.setSelection(resources.getStringArray(R.array.filter_range_type).indexOf(selectedFilterRangeValue))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        closeDialog.setOnClickListener{
            dismiss()
        }

        filterListButton.setOnClickListener{
            var listType = filterRangeType.selectedItem.toString()
            var listField = filterListField.selectedItem.toString()
            var filterValue = filterValue.text.toString()
            if(validateFields(listType,listField,filterValue)){
                dismiss()
                filterListPublishSubject!!.onNext(FilterData(listType,listField, Integer.parseInt(filterValue)))
            }
        }

    }

    fun validateFields(listType:String,listField:String,filterValue:String):Boolean{

        if(!listType.equals("") && !listField.equals("") && !filterValue.equals("")){
            return true
        }else{
            Toast.makeText(activity,"Invalid Fields", Toast.LENGTH_LONG)
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
