package com.example.dem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dem.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class UpdateTaskFragment(
    private val user: User,
    private val onTaskUpdated: (User) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pre-fill the form with current user details
        binding.title.setText(user.title)
        binding.desc.setText(user.details)

        binding.saveButton.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.desc.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                onTaskUpdated(User(title, description))
                dismiss()
            } else {
                // Show an error message or highlight the required fields
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
