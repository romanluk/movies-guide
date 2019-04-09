package com.example.moviesguide.search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.moviesguide.R
import com.example.moviesguide.api.MoviesDbApiService
import com.example.moviesguide.entities.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [SearchFragment.OnListFragmentInteractionListener] interface.
 */
class SearchFragment : Fragment(), TextWatcher {
    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var searchResultsAdapter: SearchItemRecyclerViewAdapter
    private lateinit var searchEditText: EditText
    private var disposable: Disposable? = null
    private val moviesDbApiService by lazy {
        MoviesDbApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val searchQuery = s.toString()
        disposable = moviesDbApiService.searchMovies(searchQuery).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe {
                        result -> searchResultsAdapter.setResults(result.results)
                }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultsAdapter = SearchItemRecyclerViewAdapter(listener)
        search_movies_recyclerview.layoutManager = LinearLayoutManager(activity)
        search_movies_recyclerview.adapter = searchResultsAdapter
        searchEditText = search_movies_edit_text
        searchEditText.addTextChangedListener(this)
        searchEditText.requestFocus()
        showSoftKeyboard(true)
    }

    private fun showSoftKeyboard(show: Boolean) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
        } else {
            imm.hideSoftInputFromWindow(searchEditText.windowToken,0)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(movieItem: Movie)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
