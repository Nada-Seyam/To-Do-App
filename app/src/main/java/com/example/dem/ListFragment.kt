package com.example.dem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dem.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val mylist: MutableList<User> = mutableListOf()
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter(
            userList = mylist,
            onItemDelete = { user ->
                mylist.remove(user)
                adapter.notifyDataSetChanged()
                toggleEmptyState()
            },
            onItemUpdate = { user ->
                val updateTaskFragment = UpdateTaskFragment(user) { updatedUser ->
                    val index = mylist.indexOfFirst { it.title == user.title && it.details == user.details }
                    if (index != -1) {
                        mylist[index] = updatedUser
                        adapter.notifyItemChanged(index)
                    }
                }
                updateTaskFragment.show(parentFragmentManager, "UpdateTaskFragment")
            }
        )

        binding.mylist.layoutManager = LinearLayoutManager(requireContext())
        binding.mylist.adapter = adapter

        binding.addTaskButton.setOnClickListener {
            val addTaskBottomSheetFragment = add_Task { user ->
                mylist.add(user)
                adapter.notifyDataSetChanged()
                toggleEmptyState()
            }
            addTaskBottomSheetFragment.show(parentFragmentManager, "AddTaskBottomSheetFragment")
        }

        toggleEmptyState()
    }

    private fun toggleEmptyState() {
        if (mylist.isEmpty()) {
            binding.mylist.visibility = View.GONE
            binding.emptyText.visibility = View.VISIBLE
        } else {
            binding.mylist.visibility = View.VISIBLE
            binding.emptyText.visibility = View.GONE
        }
    }
}
