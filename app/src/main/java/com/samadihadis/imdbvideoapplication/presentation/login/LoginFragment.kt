package com.samadihadis.imdbvideoapplication.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.samadihadis.imdbvideoapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.loginButton.setOnClickListener {
            val userName = binding.userNameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (userName.isEmpty()) {
                binding.userNameEditText.error = "Please Enter UserName"
            } else if (password.isEmpty()) {
                binding.passwordEditText.error = "Please Enter Password"
            } else if (
                (userName == userName1 && password == password1) ||
                (userName == userName2 && password == password2)
            ) {
                findNavController().navigate(LoginFragmentDirections.actionToVideoListFragment())
            } else {
                Toast.makeText(
                    requireContext(),
                    "UserName or Password is incorrect!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        val userName1 = "Hadis"
        val userName2 = "Hossein"
        val password1 = "Hh123456"
        val password2 = "Kk123456"
    }

}