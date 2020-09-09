package com.example.museum

class DummyDatabase {

    companion object {
        var activities: ArrayList<DummyActivity> = arrayListOf<DummyActivity>(
            DummyActivity(
                1,
                "Activity 1",
                "20 Jun",
                "25 Jun"
            ),
            DummyActivity(
                2,
                "Activity 2",
                "22 Jun",
                "25 Jun"
            ),
            DummyActivity(
                3,
                "Activity 3",
                "23 Jun",
                "30 Jun"
            )
        )
    }

}