package com.liftlife.liftlife.common;

import com.google.gson.annotations.SerializedName;

public enum TimeOfDay {
    @SerializedName("morning")
    MORNING,
    @SerializedName("noon")
    NOON,
    @SerializedName("afternoon")
    AFTERNOON,
    @SerializedName("evening")
    EVENING,
    @SerializedName("night")
    NIGHT
}
