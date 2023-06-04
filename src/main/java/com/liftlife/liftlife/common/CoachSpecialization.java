package com.liftlife.liftlife.common;

import com.google.gson.annotations.SerializedName;

public enum CoachSpecialization {
    @SerializedName("Personal Coach")
    PERSONAL("Personal Coach"),
    @SerializedName("Weight loss Coach")
    WEIGHT("Weight loss Coach"),
    @SerializedName("Physique Coach")
    STRENGTH("Physique Coach"),
    @SerializedName("Fitness Coach")
    FITNESS("Fitness Coach"),
    @SerializedName("Athletic Coach")
    ATHLETIC("Athletic Coach"),
    @SerializedName("Performance Coach")
    PERFORMANCE("Performance Coach");

    private String description;

    CoachSpecialization(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static CoachSpecialization getSpecializationFromString(String value) {
        CoachSpecialization specialization = null;

        for (CoachSpecialization coachSpec : CoachSpecialization.values()) {
            if (coachSpec.getDescription().equals(value)) {
                specialization = coachSpec;
                break;
            }
        }
        return specialization;
    }

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
