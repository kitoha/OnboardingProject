package com.onboarding.practice.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long ?= null,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val password : String,

    @Column(nullable = false)
    val phoneNumber : String,

    @Column(nullable = false)
    val name : String
)