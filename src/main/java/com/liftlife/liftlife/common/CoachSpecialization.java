package com.liftlife.liftlife.common;

import com.google.auto.value.extension.serializable.SerializableAutoValue;
import com.google.gson.annotations.SerializedName;

public enum CoachSpecialization {
    @SerializedName("Personal Trainer")
    PERSONAL,
    @SerializedName("Weight loss Trainer")
    WEIGHT,
    @SerializedName("Physique Trainers")
    STRENGTH,
    @SerializedName("Fitness Trainer")
    FITNESS,
    @SerializedName("Athletic Trainer")
    ATHLETIC,
    @SerializedName("Performance Trainer")
    PERFORMANCE;

//    @SerializedName("Personal Trainer")
//    PERSONAL("Personal Trainer"),
//    WEIGHT("Weight loss Trainer"),
//    STRENGTH("Physique Trainers"),
//    FITNESS("Fitness Trainer"),
//    ATHLETIC("Athletic Trainer"),
//    PERFORMANCE("Performance Trainers");
//
//    private String description;
//
//    CoachSpecialization(String description) {
//        this.description = description;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
    /*
    @SerializedName("Personal Trainer")
    PERSONAL,
    @SerializedName("Weight loss Trainer")
    WEIGHT,
    @SerializedName("Physique Trainers")
    STRENGTH,
    @SerializedName("Fitness Trainer")
    FITNESS,
    @SerializedName("Athletic Trainer")
    ATHLETIC,
    @SerializedName("Performance Personal Trainers")
    PERFORMANCE,
    @SerializedName("Pilates Coach")
    PILATES,
    @SerializedName("Health Coach")
    HEALTH
     */
}
