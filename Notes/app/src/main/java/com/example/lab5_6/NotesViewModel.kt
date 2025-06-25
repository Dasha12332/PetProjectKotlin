package com.example.lab5_6


import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class NotesViewModel(

    @JsonProperty("id")
    var Id : String = "",

    @JsonProperty("text")
    var Text : String = "",

    @JsonProperty("title")
    var Title : String,

    @JsonProperty("createdAt")
    var CreatedAt : String,

    @JsonProperty("updatedAt")
    var UpdatedAt : String

) : Serializable

//class NotesViewModel {
//}