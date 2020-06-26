package com.example.carwalecovidtracker


import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import butterknife.BindView
import com.example.carwalecovidtracker.pojo.FilterData
import com.example.carwalecovidtracker.pojo.SortData
import io.reactivex.subjects.PublishSubject

/**
 * A simple [Fragment] subclass.
 */
class FilterListFragment : androidx.fragment.app.DialogFragment() {

    @BindView(R.id.sortListButton) lateinit var sortListButton: Button
    @BindView(R.id.closeDialog) lateinit var closeDialog: ImageView
    @BindView(R.id.sortLisField) lateinit var sortLisField: Spinner
    @BindView(R.id.sortListype) lateinit var sortListype: Spinner
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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        closeDialog.setOnClickListener{
            dismiss()
        }
        sortListButton.setOnClickListener{
            filterListPublishSubject!!.onNext(FilterData(sortListype.selectedItem.toString(),sortLisField.selectedItem.toString(), Integer.parseInt(filterValue.text.toString())))
            dismiss()
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
